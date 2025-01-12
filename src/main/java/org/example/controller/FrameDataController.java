package org.example.controller;

import org.example.model.FrameData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/frame-data")
public class FrameDataController {
    @Autowired
    private FrameDataService frameDataService;
    private FrameDataRepository repository;

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
    public ResponseEntity<ArrayList<String>> getImage(@PathVariable String characterName, @PathVariable String input){
        ArrayList<String> image = frameDataService.getImageByCharacterAndInput(characterName, input);
        return ResponseEntity.ok(image);
    }
    @PostMapping("/{characterName}/{input}")
    public ResponseEntity<FrameData> addFrameData (@RequestBody FrameData frameData){
        repository.save(frameData);
        return ResponseEntity.ok(frameData);
    }

}
