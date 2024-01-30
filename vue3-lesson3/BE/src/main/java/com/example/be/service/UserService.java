package com.example.be.service;

import com.example.be.dto.UserRequest;
import com.example.be.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    List<User> fillAll();

    User getOne (String id);

    User add (UserRequest request , MultipartFile file);

    User update (UserRequest request ,String id);

}
