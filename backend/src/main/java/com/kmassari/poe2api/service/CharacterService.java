package com.kmassari.poe2api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kmassari.poe2api.Character;
import com.kmassari.poe2api.repository.CharacterRepository;

@Service
public class CharacterService {

    private final CharacterRepository repo;

    public CharacterService(CharacterRepository repo) {
        this.repo = repo;
    }

    public List<Character> getAll() {
        return repo.findAll();
    }

    public Character create(Character character) {
        return repo.save(character);
    }

    public Optional<Character> update(Long id, Character updatedCharacter) {
        return repo.findById(id)
            .map(character -> {
                character.setName(updatedCharacter.getName());
                character.setImage(updatedCharacter.getImage());
                character.setAscendancy1(updatedCharacter.getAscendancy1());
                character.setAscendancy2(updatedCharacter.getAscendancy2());
                character.setAscendancy3(updatedCharacter.getAscendancy3());
                return repo.save(character);
            });
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) {
            return false;
        }

        repo.deleteById(id);
        return true;
    }
}
