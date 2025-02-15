package com.campus.alphacentauri.TipoInteraccion.infrastructure;


import com.campus.alphacentauri.TipoInteraccion.domain.TipoInteraccionDTO;
import com.campus.alphacentauri.TipoInteraccion.application.TipoInteraccionServImp;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/typesinterations")
public class TipoInteraccionController {

    private final TipoInteraccionServImp typeInterationServiceImpl;

    @Autowired
    public TipoInteraccionController(TipoInteraccionServImp typeInterationServiceImpl) {
        this.typeInterationServiceImpl = typeInterationServiceImpl;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TipoInteraccionDTO> getAllTypeInterations() {
        return typeInterationServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public Optional findById(@PathVariable Long id){
        return typeInterationServiceImpl.findById(id);
    }

    @PostMapping
    public TipoInteraccionDTO createTypeInteration(@RequestBody TipoInteraccionDTO typeInterationDTO) {
        return typeInterationServiceImpl.save(typeInterationDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeInteration(@PathVariable Long id) {
        typeInterationServiceImpl.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public TipoInteraccionDTO updateTypeInteration(@PathVariable Long id, @RequestBody TipoInteraccionDTO typeInterationDTO) {
        typeInterationDTO.setId(id);
        return typeInterationServiceImpl.save(typeInterationDTO);
    }

}