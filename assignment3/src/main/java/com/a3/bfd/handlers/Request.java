package com.a3.bfd.handlers;

import org.springframework.stereotype.Component;

@Component
public interface Request {
    void handle(String type);
    String getType();
}
