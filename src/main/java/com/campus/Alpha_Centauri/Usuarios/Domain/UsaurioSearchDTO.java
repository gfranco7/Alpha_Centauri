package com.campus.Alpha_Centauri.Usuarios.Domain;

public class UsaurioSearchDTO {
    private Long id;
    private String name;
    private String username;
    private String photo;

    public UsaurioSearchDTO() {}

    public UsaurioSearchDTO(Long id, String name, String username, String photo) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.photo = photo;
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
}
