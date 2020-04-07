package com.pavel.springcloud.exchange;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;

@RestController
public class ExchangeResource {

    @GetMapping(path = "/exchange")
    public double getExchangeRate(){
        System.out.println("ExchangeResource.getExchangeRate");
        return new Random().nextDouble();
    }
}
