package com.example.lab12security.Repository;

import com.example.lab12security.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User, Integer> {

    User findUserById(int id);

    User findUserByUsername(String username);
}
