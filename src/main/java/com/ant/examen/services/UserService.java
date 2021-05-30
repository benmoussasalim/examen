package com.ant.examen.services;


import com.ant.examen.dto.ImageResponse;
import com.ant.examen.dto.MessageResponse;
import com.ant.examen.dto.PasswordRequest;
import com.ant.examen.entities.User;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

public interface UserService extends UserDetailsService {
    void createVerificationToken(User user, String token);
    public MessageResponse activateAccount(String token);
    public User findByEmail(String email);
    public MessageResponse changePassword(PasswordRequest passwordRequest);
    public MessageResponse upload(MultipartFile file, Integer idCandidat);
    public ImageResponse findImageByUser(Integer id);

}
