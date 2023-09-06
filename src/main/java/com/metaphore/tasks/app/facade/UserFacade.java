package com.metaphore.tasks.app.facade;

import com.metaphore.tasks.app.domain.Entity.User;
import com.metaphore.tasks.app.domain.Repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional
@Log4j2
public class UserFacade {
    @Autowired
    private UserRepository repository;

    public User save(User user){ return this.repository.save(user); }

    public Optional<User> findByUsername(String username){
        log.info("hola");
        return this.repository.findByUsername(username); }
}
