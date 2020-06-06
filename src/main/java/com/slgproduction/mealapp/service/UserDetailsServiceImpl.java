package com.slgproduction.mealapp.service;

import com.slgproduction.mealapp.model.User;
import com.slgproduction.mealapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        User user = userRepository.findByEmailAddress(emailAddress);
        if(user == null) throw new Exception("User with " + emailAddress + " not found");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), null);
    }
}
