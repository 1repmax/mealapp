package com.slgproduction.mealapp.controller;

import com.slgproduction.mealapp.model.User;
import com.slgproduction.mealapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("${application.context}/${application.version}")
@RequiredArgsConstructor
public class SignupController {

    private final UserRepository userRepository;

    @GetMapping("/signup")
    public ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView("signup");
        modelAndView.addObject("signup_user", new User());
        return modelAndView;
    }

    @PostMapping("/signup")
    public ModelAndView registerUser(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView("redirect:/index");
        userRepository.save(user);
        return modelAndView;
    }
}
