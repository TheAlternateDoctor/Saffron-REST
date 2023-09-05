package org.rhmodding.saffronrest.controllers;

import java.util.List;

import org.rhmodding.saffronrest.models.Files;
import org.rhmodding.saffronrest.models.Mods;
import org.rhmodding.saffronrest.repositories.FilesRepository;
import org.rhmodding.saffronrest.repositories.ModsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModController {
    
    @Autowired
    ModsRepository modsRepository;
    
    @Autowired
    FilesRepository filesRepository;

    @GetMapping(value = "/mods")
    public List<Mods> getMods(){
        return modsRepository.findAll();
    }

    @GetMapping(value = "/mods/{id}")
    public Mods getModById(@PathVariable Integer id){
        return modsRepository.findById(id).get();
    }

    @GetMapping(value = "/mods/{id}/pack")
    public List<Files> getModFilesById(@PathVariable Integer id){
        return filesRepository.findByModId(id);
    }
}
