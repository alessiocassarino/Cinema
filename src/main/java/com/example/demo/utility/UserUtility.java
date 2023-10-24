package com.example.demo.utility;

import com.example.demo.model.User;
import com.example.demo.model.dto.UserRegistrationDTO;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Component
public class UserUtility {

    public User createUserFromUserRegistrationDTO(UserRegistrationDTO dto) throws NoSuchAlgorithmException {

        String hashedPassword = hashPassword(dto.getPassword());
        return User.builder()
                .name(dto.getNome())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .password(hashedPassword)
                .isAdmin(false).build();
    }

    private String hashPassword(String plainText) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(plainText.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        return number.toString(16);
    }
}
