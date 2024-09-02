package com.example.lab12security.Controller;

import com.example.lab12security.Model.Blog;
import com.example.lab12security.Model.User;
import com.example.lab12security.Service.BlogService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {
    private final BlogService blogService;

    @GetMapping("/get-all-user-blogs")
    public ResponseEntity getAllUserBlogs(@AuthenticationPrincipal User user) {
    blogService.getAllUserBlogs(user.getId());
    return ResponseEntity.status(200).body(blogService.getAllUserBlogs(user.getId()));
    }

    @PostMapping("add-blog")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user,@Valid @RequestBody Blog blog) {
        blogService.addBlog(user.getId(), blog);
        return ResponseEntity.status(200).body("blog added successfully");
    }

    @PutMapping("/update-blog/{oldblogid}")
    public ResponseEntity updateBlog(@PathVariable int oldblogid,@AuthenticationPrincipal User user,@Valid @RequestBody Blog blog) {
        blogService.updateBlog(user.getId(), oldblogid,blog);
        return ResponseEntity.status(200).body("blog updated successfully");
    }

    @DeleteMapping("/delete-blog/{blogid}")
    public ResponseEntity deleteBlog(@PathVariable int blogid,@AuthenticationPrincipal User user) {
        blogService.deleteBlog(user, blogid);
        return ResponseEntity.status(200).body("blog deleted successfully");
    }

    @GetMapping("/get-blog-by-id/{id}")
    public ResponseEntity getBlogById(@AuthenticationPrincipal User user,@PathVariable int id) {
        return ResponseEntity.status(200).body(blogService.getBlogById(id));
    }

    @GetMapping("/get-blog-by-title/{title}")
    public ResponseEntity getBlogByTitle(@AuthenticationPrincipal User user,@PathVariable String title) {
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(title));
    }
}
