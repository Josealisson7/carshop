package br.com.alisson.carshop.exception;

import br.com.alisson.carshop.response.ExceptionMessage;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<ExceptionMessage> handleBusinessException(
            BusinessException exception
    ) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        ExceptionMessage exceptionMessage = new ExceptionMessage();
        exceptionMessage.setTimestamp(LocalDateTime.now());
        exceptionMessage.setTitle("A violation of system rules occurred");
        exceptionMessage.setDetail(exception.getMessage());
        exceptionMessage.setStatus(httpStatus.value());
        return new ResponseEntity<>(exceptionMessage, httpStatus);
    }

    @ExceptionHandler(RegisterNotFoundException.class)
    public final ResponseEntity<ExceptionMessage> handleRegisterNotFoundException(
            RegisterNotFoundException exception
    ) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ExceptionMessage exceptionMessage = new ExceptionMessage();
        exceptionMessage.setTimestamp(LocalDateTime.now());
        exceptionMessage.setTitle("Failed to find record");
        exceptionMessage.setDetail(exception.getMessage());
        exceptionMessage.setStatus(httpStatus.value());
        return new ResponseEntity<>(exceptionMessage, httpStatus);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionMessage> handleDataIntegrityViolationException(
            DataIntegrityViolationException exception
    ) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionMessage exceptionMessage = new ExceptionMessage();
        exceptionMessage.setTitle("A data breach error occurred");
        exceptionMessage.setDetail("An operation violated a data integrity constraint");
        exceptionMessage.setTimestamp(LocalDateTime.now());
        exceptionMessage.setStatus(httpStatus.value());
        return new ResponseEntity<>(exceptionMessage, httpStatus);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ExceptionMessage> handleDataAccessException(
            DataAccessException exception
    ) {
        System.out.println(exception.getMessage());
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionMessage exceptionMessage = new ExceptionMessage();
        exceptionMessage.setTitle("A data access error occurred");
        exceptionMessage.setDetail("An error occurred while trying to access the database");
        exceptionMessage.setTimestamp(LocalDateTime.now());
        exceptionMessage.setStatus(httpStatus.value());
        return new ResponseEntity<>(exceptionMessage, httpStatus);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public final ResponseEntity<ExceptionMessage> handleMissingPathVariableException(
            MissingPathVariableException exception
    ) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String variableName = exception.getVariableName();
        String message = exception.getMessage();
        ExceptionMessage exceptionMessage = new ExceptionMessage();
        exceptionMessage.setTimestamp(LocalDateTime.now());
        exceptionMessage.setStatus(httpStatus.value());
        exceptionMessage.setTitle("A validation error occurred");
        exceptionMessage.setDetail("Please refer to the erros propety for aditional details");
        exceptionMessage.setError(variableName, message);
        return new ResponseEntity<>(exceptionMessage, httpStatus);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ExceptionMessage> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ExceptionMessage exceptionMessage = new ExceptionMessage();
        exceptionMessage.setTimestamp(LocalDateTime.now());
        exceptionMessage.setTitle("One or more validation errors ocurred");
        exceptionMessage.setDetail("Please refer to the erros propety for aditional details");
        exceptionMessage.setStatus(httpStatus.value());
        exception.getBindingResult().getAllErrors().forEach(objectError -> {
            String fieldName = ((FieldError) objectError).getField();
            String message = objectError.getDefaultMessage();
            exceptionMessage.setError(fieldName, message);
        });
        return new ResponseEntity<>(exceptionMessage, httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionMessage> handleException(
            Exception exception
    ) {
        System.out.println(exception.getMessage());
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionMessage exceptionMessage = new ExceptionMessage();
        exceptionMessage.setTitle("Internal server error");
        exceptionMessage.setDetail("an unexpected error occurred on the server");
        exceptionMessage.setTimestamp(LocalDateTime.now());
        exceptionMessage.setStatus(httpStatus.value());
        return new ResponseEntity<>(exceptionMessage, httpStatus);
    }
}
