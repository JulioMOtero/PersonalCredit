package br.com.otero.parkinglot.exception;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2348601280982409984L;

    public NotFoundException(String message) {
        super(message);
    }
}


