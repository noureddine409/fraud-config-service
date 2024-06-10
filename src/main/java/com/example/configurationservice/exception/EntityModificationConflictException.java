package com.example.configurationservice.exception;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EntityModificationConflictException extends BusinessException{
    public EntityModificationConflictException(String defaultMessage, String key, Object[] args) {
        super(defaultMessage, key, args);
    }

    public EntityModificationConflictException(String defaultMessage, Throwable cause, String key, Object[] args) {
        super(defaultMessage, cause, key, args);
    }

    public EntityModificationConflictException(Throwable cause, String key, Object[] args) {
        super(cause, key, args);
    }
}
