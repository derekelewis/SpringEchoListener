package com.iimassociates.imageplex.springecholistener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringEchoListenerApplication {
    
    @Autowired
    private ListenerBean listenerBean;
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringEchoListenerApplication.class, args);
    }
}
