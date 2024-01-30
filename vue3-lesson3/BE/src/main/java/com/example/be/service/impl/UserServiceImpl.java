package com.example.be.service.impl;

import com.example.be.dto.UserRequest;
import com.example.be.entity.User;
import com.example.be.infrastructure.cloudinary.CloudinaryService;
import com.example.be.repository.UserRepository;
import com.example.be.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final CloudinaryService cloudinaryService;

    @Override
    public List<User> fillAll() {
        return userRepository.findAllByOrderByDateOfBirthDesc();
    }

    @Override
    public User getOne(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User add(UserRequest request , MultipartFile file) {
        String url = cloudinaryService.upload(file);
        User add = new User();
        add.setAddress(request.getAddress());
        add.setEmail(request.getEmail());
        add.setPassword("2000");
        add.setGender(request.getGender());
        add.setAvatar(url);
        add.setRole(request.getRole().ROLE_USER);
        add.setDateOfBirth(request.getDateOfBirth());
        add.setStatus(request.getStatus());
        add.setName(request.getName());
        return userRepository.save(add);
    }

    @Override
    public User update(UserRequest request , String id) {
        User update = userRepository.findById(id).get();
        update.setAddress(request.getAddress());
        update.setEmail(request.getEmail());
        update.setPassword("2000");
        update.setGender(request.getGender());
        update.setRole(request.getRole().ROLE_USER);
        update.setDateOfBirth(request.getDateOfBirth());
        update.setStatus(request.getStatus());
        update.setName(request.getName());
        return userRepository.save(update);
    }
}
