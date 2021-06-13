package com.ant.examen.responses;

import com.ant.examen.entities.User;
import lombok.Data;

@Data
public class JwtResponse {
    private  String token;
    private User user;

}
