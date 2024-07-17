package com.gfg.shoutreview.config;

import com.gfg.shoutreview.service.response.MovieResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@Configuration
@EnableRedisRepositories
public class RedisConfig {
    @Bean
    public JedisConnectionFactory connectionFactory() {
        // Redis Localhost
//        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("localhost", 6379);

        // Redis Cloud
        RedisStandaloneConfiguration configCloud = new RedisStandaloneConfiguration();
        configCloud.setHostName("redis-19677.c301.ap-south-1-1.ec2.redns.redis-cloud.com");
        configCloud.setPassword("BnvlPhkV9m7YEvBJ0YTLWow6jRNjCZuU");
        configCloud.setPort(19677);
        configCloud.setUsername("default");
        //return new JedisConnectionFactory(config);
        return new JedisConnectionFactory(configCloud);

    }

    @Bean
    public RedisTemplate<String, List<MovieResponse>> template(){
        RedisTemplate<String, List<MovieResponse>> template = new RedisTemplate<String, List<MovieResponse>>();
        template.setConnectionFactory(connectionFactory());
        return template;
    }
}
