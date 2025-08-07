package com.example.prj;
//import io.jsonwebtoken.security.SignatureException;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;
import java.util.stream.Collectors;

//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import com.example.prj.Responsegenerator.ResponseGenerator;



@ControllerAdvice
public class ExceptionHandling {
@ExceptionHandler(Exception.class)
public ResponseEntity<?> handleException(Exception e){
	return ResponseGenerator.errorResponse(e.getMessage());
	
}
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
    List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(error->error.getDefaultMessage()).collect(Collectors.toList());

    return ResponseGenerator.errorResponse("Validation failed:"+String.join(",",errors));
}

}
