package org.example.exception;

import java.sql.SQLException;

public class EmailExistsException extends Exception  {
    public EmailExistsException() {
        System.err.println("User with this email address already exists!");
    }
}
