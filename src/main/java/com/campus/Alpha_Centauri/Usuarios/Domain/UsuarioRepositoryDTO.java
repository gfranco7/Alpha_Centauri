package com.campus.Alpha_Centauri.Usuarios.Domain;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    boolean existsByUsuarioname(String Usuarioname);
    boolean existsByEmail(String email);
    List<Usuario> findAll();
    Usuarios save(Usuarios Usuario);
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByUsuarioname(String Usuarioname);
    Usuario findByEmail(String email);
    void deleteById(Long id);
    List<Usuario> findByNameContainingOrUsuarionameContaining(String name, String Usuarioname);
}
