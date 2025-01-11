package org.example.scraper;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.IOException;



public class DustloopScraper {
    public static void main(String[] args){
        try {
            Document doc;
            doc = Jsoup.connect("https://dustloop.com/wiki/index.php?title=BBCF/Izanami/Frame_Data").get();
/*            Element systemData = doc.select("table").get(6);
            Element normalMoves = doc.select("table").get(7);
            Element driveMoves = doc.select("table").get(8);
            Element universalMechanics = doc.select("table").get(9);
            Element specials = doc.select("table").get(10);
            Element distortionDrives = doc.select("table").get(11);
            Element exceedAccel = doc.select("table").get(12);
            Element astralHeat = doc.select("table").get(13);*/
            extractTable(doc,7,"Normal Moves","Izanami");
        } catch (IOException e){
            System.out.println("Error fetching or parsing document: "+e.getMessage());
            e.printStackTrace();
        }

    }

    private static void extractTable(Document doc, int tableIndex, String tableName, String charName){
        //strip and clean table for input
        Element table = doc.select("table").get(tableIndex);
        Element tbody = table.select("tbody").get(0);
        // store images in another db table using foreign key then retrieve
        // add https://dustloop.com/ to image src
        Elements dataRows = tbody.select("tr");
        //for loop
        for(int i = 0; i < 1; i++){
            String dataDetailsHTML = dataRows.get(i).attr("data-details");
            Document innerDoc = Jsoup.parse(dataDetailsHTML);
            Elements hitboxImages= innerDoc.select("tr").get(1).select("img");
            if (hitboxImages.isEmpty()){
                hitboxImages= innerDoc.select("tr").get(0).select("img");
            }
            //need to store image and link it to input id

            Elements colVals = dataRows.get(i).select("td").not(".details-control");
            for(Element val : colVals){
                System.out.println(val);
            }
        }



    }
}
