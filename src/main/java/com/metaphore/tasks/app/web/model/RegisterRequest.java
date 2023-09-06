package com.metaphore.tasks.app.web.model;

import com.metaphore.tasks.app.util.enums.Roles;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String nacionality;
    private Roles role;

}
