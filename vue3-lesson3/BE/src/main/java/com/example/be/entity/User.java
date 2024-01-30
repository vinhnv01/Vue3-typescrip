package com.example.be.entity;

import com.example.be.infrastructure.Gender;
import com.example.be.infrastructure.Role;
import com.example.be.infrastructure.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String avatar;

    private String name;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Long dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String address;

    @Enumerated(EnumType.STRING)
    private Status status;
}
