package com.david.secureApi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, length = 30)
    private String username;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    @Column(nullable = false)
    private Boolean enabled;//Si está activo o no en ese momento
    //Bloquear una cuenta
    private Boolean accountNonLocked;
    //Cuando se creó el usuario
    private LocalDateTime createdAt;
    //Cuando se actualizó usuario
    private LocalDateTime updatedAt;
}
