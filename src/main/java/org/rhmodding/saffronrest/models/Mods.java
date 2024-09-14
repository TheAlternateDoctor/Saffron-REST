package org.rhmodding.saffronrest.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    private String video;
    private String slot;
    private Integer game;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category", referencedColumnName = "id")
    private Category category;

    @OneToMany(mappedBy = "mod")
    private List<Authors> authors;

    @OneToMany(mappedBy = "mod")
    private List<Files> files;

    @OneToMany(mappedBy = "mod")
    private List<Files> pics;
}
