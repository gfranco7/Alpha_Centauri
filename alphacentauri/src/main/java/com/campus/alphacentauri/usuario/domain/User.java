package com.campus.alphacentauri.usuario.domain;


import com.campus.alphacentauri.Seguimiento.domain.Seguimiento;
import com.campus.alphacentauri.Interaccion.domain.Interaccion;
import com.campus.alphacentauri.Notificacion.domain.Notificacion;
import com.campus.alphacentauri.Publicacion.domain.Publicacion;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String username;
    private String password;
    private String photo;
    private String lastname;
    private String bio;


    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Publicacion> publications;

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seguimiento> followers;
    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seguimiento> following;

    @OneToMany(mappedBy = "userReceivingInteration", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Interaccion> received;
    @OneToMany(mappedBy = "userGivingInteration", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Interaccion> delivered;

    @OneToMany(mappedBy = "giver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notificacion> notificationGiver;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notificacion> notificationReceiver;


    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }


    public List<Publicacion> getPublications() {
        return publications;
    }

    public void setPublications(List<Publicacion> publications) {
        this.publications = publications;
    }

    public List<Seguimiento> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Seguimiento> followers) {
        this.followers = followers;
    }

    public List<Seguimiento> getFollowing() {
        return following;
    }

    public void setFollowing(List<Seguimiento> following) {
        this.following = following;
    }

    public List<Interaccion> getReceived() {
        return received;
    }

    public void setReceived(List<Interaccion> received) {
        this.received = received;
    }

    public List<Interaccion> getDelivered() {
        return delivered;
    }

    public void setDelivered(List<Interaccion> delivered) {
        this.delivered = delivered;
    }


    public List<Notificacion> getNotificationGiver() {
        return notificationGiver;
    }

    public void setNotificationGiver(List<Notificacion> notificationGiver) {
        this.notificationGiver = notificationGiver;
    }

    public List<Notificacion> getNotificationReceiver() {
        return notificationReceiver;
    }

    public void setNotificationReceiver(List<Notificacion> notificationReceiver) {
        this.notificationReceiver = notificationReceiver;
    }


}
