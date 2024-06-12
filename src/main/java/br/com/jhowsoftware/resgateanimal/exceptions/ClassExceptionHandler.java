package br.com.jhowsoftware.resgateanimal.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ClassExceptionHandler 
{
   @ExceptionHandler(RegistroDuplicadoException.class)	
   public ResponseEntity<StandardError> registroDuplicado(RegistroDuplicadoException e, HttpServletRequest request)
   {
		StandardError err = new StandardError();
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage(e.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
   }
   
   @ExceptionHandler(ValorDivergenteException.class)	
   public ResponseEntity<StandardError> valorDivergente(ValorDivergenteException e, HttpServletRequest request)
   {
		StandardError err = new StandardError();
		err.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		err.setMessage(e.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err);
   }
   
   @ExceptionHandler(RegistroInexistente.class)	
   public ResponseEntity<StandardError> registroInexistente(RegistroInexistente e, HttpServletRequest request)
   {
		StandardError err = new StandardError();
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setMessage(e.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
   }
   
   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) 
   {
       Map<String, String> errors = new HashMap<>();
       ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
       return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
   }
}