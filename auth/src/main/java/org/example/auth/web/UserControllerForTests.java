package org.example.auth.web;

import org.example.auth.domain.Event;
import org.example.auth.domain.UserDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerForTests {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final UserDetailsManager userDetailsManager;

    public UserControllerForTests(KafkaTemplate<String, Object> kafkaTemplate,
                                  UserDetailsManager userDetailsManager) {
        this.kafkaTemplate = kafkaTemplate;
        this.userDetailsManager = userDetailsManager;
    }

    @GetMapping("/users")
    void createUser() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails user = User.builder()
                .username("admin")
                .password("admin")
                .passwordEncoder(encoder::encode)
                .roles("ADMIN")
                .build();

        userDetailsManager.createUser(user);

        var userCreated = new Event("UserCreated",
                new UserDto(user.getUsername(), user.getAuthorities().toString()));

        kafkaTemplate.send("accounts-stream", userCreated);
        kafkaTemplate.flush();
    }
}
