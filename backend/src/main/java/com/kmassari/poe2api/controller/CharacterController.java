package com.kmassari.poe2api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kmassari.poe2api.Character;
import com.kmassari.poe2api.service.CharacterService;

@RestController
@RequestMapping("/api/characters")
@CrossOrigin(origins = {"http://localhost:4200", "https://mango-coast-030c64b10.7.azurestaticapps.net"})
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }


    @GetMapping
    public List<Character> getAll() {
        return characterService.getAll();
    }

    @PostMapping
    public ResponseEntity<Character> create(@RequestBody Character character) {
        character.setId(null);
        Character saved = characterService.create(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Character> update(@PathVariable Long id, @RequestBody Character updatedCharacter) {
        return characterService.update(id, updatedCharacter)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!characterService.delete(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
