package org.rhmodding.saffronrest.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

import org.rhmodding.saffronrest.config.AppConfig;
import org.rhmodding.saffronrest.models.Files;
import org.rhmodding.saffronrest.repositories.FilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {

    @Autowired
    FilesRepository filesRepository;

    @Autowired
    AppConfig config;

    @GetMapping(value = "/file/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody ResponseEntity<byte[]> getFile(@PathVariable Integer id) throws IOException{
        Files modFile = filesRepository.findById(id).orElse(null);
        if(modFile == null) {
            return ResponseEntity.notFound().build();
        }
        String filepath = Paths.get(config.getFilepath(), modFile.getPath()).toString() ;
        File file = new File(filepath);
        FileInputStream fInputStream = new FileInputStream(file);
        byte[] filebytes = fInputStream.readAllBytes();
        fInputStream.close();

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("Content-disposition", "attachment; filename=" + file.getName());
        return ResponseEntity.ok().headers(responseHeader).body(filebytes);
    }

}
