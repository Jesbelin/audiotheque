package com.audiotheque.apirest.repository;

import com.audiotheque.apirest.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    Artist findById (Integer id);

    @Query(value = "SELECT * FROM artist WHERE name LIKE %:name%", nativeQuery = true)
    List<Artist> findByNameLike(@Param("name") String name);
    //pour la liste on peut mettre 'like' car c'est une recherche approximative

    Artist findByName(String name);
    //on met pas 'like' car là c'est une recherche précise
}

