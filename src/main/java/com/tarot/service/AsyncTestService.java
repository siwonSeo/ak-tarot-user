package com.tarot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class AsyncTestService {

    @Autowired
    private Executor taskExecutor;

    public void callAsyncTask() {
        System.out.println("Main Thread: " + Thread.currentThread().getName());

        List<CompletableFuture<String>> cfList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            cfList.add(asyncTest());
        }
        CompletableFuture.allOf(cfList.toArray(new CompletableFuture[cfList.size()])).join();


        System.out.println("callAsyncTask() 끝");
    }

    public CompletableFuture<String> asyncTest() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000); // 가짜 비동기 작업
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("비동기 실행 스레드: {}", Thread.currentThread().getName());
            return "완료";
        }, taskExecutor);  // 여기가 중요!
    }
}
