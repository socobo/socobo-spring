package com.socobo.security.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by patrick on 08.11.16.
 */
@Entity
@Table(name = "ROLE")
public class Role {

    private static final Role ADMIN = new Role(PermissionRole.ADMIN);
    private static final Role USER = new Role(PermissionRole.USER);

    @SequenceGenerator(
            name = "role_seq_generator",
            allocationSize = 1, initialValue = 1,
            sequenceName = "role_id_seq")

    @Column(name = "ID")
    @Id @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_seq_generator")
    private Long id;

    @Enumerated
    @Column(name = "ROLE", nullable = false)
    private PermissionRole role;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    protected Role(PermissionRole role) {
        this.role = role;
    }

    public static Role getAdminRole(){
        return ADMIN;
    }

    public static Role getUserRole(){
        return USER;
    }

    public PermissionRole getRole() {
        return role;
    }

    public Collection<User> getUsers() {
        return users;
    }
}
