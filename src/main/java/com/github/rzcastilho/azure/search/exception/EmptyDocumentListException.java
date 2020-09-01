package com.github.rzcastilho.azure.search.exception;

public class EmptyDocumentListException extends RuntimeException {

    public EmptyDocumentListException() {
        super();
    }
    public EmptyDocumentListException(String message) {
        super(message);
    }
    public EmptyDocumentListException(String message, Throwable cause) {
        super(message, cause);
    }
    public EmptyDocumentListException(Throwable cause) {
        super(cause);
    }

}
