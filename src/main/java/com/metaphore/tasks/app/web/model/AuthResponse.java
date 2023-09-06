package com.metaphore.tasks.app.web.model;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class AuthResponse {
    private String token;
}
