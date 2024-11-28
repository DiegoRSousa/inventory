package com.example.externalinventory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/determine")
public class DetermineController {

    @PostMapping
    public Resource execute(@RequestBody Resource resource) throws InterruptedException {
        Thread.sleep(1500);
        var cabinet = new Cabinet(
                new TerminalBox("02SP87SS2", "Splitter"),
                "02",
                "87");

        return new Resource(resource.address(), cabinet);
    }
}
