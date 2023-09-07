package org.rhmodding.saffronrest.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.rhmodding.saffronrest.models.Files;
import org.rhmodding.saffronrest.models.Mods;
import org.rhmodding.saffronrest.repositories.FilesRepository;
import org.rhmodding.saffronrest.repositories.ModsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @GetMapping(value = "/mods/{id}/pack/{index}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody ResponseEntity<byte[]> getPack(@PathVariable Integer id, @PathVariable Integer index) throws IOException{
        List<Files> files = filesRepository.findByModId(id);
        String filepath = files.get(index).getPath();
        File file = new File(filepath);
        FileInputStream fInputStream = new FileInputStream(file);
        byte[] filebytes = fInputStream.readAllBytes();
        fInputStream.close();

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("Content-disposition", "attachment; filename=" + file.getName());
        return ResponseEntity.ok().headers(responseHeader).body(filebytes);
    }
}
