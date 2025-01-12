package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class FrameData {
    private @Id
    @GeneratedValue Long id;
    private String characterName;
    private String input;
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
    private String groundHit;
    private String airHit;
    private String groundCH;
    private String airCH;
    private String blockstop;
    private String hitstop;
    private String CHstop;
    @Column(columnDefinition = "JSON")
    private ArrayList<String> images;

    public FrameData(){

    }
    FrameData(  String characterName, String input, String damage, String guard, String startup, String active, String recovery,
                String onBlock, String onODR, String attribute, String invuln, String p1,
                String p2, String starter, String cancel, String level, String groundHit,
                String airHit, String groundCH, String airCH, String blockstop,
                String hitstop, String CHstop){
        this.characterName = characterName;
        this.input = input;
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
    }
}
