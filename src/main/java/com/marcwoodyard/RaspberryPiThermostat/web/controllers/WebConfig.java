package com.marcwoodyard.RaspberryPiThermostat.web.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("thermostat");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/settings").setViewName("settings");
    }

}
