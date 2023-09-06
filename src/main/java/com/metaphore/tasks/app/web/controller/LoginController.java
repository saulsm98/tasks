package com.metaphore.tasks.app.web.controller;

import com.metaphore.tasks.app.service.LoginService;
import com.metaphore.tasks.app.web.model.AuthResponse;
import com.metaphore.tasks.app.web.model.LoginRequest;
import com.metaphore.tasks.app.web.model.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class LoginController {

     @Autowired
     private LoginService loginService;
     @PostMapping("/login")
     public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest login){
          return ResponseEntity.ok(loginService.login(login));
     }

     @PostMapping("/users")
     public ResponseEntity<AuthResponse> createUser(@RequestBody RegisterRequest register){
          return loginService.createUser(register);
     }
}
