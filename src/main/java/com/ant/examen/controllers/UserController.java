package com.ant.examen.controllers;


import com.ant.examen.requests.PasswordRequest;
import com.ant.examen.responses.ImageResponse;
import com.ant.examen.responses.MessageResponse;
import com.ant.examen.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return userService.changePassword(passwordRequest);
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
