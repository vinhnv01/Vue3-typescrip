package com.example.be.controller;

import com.example.be.dto.UserRequest;
import com.example.be.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> view (){
        return new ResponseEntity(userService.fillAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne (@PathVariable ("id") String id){
        return new ResponseEntity(userService.getOne(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> add (@RequestParam("request") String request ,
                                  @RequestParam("file") MultipartFile file){
        Gson gson = new Gson();
        UserRequest userRequest = gson.fromJson(request, UserRequest.class);
        return new ResponseEntity(userService.add(userRequest,file), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@RequestBody UserRequest request ,
                                     @PathVariable ("id") String id){
        return new ResponseEntity(userService.update(request,id), HttpStatus.OK);
    }
}
