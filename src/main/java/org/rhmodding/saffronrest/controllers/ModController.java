package org.rhmodding.saffronrest.controllers;

import java.util.ArrayList;
import java.util.List;

import org.rhmodding.saffronrest.models.Category;
import org.rhmodding.saffronrest.models.Mods;
import org.rhmodding.saffronrest.models.ModsSimplified;
import org.rhmodding.saffronrest.repositories.CategoryRepository;
import org.rhmodding.saffronrest.repositories.ModsRepository;
import org.rhmodding.saffronrest.services.ModService;
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
    CategoryRepository categoryRepository;

    @GetMapping(value = "/mods")
    public List<Mods> getMods(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required=false) String name,
        @RequestParam(required=false) Integer category,
        @RequestParam(required=false) Integer game){
            Pageable paging = PageRequest.of(page, size);
            Category categoryFull = null;
            if(category != null){
                categoryFull = categoryRepository.findById(category).orElse(null);
            }
            Page<Mods> mods = modsRepository.findSorted(paging, name, categoryFull, game);
            return mods.getContent();
    }

    @GetMapping(value = "/mods/simple")
    public List<ModsSimplified> getModsSimplified(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required=false) String name,
        @RequestParam(required=false) Integer category,
        @RequestParam(required=false) Integer game){
            Pageable paging = PageRequest.of(page, size);
            Category categoryFull = null;
            if(category != null){
                categoryFull = categoryRepository.findById(category).orElse(null);
            }
            Page<Mods> mods = modsRepository.findSorted(paging, name, categoryFull, game);
            var modsSimple = new ArrayList<ModsSimplified>();
            for (Mods mod : mods.getContent()) {
                modsSimple.add(ModService.convertMod(mod));
            }
            return modsSimple;
    }

    @GetMapping(value = "/mods/{id}")
    public Mods getModById(@PathVariable Integer id){
        return modsRepository.findById(id).get();
    }

}
