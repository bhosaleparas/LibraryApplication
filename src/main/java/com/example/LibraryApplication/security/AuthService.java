package com.example.LibraryApplication.security;

import com.example.LibraryApplication.dto.LoginRequestDto;
import com.example.LibraryApplication.dto.LoginResponseDto;
import com.example.LibraryApplication.dto.SignupResponseDto;
import com.example.LibraryApplication.entity.UserName;
import com.example.LibraryApplication.repository.UserNameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthService {


    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserNameRepository userNameRepository;
    private final PasswordEncoder passwordEncoder;


    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword())
        );

        UserName user= (UserName) authentication.getPrincipal();

        String token=authUtil.generateaccesToken(user);
        return new LoginResponseDto(token, user.getId());

    }



    public SignupResponseDto signup(LoginRequestDto signupRequestDto) {
        UserName user=userNameRepository.findByUsername(signupRequestDto.getUsername()).orElse(null);

        if(user!=null){
            throw new IllegalArgumentException("user already exists");
        }

        user=userNameRepository.save(UserName.builder()
                .username(signupRequestDto.getUsername())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .build()
        );

        return new SignupResponseDto(user.getId(),user.getUsername());

    }
}
