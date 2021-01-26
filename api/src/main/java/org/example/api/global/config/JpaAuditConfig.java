package org.example.api.global.config;

import org.example.api.global.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaAuditConfig {
    @Bean
    public AuditorAware<User> auditorProvider() {
        return () -> Optional.of(new User(1, "testUser", "김춘수"));
    }
}
