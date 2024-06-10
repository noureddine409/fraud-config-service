package com.example.configurationservice.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ElementAlreadyExistsException extends BusinessException {
    public ElementAlreadyExistsException(String defaultMessage, String key, Object[] args) {
        super(defaultMessage, key, args);
    }

    public ElementAlreadyExistsException(String defaultMessage, Throwable cause, String key, Object[] args) {
        super(defaultMessage, cause, key, args);
    }

    public ElementAlreadyExistsException(Throwable cause, String key, Object[] args) {
        super(cause, key, args);
    }
}
