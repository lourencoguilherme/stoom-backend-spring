package br.com.gml.service.exceptions;

public class AuthenticationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AuthenticationException(String msg){
        super(msg);
    }

    public AuthenticationException(String msg, Throwable cause){
        super(msg, cause);
    }
}
