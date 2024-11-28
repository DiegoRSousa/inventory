package com.example.orchestration;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "internal-inventory", url = "http://localhost:8082")
public interface InternalInventoryClient {

    @PostMapping(value = "/determine")
    Resource determine(@RequestBody Resource resource);
}
