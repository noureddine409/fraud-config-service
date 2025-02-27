package com.example.configurationservice.exception;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BusinessException extends RuntimeException {

    private String key;
    private Object[] args;

    public BusinessException(String defaultMessage, String key, Object[] args) {
        super(defaultMessage);
        this.key = key;
        this.args = args;
    }

    public BusinessException(String defaultMessage, Throwable cause, String key, Object[] args) {
        super(defaultMessage, cause);
        this.key = key;
        this.args = args;
    }

    public BusinessException(final Throwable cause, final String key, final Object[] args) {
        super(cause);
        this.key = key;
        this.args = args;
    }


}
