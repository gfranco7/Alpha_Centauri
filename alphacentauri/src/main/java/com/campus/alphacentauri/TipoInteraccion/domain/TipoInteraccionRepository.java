package com.campus.alphacentauri.TipoInteraccion.domain;

import java.util.List;
import java.util.Optional;

public interface TipoInteraccionRepository {

    List<TipoInteraccion> findAll();

    TipoInteraccion save(TipoInteraccion typeInteration);

    Optional<TipoInteraccion> findById(Long id);

    void deleteById(Long id);

    Optional<TipoInteraccion> findByType(String type);

}
