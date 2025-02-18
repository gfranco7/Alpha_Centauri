package com.campus.alphacentauri.Interaccion.infrastructure;



import com.campus.alphacentauri.Interaccion.domain.ComentarioDTO;
import com.campus.alphacentauri.Interaccion.domain.Interaccion;
import com.campus.alphacentauri.Interaccion.domain.InteraccionDTO;
import com.campus.alphacentauri.Interaccion.domain.InteraccionRepository;
import com.campus.alphacentauri.Interaccion.application.InteraccionServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interations")
public class InteraccionController {

    private final InteraccionServiceImpl InteraccionServiceImpl;
    private final InteraccionRepository InteraccionRepository;

    @Autowired
    public InteraccionController(InteraccionServiceImpl InteraccionServiceImpl, InteraccionRepository InteraccionRepository) {
        this.InteraccionServiceImpl = InteraccionServiceImpl;
        this.InteraccionRepository = InteraccionRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InteraccionDTO> getAllInteraccions() {
        return InteraccionServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InteraccionDTO> getInteraccionById(@PathVariable Long id) {
        Optional<InteraccionDTO> InteraccionDTO = InteraccionServiceImpl.findById(id);
        return InteraccionDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/comment")
    public ResponseEntity<InteraccionDTO> updateComentario(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        if (payload == null || !payload.containsKey("comment")) {
            return ResponseEntity.badRequest().build();
        }

        Optional<InteraccionDTO> optionalInteraccionDTO = InteraccionServiceImpl.findById(id);
        if (!optionalInteraccionDTO.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        InteraccionDTO InteraccionDTO = optionalInteraccionDTO.get();
        InteraccionDTO.setComment(payload.get("comment"));

        InteraccionDTO updatedInteraccion = InteraccionServiceImpl.save(InteraccionDTO);
        return ResponseEntity.ok(updatedInteraccion);
    }

    @PostMapping
    public ResponseEntity<InteraccionDTO> createInteraccion(@RequestBody InteraccionDTO InteraccionDTO) {
        if (InteraccionDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        InteraccionDTO savedInteraccion = InteraccionServiceImpl.save(InteraccionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInteraccion);
    }


    @PutMapping("/{id}")
    public ResponseEntity<InteraccionDTO> updateInteraccion(@PathVariable Long id, @RequestBody InteraccionDTO InteraccionDTO) {
        if (InteraccionDTO == null || id == null) {
            return ResponseEntity.badRequest().build();
        }
        InteraccionDTO.setId(id);
        InteraccionDTO updatedInteraccion = InteraccionServiceImpl.save(InteraccionDTO);
        return ResponseEntity.ok(updatedInteraccion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInteraccion(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        InteraccionServiceImpl.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/check-like")
    public ResponseEntity<Map<String, Object>> checkIfUserLikedPost(
            @RequestParam Long userId,
            @RequestParam Long postId) {

        Optional<Interaccion> interaction = InteraccionRepository.findByUserGivingIdAndPublicationIdAndTypeInterationId(
                userId,
                postId,
                1L
        );

        Map<String, Object> response = new HashMap<>();
        response.put("hasLiked", interaction.isPresent());
        interaction.ifPresent(i -> response.put("interactionId", i.getId()));

        return ResponseEntity.ok(response);
    }
}
