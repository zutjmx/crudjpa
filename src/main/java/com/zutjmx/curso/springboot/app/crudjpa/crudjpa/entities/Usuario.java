package com.zutjmx.curso.springboot.app.crudjpa.crudjpa.entities;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, length = 20)
    @NotBlank(message = "Username no puede estar vacío")
    @Size(min = 4, max = 20, message = "Username debe tener entre 4 y 20 caracteres")
    private String username;

    @NotBlank(message = "Password no puede estar vacío")
    @Size(min = 6, message = "Password debe tener al menos 6 caracteres")
    private String password;
    
    @ManyToAny
    @JoinTable(
        name = "usuarios_roles",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"),
        uniqueConstraints = {
            @UniqueConstraint(
                columnNames = {"usuario_id", "role_id"}
            )
        }
    )
    private List<Role> roles;

    private boolean enabled;

    @Transient
    private boolean admin;

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
