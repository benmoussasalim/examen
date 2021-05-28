package com.ant.examen.services.imp;

import com.ant.examen.dto.ImageResponse;
import com.ant.examen.dto.MessageResponse;
import com.ant.examen.dto.PasswordRequest;
import com.ant.examen.entities.Candidat;
import com.ant.examen.entities.User;
import com.ant.examen.entities.VerificationToken;
import com.ant.examen.repository.UserRepository;
import com.ant.examen.repository.VerificationTokenRepository;
import com.ant.examen.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private VerificationTokenRepository tokenRepository;
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Value("${upload-dir}")
    private String uploadDirectory;
    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        myToken.setEnabled(true);
        tokenRepository.save(myToken);
    }

    @Override
    public MessageResponse activateAccount(String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null) {
            return new MessageResponse(false, "Attention", "Token invalid");

        }
        if (verificationToken.isEnabled()){
            return  new MessageResponse(false,"Attention","Token déja untilisée");
        }
        verificationToken.setEnabled(false);
        tokenRepository.save(verificationToken);
        User user = verificationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        return new MessageResponse(true, "Succés", "Compte activée");

    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findOneByEmail(email).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findOneByEmail(username).orElse(null);
    }
    @Override
    public MessageResponse changePassword(PasswordRequest passwordRequest) {

        User user = userRepository.findById(passwordRequest.getId()).orElse(null);
        if(user ==null) {
            return new MessageResponse(false, "Attention", "Utilisateur n'existe pas");
        }
        boolean matched = passwordEncoder.matches(passwordRequest.getOldPassword(), user.getPassword());
        if(!matched) {
            return new MessageResponse(false, "Attention", "Ancien mot de passe incorrect");
        }


        String cryptedPassword = passwordEncoder.encode(passwordRequest.getNewPassword());

        user.setPassword(cryptedPassword);

        userRepository.save(user);

        return new MessageResponse(true, "Succès", "Opération effectuée");
    }



    @Override
    public MessageResponse upload(MultipartFile file, Integer idCandidat) {

        User user= userRepository.getOne(idCandidat);





            Path rootLocation = Paths.get(uploadDirectory);

            try {
                Files.copy(file.getInputStream(), rootLocation.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
               user.setImage(file.getOriginalFilename());
               userRepository.save(user);
            } catch (IOException e) {

                e.printStackTrace();
                return new MessageResponse(false, "Attention", "Opération non effectuée");
            }



        return new MessageResponse(true, "Succès", "Opération effectuée");
    }


    @Override
    public ImageResponse findImageByUser(Integer id) {


        User user= userRepository.findById(id).orElse(null);



        Path rootLocation = Paths.get(uploadDirectory);

            Path file = rootLocation.resolve(user.getImage());
            try {
                byte[] imgByte = Files.readAllBytes(file);
                ImageResponse imgResponse = new ImageResponse();

                imgResponse.setPicture(imgByte);
                return  imgResponse;

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // resources.add(resource);



        return null;
    }
}