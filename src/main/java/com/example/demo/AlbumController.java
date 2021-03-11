package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping("/album")
    public String getAlbum() {
        return albumService.getAlbum();
    }

}
