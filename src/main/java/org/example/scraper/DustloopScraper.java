package org.example.scraper;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

import org.example.model.FrameData;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DustloopScraper {
    public static void main(String[] args){
        try {
            Document doc;
            String charName = "Jubei";
            doc = Jsoup.connect("https://dustloop.com/wiki/index.php?title=BBCF/"+charName+"/Frame_Data").get();
/*            Element systemData = doc.select("table").get(6);
            Element normalMoves = doc.select("table").get(7);
            Element driveMoves = doc.select("table").get(8);
            Element universalMechanics = doc.select("table").get(9);
            Element specials = doc.select("table").get(10);
            Element distortionDrives = doc.select("table").get(11);
            Element exceedAccel = doc.select("table").get(12);
            Element astralHeat = doc.select("table").get(13);*/
            extractTable(doc,7,charName);
            extractTable(doc,8,charName);
            extractTable(doc,9,charName);
            extractTable(doc,10,charName);
            extractTable(doc,11,charName);
            extractTable(doc,12,charName);
            extractTable(doc,13,charName);

        } catch (IOException e){
            System.out.println("Error fetching or parsing document: "+e.getMessage());
            e.printStackTrace();
        }

    }

    private static void extractTable(Document doc, int tableIndex, String charName){
        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .defaultHeader("Content-Type", "application/json")
                .build();
        //strip and clean table for input
        Element table = doc.select("table").get(tableIndex);
        Element tbody = table.select("tbody").get(0);
        // store images in another db table using foreign key then retrieve
        // add https://dustloop.com/ to image src
        Elements dataRows = tbody.select("tr");
        List<FrameData> frameDataList = new ArrayList<FrameData>();

        //for loop
        for(int i = 0; i < dataRows.size(); i++){
            String dataDetailsHTML = dataRows.get(i).attr("data-details");
            Document innerDoc = Jsoup.parse(dataDetailsHTML);
            Elements hitboxImages= innerDoc.select("tr").get(1).select("img");
            if (hitboxImages.isEmpty()){
                hitboxImages= innerDoc.select("tr").get(0).select("img");
            }
            ArrayList<String> imageLinks = new ArrayList<>();
            //need to store image and link it to input id
            for(Element img : hitboxImages){
                //split
                String[] imgSplit = img.attr("srcset").split(", ");
                String imgLink = imgSplit[imgSplit.length-1];
                String imgLinkSplit = imgLink.split(" ")[0];
                imageLinks.add("https://dustloop.com"+imgLinkSplit);
            }
            Elements colVals = dataRows.get(i).select("td").not(".details-control");

            FrameData frameData = new FrameData();

            frameData.setCharacterName(charName);
            frameData.setInput(colVals.get(0).text());
            frameData.setDamage(colVals.get(1).text());
            frameData.setGuard(colVals.get(2).text());
            frameData.setStartup(colVals.get(3).text());
            frameData.setActive(colVals.get(4).text());
            frameData.setRecovery(colVals.get(5).text());
            frameData.setOnBlock(colVals.get(6).text());
            frameData.setOnODR(colVals.get(7).text());
            frameData.setAttribute(colVals.get(8).text());
            frameData.setInvuln(colVals.get(9).text());
            frameData.setP1(colVals.get(10).text());
            frameData.setP2(colVals.get(11).text());
            frameData.setStarter(colVals.get(12).text());
            frameData.setCancel(colVals.get(13).text());
            frameData.setLevel(colVals.get(14).text());
            frameData.setGroundHit(colVals.get(15).text());
            frameData.setAirHit(colVals.get(16).text());
            frameData.setGroundCH(colVals.get(17).text());
            frameData.setAirCH(colVals.get(18).text());
            frameData.setBlockstop(colVals.get(19).text());
            frameData.setHitstop(colVals.get(20).text());
            frameData.setImages(imageLinks);
            frameData.setCHstop(colVals.get(21).text());

            frameDataList.add(frameData);
        }

        for(FrameData framedata : frameDataList){
            System.out.println(framedata.getImages());
        }

        //batch POST the list
        client.post()
                .uri("/api/frame-data/batch")
                .body(BodyInserters.fromValue(frameDataList))
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                        response -> response.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new RuntimeException("API Error: " + errorBody)))
                )
                .bodyToMono(Void.class)
                .block(); // Executes the request synchronously*/
    }

    public List<FrameData> extractTableTest(){
        try {
            Document doc;
            doc = Jsoup.connect("https://dustloop.com/wiki/index.php?title=BBCF/Izanami/Frame_Data").get();
            int tableIndex = 7;
            String tableName = "Normal Moves";
            String charName = "Izanami";
            WebClient client = WebClient.create();
            //strip and clean table for input
            Element table = doc.select("table").get(tableIndex);
            Element tbody = table.select("tbody").get(0);
            // store images in another db table using foreign key then retrieve
            // add https://dustloop.com/ to image src
            Elements dataRows = tbody.select("tr");
            List<FrameData> frameDataList = new ArrayList<FrameData>();

            //for loop
            for (int i = 0; i < dataRows.size(); i++) {
                String dataDetailsHTML = dataRows.get(i).attr("data-details");
                Document innerDoc = Jsoup.parse(dataDetailsHTML);
                Elements hitboxImages = innerDoc.select("tr").get(1).select("img");
                if (hitboxImages.isEmpty()) {
                    hitboxImages = innerDoc.select("tr").get(0).select("img");
                }
                //need to store image and link it to input id

                Elements colVals = dataRows.get(i).select("td").not(".details-control");

                FrameData frameData = new FrameData();

                frameData.setCharacterName(charName);
                frameData.setInput(colVals.get(0).text());
                frameData.setDamage(colVals.get(1).text());
                frameData.setGuard(colVals.get(2).text());
                frameData.setStartup(colVals.get(3).text());
                frameData.setActive(colVals.get(4).text());
                frameData.setRecovery(colVals.get(5).text());
                frameData.setOnBlock(colVals.get(6).text());
                frameData.setOnODR(colVals.get(7).text());
                frameData.setAttribute(colVals.get(8).text());
                frameData.setInvuln(colVals.get(9).text());
                frameData.setP1(colVals.get(10).text());
                frameData.setP2(colVals.get(11).text());
                frameData.setStarter(colVals.get(12).text());
                frameData.setCancel(colVals.get(13).text());
                frameData.setLevel(colVals.get(14).text());
                frameData.setGroundHit(colVals.get(15).text());
                frameData.setAirHit(colVals.get(16).text());
                frameData.setGroundCH(colVals.get(17).text());
                frameData.setAirCH(colVals.get(18).text());
                frameData.setBlockstop(colVals.get(19).text());
                frameData.setHitstop(colVals.get(20).text());
                frameData.setCHstop(colVals.get(21).text());

                frameDataList.add(frameData);
            }
            return frameDataList;

        } catch (IOException e){
            System.out.println("Error fetching or parsing document: "+e.getMessage());
            e.printStackTrace();
        }
        return null;

    }
}
