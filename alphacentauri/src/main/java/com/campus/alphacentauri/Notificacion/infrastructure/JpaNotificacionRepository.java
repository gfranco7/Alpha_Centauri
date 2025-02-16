package com.campus.alphacentauri.Notificacion.infrastructure;


import com.campus.alphacentauri.Notificacion.domain.Notificacion;
import com.campus.alphacentauri.Notificacion.domain.NotificacionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaNotificacionRepository extends JpaRepository<Notificacion, Long>, NotificacionRepository {
    @Query("SELECT n FROM Notificacion n WHERE n.receiver.id = :userId")
    List<Notificacion> findNotificationsUserId(@Param("userId") Long userId);

    @Modifying
    @Query("UPDATE Notificacion n SET n.checked = true WHERE n.receiver.id = :userId")
    void markAllNotificationsAsRead(@Param("userId") Long userId);
}
