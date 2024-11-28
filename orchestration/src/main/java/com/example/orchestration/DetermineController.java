package com.example.orchestration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/determine")
public class DetermineController {

    private final ExternalInventoryClient externalInventoryClient;
    private final InternalInventoryClient internalInventoryClient;

    public DetermineController(ExternalInventoryClient externalInventoryClient, InternalInventoryClient internalInventoryClient) {
        this.externalInventoryClient = externalInventoryClient;
        this.internalInventoryClient = internalInventoryClient;
    }

    @PostMapping
    public Resource determine(@RequestBody Resource resource) {
        resource = externalInventoryClient.determine(resource);
        resource = internalInventoryClient.determine(resource);
        return resource;
    }

    @GetMapping
    public Resource determine(@RequestParam("streetName") String streetName, @RequestParam("streetNumber") String streetNumber)  {
        var resource = new Resource(new Address(streetName, streetNumber));
        resource = externalInventoryClient.determine(resource);
        resource = internalInventoryClient.determine(resource);
        System.out.println(Thread.currentThread());
        return resource;
    }
}
