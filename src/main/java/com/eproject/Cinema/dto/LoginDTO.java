package com.eproject.Cinema.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDTO {
      @NotEmpty(message = "Username should be could not empty")
      private String userName;

      @NotEmpty(message = "Password should be could not empty")
      private String password;
}
