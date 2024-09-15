package org.rhmodding.saffronrest.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

import org.rhmodding.saffronrest.config.AppConfig;
import org.rhmodding.saffronrest.models.Pics;
import org.rhmodding.saffronrest.repositories.PicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PicsController {


    @Autowired
    PicsRepository picsRepository;

    @Autowired
    AppConfig config;

    @GetMapping(value = "/pics/{id}")
    public @ResponseBody ResponseEntity<byte[]> getPics(@PathVariable Integer id) throws IOException{
        Pics modPics = picsRepository.findById(id).orElse(null);
        if(modPics == null) {
            return ResponseEntity.notFound().build();
        }
        String filepath = Paths.get(config.getPicpath(), modPics.getPath()).toString() ;
        File file = new File(filepath);
        FileInputStream fInputStream = new FileInputStream(file);
        byte[] filebytes = fInputStream.readAllBytes();
        fInputStream.close();

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(filebytes);
    }
}
