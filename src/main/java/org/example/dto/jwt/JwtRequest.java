package org.example.dto.jwt;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;

}
