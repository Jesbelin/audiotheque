package com.audiotheque.apirest.repository;

import com.audiotheque.apirest.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
    Album findByTitle (String title);
}
