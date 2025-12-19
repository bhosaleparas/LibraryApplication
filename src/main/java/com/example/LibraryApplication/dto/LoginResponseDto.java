package com.example.LibraryApplication.dto;

import lombok.Data;

@Data
public class LoginResponseDto {

     String jwt;
     Long userId;

    public LoginResponseDto(String token, Long id) {
        this.jwt = token;
        this.userId = id;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
