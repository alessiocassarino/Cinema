package com.example.demo.model.dto;



import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserRegistrationDTO {

    private String nome;
    private String surname;
    private String email;
    private String phoneNumber;
    private String password;
}
