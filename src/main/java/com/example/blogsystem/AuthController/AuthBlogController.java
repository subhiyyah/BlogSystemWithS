package com.example.blogsystem.AuthController;

import com.example.blogsystem.ApiResponse.ApiResponse;
import com.example.blogsystem.AuthService.AuthBlogService;
import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/blog")
public class AuthBlogController {
    private final AuthBlogService blogService;

    @PostMapping("/add")
    public ResponseEntity<?>addBlog(@AuthenticationPrincipal User user,@Valid @RequestBody Blog blog ){
        blogService.addBlog(user.getId(),blog);
        return ResponseEntity.status(200).body(new ApiResponse("add done"));
    }


    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@AuthenticationPrincipal User user,@PathVariable Integer blogId, @RequestBody @Valid Blog blog){
        blogService.updateBlog(user.getId(),blogId,blog);
        return ResponseEntity.status(200).body(new ApiResponse("Blog updated successfully"));
    }
    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(@AuthenticationPrincipal User user){
        List<Blog> blogs=blogService.getAll(user.getId());
        return ResponseEntity.status(200).body(blogs);
    }

    @GetMapping("/get-by-id/{blogId}")
    public ResponseEntity<?>getBlogById(@AuthenticationPrincipal User user,@PathVariable Integer blogId){
       Blog blog= blogService.getBlogById(user.getId(),blogId);
        return ResponseEntity.status(200).body(blog);
    }
    @GetMapping("/get-by-title/{blogTitle}")
    public ResponseEntity<?>getBlogByTitle(@AuthenticationPrincipal User user ,@PathVariable String blogTitle){
        Blog blog=blogService.getBlogByTitle(user.getId(),blogTitle);
        return ResponseEntity.status(200).body(blog);
    }



    @DeleteMapping("/delete/{todoId}")
    public ResponseEntity<?>deleteBlog(@AuthenticationPrincipal User user ,@PathVariable Integer blogId){
        blogService.deleteBlog(user.getId(),blogId);
        return ResponseEntity.status(200).body(new ApiResponse("delete done"));


    }






}