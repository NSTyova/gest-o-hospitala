package com.example.teste.web.exception;

public class StorageException extends RuntimeException{

	
	private static final long serialVersionUID = -9057255428754813713L;

	public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
