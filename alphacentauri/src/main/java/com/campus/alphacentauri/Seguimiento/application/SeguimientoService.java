package com.campus.alphacentauri.Seguimiento.application;
import com.campus.alphacentauri.Seguimiento.domain.Seguimiento;
import com.campus.alphacentauri.Seguimiento.domain.SeguimientoDTO;
import java.util.List;
import java.util.Optional;

public interface SeguimientoService {


    List<Seguimiento> findAll();

    Seguimiento save(Seguimiento follow);

    Optional<Seguimiento> findById(Long id);

    void deleteById(Long id);

    void deleteByFollowerIdAndFollowingId(Long followerId, Long followingId);

    void deleteByFollower_IdAndFollowing_Id(Long followerId, Long followingId);
}
