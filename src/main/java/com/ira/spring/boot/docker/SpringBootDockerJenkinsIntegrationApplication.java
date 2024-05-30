package com.ira.spring.boot.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringBootDockerJenkinsIntegrationApplication {

    @GetMapping("/{name}")
    public String getMessage(@PathVariable String name) {
        return "Welcome to Jenkins Docker Integration: "+name;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDockerJenkinsIntegrationApplication.class, args);
    }
}
