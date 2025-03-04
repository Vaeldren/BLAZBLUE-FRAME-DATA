package org.example.controller;

import org.apache.coyote.Response;
import org.example.model.FrameData;
import org.example.service.FrameDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/frame-data")
public class FrameDataController {
    @Autowired
    private FrameDataService frameDataService;

    // Dynamic endpoint to get frame data for any character
    @GetMapping("/{characterName}")
    public ResponseEntity<List<FrameData>> getFrameDataByCharacter(@PathVariable String characterName) {
        List<FrameData> frameDataList = frameDataService.getFrameDataByCharacter(characterName);
        if (frameDataList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(frameDataList);
    }

    @GetMapping("/{characterName}/{input}")
    public ResponseEntity<FrameData> getFrameData(@PathVariable String characterName, @PathVariable String input){
        FrameData frameData = frameDataService.getFrameDataByCharacterAndInput(characterName,input);
        return ResponseEntity.ok(frameData);
    }

    @GetMapping("/{characterName}/{input}/image")
    public ResponseEntity<List<String>> getImage(@PathVariable String characterName, @PathVariable String input){
        List<String> image = frameDataService.getImageByCharacterAndInput(characterName, input);
        return ResponseEntity.ok(image);
    }

    @GetMapping("/{characterName}/inputs")
    public ResponseEntity<List<String>> getInputsByCharacter(@PathVariable String characterName){
        List<String> inputs = frameDataService.getInputsByCharacter(characterName);
        return ResponseEntity.ok(inputs);
    }

    @PostMapping("/{characterName}/{input}")
    public ResponseEntity<String> addFrameData (@RequestBody FrameData frameData){
        frameDataService.save(frameData);
        return ResponseEntity.ok("Frame data saved successfully");
    }
    @DeleteMapping("/{characterName}/{input}")
    public ResponseEntity<String> deleteFrameData(@PathVariable String characterName, @PathVariable String input){
        FrameData frameData = frameDataService.getFrameDataByCharacterAndInput(characterName,input);
        frameDataService.deleteByCharacterNameAndInput(frameData);
        return ResponseEntity.ok("Frame data deleted successfully");
    }

    @DeleteMapping("/{characterName}")
    public ResponseEntity<String> deleteAllFrameData(@PathVariable String characterName){
        frameDataService.deleteByCharacterName(characterName);
        return ResponseEntity.ok("All frame data for "+characterName+" deleted successfully");
    }

    @PostMapping("/batch")
    public ResponseEntity<String> saveFrameDataBatch(@RequestBody List<FrameData> frameDataList){
        frameDataService.saveAll(frameDataList);
        return ResponseEntity.ok("Batch data saved successfully");
    }



}
