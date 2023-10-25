package com.example.demo.service;
import com.example.demo.model.dto.LogoutDTO;
import com.example.demo.utility.UserUtility;
import com.example.demo.utility.ValidationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    @Autowired
    private ValidationUtility validationUtility;

    @Autowired
    private UserUtility userUtility;



    public ResponseEntity<String> logout(LogoutDTO logoutDTO) {

        userUtility.setTokenToInactive(logoutDTO);
        return ResponseEntity.status(HttpStatus.OK).body("L'ogout effettuato");

    }
}
