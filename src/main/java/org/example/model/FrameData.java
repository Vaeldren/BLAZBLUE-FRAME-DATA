package org.example.model;
import java.util.ArrayList;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FrameData {
    private @Id
    @GeneratedValue Long frameDataID;
    private String characterName;
    private String input;
    private int damage;
    private String guard;
    private int startup;
    private int active;
    private int recovery;
    private int onBlock;
    private int onODR;
    private String attribute;
    private String invuln;
    private int p1;
    private int p2;
    private String starter;
    private String cancel;
    private int level;
    private String groundHit;
    private String airHit;
    private String groundCH;
    private String airCH;
    private int blockstop;
    private String hitstop;
    private String CHstop;
    @Column(columnDefinition = "JSON")
    private ArrayList<String> images;

    public FrameData( String characterName, String input, int damage, String guard, int startup, int active, int recovery,
                int onBlock, int onODR, String attribute, String invuln, int p1,
                int p2, String starter, String cancel, int level, String groundHit,
                String airHit, String groundCH, String airCH, int blockstop,
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

    public Long getFrameDataID(){
        return frameDataID;
    }

    public String getInput(){
        return input;
    }
    public ArrayList<String> getImages() {
        return images;
    }

}
