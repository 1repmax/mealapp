package com.slgproduction.mealapp.service;

import com.slgproduction.mealapp.model.Authority;
import com.slgproduction.mealapp.model.User;
import com.slgproduction.mealapp.repository.AuthorityRepository;
import com.slgproduction.mealapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public void saveUser(User user) {
        Optional<Authority> authority = authorityRepository.findById(1L);
        user.setAuthority(authority.orElse(null));
        userRepository.save(user);
    }

    public User getSessionUser(String username){
        return  userRepository.findByUsername(username);
    }

//    public User login(String email, String password) throws Exception {
//        User user = userRepository.findByEmailAddress(email);
//
//        if(user == null) {
//            throw new Exception("User not found");
//        }
//        return user;
//    }
}
