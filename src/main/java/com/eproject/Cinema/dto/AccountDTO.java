package com.eproject.Cinema.dto;

import java.sql.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    @NotEmpty(message = "userName is required!")
    private String userName;

    @NotEmpty(message = "Password is required!")
    private String password;

    @NotEmpty(message = "Email is required!")
    @Email(message = "Invalid Email Format")
    private String email;

    private boolean gender;

    @NotEmpty(message = "Phone is required!")
    private String phone;

    @NotEmpty(message = "Address is required!")
    private String address;

    @NotEmpty(message = "Role is required!")
    private String role;

    private Date dob;

}
