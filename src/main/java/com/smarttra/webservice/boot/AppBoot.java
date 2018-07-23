package com.smarttra.webservice.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Nada Gamal
 * @since 22/07/2018
 *
 * Run up the application here
 * **/

@SpringBootApplication(scanBasePackages = {"com.smarttra.webservice"})
public class AppBoot{
    public static void main(String[] args) {
         SpringApplication.run(AppBoot.class, args);
    }
}