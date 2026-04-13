package com.kmassari.poe2api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kmassari.poe2api.Character;
import com.kmassari.poe2api.repository.CharacterRepository;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;



@Service
public class CharacterService {

    private final CharacterRepository repo;
    private final ObservationRegistry observationRegistry;


    public CharacterService(CharacterRepository repo, ObservationRegistry observationRegistry) {
        this.repo = repo;
        this.observationRegistry = observationRegistry;
    }

    public List<Character> getAll() {
        return Observation.createNotStarted("characters.get-all", observationRegistry)
            .lowCardinalityKeyValue("operation", "get-all")
            .observe(() -> repo.findAll());
    }

    public Character create(Character character) {
        return Observation.createNotStarted("characters.create", observationRegistry)
            .lowCardinalityKeyValue("operation", "create")
            .observe(() -> repo.save(character));
    }

    public Optional<Character> update(Long id, Character updatedCharacter) {
        return Observation.createNotStarted("characters.update", observationRegistry)
            .lowCardinalityKeyValue("operation", "update")
            .observe(() -> repo.findById(id)
                .map(character -> {
                    character.setName(updatedCharacter.getName());
                    character.setImage(updatedCharacter.getImage());
                    character.setAscendancy1(updatedCharacter.getAscendancy1());
                    character.setAscendancy2(updatedCharacter.getAscendancy2());
                    character.setAscendancy3(updatedCharacter.getAscendancy3());
                    return repo.save(character);
                }));
    }

    public boolean delete(Long id) {
        return Observation.createNotStarted("characters.delete", observationRegistry)
            .lowCardinalityKeyValue("operation", "delete")
            .observe(() -> {
                if (!repo.existsById(id)) {
                    return false;
                }
                repo.deleteById(id);
                return true;
            });
    }
}
