package com.example.LibraryApplication.security;

import com.example.LibraryApplication.repository.UserNameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomeUserDetailsService implements UserDetailsService {

    private final UserNameRepository userNameRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         return userNameRepository.findByUsername(username).orElseThrow();
    }
}
