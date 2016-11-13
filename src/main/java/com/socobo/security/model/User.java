package com.socobo.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.socobo.security.validation.validationAnnotation.Email;
import com.socobo.security.validation.validationAnnotation.MatchingPasswords;
import com.socobo.shared.persistence.PersistentObject;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

/**
 * Created by patrick on 04.11.16.
 */
@Entity
@Table(name="SOCOBO_USER")
@MatchingPasswords(message = "{socobo.registration.user.password.mismatch}")
public class User extends PersistentObject implements Serializable{

    @NotNull(message = "{socobo.registration.user.username.required}")
    @NotEmpty(message = "{socobo.registration.user.username.required}")
    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;

    @NotNull(message = "{socobo.registration.user.email.required}")
    @NotEmpty(message = "{socobo.registration.user.email.required}")
    @Email(message = "{socobo.registration.user.email.format}")
    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @JsonIgnore
    @NotNull(message = "{socobo.registration.user.password.required}")
    @NotEmpty(message = "{socobo.registration.user.password.required}")
    @Size(min = 8, message = "{socobo.registration.user.password.length}")
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Transient
    @JsonIgnore
    @NotNull(message = "{socobo.registration.user.password.confirmation}")
    @NotEmpty(message = "{socobo.registration.user.password.confirmation}")
    private String repeatedPassword;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private Status status;

    @JsonIgnore
    @Column(name = "CREATED", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @JsonIgnore
    @Column(name = "UPDATED", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "USERS_ROLES",
                joinColumns = @JoinColumn(name = "USER_ID"),
                inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles = new HashSet<>();

    protected User(){}

    public User(String username, String email, String password, Status status) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public boolean hasRole(Role role){
        return roles.contains(role);
    }

    public Set<Role> listRoles(){
        return Collections.unmodifiableSet(this.roles);
    }

    public void activate(){
        this.setStatus(Status.ACTIVE);
    }

    public void deactivate(){
        this.setStatus(Status.INACTIVE);
    }

    public void lock(){
        this.setStatus(Status.LOCKED);
    }

    public void markDeleted(){
        this.setStatus(Status.DELETED);
    }

    public void addRole(Role role){
        this.roles.add(role);
        Collection<User> users = role.getUsers();
        if(Objects.isNull(role.getUsers())){
            users = new ArrayList<>();
        }
        users.add(this);
        role.setUsers(users);
    }

    public void deleteRole(Role role){
        this.roles.remove(role);
        Collection<User> users = role.getUsers();
        if(!Objects.isNull(role.getUsers())){
            users.remove(this);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", repeatedPassword='" + repeatedPassword + '\'' +
                ", status=" + status +
                ", created=" + created +
                ", lastUpdated=" + lastUpdated +
                ", roles=" + roles +
                '}';
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

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty
    public Date getCreated() {
        return created;
    }

    @JsonIgnore
    public void setCreated(Date created) {
        this.created = created;
    }

    @JsonProperty
    public Date getLastUpdated() {
        return lastUpdated;
    }

    @JsonIgnore
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
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

    @PrePersist
    protected void beforeCreation(){
        this.lastUpdated = this.created = new Date();
    }

    @PreUpdate
    protected void beforeUpdate(){
        this.lastUpdated = new Date();
    }

}
