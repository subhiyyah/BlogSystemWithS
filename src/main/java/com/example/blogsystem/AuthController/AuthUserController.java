package com.example.blogsystem.AuthController;

import com.example.blogsystem.ApiResponse.ApiResponse;
import com.example.blogsystem.AuthService.UserService;
import com.example.blogsystem.Model.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class AuthUserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid User user){
        userService.RegisterUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User registered successfully"));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<?>updateUser(@PathVariable Integer userId,@RequestBody @Valid User user){
        userService.update(userId,user);
        return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
    }

    @GetMapping("/get-all")
    public ResponseEntity<?>getAllUser(){
        List<User> userList=userService.getAllUser();
        return ResponseEntity.status(200).body(userList);
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?>deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
    }




}