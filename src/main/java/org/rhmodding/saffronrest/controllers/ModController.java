package org.rhmodding.saffronrest.controllers;

import java.util.List;

import org.rhmodding.saffronrest.models.Mods;
import org.rhmodding.saffronrest.repositories.FilesRepository;
import org.rhmodding.saffronrest.repositories.ModsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModController {
    
    @Autowired
    ModsRepository modsRepository;
    
    @Autowired
    FilesRepository filesRepository;

    @GetMapping(value = "/mods")
    public List<Mods> getMods(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size){
        Pageable paging = PageRequest.of(page, size);
        Page<Mods> mods = modsRepository.findAll(paging);
        return mods.getContent();
    }

    @GetMapping(value = "/mods/{id}")
    public Mods getModById(@PathVariable Integer id){
        return modsRepository.findById(id).get();
    }
}
