package org.rhmodding.saffronrest.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

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

    @OneToMany(mappedBy = "mod")
    private List<Authors> authors;

    @OneToMany(mappedBy = "mod")
    private List<Files> files;
}
