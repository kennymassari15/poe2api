package com.kmassari.poe2_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kmassari.poe2_api.Character;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
}
