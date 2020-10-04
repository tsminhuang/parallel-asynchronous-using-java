package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.*;

class CompletableFutureHelloWorldTest {

    HelloWorldService hws = new HelloWorldService();
    CompletableFutureHelloWorld cfhw = new CompletableFutureHelloWorld(hws);

    @Test
    void helloWorld() {

        //given
        //when
        CompletableFuture<String> completableFuture = cfhw.helloWorld();

        //then
        completableFuture
                .thenAccept(s -> {
                    //assertEquals("hello world", s);
                    assertEquals("HELLO WORLD", s);
                })
                .join();
    }

    @Test
    void helloWorld_1() {

        //when
        String hw = cfhw.helloWorld_1();

        //then

        assertEquals("HELLO WORLD", hw);

    }

    @Test
    void helloWorld_multiple_async_calls() {

        //given
        //when
        String hw = cfhw.helloWorld_multiple_async_calls();

        //then
        assertEquals("HELLO WORLD!", hw);

    }

    @Test
    void helloWorld_multiple_async_calls_1() {

        //given
        //when
        String hw = cfhw.helloWorld_multiple_async_calls_1();

        //then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", hw);

    }

    @Test
    void helloWorld_thenCompose() {

        //given
        //when
        startTimer();

        CompletableFuture<String> completableFuture = cfhw.helloWorld_thenCompose();

        //then
        completableFuture
                .thenAccept(s -> {
                    //assertEquals("hello world", s);
                    assertEquals("HELLO WORLD!", s);
                })
                .join();
        timeTaken();


    }

    @Test
    void helloWorld_withSize() {

        //given
        //when
        CompletableFuture<String> completableFuture = cfhw.helloWorld_withSize();

        //then
        completableFuture
                .thenAccept(s -> {
                    assertEquals("11 - HELLO WORLD", s);
                })
                .join();
    }


}