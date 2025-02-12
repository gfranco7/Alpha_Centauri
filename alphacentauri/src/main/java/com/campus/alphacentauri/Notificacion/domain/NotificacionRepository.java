package com.campus.alphacentauri.Notificacion.domain;


import com.campus.alphacentauri.Notificacion.domain.Notificacion;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NotificacionRepository {
    @Modifying
    @Query("UPDATE Notification n SET n.checked = true WHERE n.receiver.id = :userId")
    void markAllNotificationsAsRead(@Param("userId") Long userId);

    @Query("SELECT n FROM Notification n WHERE n.receiver.id = :userId")
    List<Notificacion> findNotificationsByUserId(@Param("userId") Long userId);

    Notificacion save(Notificacion notification);
    List<Notificacion> findAll();
    Optional<Notificacion> findById(Long id);



}
