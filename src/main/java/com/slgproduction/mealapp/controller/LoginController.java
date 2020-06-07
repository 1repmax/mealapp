package com.slgproduction.mealapp.controller;

        import lombok.RequiredArgsConstructor;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;
        import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("${application.context}/${application.version}")
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

}
