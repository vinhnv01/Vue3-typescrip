package com.example.be.dto;

import com.example.be.infrastructure.Gender;
import com.example.be.infrastructure.Role;
import com.example.be.infrastructure.Status;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequest {

    private String avatar;

    private String name;

    private String email;

    private Role role;

    private Long dateOfBirth;

    private Gender gender;

    private String address;

    private Status status;
}
