package com.campus.Alpha_Centauri.Publicaciones.Domain;
import com.campus.Alpha_Centauri.Interacciones.Domain.*;
import com.campus.Alpha_Centauri.Usuarios.Domain.Usuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    private Usuario publisher;

    private LocalDateTime date;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Interaccion> interactions;

    public Publicacion() {
    }

    public Publicacion(Long id, String description, String photo, Usuario publisher, LocalDateTime date) {
        this.id = id;
        this.description = description;
        this.photo = photo;
        this.publisher = publisher;
        this.date = date;
    }

    public Publicacion(String description, String photo, Usuario publisher, LocalDateTime date) {
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

    public Usuario getUser() {
        return publisher;
    }

    public void setUser(Usuario publisher) {
        this.publisher = publisher;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<Interation> getInterations() {
        return interactions;
    }

    public void setInterations(List<Interation> interactions) {
        this.interactions = interactions;
    }

    public Usuario getPublisher() {
        return publisher;
    }

    public void setPublisher(Usuario publisher) {
        this.publisher = publisher;
    }
}
