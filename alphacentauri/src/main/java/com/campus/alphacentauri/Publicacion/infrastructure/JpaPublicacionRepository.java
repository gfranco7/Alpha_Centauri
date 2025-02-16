package com.campus.alphacentauri.Publicacion.infrastructure;


import com.campus.alphacentauri.Publicacion.domain.Publicacion;
import com.campus.alphacentauri.Publicacion.domain.PublicacionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaPublicacionRepository extends JpaRepository<Publicacion, Long>, PublicacionRepository {
    @Query("select p from Publicacion p where p.publisher.id = :id")
    List<Publicacion> findByUserId(Long id);

}
