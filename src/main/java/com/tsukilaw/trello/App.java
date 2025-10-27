package com.tsukilaw.trello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        System.out.print("Starting...");
        SpringApplication.run(App.class, args);
        System.out.print("Trello Service Started.");
    }

}
