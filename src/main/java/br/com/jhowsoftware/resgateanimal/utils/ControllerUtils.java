package br.com.jhowsoftware.resgateanimal.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

public class ControllerUtils 
{
	 @Data
	 @JsonInclude(JsonInclude.Include.NON_NULL)
	 public static class ResponseAPI<T> 
	 {
		 private String status;
	     private T data;
	     private String message;
	 }
	 
	 public static <T> ResponseAPI<T> responseSucesso(String status, T data, String message) 
	 {
		 ResponseAPI<T> response = new ResponseAPI<>();
	     response.setStatus(status);
	     response.setData(data);
	     response.setMessage(message);
	     return response;
	 }
	 
	 public static <T> ResponseAPI<T> responseErro(String status, T data, String errorMessage) 
	 {
		 ResponseAPI<T> response = new ResponseAPI<>();
	     response.setStatus(status);
	     response.setData(data);
	     response.setMessage(errorMessage);
	     return response;
	 }
}