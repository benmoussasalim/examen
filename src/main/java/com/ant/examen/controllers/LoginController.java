
package com.ant.examen.controllers;

import com.ant.examen.config.JwtUtil;
import com.ant.examen.dto.JwtRequest;
import com.ant.examen.dto.JwtResponse;
import com.ant.examen.entities.User;
import com.ant.examen.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private  JwtUtil jwtUtil;
    @PostMapping
    public JwtResponse authenticate (@RequestBody JwtRequest jwtRequest){
     Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequest.getEmail()
                ,jwtRequest.getPassword()));
     String token = jwtUtil.generateToken(auth);
     JwtResponse jwtResponse = new JwtResponse();
     jwtResponse.setToken(token);
    jwtResponse.setUser(userService.findByEmail(jwtRequest.getEmail()));
     return  jwtResponse;
    }
}
