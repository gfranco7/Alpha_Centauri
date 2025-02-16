package com.campus.alphacentauri.usuario.domain;

import java.util.List;

public class LoginResponseDTO {
    private String token;
    private Long id;
    private String name;
    private String lastname;
    private String email;
    private String username;
    private String photo;
    private String bio;
    private List<Long> publications;
    private List<Long> followersIds;
    private List<Long> followingIds;

    public String getToken() {
        return token;
    }

    public List<Long> getPublications() {
        return publications;
    }

    public void setPublications(List<Long> publications) {
        this.publications = publications;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Long> getFollowersIds() {
        return followersIds;
    }

    public void setFollowersIds(List<Long> followersIds) {
        this.followersIds = followersIds;
    }

    public List<Long> getFollowingIds() {
        return followingIds;
    }

    public void setFollowingIds(List<Long> followingIds) {
        this.followingIds = followingIds;
    }
}
