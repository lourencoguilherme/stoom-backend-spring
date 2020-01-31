package br.com.gml.service.exceptions;

public class SpecificationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SpecificationException(String msg){
        super(msg);
    }

    public SpecificationException(String msg, Throwable cause){
        super(msg, cause);
    }
}
