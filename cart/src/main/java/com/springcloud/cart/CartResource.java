package com.springcloud.cart;

import com.springcloud.cart.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CartResource {

    private Map<String, List<CartItem>> items = new HashMap<>();

    @Autowired
    private ExchangeClient exchangeClient;

    @Autowired
    private RedisClient redisClient;

    @PostMapping("/cart/{customerId}")
    public void addToCart(@PathVariable String customerId, @RequestBody CartItem cartItem){
        System.out.println(cartItem);
        System.out.println("received exchange rate:" + exchangeClient.getExchangeRate());
    }

    @GetMapping("/cartRate/{id}")
    public String getRate(@PathVariable String id){
        System.out.println("CartResource.getRate");
         redisClient.publish(id);
         return String.valueOf(exchangeClient.getExchangeRate());
    }

}
