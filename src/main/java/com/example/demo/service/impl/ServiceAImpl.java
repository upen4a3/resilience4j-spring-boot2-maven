package com.example.demo.service.impl;

import com.example.demo.service.DemoService;
import com.example.demo.service.ServiceA;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service(value = "serviceAImpl")
public class ServiceAImpl implements ServiceA
{
    private DemoService demoService;

    public ServiceAImpl(DemoService demoService)
    {
        this.demoService=demoService;
    }

    @Override
    public String search(String a)
    {
        return demoService.search(a);
    }

    @Override
    public CompletableFuture<String> futureSuccess() {
        return demoService.futureSuccess();
    }
}
