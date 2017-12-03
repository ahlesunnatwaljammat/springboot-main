package edu.learn.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

@Data
@ToString(exclude = "password")
@Entity
@Table(name = "USER", uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
public class User implements Serializable, UserDetails {
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    private @Id @GeneratedValue Long id;
    private String username;
    private @JsonIgnore String password;

    public User(){
    }

    public User(String username, String password) {
        this.username = username;
        this.password = PASSWORD_ENCODER.encode(password);
    }

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    @Transient
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("USER", "ADMIN");
    }

    @Transient
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Transient
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Transient
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
