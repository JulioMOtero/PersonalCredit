package br.com.otero.parkinglot.exception;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 994386462197741938L;

    public BusinessException(String mensagem) {
        super(mensagem);
    }
}
