package com.campus.alphacentauri.TipoInteraccion.infrastructure;


import com.campus.alphacentauri.TipoInteraccion.domain.TipoInteraccion;
import com.campus.alphacentauri.TipoInteraccion.domain.TipoInteraccionRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTipoInteraccionRepository extends JpaRepository<TipoInteraccion, Long>, TipoInteraccionRepository {

}
