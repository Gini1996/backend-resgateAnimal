package br.com.jhowsoftware.resgateanimal.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_IMPLEMENTED.value());
		err.setError("Erro de implementação: ");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(err);
   }
   
   @ExceptionHandler(ValorDivergenteException.class)	
   public ResponseEntity<StandardError> valorDivergente(ValorDivergenteException e, HttpServletRequest request)
   {
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		err.setError("Erro de implementação: ");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err);
   }
}