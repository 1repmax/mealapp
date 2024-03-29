package com.slgproduction.mealapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("${application.context}/${application.version}")
public class IndexController {

    @GetMapping("/index")
    public ModelAndView getIndex() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

}
