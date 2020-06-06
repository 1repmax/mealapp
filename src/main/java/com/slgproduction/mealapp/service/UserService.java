package com.slgproduction.mealapp.service;

import com.slgproduction.mealapp.model.User;
import com.slgproduction.mealapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User login(String email, String password) throws Exception {
        User user = userRepository.findByEmailAddress(email);

        if(user == null) {
            throw new Exception("User not found");
        }
        return user;
    }
}
