package com.campus.alphacentauri.Publicacion.infrastructure;

import com.campus.alphacentauri.Publicacion.domain.PublicacionDTO;
import com.campus.alphacentauri.Publicacion.application.PublicacionServImp;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("uploads/").resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header("Content-Type", Files.probeContentType(filePath))
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public Optional findById(@PathVariable Long id){
        return publicationServiceImpl.findById(id);
    }

    @GetMapping("/user/{id}")
    public List<PublicacionDTO> findByUserId(@PathVariable Long id){
        return publicationServiceImpl.findByUserId(id);
    }

    @PostMapping
    public PublicacionDTO createPublication(@RequestBody PublicacionDTO publicationDTO) {
        return publicationServiceImpl.save(publicationDTO);
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<String> createPublicationWithImage(
            @RequestParam("description") String description,
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("username") String username,
            @RequestParam("publisherId") Long publisherId) {

        try {
            String uploadDir = "uploads/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = System.currentTimeMillis() + "_" + photo.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            Files.copy(photo.getInputStream(), filePath);

            PublicacionDTO publicationDTO = new PublicacionDTO(
                    null,
                    description,
                    fileName,
                    username,
                    LocalDateTime.now(),
                    publisherId
            );

            publicationServiceImpl.save(publicationDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body("Publicación creada exitosamente");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la imagen");
        }
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
        if(newDescription == null || newDescription.trim().isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        PublicacionDTO updatedPublication = publicationServiceImpl.updateDescription(id, newDescription);
        return ResponseEntity.ok(updatedPublication);
    }


}
