package com.ant.examen.controllers;

import com.ant.examen.dto.ImageResponse;
import com.ant.examen.dto.MessageResponse;
import com.ant.examen.dto.PasswordRequest;
import com.ant.examen.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired

    private UserService userService;

    @PatchMapping
    public MessageResponse changePassword(@RequestBody PasswordRequest passwordRequest) {
        return  userService.changePassword(passwordRequest);
    }

    @PostMapping("/{id}")
    public MessageResponse upload(@RequestParam("file") MultipartFile file, @PathVariable Integer id) {
        return userService.upload(file, id);
    }

    @GetMapping("/{id}")
    public ImageResponse findByCandidat(@PathVariable Integer id) {
        return userService.findImageByUser(id);
    }

}
