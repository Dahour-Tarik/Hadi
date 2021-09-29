package com.auth.authuser.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name="users")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="roleuser")
public class User {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", length = 65)
    private String userName;
    @Column(name = "password", length = 64)
    private String password;
    @Column(name = "email", unique = true, length = 115)
    private String email;
    @Column(name = "enabled")
    private boolean active = false;
    @Column(name = "role", length = 64)
    private String role;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] image;



    public User() {
    }

    public User(String userName, String password, String email, boolean active, String role, byte[] image) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.active = active;
        this.role = role;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }



}
