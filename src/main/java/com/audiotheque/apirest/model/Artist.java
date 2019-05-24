package com.audiotheque.apirest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "ArtistId")
    private Integer id;

    @Column (name = "Name")
    private String name;

    public Artist() {
    }

    public Artist(List<Album> albums, Integer id, String name) {
        this.albums = albums;
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "artist")
    @JsonManagedReference
    private List<Album> albums;
}
