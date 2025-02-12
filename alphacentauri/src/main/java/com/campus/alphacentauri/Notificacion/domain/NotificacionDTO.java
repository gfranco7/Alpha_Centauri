package com.campus.alphacentauri.Notificacion.domain;


import java.time.LocalDateTime;

public class NotificacionDTO {
    private Long id;
    private Long idGiver;
    private Long idReceiver;
    private LocalDateTime date;
    private String content;
    private String type;
    private boolean checked = false;

    public NotificacionDTO() {
        this.checked = false;
    }

    public NotificacionDTO(Long id, Long idGiver, Long idReceiver, LocalDateTime date, String content, String type, boolean checked) {
        this.id = id;
        this.idGiver = idGiver;
        this.idReceiver = idReceiver;
        this.date = date;
        this.content = content;
        this.type = type;
        this.checked = checked;
    }

    public NotificacionDTO(String type, String content, Long idGiver, Long idReceiver ) {
        this.idGiver = idGiver;
        this.idReceiver = idReceiver;
        this.content = content;
        this.type = type;
        this.checked = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdGiver() {
        return idGiver;
    }

    public void setIdGiver(Long idGiver) {
        this.idGiver = idGiver;
    }

    public Long getIdReceiver() {
        return idReceiver;
    }

    public void setIdReceiver(Long idReceiver) {
        this.idReceiver = idReceiver;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
