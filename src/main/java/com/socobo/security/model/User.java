package com.socobo.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.socobo.security.validation.validationAnnotation.Email;
import com.socobo.security.validation.validationAnnotation.MatchingPasswords;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by patrick on 04.11.16.
 */
@Entity
@Table(name="SOCOBO_USER")
@MatchingPasswords(message = "{socobo.registration.user.password.mismatch}")
public class User implements Serializable{

    private static final long serialVersionUID = -8402474030984374222L;

    @SequenceGenerator(
            name = "user_seq_generator",
            allocationSize = 1, initialValue = 1,
            sequenceName = "user_id_seq")

    @Column(name = "ID")
    @Id @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_seq_generator")
    private Long id;

    @NotNull(message = "{socobo.registration.user.username.required}")
    @NotEmpty(message = "{socobo.registration.user.username.required}")
    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;

    @NotNull(message = "{socobo.registration.user.email.required}")
    @NotEmpty(message = "{socobo.registration.user.email.required}")
    @Email(message = "{socobo.registration.user.email.format}")
    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @NotNull(message = "{socobo.registration.user.password.required}")
    @NotEmpty(message = "{socobo.registration.user.password.required}")
    @Size(min = 8, message = "{socobo.registration.user.password.length}")
    @Column(name = "PASSWORD", unique = true, nullable = false)
    private String password;

    @Transient
    @JsonIgnore
    @NotNull(message = "{socobo.registration.user.password.confirmation}")
    @NotEmpty(message = "{socobo.registration.user.password.confirmation}")
    private String repeatedPassword;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", unique = true, nullable = false)
    private Status status;

    protected User(){}

    public User(String username, String email, String password, Status status) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
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

    @JsonIgnore
    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    @JsonProperty
    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

    @JsonProperty
    public Status getStatus() {
        return status;
    }

    @JsonIgnore
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", repeatedPassword='" + repeatedPassword + '\'' +
                ", status=" + status +
                '}';
    }
//Equals and HashCode were intentionally left out
}
