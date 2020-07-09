package com.example.demo.service.impl;

import com.example.demo.service.DemoService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Component(value = "backendAService")
public class DemoServiceImpl implements DemoService
{
    private static final String BACKEND_A = "backendA";

    @Override
    @CircuitBreaker(name = BACKEND_A ,fallbackMethod = "getFallbackRatings")
    @Bulkhead(name = BACKEND_A)
    public String search(String id)
    {
        if(id.equals("a")) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "This is a remote exception");
        }
        else
        {
            return "SUCCESS";
        }
    }

    @Override
    @Bulkhead(name = BACKEND_A, type = Bulkhead.Type.THREADPOOL)
    @CircuitBreaker(name = BACKEND_A,fallbackMethod = "futureFallback")
    @Retry(name = BACKEND_A)
    public CompletableFuture<String> futureSuccess() {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.completeExceptionally(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "This is a remote exception"));
        return future;
    }

    private String getFallbackRatings(String id,CallNotPermittedException ex)
    {

        return "Recovered HttpServerErrorException: " + ex.getMessage();
    }

    private CompletableFuture<String> futureFallback(CallNotPermittedException ex) {
        return CompletableFuture.completedFuture("Recovered specific CallNotPermittedException: " + ex.toString());
    }

}
