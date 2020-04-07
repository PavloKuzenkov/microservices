package com.springcloud.cart;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("exchange-server")
public interface ExchangeClient {

    @GetMapping(path = "/exchange")
    public double getExchangeRate();

}
