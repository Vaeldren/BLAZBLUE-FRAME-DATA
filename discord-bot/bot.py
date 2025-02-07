
import discord
from discord.commands import option
import requests
import json
import dotenv
import os

dotenv.load_dotenv()

url = "http://localhost:8080/api"

bot = discord.Bot()

blazblue_centralfiction_characters = [
    "Ragna the Bloodedge", "Jin Kisaragi", "Noel Vermillion", "Rachel Alucard",
    "Taokaka", "Iron Tager", "Litchi Faye Ling", "Arakune", "Bang Shishigami",
    "Carl Clover", "Hakumen", "Nu-13", "Lambda-11", "Tsubaki Yayoi", "Hazama", 
    "Mu-12", "Makoto Nanaya", "Valkenhayn R. Hellsing", "Platinum the Trinity", 
    "Relius Clover", "Izayoi", "Amane Nishiki", "Bullet", "Azrael", 
    "Kagura Mutsuki", "Kokonoe", "Yuuki Terumi", "Celica A. Mercury", 
    "Hibiki Kohaku", "Nine the Phantom", "Naoto Kurogane", "Izanami", 
    "Es", "Mai Natsume", "Susanoo", "Jubei"
]

@bot.event
async def on_ready():
    print(f'Logged in as {bot.user} (ID: {bot.user.id})')

async def get_inputs(ctx: discord.AutocompleteContext):
    character = ctx.options['character']
    if character in blazblue_centralfiction_characters:
        #request for char inputs
        characterUnderscore = character.replace(" ","_")
        inputList = requests.get(url+'/frame-data/'+characterUnderscore+"/inputs")
        inputListData = inputList.json()
        return [move for move in inputListData if ctx.value.lower() in move.lower()]
        
async def get_characters(ctx: discord.AutocompleteContext):
    return [char for char in blazblue_centralfiction_characters if char.lower().startswith(ctx.value.lower())]

class ImagePaginator(discord.ui.View):
    def __init__(self, embed, images):
        super().__init__()
        self.images = images
        self.embed = embed
        self.index = 0
    
    async def update_embed(self, interaction: discord.Interaction):
        self.embed.set_image(url=self.images[self.index])
        await interaction.response.edit_message(embed=self.embed, view=self)
    
    @discord.ui.button(label="Previous image", style=discord.ButtonStyle.primary, disabled=True)
    async def previous(self, button: discord.ui.Button, interaction: discord.Interaction):
        if self.index > 0:
            self.index -= 1
            self.next.disabled = False
            if self.index == 0:
                button.disabled = True
            await self.update_embed(interaction)

    
    @discord.ui.button(label="Next image", style=discord.ButtonStyle.primary)
    async def next(self, button: discord.ui.Button, interaction: discord.Interaction):
        if self.index < len(self.images) - 1:
            self.index += 1
            self.previous.disabled = False
            if self.index == len(self.images) - 1:
                button.disabled = True
            await self.update_embed(interaction)


@bot.slash_command(name="framedata")
@option("character",description="Enter the BLAZBLUE character's name",autocomplete=get_characters)
@option("move",description="Enter the move input",autocomplete=get_inputs)
async def framedata(ctx: discord.ApplicationContext, 
                    character: str, 
                    move: str):

    characterUnderscore = character.replace(" ","_")
    responseFrameData = requests.get(url+"/frame-data/"+characterUnderscore+"/"+move)

    characterFrameData = responseFrameData.json()

    embed = discord.Embed(
        title=characterFrameData['input'],
        description=characterFrameData['moveName']
    )
    embed.set_author(name=character)

    if(characterFrameData['notes'] != "notes:"):
        embed.set_footer(text=characterFrameData['notes'])
    
    fields = {
        "Startup": characterFrameData['startup'],
        "Active": characterFrameData['active'],
        "Recovery": characterFrameData['recovery'],
        "On Block": characterFrameData['onBlock'],
        "Guard" : characterFrameData['guard'],
        "Damage" : characterFrameData['damage'],
        "Attribute" : characterFrameData['attribute'],
        "Invuln" : characterFrameData['invuln'],
        "Cancel" : characterFrameData['cancel']
    }
    
    for name, value in fields.items():
        if value:
            embed.add_field(name=name, value=value)

    embed.set_thumbnail(url="https://raw.githubusercontent.com/Vaeldren/BLAZBLUE-FRAME-DATA/master/discord-bot/icons/"+characterUnderscore+".png")

    embed.set_image(url=characterFrameData['images'][0])
    view = ImagePaginator(embed, characterFrameData['images'])

    if(len(characterFrameData['images']) > 1):
        await ctx.respond(embed=embed,view=view)
    else:
        await ctx.respond(embed=embed)


# @bot.command
# async def systemdata(ctx, character: str):
#     await ctx.send()
token = str(os.getenv("TOKEN"))
bot.run(token)