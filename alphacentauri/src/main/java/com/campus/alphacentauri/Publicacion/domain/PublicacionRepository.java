package com.campus.alphacentauri.Publicacion.domain;

import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PublicacionRepository {

    List<Publicacion> findAll();

    Publicacion save(Publicacion typeInteration);

    Optional<Publicacion> findById(Long id);

    void deleteById(Long id);

    @Query("select p from Publicacion p where p.publisher.id = :id")
    List<Publicacion> findByUserId(Long id);


}
