package com.example.lab12security.Service;

import com.example.lab12security.Api.ApiException;
import com.example.lab12security.Model.User;
import com.example.lab12security.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = authRepository.findUserByUsername(username);

        if (user == null) {
            throw new ApiException("User not found");
        }

        return user;
    }
}
