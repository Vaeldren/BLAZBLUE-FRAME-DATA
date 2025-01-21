package org.example.repository;

import org.example.model.FrameData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FrameDataRepository extends JpaRepository<FrameData, Long> {

    List<FrameData> findByCharacterName(String characterName);
    FrameData findByCharacterNameAndInput(String characterName, String input);



}
