package com.github.rzcastilho.azure.search.exception;

public class IndexNotDetectException extends RuntimeException {

    public IndexNotDetectException() {
        super();
    }
    public IndexNotDetectException(String message) {
        super(message);
    }
    public IndexNotDetectException(String message, Throwable cause) {
        super(message, cause);
    }
    public IndexNotDetectException(Throwable cause) {
        super(cause);
    }

}
