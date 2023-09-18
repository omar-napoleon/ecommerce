package com.demo.ecommerce.handler;


import com.demo.ecommerce.dto.ExceptionResponseDto;
import com.demo.ecommerce.exception.CustomException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.MissingRequestValueException;
import org.springframework.web.server.ServerWebInputException;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionCustomHandler {

    private ExceptionResponseDto sendResponse(HttpStatus status, Throwable ex) {
        return new ExceptionResponseDto(status.getReasonPhrase(), ex.getMessage());
    }

    private ResponseEntity<ExceptionResponseDto> sendResponse(HttpStatus status, String ex) {
       return new ResponseEntity<>(new ExceptionResponseDto(status.getReasonPhrase(), ex), status);
    }

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ExceptionResponseDto> handleCustomException(CustomException ex) {
        return sendResponse(ex.getHttpStatus(), ex.getMessage());
    }


    @ExceptionHandler({NoSuchElementException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ExceptionResponseDto handleElementException(Exception ex) {
        return sendResponse(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler({
            ConstraintViolationException.class,
            MissingRequestValueException.class,
            MethodArgumentTypeMismatchException.class,
            ServerWebInputException.class,
            MissingServletRequestParameterException.class
    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponseDto handleParameterException(Exception ex) {
        return sendResponse(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler({Throwable.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponseDto handleRuntimeException(Throwable ex) {

        return sendResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<List<ExceptionResponseDto>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        // Get the error messages for invalid fields
        List<FieldError> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new FieldError(fieldError.getField(),fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        List<ExceptionResponseDto> messages = errors.stream()
                .map(error ->
                        new ExceptionResponseDto(
                                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                                error.getDefaultMessage())
                ).toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messages);
    }
}
