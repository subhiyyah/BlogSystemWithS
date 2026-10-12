package com.example.blogsystem.AuthService;

import com.example.blogsystem.AuthRepository.UserRepository;
import com.example.blogsystem.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;//لازم نعرفه عشان نستخدمه داخل االكلاس



    public void RegisterUser(User user ){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //اعدل عليها بعد اضافة الباسسورد
        userRepository.save(user);
    }




    public void update(Integer userId,User user){
        User oldUser=userRepository.findUserById(userId);
        if(oldUser==null){
            throw new RuntimeException("user id not found");
        }
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setBlog(user.getBlog());
        userRepository.save(oldUser);
    }
    // اعطاء الصلاحيات للادمن فقط
    public List<User> getAllUser(){
        return userRepository.findAll();
    }




    public void deleteUser(Integer userId){
        User user=userRepository.findUserById(userId);
        if(user==null){
            throw new RuntimeException("user id not found");
        }

        userRepository.delete(user);
    }





}