package com.example.CarpathiansBlog.controllers;

import com.example.CarpathiansBlog.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ImageUploadController {
    @Autowired
    private StorageService storageService;

    @GetMapping(value = {"/get-image/{filename:.+}"})
    @ResponseBody
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).header(HttpHeaders.CONTENT_DISPOSITION,
                " filename=\"" + file.getFilename() + "\"").body(file);
    }
}
