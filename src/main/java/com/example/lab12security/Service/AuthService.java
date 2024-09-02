package com.example.lab12security.Service;

import com.example.lab12security.Api.ApiException;
import com.example.lab12security.Model.User;
import com.example.lab12security.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public List<User> getAllUsers() {
        return authRepository.findAll();
    }

    public void registerUser(User user) {

        user.setRole("USER");

        String Hash =new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(Hash);

        authRepository.save(user);
    }

    public void updateUser(User user,User updatedUser) {

        User u = authRepository.findUserById(user.getId());

        u.setUsername(updatedUser.getUsername());
        authRepository.save(u);
    }

    public void deleteUser(int id,User user) {
        User u = authRepository.findUserById(id);

        if (u==null){
            throw new ApiException("Wrong ID");
        }

        if(u.getId()==user.getId() || user.getRole().equals("ADMIN")){
            authRepository.delete(u);
        }else throw new ApiException("you are not the user");
    }
}
