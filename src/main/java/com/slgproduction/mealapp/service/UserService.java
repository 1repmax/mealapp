package com.slgproduction.mealapp.service;

import com.slgproduction.mealapp.model.User;
import com.slgproduction.mealapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;


    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User login(String email, String password) throws Exception {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password);
//        authenticationManager.authenticate(token);
//
//        if(token.isAuthenticated()) {
//            SecurityContextHolder.getContext().setAuthentication(token);
//        }

        String encrypted = bCryptPasswordEncoder.encode(password);
        User user = userRepository.findByEmailAddress(email);
        if(encrypted.equals(user.getPassword())) {
            return user;
        }
        throw new Exception("User not found");
    }
}
