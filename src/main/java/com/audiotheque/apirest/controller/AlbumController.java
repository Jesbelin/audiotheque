package com.audiotheque.apirest.controller;

import com.audiotheque.apirest.exception.ConflictException;
import com.audiotheque.apirest.model.Album;
import com.audiotheque.apirest.repository.AlbumRepository;
import com.audiotheque.apirest.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/albums")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    //pour ajouter un album
    @RequestMapping (
            value = "",
            method = RequestMethod.POST
    )
    public Album addAlbum (
            @RequestBody Album album) throws ConflictException
    {
        return albumService.addAlbum(album);
    }

    //pour supprimer un album
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ResponseStatus (value = HttpStatus.NO_CONTENT)
    public @ResponseBody void deleteAlbum (
            @PathVariable("id") Integer id)
    {
        albumService.deleteAlbum(id);
    }
}
