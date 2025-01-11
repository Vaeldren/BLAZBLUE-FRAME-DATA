package org.example.controller;

import org.example.model.FrameData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
