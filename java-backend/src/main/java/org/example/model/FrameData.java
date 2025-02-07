package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.converter.JsonConverter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "framedata")
@Getter
@Setter
@AllArgsConstructor
public class FrameData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String characterName;
    private String input;
    private String moveName;
    private String damage;
    private String guard;
    private String startup;
    private String active;
    private String recovery;
    private String onBlock;
    private String onODR;
    private String attribute;
    private String invuln;
    private String p1;
    private String p2;
    private String starter;
    private String cancel;
    private String level;
    private String blockstun;
    private String groundHit;
    private String airHit;
    private String groundCH;
    private String airCH;
    private String blockstop;
    private String hitstop;
    private String CHstop;
    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = JsonConverter.class)
    private List<String> images;

    public FrameData() {
    }

    public FrameData(String characterName, String input, String damage) {
        this.characterName = characterName;
        this.input = input;
        this.damage = damage;
    }

    FrameData(String characterName, String input, String moveName, String damage, String guard, String startup, String active, String recovery,
              String onBlock, String onODR, String attribute, String invuln, String p1,
              String p2, String starter, String cancel, String level, String groundHit,
              String airHit, String groundCH, String airCH, String blockstop,
              String hitstop, String CHstop, String notes) {
        this.characterName = characterName;
        this.input = input;
        this.moveName = moveName;
        this.damage = damage;
        this.guard = guard;
        this.startup = startup;
        this.active = active;
        this.recovery = recovery;
        this.onBlock = onBlock;
        this.onODR = onODR;
        this.attribute = attribute;
        this.invuln = invuln;
        this.p1 = p1;
        this.p2 = p2;
        this.starter = starter;
        this.cancel = cancel;
        this.level = level;
        this.groundHit = groundHit;
        this.airHit = airHit;
        this.groundCH = groundCH;
        this.airCH = airCH;
        this.blockstop = blockstop;
        this.hitstop = hitstop;
        this.CHstop = CHstop;
        this.notes = notes;
    }
}