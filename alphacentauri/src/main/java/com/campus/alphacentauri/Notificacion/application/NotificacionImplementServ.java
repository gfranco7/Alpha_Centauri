package com.campus.alphacentauri.Notificacion.application;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campus.alphacentauri.Notificacion.domain.Notificacion;
import com.campus.alphacentauri.Notificacion.domain.NotificacionDTO;
import com.campus.alphacentauri.Notificacion.domain.NotificacionRepository;
import com.campus.alphacentauri.usuario.domain.User;
import com.campus.alphacentauri.usuario.domain.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class NotificacionImplementServ {

    @Autowired
    private NotificacionRepository notificacionRepository;
    @Autowired
    private UserRepository userRepository;



    public List<NotificacionDTO> findNotificationsByUser(Long id) {
        return notificacionRepository.findNotificationsUserId(id).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void markAllNotificationsAsRead(Long userId) {
        notificacionRepository.markAllNotificationsAsRead(userId);
    }

    public Optional<NotificacionDTO> findById(Long id) {
        return notificacionRepository.findById(id)
                .map(this::toDTO);
    }

    public NotificacionDTO createNotification(NotificacionDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("El DTO de de notification es nulo");
        }
        Notificacion notification = toEntity(dto);
        notification.setDate(LocalDateTime.now());

        Notificacion savedNotification = notificacionRepository.save(notification);
        return toDTO(savedNotification);
    }

    public NotificacionDTO toDTO(Notificacion notification) {
        if (notification == null) {
            return null;
        }
        return new NotificacionDTO(
                notification.getId(),
                notification.getGiver() != null ? notification.getGiver().getId() : null,
                notification.getReceiver() != null ? notification.getReceiver().getId() : null,
                notification.getDate(),
                notification.getContent(),
                notification.getType(),
                notification.isChecked()
        );
    }

    public Notificacion toEntity(NotificacionDTO dto) {
        if (dto == null) {
            return null;
        }
        Notificacion notification = new Notificacion();
        notification.setId(dto.getId());

        User giver = userRepository.findById(dto.getIdGiver())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        notification.setGiver(giver);
        User receiver = userRepository.findById(dto.getIdReceiver())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        notification.setReceiver(receiver);

        notification.setDate(dto.getDate());
        notification.setContent(dto.getContent());
        notification.setType(dto.getType());
        notification.setChecked(dto.isChecked());
        return notification;
    }

}
