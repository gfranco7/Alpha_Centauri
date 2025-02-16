package com.campus.alphacentauri.Interaccion.infrastructure;


import com.campus.alphacentauri.Interaccion.domain.Interaccion;
import com.campus.alphacentauri.Interaccion.domain.InteraccionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaInteraccionRepository extends JpaRepository<Interaccion, Long>, InteraccionRepository {

}
