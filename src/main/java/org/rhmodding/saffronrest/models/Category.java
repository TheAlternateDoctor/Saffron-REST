package org.rhmodding.saffronrest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer level;
    @Column(name = "parent", insertable=false, updatable=false)
    private Integer parent;
    @JsonIgnore
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name = "parent", referencedColumnName = "id")
    private Category parentCategory;
}
