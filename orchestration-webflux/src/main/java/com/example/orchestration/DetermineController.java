package com.example.orchestration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/determine")
public class DetermineController {

    private final InternalInventoryClient internalInventoryClient;
    private final ExternalInventoryClient externalInventoryClient;

    public DetermineController(InternalInventoryClient internalInventoryClient, ExternalInventoryClient externalInventoryClient) {
        this.internalInventoryClient = internalInventoryClient;
        this.externalInventoryClient = externalInventoryClient;
    }

    @GetMapping
    public Mono<Resource> hello(@RequestParam("streetName") String streetName, @RequestParam("streetNumber") String streetNumber) {
        final var resource = new Resource(new Address(streetName, streetNumber));

        return externalInventoryClient.determine(resource)
                    .flatMap(internalInventoryClient::determine);

//      return Mono.fromCallable(() -> externalInventoryClient.determine(resource))
//                .flatMap(externalResource -> Mono.fromCallable(() -> internalInventoryClient.determine(externalResource)));
//                .flatMap(internalResource -> internalResource);


    }

//    @PostMapping
//    public Mono<Resource> determine(@RequestBody Resource resource) {
//        return Mono.fromCallable(() -> externalInventoryClient.determine(resource))
//                .flatMap(externalResource -> Mono.fromCallable(() -> internalInventoryClient.determine(externalResource.block())))
//                .flatMap(internalResource -> internalResource);
//    }
}