package com.a3.bfd;

import com.a3.bfd.handlers.AddproductHandler;
import com.a3.bfd.handlers.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BfdApplication {
    @Autowired
    private AddproductHandler addproductHandler;

    public static void main(String[] args) {
        SpringApplication.run(BfdApplication.class, args);
    }



}
