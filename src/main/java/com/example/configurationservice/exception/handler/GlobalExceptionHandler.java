package com.example.configurationservice.exception.handler;


import com.example.configurationservice.dto.ErrorResponse;
import com.example.configurationservice.exception.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
@AllArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(@NotNull HttpMessageNotReadableException ex, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .code(BAD_REQUEST.value())
                        .status(BAD_REQUEST)
                        .message(ex.getMessage().split(":")[0])
                        .build());
    }

    @ExceptionHandler(value = ElementNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleException(final ElementNotFoundException e) {
        log.warn(getMessage(e));
        return getResponseEntity(NOT_FOUND, e);
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleException(final BusinessException e) {
        log.error(getMessage(e));
        return getResponseEntity(INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler(value = ElementAlreadyExistsException.class)
    @ResponseStatus(FOUND)
    public ResponseEntity<ErrorResponse> handleException(final ElementAlreadyExistsException e) {
        log.warn(getMessage(e));
        return getResponseEntity(FOUND, e);
    }

    @ExceptionHandler(value = DataIntegrityException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleException(final DataIntegrityException e) {
        log.warn(getMessage(e));
        return getResponseEntity(FOUND, e);
    }

    @ExceptionHandler(value = EntityModificationConflictException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleException(final EntityModificationConflictException e) {
        log.warn(getMessage(e));
        return getResponseEntity(CONFLICT, e);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleException(final @NotNull IllegalArgumentException e) {
        log.warn(e.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(ErrorResponse.builder().code(BAD_REQUEST.value()).status(BAD_REQUEST).message(e.getMessage()).build());
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(SERVICE_UNAVAILABLE)
    public ResponseEntity<ErrorResponse> handleIOException(@NotNull IOException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                        .code(INTERNAL_SERVER_ERROR.value())
                        .status(INTERNAL_SERVER_ERROR)
                        .message(ex.getMessage().split(":")[0])
                        .build());
    }


    private @NotNull ResponseEntity<ErrorResponse> getResponseEntity(final HttpStatus status, final @NotNull BusinessException e) {
        if (Objects.isNull(e.getKey())) {
            // default message
            return ResponseEntity.status(status)
                    .body(ErrorResponse.builder().code(status.value()).status(status).message(e.getMessage()).build());
        }
        return ResponseEntity.status(status)
                .body(ErrorResponse.builder().code(status.value()).status(status).message(getMessage(e)).build());
    }

    // message from properties
    private @NotNull String getMessage(final @NotNull BusinessException e) {
        return messageSource.getMessage(e.getKey(), e.getArgs(), null);
    }

}


