package com.example.internalinventory;

import java.util.List;

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

        if(null == resource.cabinet().cable() || null == resource.cabinet().fiber())
                throw new IllegalArgumentException("Invalid resource");


        var shelves = new Shelf(List.of(
                new Card("GPON", "54", "512000")
        ));

        var cabinet = new Cabinet(
                new TerminalBox("02SP87SS2", "Splitter"),
                "02",
                "87",
                List.of(shelves));

        return new Resource(
                resource.address(),
                cabinet);
    }
}
