package com.campus.alphacentauri.Seguimiento.domain;

import com.campus.alphacentauri.usuario.domain.User;

import java.util.List;
import java.util.Optional;

public interface SeguimientoRepository {

    List<Seguimiento> findAll();

    Seguimiento save(Seguimiento follow);

    Optional<Seguimiento> findById(Long id);

    void deleteById(Long id);

    void deleteByFollowerIdAndFollowingId(Long followerId, Long followingId);

    void deleteByFollower_IdAndFollowing_Id(Long followerId, Long followingId);

    void deleteByFollowerAndFollowing(User follower, User following);

}