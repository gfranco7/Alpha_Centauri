package com.campus.alphacentauri.Interaccion.domain;



import com.campus.alphacentauri.Publicacion.domain.Publicacion;
import com.campus.alphacentauri.TipoInteraccion.domain.TipoInteraccion;
import com.campus.alphacentauri.usuario.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Interaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publicacion publication;

    @ManyToOne
    @JoinColumn(name = "user_receiving_id")
    private User userReceivingInteration;

    @ManyToOne
    @JoinColumn(name = "user_giving_id")
    private User userGivingInteration;

    @ManyToOne
    @JoinColumn(name = "type_interation_id")
    private TipoInteraccion typeInteration;

    private LocalDateTime date;

    private String comment;

    private String username;

    public Interaccion() {
    }

    public Interaccion(Long id, Publicacion publication, User userReceivingInteration, User userGivingInteration, TipoInteraccion typeInteration, LocalDateTime date, String comment, String username) {
        this.id = id;
        this.publication = publication;
        this.userReceivingInteration = userReceivingInteration;
        this.userGivingInteration = userGivingInteration;
        this.typeInteration = typeInteration;
        this.date = date;
        this.comment = comment;
        this.username = username;
    }

    public Interaccion(Long id, Publicacion publication, User userReceivingInteration, User userGivingInteration, TipoInteraccion typeInteration, LocalDateTime date) {
        this.id = id;
        this.publication = publication;
        this.userReceivingInteration = userReceivingInteration;
        this.userGivingInteration = userGivingInteration;
        this.typeInteration = typeInteration;
        this.date = date;
    }

    public Interaccion(Long id, Publicacion publication, User userReceivingInteration, User userGivingInteration, TipoInteraccion typeInteration, LocalDateTime date, String comment) {
        this.id = id;
        this.publication = publication;
        this.userReceivingInteration = userReceivingInteration;
        this.userGivingInteration = userGivingInteration;
        this.typeInteration = typeInteration;
        this.date = date;
        this.comment = comment;
    }

    public Interaccion(Publicacion publication, User userReceivingInteration, User userGivingInteration, TipoInteraccion typeInteration, LocalDateTime date) {
        this.publication = publication;
        this.userReceivingInteration = userReceivingInteration;
        this.userGivingInteration = userGivingInteration;
        this.typeInteration = typeInteration;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Publicacion getPublication() {
        return publication;
    }

    public void setPublication(Publicacion publication) {
        this.publication = publication;
    }

    public User getUserReceivingInteration() {
        return userReceivingInteration;
    }

    public void setUserReceivingInteration(User userReceivingInteration) {
        this.userReceivingInteration = userReceivingInteration;
    }

    public User getUserGivingInteration() {
        return userGivingInteration;
    }

    public void setUserGivingInteration(User userGivingInteration) {
        this.userGivingInteration = userGivingInteration;
    }

    public TipoInteraccion getTypeInteration() {
        return typeInteration;
    }

    public void setTypeInteration(TipoInteraccion typeInteration) {
        this.typeInteration = typeInteration;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}