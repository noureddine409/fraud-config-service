package com.example.configurationservice.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ElementNotFoundException extends BusinessException {

    public ElementNotFoundException(String defaultMessage, String key, Object[] args) {
        super(defaultMessage, key, args);
    }

    public ElementNotFoundException(String defaultMessage, Throwable cause, String key, Object[] args) {
        super(defaultMessage, cause, key, args);
    }

    public ElementNotFoundException(Throwable cause, String key, Object[] args) {
        super(cause, key, args);
    }
}
