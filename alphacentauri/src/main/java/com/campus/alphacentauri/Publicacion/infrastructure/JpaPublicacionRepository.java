package com.campus.alphacentauri.Publicacion.infrastructure;


import com.campus.alphacentauri.Publicacion.domain.Publicacion;
import com.campus.alphacentauri.Publicacion.domain.PublicacionRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPublicacionRepository extends JpaRepository<Publicacion, Long>, PublicacionRepository {

}
