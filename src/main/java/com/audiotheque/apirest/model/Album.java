package com.audiotheque.apirest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity //pour dire à JPA que cette classe doit faire référence à une entité de bdd
@Table(name = "album") //au cas où le nom de la table diffère du nom de la classe
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AlbumId")
    private Integer id;

    @Column(name = "Title")
    private String title;

    public Album(){
    }

    public Album(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ArtistId")
    private Artist artist;

    public Album(Artist artist) {
        this.artist = artist;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
