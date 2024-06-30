package com.engsoft2.cache_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "t1-service")
public interface T1ServiceProxy {
    @GetMapping("/assinvalida/{codass}")
    public CacheService retrieveEndDate(@PathVariable String codass);
}