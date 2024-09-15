package org.rhmodding.saffronrest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Authors {
    
    @Id
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modID")
    @JsonIgnore
    private Mods mod;
    // @JoinColumn(name="userId", nullable=false)
    private Integer userId;
    private String name;
    private String role;
}
