package com.socobo.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.socobo.shared.persistence.PersistentObject;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by patrick on 08.11.16.
 */
@Entity
@Table(name = "ROLE")
public class Role extends PersistentObject{

    private enum PermissionRole {
        USER,
        ADMIN
    }

    public static Role ADMIN = new Role(PermissionRole.ADMIN);
    public static Role USER = new Role(PermissionRole.USER);

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    private PermissionRole role;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    protected Role(){}

    private Role(PermissionRole role) {
        this.role = role;
    }

    public PermissionRole getRole() {
        return role;
    }

    public void setRole(PermissionRole role) {
        this.role = role;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + getId() +
                ", role='" + role + '\'' +
                '}';
    }
}
