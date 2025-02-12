package com.campus.alphacentauri.Seguimiento.domain;


import com.campus.alphacentauri.usuario.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Seguimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "following_id")
    private User following;

    private LocalDateTime date;

    public Seguimiento() {
    }

    public Seguimiento(Long id, User follower, User following, LocalDateTime date) {
        this.id = id;
        this.follower = follower;
        this.following = following;
        this.date = date;
    }

    public Seguimiento(User follower, User following, LocalDateTime date) {
        this.follower = follower;
        this.following = following;
        this.date = date;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public User getFollowing() {
        return following;
    }

    public void setFollowing(User following) {
        this.following = following;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
