package com.github.rzcastilho.azure.search.exception;

public class NullDocumentException extends RuntimeException {

    public NullDocumentException() {
        super();
    }
    public NullDocumentException(String message) {
        super(message);
    }
    public NullDocumentException(String message, Throwable cause) {
        super(message, cause);
    }
    public NullDocumentException(Throwable cause) {
        super(cause);
    }

}
