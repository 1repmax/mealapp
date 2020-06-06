package com.slgproduction.mealapp.controller;

import com.slgproduction.mealapp.model.LoginWrapper;
import com.slgproduction.mealapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("${application.context}/${application.version}")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("loginWrapper", new LoginWrapper());
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute LoginWrapper loginWrapper) throws Exception {
        ModelAndView modelAndView = new ModelAndView("recipes");
        userService.login(loginWrapper.getEmail(), loginWrapper.getPassword());
        return modelAndView;
    }
}
