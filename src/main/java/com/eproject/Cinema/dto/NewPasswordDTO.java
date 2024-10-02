package com.eproject.Cinema.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class NewPasswordDTO {
  @NotEmpty(message = "Email should be could not empty")
  private String email;

  @NotEmpty(message = "Password should be could not empty")
  private String newPassword;
}
