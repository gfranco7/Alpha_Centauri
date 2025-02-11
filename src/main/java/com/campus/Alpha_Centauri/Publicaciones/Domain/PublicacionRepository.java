package com.campus.Alpha_Centauri.Publicaciones.Domain;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public class PublicacionRepository {
    List<Publication> findAll();

    Publication save(Publication typeInteration);

    Optional<Publication> findById(Long id);

    void deleteById(Long id);

    @Query("select p from Publication p where p.publisher.id = :id")
    List<Publication> findByUserId(Long id);


}
