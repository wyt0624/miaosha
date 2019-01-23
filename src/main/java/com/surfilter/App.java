package com.surfilter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = {"com.surfilter"})
@RestController
@MapperScan("com.surfilter.dao")
@EnableScheduling
public class App {

    public static void main( String[] args ){

        System.out.println( "Hello World!" );
        SpringApplication.run(App.class,args);
    }
}
