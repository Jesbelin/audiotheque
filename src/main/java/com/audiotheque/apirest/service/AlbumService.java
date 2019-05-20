package com.audiotheque.apirest.service;

import com.audiotheque.apirest.exception.ConflictException;
import com.audiotheque.apirest.model.Album;
import com.audiotheque.apirest.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    //création d'un nouvel album
    public Album addAlbum(Album album) throws ConflictException {
        if(albumRepository.findByTitle(album.getTitle()) !=null){
            throw new ConflictException ("L'album " + album.getTitle() + " existe déjà");
        }
        return albumRepository.save(album);
    }

    //supprimer un album
    public void deleteAlbum(Integer id) {
        albumRepository.delete(id);
    }
}
