package com.campus.alphacentauri.Interaccion.domain;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InteraccionRepository {

    List<Interaccion> findAll();

    Interaccion save(Interaccion interation);

    Optional<Interaccion> findById(Long id);

    void deleteById(Long id);

    List<Interaccion> findByPublicationId(Long publicationId);

    @Query("SELECT i FROM Interation i WHERE " +
            "i.userGivingInteration.id = :userId AND " +
            "i.publication.id = :postId AND " +
            "i.typeInteration.id = :typeId")
    Optional<Interaccion> findByUserGivingIdAndPublicationIdAndTypeInterationId(
            @Param("userId") Long userId,
            @Param("postId") Long postId,
            @Param("typeId") Long typeId
    );

}