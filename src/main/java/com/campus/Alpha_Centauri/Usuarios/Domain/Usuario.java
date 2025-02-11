package com.campus.Alpha_Centauri.Usuarios.Domain;

public class User {

import com.campus.ledrian.chat.domain.ChatMessage;
import com.campus.ledrian.follow.domain.Follow;
import com.campus.ledrian.interation.domain.Interation;
import com.campus.ledrian.notification.domain.Notification;
import com.campus.ledrian.publication.domain.Publication;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

    @Entity
    @Table(name = "usuario")
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
        private List<Publication> publications;

        @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Follow> followers;
        @OneToMany(mappedBy = "following", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Follow> following;

        @OneToMany(mappedBy = "userReceivingInteration", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Interation> received;
        @OneToMany(mappedBy = "userGivingInteration", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Interation> delivered;

        @OneToMany(mappedBy = "giver", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Notification> notificationGiver;

        @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Notification> notificationReceiver;

        @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<ChatMessage> sentMessages = new ArrayList<>();

        @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<ChatMessage> receivedMessages = new ArrayList<>();

        public User() {
        }

        public User(Long id, String name, String email, String username, String password, String photo, String lastname) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.username = username;
            this.password = password;
            this.photo = photo;
            this.lastname = lastname;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
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

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public List<Publication> getPublications() {
            return publications;
        }

        public void setPublications(List<Publication> publications) {
            this.publications = publications;
        }

        public List<Follow> getFollowers() {
            return followers;
        }

        public void setFollowers(List<Follow> followers) {
            this.followers = followers;
        }

        public List<Follow> getFollowing() {
            return following;
        }

        public void setFollowing(List<Follow> following) {
            this.following = following;
        }

        public List<Interation> getReceived() {
            return received;
        }

        public void setReceived(List<Interation> received) {
            this.received = received;
        }

        public List<Interation> getDelivered() {
            return delivered;
        }

        public void setDelivered(List<Interation> delivered) {
            this.delivered = delivered;
        }


        public List<Notification> getNotificationGiver() {
            return notificationGiver;
        }

        public void setNotificationGiver(List<Notification> notificationGiver) {
            this.notificationGiver = notificationGiver;
        }

        public List<Notification> getNotificationReceiver() {
            return notificationReceiver;
        }

        public void setNotificationReceiver(List<Notification> notificationReceiver) {
            this.notificationReceiver = notificationReceiver;
        }


}
