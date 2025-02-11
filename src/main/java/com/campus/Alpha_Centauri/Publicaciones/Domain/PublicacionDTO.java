package com.campus.Alpha_Centauri.Publicaciones.Domain;

import com.campus.Alpha_Centauri.Interacciones.Domain.InteraccionDTO;
import java.time.LocalDateTime;
import java.util.List;

public class PublicacionDTO {
    private Long id;
    private String description;
    private String photo;
    private String username;
    private LocalDateTime date;
    private Long publisherId;
    private List<InterationDTO> interations;

    public PublicacionDTO(Long id, String description, String photo, String username, LocalDateTime date, Long publisherId, List<InterationDTO> interations) {
        this.id = id;
        this.description = description;
        this.photo = photo;
        this.username = username;
        this.date = date;
        this.publisherId = publisherId;
        this.interations = interations;
    }

    public PublicacionDTO(Long id, String description, String photo, String username, LocalDateTime date, Long publisherId) {
        this.id = id;
        this.description = description;
        this.photo = photo;
        this.username = username;
        this.date = date;
        this.publisherId = publisherId;
    }

    public PublicacionDTO() {
    }

    public PublicacionDTO(String description, String photo, String username, LocalDateTime date) {
        this.description = description;
        this.photo = photo;
        this.username = username;
        this.date = date;
    }

    public List<InterationDTO> getInterations() {
        return interations;
    }

    public void setInterations(List<InterationDTO> interations) {
        this.interations = interations;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
