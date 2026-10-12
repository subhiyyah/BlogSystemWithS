package com.example.blogsystem.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@org.springframework.context.annotation.Configuration
@EnableWebSecurity
public class Configuration {
    @Bean//من غير ال bean   -ما هتشتغل
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http)throws Exception{
        return http
                .csrf(csrf -> csrf.disable())//disable يتأكد من الحماية من هجمات CSRF بتقفيل الطريق المؤدي لها
                //السلسلة الثانية
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))//هنا نحدد سياسة إنشاء الجلسة، إذا كانت مطلوبة فقط



                //filter chain

                .authorizeHttpRequests(auth -> auth//permitAll الصلاحية للجميع
                                .requestMatchers(HttpMethod.POST,"/api/v1/user/register").permitAll()


                                .anyRequest().authenticated()//جميع الطلب الأخرى تحتاج إلى مصادقة

                )
                .logout(logout -> logout
                                .logoutUrl("/api/v1/user/logout")//هنا حددت رابط تسجيل الخروج)
                                .invalidateHttpSession(true)
                        //حذف السشن الحالية
                )
                //هنا حددت انو عند تسجيل الخروج يتم إبطال الجلسة الحالية
                .httpBasic( basic -> {})
                .build();
    }
}