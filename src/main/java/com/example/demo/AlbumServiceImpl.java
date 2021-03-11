package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AlbumServiceImpl implements AlbumService{

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(AlbumServiceImpl.class);

    @Override
    public String getAlbum() {

        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");

        String url = "https://badurl.com/willproducerror";

        return circuitBreaker.run(() -> restTemplate.getForObject(url, String.class), (throwable -> {
            logger.error(throwable.getMessage());
            return getDefaultAlbum();
        }));
    }

    private String getDefaultAlbum() {

        // logic
        return "default album";

    }
}
