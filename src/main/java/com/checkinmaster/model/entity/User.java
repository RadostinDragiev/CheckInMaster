package com.checkinmaster.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseUser {

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "last_login_date")
    private LocalDateTime lastLoginDate;

    @Column(name = "is_active")
    private boolean isActive;
}
