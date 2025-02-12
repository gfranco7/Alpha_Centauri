package com.campus.alphacentauri.usuario.infrastructure;

import com.campus.alphacentauri.usuario.domain.User;
import com.campus.alphacentauri.usuario.domain.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long>, UserRepository {

}
