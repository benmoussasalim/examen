package com.ant.examen.requests;

import lombok.Data;

@Data
public class JwtRequest {
    private String email;
    private String password;
}
