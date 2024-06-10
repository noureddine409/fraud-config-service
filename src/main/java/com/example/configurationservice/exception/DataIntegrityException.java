package com.example.configurationservice.exception;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DataIntegrityException extends BusinessException{

    public DataIntegrityException(String defaultMessage, String key, Object[] args) {
        super(defaultMessage, key, args);
    }

    public DataIntegrityException(String defaultMessage, Throwable cause, String key, Object[] args) {
        super(defaultMessage, cause, key, args);
    }

    public DataIntegrityException(Throwable cause, String key, Object[] args) {
        super(cause, key, args);
    }
}
