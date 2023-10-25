package com.example.demo.service;
import com.example.demo.model.dto.LogoutDTO;
import com.example.demo.utility.UserUtility;
import com.example.demo.utility.ValidationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class UserService {

    @Autowired
    private ValidationUtility validationUtility;

    @Autowired
    private UserUtility userUtility;



    public ResponseEntity<Map<String, String>> logout(LogoutDTO logoutDTO) {

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "L'ogout effettuato");
        userUtility.setTokenToInactive(logoutDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);

    }
}
