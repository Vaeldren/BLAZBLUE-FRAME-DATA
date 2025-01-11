package org.example.controller;

import org.example.model.FrameData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrameDataService {
    @Autowired
    private FrameDataRepository frameDataRepository;

    public List<FrameData> getFrameDataByCharacter(String characterName){
        return frameDataRepository.findByCharacterName(characterName);
    }

    public FrameData getFrameDataByCharacterAndInput(String characterName, String input){
        return frameDataRepository.findByCharacterNameAndInput(characterName,input);
    }
}
