package com.campus.alphacentauri.TipoInteraccion.domain;


import com.campus.alphacentauri.Interaccion.domain.Interaccion;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class TipoInteraccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;

    @OneToMany(mappedBy = "typeInteration", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Interaccion> interactions;

    public TipoInteraccion() {
    }

    public TipoInteraccion(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Interaccion> getInterations() {
        return interactions;
    }

    public void setInterations(List<Interaccion> interactions) {
        this.interactions = interactions;
    }



}