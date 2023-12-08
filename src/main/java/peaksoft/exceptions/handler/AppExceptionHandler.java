package peaksoft.exceptions.handler;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import peaksoft.exceptions.AlreadyExistsException;
import peaksoft.exceptions.BadCredentialsException;
import peaksoft.exceptions.NotFoundException;

import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> handleNotFoundException(@NotNull NotFoundException exception){
        Map<String,String> errors = new HashMap<>();
        errors.put("message",exception.getMessage());
        return errors;
    }
    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String,String> handleAlreadyExistsException(@NotNull AlreadyExistsException exception){
        Map<String,String> errors = new HashMap<>();
        errors.put("message",exception.getMessage());
        return errors;
    }
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String,String> handleBadCredentials(@NotNull BadCredentialsException exception){
        Map<String,String> errors = new HashMap<>();
        errors.put("message",exception.getMessage());
        return errors;
    }

}
