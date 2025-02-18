package com.campus.alphacentauri.Interaccion.application;


import com.campus.alphacentauri.Interaccion.domain.ComentarioDTO;
import com.campus.alphacentauri.Interaccion.domain.Interaccion;
import com.campus.alphacentauri.Interaccion.domain.InteraccionDTO;
import com.campus.alphacentauri.Interaccion.domain.InteraccionRepository;
import com.campus.alphacentauri.Notificacion.application.NotificacionImplementServ;
import com.campus.alphacentauri.Notificacion.domain.NotificacionDTO;
import com.campus.alphacentauri.Publicacion.domain.Publicacion;
import com.campus.alphacentauri.Publicacion.domain.PublicacionRepository;
import com.campus.alphacentauri.TipoInteraccion.domain.TipoInteraccion;
import com.campus.alphacentauri.TipoInteraccion.domain.TipoInteraccionRepository;
import com.campus.alphacentauri.usuario.domain.User;
import com.campus.alphacentauri.usuario.domain.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;

@Service
public class InteraccionServiceImpl implements InteraccionService {

    private final NotificacionImplementServ NotificacionService;
    private final InteraccionRepository InteraccionRepository;
    private final UserRepository usuarioRepository;
    private final TipoInteraccionRepository TipoInteraccionRepository;
    private final PublicacionRepository PublicacionRepository;


    @Autowired
    public InteraccionServiceImpl(NotificacionImplementServ NotificacionService, InteraccionRepository InteraccionRepository, UserRepository usuarioRepository, TipoInteraccionRepository TipoInteraccionRepository, PublicacionRepository PublicacionRepository) {
        this.NotificacionService = NotificacionService;
        this.InteraccionRepository = InteraccionRepository;
        this.usuarioRepository = usuarioRepository;
        this.TipoInteraccionRepository = TipoInteraccionRepository;
        this.PublicacionRepository = PublicacionRepository;
    }


    @Override
    public List<InteraccionDTO> findAll() {
        return InteraccionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<InteraccionDTO> findById(Long id) {
        return InteraccionRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public InteraccionDTO save(InteraccionDTO InteraccionDTO) {
        if (InteraccionDTO == null) {
            throw new IllegalArgumentException("El DTO de interacción no puede ser nulo");
        }

        Interaccion Interaccion = convertToEntity(InteraccionDTO);
        Interaccion savedInteraccion = InteraccionRepository.save(Interaccion);

        NotificacionDTO NotificacionDTO = new NotificacionDTO();
        if (savedInteraccion.getTypeInteration().getId() == 1) {
            NotificacionDTO.setType("Like");
            NotificacionDTO.setContent(savedInteraccion.getUserGivingInteration().getUsername() + " liked your post");
        } else if (savedInteraccion.getTypeInteration().getId() == 2){
            NotificacionDTO.setType("Comentario");
            NotificacionDTO.setContent(savedInteraccion.getUserGivingInteration().getUsername() + ": "+savedInteraccion.getComment());
        } else {
            NotificacionDTO.setType("Interaccion");
            NotificacionDTO.setContent("You recived an Interaccion from " + savedInteraccion.getUserGivingInteration().getUsername());
        }

        NotificacionDTO.setIdGiver(savedInteraccion.getUserGivingInteration().getId());
        NotificacionDTO.setIdReceiver(savedInteraccion.getPublication().getPublisher().getId());
        NotificacionService.createNotification(NotificacionDTO);

        return convertToDTO(savedInteraccion);
    }


    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID de la interacción no puede ser nulo");
        }
        InteraccionRepository.deleteById(id);
    }

    private InteraccionDTO convertToDTO(Interaccion Interaccion) {
        return new InteraccionDTO(
                Interaccion.getId(),
                Interaccion.getPublication().getId(),
                Interaccion.getUserGivingInteration().getId(),
                Interaccion.getUserReceivingInteration().getId(),
                Interaccion.getTypeInteration().getId(),
                Interaccion.getDate(),
                Interaccion.getUsername()
        );
    }

    private ComentarioDTO convertComentarioToDTO(Interaccion Interaccion) {
        return new ComentarioDTO(
                Interaccion.getId(),
                Interaccion.getPublication().getId(),
                Interaccion.getUserGivingInteration().getId(),
                Interaccion.getUserReceivingInteration().getId(),
                Interaccion.getTypeInteration().getId(),
                Interaccion.getDate(),
                Interaccion.getComment()
        );
    }

    private Interaccion convertToEntity(InteraccionDTO InteraccionDTO) {
        if (InteraccionDTO == null) {
            throw new IllegalArgumentException("El DTO de interacción no puede ser nulo");
        }

        Interaccion Interaccion = new Interaccion();
        Interaccion.setId(InteraccionDTO.getId());

        Publicacion Publicacion = PublicacionRepository.findById(InteraccionDTO.getPublicationId())
                .orElseThrow(() -> new IllegalArgumentException("Publicación no encontrada con ID: " + InteraccionDTO.getPublicationId()));
        Interaccion.setPublication(Publicacion);

        User usuarioGiving = usuarioRepository.findById(InteraccionDTO.getUserGivingId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario que da la interacción no encontrado con ID: " + InteraccionDTO.getUserGivingId()));
        Interaccion.setUserGivingInteration(usuarioGiving);

        User usuarioReceiving = usuarioRepository.findById(InteraccionDTO.getUserReceivingId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario que recibe la interacción no encontrado con ID: " + InteraccionDTO.getUserReceivingId()));
        Interaccion.setUserReceivingInteration(usuarioReceiving);

        TipoInteraccion TipoInteraccion = TipoInteraccionRepository.findById(InteraccionDTO.getTypeInterationId())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de interacción no encontrado con ID: " + InteraccionDTO.getTypeInterationId()));
        Interaccion.setTypeInteration(TipoInteraccion);

        Interaccion.setDate(InteraccionDTO.getDate());

        Interaccion.setComment(InteraccionDTO.getComment());

        Interaccion.setUsername(InteraccionDTO.getUsername());

        return Interaccion;
    }

    private Interaccion convertComentarioToEntity(ComentarioDTO commentDTO) {
        if (commentDTO == null) {
            throw new IllegalArgumentException("El DTO de interacción no puede ser nulo");
        }

        Interaccion Interaccion = new Interaccion();
        Interaccion.setId(commentDTO.getId());

        Publicacion Publicacion = PublicacionRepository.findById(commentDTO.getPublicationId())
                .orElseThrow(() -> new IllegalArgumentException("Publicación no encontrada con ID: " + commentDTO.getPublicationId()));
        Interaccion.setPublication(Publicacion);

        User usuarioGiving = usuarioRepository.findById(commentDTO.getUserGivingId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario que da la interacción no encontrado con ID: " + commentDTO.getUserGivingId()));
        Interaccion.setUserGivingInteration(usuarioGiving);

        User usuarioReceiving = usuarioRepository.findById(commentDTO.getUserReceivingId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario que recibe la interacción no encontrado con ID: " + commentDTO.getUserReceivingId()));
        Interaccion.setUserReceivingInteration(usuarioReceiving);

        TipoInteraccion TipoInteraccion = TipoInteraccionRepository.findById(commentDTO.getTypeInterationId())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de interacción no encontrado con ID: " + commentDTO.getTypeInterationId()));
        Interaccion.setTypeInteration(TipoInteraccion);

        Interaccion.setDate(commentDTO.getDate());

        Interaccion.setComment(commentDTO.getComment());

        return Interaccion;
    }
}
