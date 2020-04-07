package com.springcloud.cart.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
public class RedisConfig {

  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    System.out.println("RedisConfig.jedisConnectionFactory");
    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
    jedisConnectionFactory.setHostName("redis");
    jedisConnectionFactory.setPort(6379);
    return jedisConnectionFactory;
  }
  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
    template.setConnectionFactory(jedisConnectionFactory());
    template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
    return template;
  }

  @Bean
  public RedisClient redisPublisher() {
    return new RedisClient(redisTemplate(), topic());
  }

  @Bean
  MessageListenerAdapter messageListener() {
    return new MessageListenerAdapter(new MessageSubscriber());
  }

  @Bean
  ChannelTopic topic() {
    return new ChannelTopic("messageQueue");
  }

  @Bean
  RedisMessageListenerContainer redisContainer() {
    RedisMessageListenerContainer container
        = new RedisMessageListenerContainer();
    container.setConnectionFactory(jedisConnectionFactory());
    container.addMessageListener(messageListener(), topic());
    return container;
  }
}
