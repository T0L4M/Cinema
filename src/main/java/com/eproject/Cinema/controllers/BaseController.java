package com.eproject.Cinema.controllers;

import java.util.ArrayList;
import java.util.List;

import com.eproject.Cinema.dto.ErrorDTO;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class BaseController {

    /**
     * Get errors
     * 
     * @param br
     * @return
     */
    public List<ErrorDTO> getErrors(BindingResult br) {
        List<ErrorDTO> errors = new ArrayList<>();
        for (FieldError err : br.getFieldErrors()) {
            errors.add(new ErrorDTO(err.getField(), err.getDefaultMessage()));
        }

        return errors;

    }
}
