package com.example.application;

import com.example.application.views.main.LabelService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceProvider {

    @Bean
    public LabelService labelService(ClojureAccess clojureAccess)
    {
        try {
            return (LabelService) clojureAccess.callClojure("todoapp.services", "create-label-service");
        } catch (Exception e) {
            return s -> "not the label we except...";
        }
    }
}
