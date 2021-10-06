package br.com.otero.personalcredit.exception;

public class PessoaNaoEncontradaException extends RuntimeException {

    public PessoaNaoEncontradaException(String message) {
        super(message);
    }
}