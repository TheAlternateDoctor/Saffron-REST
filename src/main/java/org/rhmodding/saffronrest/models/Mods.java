package org.rhmodding.saffronrest.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Mods {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String shortDesc;
    private String longDesc;
    private Boolean isLegacy;
    private Integer baseSlot;
    private Boolean isModpack;
    private Integer game;
}