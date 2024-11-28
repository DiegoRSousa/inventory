package com.example.orchestration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "external-inventory", url = "http://localhost:8081")
public interface ExternalInventoryClient {

    @PostMapping(value = "/determine")
    Resource determine(@RequestBody Resource resource);
}
