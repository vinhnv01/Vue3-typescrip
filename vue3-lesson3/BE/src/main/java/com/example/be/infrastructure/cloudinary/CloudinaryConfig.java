package com.example.be.infrastructure.cloudinary;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CloudinaryConfig {

    @Value("${cloudinary.cloud-name}")
    private String CLOUDINARY_NAME;

    @Value("${cloudinary.api-key}")
    private String CLOUDINARY_KEY;

    @Value("${cloudinary.api-secret}")
    private String CLOUDINARY_API_SECRET;

    @Bean
    public Cloudinary getCloudinary(){
        Map config = new HashMap();
        config.put("cloud_name", CLOUDINARY_NAME);
        config.put("api_key", CLOUDINARY_KEY);
        config.put("api_secret", CLOUDINARY_API_SECRET);
        config.put("secure", true);
        return new Cloudinary(config);
    }
}
