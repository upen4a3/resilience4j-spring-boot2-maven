package com.example.demo.controller;

import com.example.demo.service.ServiceA;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class DemoController
{
    private ServiceA demoService;

    public DemoController(@Qualifier("serviceAImpl") ServiceA demoService)
    {
        this.demoService=demoService;
    }
    @GetMapping("/search/{id}")
    public String search(@PathVariable String id)
    {
        return demoService.search(id);
    }


    @GetMapping("futureSuccess")
    public CompletableFuture<String> futureSuccess(){
        return demoService.futureSuccess();
    }
}
