package com.example.demo.utility;

import com.example.demo.model.AccessToken;
import com.example.demo.model.User;
import com.example.demo.model.dto.LoginResponseDTO;
import com.example.demo.model.dto.LogoutDTO;
import com.example.demo.model.dto.UserRegistrationDTO;
import com.example.demo.repository.AccessTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Component
public class UserUtility {
    @Autowired
    private AccessTokenRepository accessTokenRepository;

    @Autowired
    private ValidationUtility validationUtility;

    public User createUserFromUserRegistrationDTO(UserRegistrationDTO dto) throws NoSuchAlgorithmException {

        String hashedPassword = hashPassword(dto.getPassword());

        return User.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .password(hashedPassword)
                .isActive(true)
                .isAdmin(false).build();
    }

    public LoginResponseDTO createLoginResponseDTOFromUser(User user, String token) {
        return LoginResponseDTO.builder()
                .token(token)
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .isAdmin(user.getIsAdmin())
                .build();
    }

    public void setTokenToInactive(LogoutDTO logoutDTO) {
        if (logoutDTO == null) {
            throw new IllegalArgumentException("Il dto non può essere null");
        }

        String tokenValue = logoutDTO.getToken();
        validationUtility.validateToken(tokenValue);
        String criptedUsername = logoutDTO.getToken().substring(13, 53);

        AccessToken accessToken = accessTokenRepository.findLastByValueContaining(criptedUsername)
                .orElseThrow(() -> new IllegalArgumentException("Il token inviato non è corretto"));

        accessToken.setIsActive(false);
        accessTokenRepository.save(accessToken);
    }

    public String hashPassword(String plainText) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(plainText.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        return number.toString(16);
    }
}
