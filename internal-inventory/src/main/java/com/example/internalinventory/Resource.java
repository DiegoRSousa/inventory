package com.example.internalinventory;

import java.util.List;

public record Resource (Address address, Cabinet cabinet) { 

}

record Address(String streetName, String streetNumber) {
}


record Cabinet(TerminalBox terminalBox, String cable, String fiber, List<Shelf> shelves) {
}

record TerminalBox(String id, String type) {
}

record Shelf(List<Card> cards) {
}

record Card(String technology, String freePorts, String maxSpeed) {
}