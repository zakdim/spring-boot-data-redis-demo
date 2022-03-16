package com.example.springbootdataredisdemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
public class RedisConfiguration {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName("localhost");
        config.setPort(6379);
        config.setDatabase(0);
        // Note: Uncomment the below line if any password has been set
        // redisStandaloneConfiguration.setPassword(RedisPassword.of("password"));

        LettuceConnectionFactory lcf = new LettuceConnectionFactory(config);
        lcf.afterPropertiesSet();

        return lcf;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        template.afterPropertiesSet();

        return template;
    }
}
