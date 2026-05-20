
package com.example.rickstarter.repository;

import com.example.rickstarter.model.Character;
import java.util.*;

public interface CharacterRepository {
    Optional<Character> findById(Integer id);
    List<Character> findPage(int page, int size);
    long count();
    void save(Character c);
    void update(Character c);
    void deleteById(Integer id);
}
