
import discord
from discord import option
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
        return inputList.json()

@bot.slash_command(description="This fetches a character's move")
@option("character",description="Choose your character",choices=blazblue_centralfiction_characters)
@option("move",description="Enter the move input",choices=get_inputs())
async def framedata(ctx: discord.ApplicationContext, 
                    character: str, 
                    move: str):
    
    #
    responseFrameData = requests.get(url+"/frame-data/"+character+"/"+move)

    characterFrameData = responseFrameData.json()

    embed = discord.Embed(
        title=characterFrameData['input'],
        description=characterFrameData['moveName']
    )
    embed.set_author(name=characterFrameData['characterName'])
    embed.add_field(name="Startup",value=characterFrameData['startup'], inline=True)
    embed.add_field(name="Active",value=characterFrameData['active'], inline=True)
    embed.add_field(name="On Block",value=characterFrameData['onBlock'], inline=True)
    embed.add_field(name="Guard",value=characterFrameData['guard'])
    embed.add_field(name="Damage",value=characterFrameData['damage'])
    embed.add_field(name="Attribute",value=characterFrameData['attribute'])
    if(characterFrameData['invuln'] != ""):
        embed.add_field(name="Invuln",value=characterFrameData['invuln'])
    embed.set_image(url=characterFrameData['images'][0])
    if(characterFrameData['notes'] != "notes:"):
        embed.set_footer(text=characterFrameData['notes'])
    await ctx.respond(embed=embed)


# @bot.command
# async def systemdata(ctx, character: str):
#     await ctx.send()
token = str(os.getenv("TOKEN"))
bot.run(token)