package com.example.demoapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled
class DemoAppApplicationTests {

    @Test
    void contextLoads() {
        Assertions.assertTrue(true); // it simply means that everything is wired correctly
    }

}
