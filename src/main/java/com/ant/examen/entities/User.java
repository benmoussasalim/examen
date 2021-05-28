package com.ant.examen.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable , UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String image;
    private String email;
    private String password;
    private String adresse;
    private String telephone;
    private boolean enabled;
    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private VerificationToken verificationToken;
@JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(this instanceof Admin)  {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
       else if(this instanceof Candidat)  {
            authorities.add(new SimpleGrantedAuthority("ROLE_CANDIDAT"));
        }
        else if(this instanceof Entreprise)  {
            authorities.add(new SimpleGrantedAuthority("ROLE_ENTREPRISE"));
        }
        return authorities;
    }
@JsonIgnore
    @Override
    public String getUsername() {
        return email;
    }
@JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
@JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
@JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
