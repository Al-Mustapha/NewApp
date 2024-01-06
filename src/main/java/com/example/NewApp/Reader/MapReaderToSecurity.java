package com.example.NewApp.Reader;

import com.example.NewApp.Security.UserRole;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
public class MapReaderToSecurity implements UserDetails {

    private Reader reader;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<Role> roles = reader.getRoles();
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        for(Role role: roles)
//            authorities.add(new SimpleGrantedAuthority(role.getRole()));

//    return Collections.singleton(new SimpleGrantedAuthority(reader.getRole().name()));
    return null;
    }

    @Override
    public String getPassword() {
        return reader.getPassword();
    }

    @Override
    public String getUsername() {
        return reader.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
