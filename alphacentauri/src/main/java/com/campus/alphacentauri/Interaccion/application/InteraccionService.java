package com.campus.alphacentauri.Interaccion.application;
import com.campus.alphacentauri.Interaccion.domain.InteraccionDTO;
import java.util.List;
import java.util.Optional;

public interface InteraccionService {

    List<InteraccionDTO> findAll();

    Optional<InteraccionDTO> findById(Long id);

    InteraccionDTO save(InteraccionDTO interationDTO);

    void deleteById(Long id);
}
