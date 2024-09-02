package com.example.lab12security.Service;

import com.example.lab12security.Api.ApiException;
import com.example.lab12security.Model.Blog;
import com.example.lab12security.Model.User;
import com.example.lab12security.Repository.AuthRepository;
import com.example.lab12security.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;


    public List<Blog> getAllUserBlogs(Integer userId) {
        User u =authRepository.findUserById(userId);
        return blogRepository.findBlogByUser(u);
    }

    public void addBlog(int userID,Blog blog) {
        User u = authRepository.findUserById(userID);
        blog.setUser(u);
        blogRepository.save(blog);
    }

    public void updateBlog(int userID,int oldBlogID,Blog blog) {
        User u = authRepository.findUserById(userID);
        Blog oldBlog = blogRepository.findBlogById(oldBlogID);

        if (oldBlog == null){
            throw new ApiException("Blog not found");
        } else if (oldBlog.getUser().getId()!=u.getId()) {
            throw new ApiException("User id mismatch");
        }
        oldBlog.setTitle(blog.getTitle());
        oldBlog.setBody(blog.getBody());
        oldBlog.setUser(u);
        blogRepository.save(oldBlog);
    }

    public void deleteBlog(User userID,int blogID) {
        User u = authRepository.findUserById(userID.getId());
        Blog blog = blogRepository.findBlogById(blogID);
        if (blog==null){
            throw new ApiException("Blog not found");
        }else if (blog.getUser().getId()==u.getId() || u.getRole().equalsIgnoreCase("ADMIN")) {
            blogRepository.delete(blog);
        }else throw new ApiException("User id mismatch");
    }

    public Blog getBlogById(int blogID) {
        Blog blog = blogRepository.findBlogById(blogID);
        if (blog==null){
            throw new ApiException("Blog not found");
        }
        return blog;
    }

    public Blog getBlogByTitle(String title) {
        Blog blog = blogRepository.findBlogByTitle(title);
        if (blog==null){
            throw new ApiException("Blog not found");
        }
        return blog;
    }
}
