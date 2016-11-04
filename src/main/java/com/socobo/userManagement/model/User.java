package com.socobo.userManagement.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by patrick on 04.11.16.
 */
@Entity
@Table(name="SOCOBO_USER")
public class User implements Serializable{

    private static final long serialVersionUID = -8402474030984374222L;

    @SequenceGenerator(name = "user_seq_generator", allocationSize = 1, initialValue = 1, sequenceName = "user_id_seq")

    @Column(name = "ID")
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_generator")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private  String password;

    protected User(){}

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Equals and HashCode were intentionally left out
}
