package com.thepuar.excelmixer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExcelMixerApp implements CommandLineRunner {

    public static void main(String args[]){
        SpringApplication.run(ExcelMixerApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
