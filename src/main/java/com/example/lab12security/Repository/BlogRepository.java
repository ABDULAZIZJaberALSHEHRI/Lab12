package com.example.lab12security.Repository;

import com.example.lab12security.Model.Blog;
import com.example.lab12security.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {

    Blog findBlogById(int id);

    Blog findBlogByTitle(String title);


    List<Blog> findBlogByUser(User user);
}
