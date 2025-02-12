package com.campus.alphacentauri.Seguimiento.application;
import com.campus.alphacentauri.Seguimiento.domain.SeguimientoDTO;
import java.util.List;
import java.util.Optional;

public interface SeguimientoService {

    List<SeguimientoDTO> findAll();

    Optional<SeguimientoDTO> findById(Long id);

    SeguimientoDTO save(SeguimientoDTO followDTO);

    void deleteById(Long id);

    void unfollow(Long followerId, Long followingId);
}
