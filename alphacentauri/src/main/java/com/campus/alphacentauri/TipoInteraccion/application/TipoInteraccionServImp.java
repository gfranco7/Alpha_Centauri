package com.campus.alphacentauri.TipoInteraccion.application;


import com.campus.alphacentauri.TipoInteraccion.domain.TipoInteraccion;
import com.campus.alphacentauri.TipoInteraccion.domain.TipoInteraccionDTO;
import com.campus.alphacentauri.TipoInteraccion.domain.TipoInteraccionRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoInteraccionServImp implements TipoInteraccionService {

    private final TipoInteraccionRepository typeInterationRepository;

    @Autowired
    public TipoInteraccionServImp(TipoInteraccionRepository typeInterationRepository) {
        this.typeInterationRepository = typeInterationRepository;
    }


    @Override
    public List<TipoInteraccionDTO> findAll() {
        return typeInterationRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public  Optional<TipoInteraccionDTO> findById(Long id) {
        return typeInterationRepository.findById(id)
                .map(this::convertToDTO);

    }

    @Override
    public TipoInteraccionDTO save(TipoInteraccionDTO typeInterationDTO) {
        TipoInteraccion typeInteration = convertToEntity(typeInterationDTO);
        TipoInteraccion savedTypeInteration = typeInterationRepository.save(typeInteration);
        return convertToDTO(savedTypeInteration);
    }

    @Override
    public void deleteById(Long id) {
        typeInterationRepository.deleteById(id);
    }

    private TipoInteraccionDTO convertToDTO(TipoInteraccion typeInterationDTO) {
        return new TipoInteraccionDTO(
                typeInterationDTO.getId(),
                typeInterationDTO.getType());
    }

    private TipoInteraccion convertToEntity(TipoInteraccionDTO typeInterationDTO) {
        TipoInteraccion typeInteration = new TipoInteraccion();
        typeInteration.setId(typeInterationDTO.getId());
        typeInteration.setType(typeInterationDTO.getType());
        return typeInteration;
    }

}