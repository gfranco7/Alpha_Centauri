package com.campus.alphacentauri.Publicacion.application;
import com.campus.alphacentauri.Publicacion.domain.PublicacionDTO;
import java.util.List;
import java.util.Optional;

public interface PublicacionService {
    List<PublicacionDTO> findAll();
    Optional<PublicacionDTO> findById(Long id);
    void deleteById(Long id);
    List<PublicacionDTO> findByUserId(Long userId);
    //   Publication(TypeInteration typeInterationDTO);
//   TypeInteration convertToEntity(Publication);
    PublicacionDTO updateDescription(Long id, String description);
}
