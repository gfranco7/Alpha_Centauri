package com.campus.alphacentauri.TipoInteraccion.application;

import com.campus.alphacentauri.TipoInteraccion.domain.TipoInteraccion;
import com.campus.alphacentauri.TipoInteraccion.domain.TipoInteraccionDTO;
import java.util.List;
import java.util.Optional;

public interface TipoInteraccionService {
    List<TipoInteraccionDTO> findAll();
    Optional<TipoInteraccionDTO> findById(Long id);
    TipoInteraccionDTO save(TipoInteraccionDTO typeInterationDTO);
    void deleteById(Long id);
//   TypeInterationDTO convertToDTO(TypeInteration typeInterationDTO);
//   TypeInteration convertToEntity(TypeInterationDTO typeInterationDTO);
}
