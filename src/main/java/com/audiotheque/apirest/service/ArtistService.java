package com.audiotheque.apirest.service;


import com.audiotheque.apirest.exception.ConflictException;
import com.audiotheque.apirest.model.Artist;
import com.audiotheque.apirest.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepository;

    //affichage d'un artiste via son id
    public Artist afficherArtist(Integer id){
        Artist artist = artistRepository.findOne(id);
        if (artist == null){
            throw new EntityNotFoundException("L'artiste d'id " + id + " n'a pas été trouvé");
        }
        return artist;
    }

    //affichage d'un artiste via son nom
    public List<Artist> nomArtiste(String name) {
        List<Artist> artist = artistRepository.findByNameLike(name);
        if (artist == null){
            throw new EntityNotFoundException("L'artiste de nom" + name + " n'a pas été trouvé");
        }
        return artist;
    }

    //on compte le nombre d'artistes
    public Long nombreArtistes (){
        return artistRepository.count(); //il existe une méthode Count() dans le repository.
    }

    //afficher la liste paginée de tous les artistes
    public Page<Artist> findAllArtistes (Integer page, Integer size, String sortProperty, String sortDirection) {
        //vérification du paramètre page
        if (page == null) {
            page = 0;
        } else if (page < 0) {
            throw new IllegalArgumentException("Le numéro de page ne peut être inférieur à 0");
        } else if (page > nombreArtistes() / size) {
            throw new IllegalArgumentException("Le numéro de page ne peut être supérieur à " + (nombreArtistes() / size) + 1);
        }
        PageRequest pageable = new PageRequest(page, size, Sort.Direction.fromString(sortDirection), sortProperty);
        return artistRepository.findAll(pageable);
    }

    //création d'un nouvel artiste
    public Artist addArtist(Artist artist) throws ConflictException {
        //vérifier si l'artiste existe déjà
        if (artistRepository.findByName(artist.getName()) !=null){
            throw new ConflictException ("L'artiste " + artist.getName() + " existe déjà");
        }
        return artistRepository.save(artist);
    }

    //modification d'un artiste
    public Artist updateArtist (Integer id, Artist artist){
        return artistRepository.save(artist);
    }

    //suppression d'un artiste
    public void deleteArtist (Integer id){
        artistRepository.delete(id);
    }
}
