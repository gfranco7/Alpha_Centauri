package com.campus.alphacentauri.Seguimiento.infrastructure;


import com.campus.alphacentauri.Seguimiento.domain.SeguimientoDTO;
import com.campus.alphacentauri.Seguimiento.application.*;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/follows")
public class SeguimientoController {

    private final SeguimientoImplementServ seguimientoImplementServ;

    @Autowired
    public SeguimientoController(SeguimientoImplementServ followServiceImpl) {
        this.seguimientoImplementServ = followServiceImpl;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SeguimientoDTO> getAllFollows() {
        return seguimientoImplementServ.findAll();
    }

    @GetMapping("/{id}")
    public Optional findById(@PathVariable Long id){
        return seguimientoImplementServ.findById(id);
    }

    @PostMapping
    public SeguimientoDTO createFollow(@RequestBody SeguimientoDTO followDTO) {
        return seguimientoImplementServ.save(followDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFollow(@PathVariable Long id) {
        seguimientoImplementServ.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @Transactional
    @DeleteMapping("/unfollow")
    public ResponseEntity<Void> unfollow(
            @RequestParam Long followerId,
            @RequestParam Long followingId) {
        seguimientoImplementServ.unseguimiento(followerId, followingId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public SeguimientoDTO updateFollow(@PathVariable Long id, @RequestBody SeguimientoDTO followDTO) {
        followDTO.setId(id);
        return seguimientoImplementServ.save(followDTO);
    }
}