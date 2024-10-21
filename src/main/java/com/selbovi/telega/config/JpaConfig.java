package com.selbovi.telega.config;

import com.selbovi.telega.repository.ProductRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = ProductRepository.class)
@Configuration
public class JpaConfig {
}
