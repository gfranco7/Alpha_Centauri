package com.campus.alphacentauri.Publicacion.application;


import com.campus.alphacentauri.Interaccion.domain.Interaccion;
import com.campus.alphacentauri.Interaccion.domain.InteraccionDTO;
import com.campus.alphacentauri.Interaccion.domain.InteraccionRepository;
import com.campus.alphacentauri.Notificacion.application.NotificacionImplementServ;
import com.campus.alphacentauri.Notificacion.domain.NotificacionDTO;
import com.campus.alphacentauri.Publicacion.domain.Publicacion;
import com.campus.alphacentauri.Publicacion.domain.PublicacionDTO;
import com.campus.alphacentauri.Publicacion.domain.PublicacionRepository;
import com.campus.alphacentauri.usuario.domain.User;
import com.campus.alphacentauri.usuario.domain.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PublicacionServImp implements PublicacionService {
    @Autowired
    private PublicacionRepository publicationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InteraccionRepository interationRepository;
    @Autowired
    private NotificacionImplementServ notificationService;

    @Override
    public List<PublicacionDTO> findAll() {
        return publicationRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PublicacionDTO> findById(Long id) {
        return publicationRepository.findById(id)
                .map(this::convertToDTO);

    }

    public PublicacionDTO save(PublicacionDTO publicationDTO) {
        Publicacion publication = convertToEntity(publicationDTO);

        // Se guarda la publicación sin asignar el ID manualmente
        Publicacion savedPublication = publicationRepository.save(publication);

        // Crear notificación para los seguidores
        savedPublication.getPublisher().getFollowers().forEach(follower ->
                notificationService.createNotification(
                        new NotificacionDTO(
                                "Publication",
                                savedPublication.getDescription(),
                                savedPublication.getPublisher().getId(),
                                follower.getId()
                        )
                )
        );

        return convertToDTO(savedPublication);
    }

    @Override
    @Transactional
    public PublicacionDTO updateDescription(Long id, String description) {
        Publicacion publication = publicationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Publicación no encontrada para id: " + id));
        publication.setDescription(description);
        Publicacion updatedPublication = publicationRepository.save(publication);
        return convertToDTO(updatedPublication);
    }

    @Override
    public void deleteById(Long id) {
        publicationRepository.deleteById(id);
    }

    @Override
    public List<PublicacionDTO> findByUserId(Long userId) {
        return publicationRepository.findByUserId(userId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private PublicacionDTO convertToDTO(Publicacion publication) {
        List<Interaccion> interaciones = interationRepository.findByPublicationId(publication.getId());

        List<InteraccionDTO> interationDTOs = interaciones.stream()
                .map(interation -> new InteraccionDTO(
                        interation.getId(),
                        interation.getPublication().getId(),
                        interation.getUserGivingInteration().getId(),
                        interation.getUserReceivingInteration().getId(),
                        interation.getTypeInteration().getId(),
                        interation.getDate(),
                        interation.getComment(),
                        interation.getUsername()
                ))
                .collect(Collectors.toList());

        return new PublicacionDTO(
                publication.getId(),
                publication.getDescription(),
                publication.getPhoto(),
                publication.getUser().getUsername(),
                publication.getDate(),
                publication.getPublisher().getId(),
                interationDTOs
        );
    }

    private Publicacion convertToEntity(PublicacionDTO publicationDTO) {
        Publicacion publication = new Publicacion();
        publication.setDescription(publicationDTO.getDescription());
        publication.setPhoto(publicationDTO.getPhoto());
        publication.setDate(publicationDTO.getDate());

        // Asegurémonos de usar el campo correcto para asignar el publisher
        User user = userRepository.findByUsername(publicationDTO.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        publication.setPublisher(user);  // Este campo debe ser 'publisher', no 'user'

        return publication;
    }


}