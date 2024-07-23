package com.eproject.Cinema.dto;

import java.sql.Time;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HourDTO {
    private Time time_from;

    private Time time_to;
    
    private double price;

}
