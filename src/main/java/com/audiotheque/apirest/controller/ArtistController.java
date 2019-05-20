package com.audiotheque.apirest.controller;

import com.audiotheque.apirest.exception.ConflictException;
import com.audiotheque.apirest.model.Artist;
import com.audiotheque.apirest.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    @Autowired
    private ArtistService artistService;

    //pour trouver l'artiste selon son id
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{id}"
    )
    public Artist trouverArtiste(
            @PathVariable Integer id
    ){
        return this.artistService.afficherArtist(id);
    }

    //pour trouver l'artiste selon son nom
    @RequestMapping (params = "name")
    public List<Artist>  nomArtiste(@RequestParam (value="name") String name) throws Exception{
        return artistService.nomArtiste(name);
    }

    //pour afficher la liste des artites
    @RequestMapping()
    public Page<Artist> findAllArtistes(
            @RequestParam ("page") Integer page,
            @RequestParam ("size") Integer size,
            @RequestParam ("sortProperty") String sortProperty,
            @RequestParam ("sortDirection") String sortDirection) throws Exception
    {
        return artistService.findAllArtistes(page, size, sortProperty, sortDirection);
    }

    //pour créer un artiste
    @RequestMapping (
            value = "",
            method = RequestMethod.POST
    )
    public Artist addArtist(
                @RequestBody Artist artist) throws ConflictException
    {
        return artistService.addArtist(artist);
    }

    //pour modifier un artiste
    @RequestMapping (
            method = RequestMethod.PUT,
            value = "/{id}"
    )
    public Artist updateArtist (
            @PathVariable (value = "id") Integer id,
            @RequestBody Artist artist)
    {
        return artistService.updateArtist(id, artist);
    }

    //pour supprimer un artiste
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ResponseStatus (value = HttpStatus.NO_CONTENT)
    public @ResponseBody void deleteArtist (
            @PathVariable("id") Integer id) {
        artistService.deleteArtist(id);
    }
}
