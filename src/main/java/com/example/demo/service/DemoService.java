package com.example.demo.service;

import java.util.concurrent.CompletableFuture;

public interface DemoService
{
    String search(String id);

    CompletableFuture<String> futureSuccess();
}
