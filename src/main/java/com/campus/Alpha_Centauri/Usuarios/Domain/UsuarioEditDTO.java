package com.campus.Alpha_Centauri.Usuarios.Domain;


import java.util.List;

public class UsuarioEditDTO {
    private Long id;
    private String name;
    private String lastname;
    private String username;
    private String photo;
    private String bio;
    private List<Long> publications;
    private List<Long> followersIds;
    private List<Long> followingIds;

    public UsuarioEditDTO() {
    }

    public UsuarioEditDTO(Long id, String name, String lastname, String username, String photo, String bio, List<Long> publications, List<Long> followersIds, List<Long> followingIds) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.photo = photo;
        this.bio = bio;
        this.publications = publications;
        this.followersIds = followersIds;
        this.followingIds = followingIds;
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

    public List<Long> getPublications() {
        return publications;
    }

    public void setPublications(List<Long> publications) {
        this.publications = publications;
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
