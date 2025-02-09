package com.my.app.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.app.user.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.charset.StandardCharsets;

@Service
public class UserService {

    private final S3Client s3Client;
    private final ObjectMapper objectMapper; // For JSON conversion

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public UserService(S3Client s3Client, ObjectMapper objectMapper) {
        this.s3Client = s3Client;
        this.objectMapper = objectMapper;
    }

    public String saveUserToS3(User user) {
        String fileName = "users/" + user.getUsername() + ".json"; // Save as JSON
        try {
            // Convert User object to JSON string
            String jsonContent = objectMapper.writeValueAsString(user);

            // Upload JSON file directly to S3
            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(fileName)
                            .contentType("application/json") // Ensure AWS recognizes it as JSON
                            .build(),
                    RequestBody.fromString(jsonContent, StandardCharsets.UTF_8)
            );

            // Return the S3 URL of the uploaded file
            return "https://" + bucketName + ".s3.amazonaws.com/" + fileName;

        } catch (Exception e) {
            throw new RuntimeException("Failed to store user data in S3", e);
        }
    }
}
