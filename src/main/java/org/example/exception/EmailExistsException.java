package org.example.exception;

public class EmailExistsException extends Exception  {
    public EmailExistsException() {
        System.err.println("User with this email address already exists!");
    }
}
