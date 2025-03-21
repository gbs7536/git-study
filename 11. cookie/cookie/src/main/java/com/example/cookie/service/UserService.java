// UserService.java

package com.example.cookie.service;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.LoginRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    //login logic
    public void login(
        LoginRequest loginRequest,
        HttpServletResponse httpServletResponse
    ){
        var id = loginRequest.getId();
        var pw = loginRequest.getPassword();

        var optionalUser = userRepository.findByName(id);

        if(optionalUser.isPresent()){
            var userDto = optionalUser.get();

            if(userDto.getPassword().equals(pw)){
                // cookie 해당 정볼르 저장
                var cookie = new Cookie("authorization-cookie",userDto.getId());
                cookie.setDomain("localhost");
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                cookie.setSecure(true); // https 에서만 사용되도록 설정
                cookie.setMaxAge(-1);   // session

                httpServletResponse.addCookie(cookie);
            }
        }else{
            throw new RuntimeException("User Not Found");
        }

    }
}
