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
import com.kmassari.poe2api.repository.CharacterRepository;


@RestController
@RequestMapping("/api/characters")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CharacterController {

    private final CharacterRepository repo;

    public CharacterController(CharacterRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Character> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<Character> create(@RequestBody Character character) {
        character.setId(null);
        Character saved = repo.save(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Character> update(@PathVariable Long id, @RequestBody Character updatedCharacter) {
        return repo.findById(id)
            .map(character -> {
                character.setName(updatedCharacter.getName());
                character.setImage(updatedCharacter.getImage());
                character.setAscendancy1(updatedCharacter.getAscendancy1());
                character.setAscendancy2(updatedCharacter.getAscendancy2());
                character.setAscendancy3(updatedCharacter.getAscendancy3());

                Character saved = repo.save(character);
                return ResponseEntity.ok(saved);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
