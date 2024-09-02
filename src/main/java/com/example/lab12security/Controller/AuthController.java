package com.example.lab12security.Controller;

import com.example.lab12security.Model.User;
import com.example.lab12security.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/get-all-users")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.status(200).body(authService.getAllUsers());
    }

    @PostMapping("/register-user")
    public ResponseEntity registerUser(@Valid @RequestBody User user){
        authService.registerUser(user);
        return ResponseEntity.status(200).body("user registered successfully");
    }

    @PutMapping("/update-user")
    public ResponseEntity updateUser(@AuthenticationPrincipal User user, @Valid @RequestBody User updatedUser){
        authService.updateUser(user, updatedUser);
        return ResponseEntity.status(200).body("user updated successfully");
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity deleteUser( @PathVariable int id,@AuthenticationPrincipal User user){
        authService.deleteUser(id, user);
        return ResponseEntity.status(200).body("user deleted successfully");
    }
}
