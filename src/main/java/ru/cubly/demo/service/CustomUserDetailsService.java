package ru.cubly.demo.service;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.cubly.demo.entity.Role;
import ru.cubly.demo.entity.User;
import ru.cubly.demo.entity.CustomUserDetails;
import ru.cubly.demo.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Primary
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User domainUser = userRepository.findByUsername(username);

        Set<Role> roles = domainUser.getRoles();

        Set<GrantedAuthority> authorities = new HashSet<>();
        for(Role role: roles){
            authorities.add(new SimpleGrantedAuthority(role.name()));
        }

        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUser(domainUser);
        customUserDetails.setAuthorities(authorities);

        return customUserDetails;
    }
}

