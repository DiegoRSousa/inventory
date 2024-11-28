package com.example.orchestration;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class ExternalInventoryClient {
    private final WebClient webClient;

    public ExternalInventoryClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
    }

    public Mono<Resource> determine(Resource resource) {
        return webClient.post()
                .uri("/determine")
                .bodyValue(resource)
                .retrieve()
                .bodyToMono(Resource.class);
    }
}
