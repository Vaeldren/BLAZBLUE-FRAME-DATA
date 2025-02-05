package org.example.service;

import org.example.model.FrameData;
import org.example.repository.FrameDataRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
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

    public List<String> getImageByCharacterAndInput(String characterName, String input){
        FrameData frameData = frameDataRepository.findByCharacterNameAndInput(characterName, input);
        return frameData.getImages();
    }

    public FrameData updateFrameData(String characterName, String input, FrameData newFrameData){
        FrameData existingFrameData = frameDataRepository.findByCharacterNameAndInput(characterName,input);

        BeanUtils.copyProperties(newFrameData,existingFrameData, "id");

        return frameDataRepository.save(existingFrameData);
    }

    public void save(FrameData frameData){
        frameDataRepository.save(frameData);
    }

    public void saveAll(List<FrameData> frameDataList){
        frameDataRepository.saveAll(frameDataList);
    }

    public void deleteByCharacterName(String characterName) {
        List<FrameData> frameData = frameDataRepository.findByCharacterName(characterName);
        frameDataRepository.deleteAll(frameData);
    }
    public void deleteByCharacterNameAndInput(FrameData frameData) {
        frameDataRepository.deleteByCharacterNameAndInput(frameData.getCharacterName(),frameData.getInput());
    }
}
