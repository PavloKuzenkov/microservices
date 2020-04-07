package com.springcloud.cart.redis;

import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageSubscriber implements MessageListener {

  public static List<String> messageList = new ArrayList<String>();

  @Override
  public void onMessage(org.springframework.data.redis.connection.Message message, byte[] bytes) {
    System.out.println("Message received: " + new String(message.getBody()));
    messageList.add(message.toString());
  }
}
