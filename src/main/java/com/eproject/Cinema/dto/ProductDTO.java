package com.eproject.Cinema.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @NotEmpty(message = "Name is required!")
    private String name;

    private MultipartFile image;

    @NotEmpty(message = "Type is required!")
    private String type;

    private double price;

    private boolean status;

}
