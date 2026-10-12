package com.example.blogsystem.AuthService;

import com.example.blogsystem.AuthRepository.BlogRepository;
import com.example.blogsystem.AuthRepository.UserRepository;
import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthBlogService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;


    public List<Blog> getAll(Integer userId){
        User user =userRepository.findUserById(userId);

        if(user==null){
            throw new RuntimeException("user id not found");
        }
        return blogRepository.findAll();
    }

    public void addBlog(Integer userId, Blog blog){
        User user =userRepository.findUserById(userId);
        if(user==null){
            throw new RuntimeException("user id not found");
        }
        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void updateBlog(Integer userId , Integer blogId , Blog blog){
        User user =userRepository.findUserById(userId);
        if(user==null){
            throw new RuntimeException("user id not found");
        }
        Blog oldBlog=blogRepository.findBlogById(blogId);
        if(oldBlog==null){
            throw new RuntimeException("bold id not found");
        }
    }

    public void deleteBlog(Integer userId, Integer blogId){
        User user= userRepository.findUserById(userId);
        if(user==null){
            throw new RuntimeException("user id not found");

        }

        Blog blog=blogRepository.findBlogById(blogId);
        if(blog==null){
            throw new RuntimeException("blog id not found");
        }

        if(blog.getUser().getId()!=userId){
            throw new RuntimeException("something error");
        }

        blogRepository.delete(blog);
    }

    public Blog getBlogById(Integer userId,Integer blogId){
        User user=userRepository.findUserById(userId);
        if(user==null){
            throw new RuntimeException("user id not found");
        }

        Blog blog=blogRepository.findBlogById(blogId);
        if(blog==null){
            throw new RuntimeException("blog id not found");
        }

        if(blog.getUser().getId()!=userId){
            throw new RuntimeException("Something error");
        }
        return blog;
    }
    public Blog getBlogByTitle(Integer userId,String title){
        User user=userRepository.findUserById(userId);
        if(user==null){
            throw new RuntimeException("user id not found");
        }


        Blog blog=blogRepository.findBlogByTitle(title);
        if(blog==null){
            throw new RuntimeException("blog title not found");
        }


        if(blog.getUser().getId()!=userId){
            throw new RuntimeException("Something error");
        }
        return blog;

    }

    public List<Blog>getAllBlog(){
        return blogRepository.findAll();
    }


}