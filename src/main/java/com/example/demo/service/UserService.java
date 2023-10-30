package com.example.demo.service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.AccessToken;
import com.example.demo.model.User;
import com.example.demo.model.dto.LoginRequestDTO;
import com.example.demo.model.dto.LoginResponseDTO;
import com.example.demo.model.dto.LogoutDTO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.repository.AccessTokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utility.AccessTokenUtility;
import com.example.demo.utility.UserUtility;
import com.example.demo.utility.ValidationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private ValidationUtility validationUtility;
    @Autowired
    private UserUtility userUtility;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccessTokenUtility accessTokenUtility;
    @Autowired
    private AccessTokenRepository accessTokenRepository;

    public ResponseEntity<Map<String, String>> logout(LogoutDTO logoutDTO) {

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "L'ogout effettuato");
        userUtility.setTokenToInactive(logoutDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public ResponseEntity<Map<String, LoginResponseDTO>> login(LoginRequestDTO loginRequestDTO)
            throws NoSuchAlgorithmException {

        validationUtility.validateLoginRequestDTO(loginRequestDTO);

        String hashPassword = userUtility.hashPassword(loginRequestDTO.getPassword());
        User user = userRepository.findByEmailAndPasswordAndIsActiveTrue(loginRequestDTO.getEmail(), hashPassword)
                .orElseThrow(UserNotFoundException::new);

        AccessToken accessToken = accessTokenUtility.getAccessToken(loginRequestDTO.getEmail());
        accessToken.setUser(user);
        accessTokenRepository.save(accessToken);

        Map<String, LoginResponseDTO> responseMap = new HashMap<>();

        LoginResponseDTO loginResponseDTO = userUtility.createLoginResponseDTOFromUser(user, accessToken.getValue());
        responseMap.put("data", loginResponseDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }

    public ResponseEntity<Map<String, String>> deleteUser(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("L'userId è errato");
        }
        userRepository.updateIsActiveToFalseById(userId);
        Map<String, String> mapToReturn = new HashMap<>();
        mapToReturn.put("message", "User eliminata");
        return ResponseEntity.status(HttpStatus.OK).body(mapToReturn);
    }

    public ResponseEntity<List<UserDTO>> getAll() {
        List<User> userList = userRepository.findAllByIsAdminFalse();
        List<UserDTO> userDTOList = userUtility.createUserDTOList(userList);
        return ResponseEntity.status(HttpStatus.OK).body(userDTOList);
    }
}
