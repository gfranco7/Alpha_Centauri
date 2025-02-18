package com.campus.alphacentauri.Publicacion.infrastructure;

import com.campus.alphacentauri.Publicacion.domain.PublicacionDTO;
import com.campus.alphacentauri.Publicacion.application.PublicacionServImp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/publications")
public class PublicacionController {

    private final PublicacionServImp publicationServiceImpl;

    @Autowired
    public PublicacionController(PublicacionServImp publicationServiceImpl) {
        this.publicationServiceImpl = publicationServiceImpl;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PublicacionDTO> getAllPublications() {
        return publicationServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public Optional<PublicacionDTO> findById(@PathVariable Long id) {
        return publicationServiceImpl.findById(id);
    }

    @GetMapping("/user/{id}")
    public List<PublicacionDTO> findByUserId(@PathVariable Long id) {
        return publicationServiceImpl.findByUserId(id);
    }

    @PostMapping
    public PublicacionDTO createPublication(@RequestBody PublicacionDTO publicationDTO) {
        return publicationServiceImpl.save(publicationDTO);
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<String> createPublicationWithImage(
            @RequestParam("description") String description,
            @RequestParam("photo") String photoUrl, // Cambio aquí: recibimos la URL como texto
            @RequestParam("username") String username,
            @RequestParam("publisherId") Long publisherId) {

        PublicacionDTO publicationDTO = new PublicacionDTO();
        publicationDTO.setDescription(description);
        publicationDTO.setPhoto(photoUrl);
        publicationDTO.setUsername(username);
        publicationDTO.setDate(LocalDateTime.now());
        publicationDTO.setPublisherId(publisherId);

        publicationServiceImpl.save(publicationDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body("Publicación creada exitosamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable Long id) {
        publicationServiceImpl.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public PublicacionDTO updatePublication(@PathVariable Long id, @RequestBody PublicacionDTO publicationDTO) {
        publicationDTO.setId(id);
        return publicationServiceImpl.save(publicationDTO);
    }

    @PatchMapping("/{id}/description")
    public ResponseEntity<PublicacionDTO> updatePublicationDescription(
            @PathVariable Long id,
            @RequestBody Map<String, String> payload) {

        String newDescription = payload.get("description");
        if (newDescription == null || newDescription.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        PublicacionDTO updatedPublication = publicationServiceImpl.updateDescription(id, newDescription);
        return ResponseEntity.ok(updatedPublication);
    }
}