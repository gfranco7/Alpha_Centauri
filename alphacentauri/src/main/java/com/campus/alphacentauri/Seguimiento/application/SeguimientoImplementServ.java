package com.campus.alphacentauri.Seguimiento.application;


import com.campus.alphacentauri.Seguimiento.domain.Seguimiento;
import com.campus.alphacentauri.Seguimiento.domain.SeguimientoDTO;
import com.campus.alphacentauri.Seguimiento.domain.SeguimientoRepository;
import com.campus.alphacentauri.Notificacion.application.NotificacionImplementServ;
import com.campus.alphacentauri.Notificacion.domain.NotificacionDTO;
import com.campus.alphacentauri.usuario.domain.User;
import com.campus.alphacentauri.usuario.domain.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SeguimientoImplementServ {

    private final SeguimientoRepository seguimientoRepository;
    private final UserRepository userRepository;
    @Autowired
    private NotificacionImplementServ NotificacionService;


    @Autowired
    public SeguimientoImplementServ(SeguimientoRepository seguimientoRepository, UserRepository userRepository) {
        this.seguimientoRepository = seguimientoRepository;
        this.userRepository = userRepository;
    }



    public List<SeguimientoDTO> findAll() {
        return seguimientoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<SeguimientoDTO> findById(Long id) {
        return seguimientoRepository.findById(id)
                .map(this::convertToDTO);

    }


    public SeguimientoDTO save(SeguimientoDTO SeguimientoDTO) {
        Seguimiento seguimiento = convertToEntity(SeguimientoDTO);
        Seguimiento savedFollow = seguimientoRepository.save(seguimiento);

        NotificacionDTO NotificacionDTO = new NotificacionDTO();
        NotificacionDTO.setType("Follow");
        NotificacionDTO.setContent(seguimiento.getFollowing().getUsername() + " started following you...");
        NotificacionDTO.setIdGiver(seguimiento.getFollowing().getId());
        NotificacionDTO.setIdReceiver(seguimiento.getFollower().getId());
        NotificacionService.createNotification(NotificacionDTO);
        return convertToDTO(savedFollow);
    }

    @Transactional
    public void unseguimiento(Long seguimientoerId, Long seguimientoingId) {
        // Buscar las entidades seguimientoer y seguimientoing por sus ID
        User seguimientoer = userRepository.findById(seguimientoerId)
                .orElseThrow(() -> new RuntimeException("Follower not found"));
        User seguimientoing = userRepository.findById(seguimientoingId)
                .orElseThrow(() -> new RuntimeException("Following not found"));

        // Eliminar el seguimiento (dejar de seguir)
        seguimientoRepository.deleteByFollowerAndFollowing(seguimientoer, seguimientoing);
    }

    public void deleteById(Long id) {
        seguimientoRepository.deleteById(id);
    }

    private SeguimientoDTO convertToDTO(Seguimiento seguimiento) {
        return new SeguimientoDTO(
                seguimiento.getId(),
                seguimiento.getFollower().getId(),
                seguimiento.getFollowing().getId(),
                seguimiento.getDate()
        );
    }
    private Seguimiento convertToEntity(SeguimientoDTO seguimientoDTO) {
        Seguimiento seguimiento = new Seguimiento();
        seguimiento.setId(seguimientoDTO.getId());

        User userFollowing = userRepository.findById(seguimientoDTO.getUsernameFollowingId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        seguimiento.setFollower(userFollowing);

        User userFollowed = userRepository.findById(seguimientoDTO.getUsernameFollowedId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        seguimiento.setFollowing(userFollowed);

        seguimiento.setDate(seguimientoDTO.getDate());
        if (seguimientoDTO.getUsernameFollowedId().equals(seguimientoDTO.getUsernameFollowingId())) {
            throw new IllegalArgumentException("Can't seguimiento yourself back.");
        }

        return seguimiento;
    }

}