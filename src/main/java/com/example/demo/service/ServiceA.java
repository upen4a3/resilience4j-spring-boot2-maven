package com.example.demo.service;

import java.util.concurrent.CompletableFuture;

public interface ServiceA
{
    String search(String a);

    CompletableFuture<String> futureSuccess();
}
