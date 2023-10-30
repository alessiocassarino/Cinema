package com.example.demo.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String status;
}
