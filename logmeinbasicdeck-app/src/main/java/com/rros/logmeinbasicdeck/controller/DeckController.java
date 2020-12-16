package com.rros.logmeinbasicdeck.controller;

import com.rros.logmeinbasicdeck.service.DeckService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/decks")
public class DeckController {

    private final DeckService deckService;

    @Autowired
    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    @Operation(summary = "Create deck")
    @PostMapping
    public UUID create() {
        return deckService.create();
    }
}
