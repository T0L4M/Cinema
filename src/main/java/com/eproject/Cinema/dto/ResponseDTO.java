package com.eproject.Cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResponseDTO<T> {
	private String message;
	private T data;
	private T errors;
	private int code;
}