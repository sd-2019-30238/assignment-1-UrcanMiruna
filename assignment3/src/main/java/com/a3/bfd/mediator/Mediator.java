package com.a3.bfd.mediator;

import com.a3.bfd.handlers.Request;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public interface Mediator {
    void handle(Request request);
    void addHandler(Request request);
}
