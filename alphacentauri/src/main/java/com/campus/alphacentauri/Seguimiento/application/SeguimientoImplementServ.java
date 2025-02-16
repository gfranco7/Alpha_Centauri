package com.campus.alphacentauri.Seguimiento.application;


import com.campus.alphacentauri.Seguimiento.domain.Seguimiento;
import com.campus.alphacentauri.Seguimiento.domain.SeguimientoDTO;
import com.campus.alphacentauri.Seguimiento.domain.SeguimientoRepository;
//import com.campus.alphacentauri.notification.application.NotificationServiceImpl;
//import com.campus.alphacentauri.notification.domain.NotificationDTO;
//import com.campus.alphacentauri.notification.domain.NotificationRepository;
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
    public SeguimientoImplementServ(SeguimientoRepository followRepository, UserRepository userRepository/*,NotificationServiceImpl notificationService*/) {
        this.seguimientoRepository = followRepository;
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

    public SeguimientoDTO save(SeguimientoDTO followDTO) {
        return null;
    }

    /*@Override
    public SeguimientoDTO save(SeguimientoDTO SeguimientoDTO) {
        Seguimiento seguimiento = convertToEntity(SeguimientoDTO);
        Seguimiento savedFollow = SeguimientoRepository.save(seguimiento);

        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setType("Follow");
        notificationDTO.setContent(follow.getFollowing().getUsername() + " started following you...");
        notificationDTO.setIdGiver(follow.getFollowing().getId());
        notificationDTO.setIdReceiver(follow.getFollower().getId());
        notificationService.createNotification(notificationDTO);
        return convertToDTO(savedFollow);
    } */

    @Transactional
    public void unfollow(Long followerId, Long followingId) {
        // Buscar las entidades follower y following por sus ID
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("Follower not found"));
        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new RuntimeException("Following not found"));

        // Eliminar el seguimiento (dejar de seguir)
        seguimientoRepository.deleteByFollowerAndFollowing(follower, following);
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
            throw new IllegalArgumentException("Can't follow yourself back.");
        }

        return seguimiento;
    }

}