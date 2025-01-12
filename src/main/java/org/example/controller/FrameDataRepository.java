package org.example.controller;

import org.example.model.FrameData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FrameDataRepository extends JpaRepository<FrameData, Long> {

    List<FrameData> findByCharacterName(String characterName);
    FrameData findByCharacterNameAndInput(String characterName, String input);



}
