package com.slgproduction.mealapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginWrapper {

    private String email;
    private String password;
}
