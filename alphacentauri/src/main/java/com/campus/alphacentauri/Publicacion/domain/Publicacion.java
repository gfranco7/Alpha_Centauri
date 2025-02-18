package com.campus.alphacentauri.Publicacion.domain;
import com.campus.alphacentauri.Interaccion.domain.Interaccion;
import com.campus.alphacentauri.usuario.domain.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String photo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User publisher;

    private LocalDateTime date;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Interaccion> interactions;

    public Publicacion() {
    }

    public Publicacion(Long id, String description, String photo, User publisher, LocalDateTime date) {
        this.id = id;
        this.description = description;
        this.photo = photo;
        this.publisher = publisher;
        this.date = date;
    }

    public Publicacion(String description, String photo, User publisher, LocalDateTime date) {
        this.description = description;
        this.photo = photo;
        this.publisher = publisher;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public User getUser() {
        return publisher;
    }

    public void setUser(User publisher) {
        this.publisher = publisher;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

   public List<Interaccion> getInterations() {
        return interactions;
    }

    public void setInterations(List<Interaccion> interactions) {
        this.interactions = interactions;
    }

    public User getPublisher() {
        return publisher;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }

}
