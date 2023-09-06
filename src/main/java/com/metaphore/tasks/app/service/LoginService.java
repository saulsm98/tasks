package com.metaphore.tasks.app.service;

import com.metaphore.tasks.app.domain.Entity.User;
import com.metaphore.tasks.app.facade.UserFacade;
import com.metaphore.tasks.app.web.model.AuthResponse;
import com.metaphore.tasks.app.web.model.LoginRequest;
import com.metaphore.tasks.app.web.model.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class LoginService {
    private final UserFacade userFacade;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest login)  {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        UserDetails user = userFacade.findByUsername(login.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder().token(token).build();
    }

    public ResponseEntity<AuthResponse> createUser(RegisterRequest register){
        User user = User.builder()
                .username(register.getUsername())
                .password(register.getPassword())
                .name(register.getName())
                .lastname(register.getLastname())
                .nacionality(register.getNacionality())
                .role(register.getRole())
                .build();

        userFacade.save(user);

        return ResponseEntity.ok(new AuthResponse(jwtService.getToken(user)));
    }
}
