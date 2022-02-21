package com.opensource.tools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * project spring boot starter
 *
 * @author yangzai
 * @date 2022-02-16
 */
@SpringBootApplication
public class Application {

    /**
     * application main method
     *
     * @param args start params
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
