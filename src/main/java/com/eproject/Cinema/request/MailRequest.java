package com.eproject.Cinema.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MailRequest {
    @NotEmpty
    private String email;

}
