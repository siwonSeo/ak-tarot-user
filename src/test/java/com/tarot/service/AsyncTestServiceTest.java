package com.tarot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Profile("default")
class AsyncTestServiceTest {
    @Autowired
    private AsyncTestService asyncTestService;

    @Test
    void callAsyncTask() {
        asyncTestService.callAsyncTask();
    }
}