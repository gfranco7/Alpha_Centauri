package com.campus.alphacentauri.Interaccion.domain;

import java.time.LocalDateTime;

public class InteraccionDTO {

    private Long id;
    private Long publicationId;
    private Long userGivingId;
    private Long userReceivingId;
    private Long typeInterationId;
    private LocalDateTime date;
    private String comment;
    private String username;

    public InteraccionDTO() {
    }

    public InteraccionDTO(Long id, Long publicationId, Long userGivingId, Long userReceivingId, Long typeInterationId, LocalDateTime date, String comment, String username) {
        this.id = id;
        this.publicationId = publicationId;
        this.userGivingId = userGivingId;
        this.userReceivingId = userReceivingId;
        this.typeInterationId = typeInterationId;
        this.date = date;
        this.comment = comment;
        this.username = username;
    }

    public InteraccionDTO(Long id, Long publicationId, Long userGivingId, Long userReceivingId, Long typeInterationId, LocalDateTime date, String comment) {
        this.id = id;
        this.publicationId = publicationId;
        this.userGivingId = userGivingId;
        this.userReceivingId = userReceivingId;
        this.typeInterationId = typeInterationId;
        this.date = date;
        this.comment = comment;
    }

    public InteraccionDTO(Long id, Long publicationId, Long userGivingId, Long userReceivingId, Long typeInterationId, LocalDateTime date) {
        this.id = id;
        this.publicationId = publicationId;
        this.userGivingId = userGivingId;
        this.userReceivingId = userReceivingId;
        this.typeInterationId = typeInterationId;
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
    }

    public Long getUserGivingId() {
        return userGivingId;
    }

    public void setUserGivingId(Long userGivingId) {
        this.userGivingId = userGivingId;
    }

    public Long getUserReceivingId() {
        return userReceivingId;
    }

    public void setUserReceivingId(Long userReceivingId) {
        this.userReceivingId = userReceivingId;
    }

    public Long getTypeInterationId() {
        return typeInterationId;
    }

    public void setTypeInterationId(Long typeInterationId) {
        this.typeInterationId = typeInterationId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
