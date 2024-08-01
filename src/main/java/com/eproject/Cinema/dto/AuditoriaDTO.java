package com.eproject.Cinema.dto;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditoriaDTO {
    @NotEmpty(message = "Name is required! Plss Input chacracter for the name. Thank You!")
    private String name;

    @Range(min = 0, max = 30, message = "ColNum must be greater than 0 and lower than 30")
    private int colNum;

    @Range(min = 0, max = 30, message = "RowNum must be greater than 0 and lower than 30")
    private int rowNum;
}
