package com.example.rickstarter.repository;

import com.example.rickstarter.model.Character;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryCharacterRepository implements CharacterRepository {
	// Ideia é usar HashMap
	private static final Map<Integer, Character> db = new LinkedHashMap<>();
	private static final AtomicInteger idGenerator = new AtomicInteger(0);

	@Override
	public Optional<Character> findById(Integer id) {
		return Optional.ofNullable(db.get(id));
	}

	@Override
	public List<Character> findPage(int page, int size) {
		List<Character> allCharacters = new ArrayList<>(db.values());
		// Paginação necessária para o desenvolvimento do teste como solicitado
		int fromIndex = (page - 1) * size;

		if (db.isEmpty() || allCharacters.size() < fromIndex) {
			return Collections.emptyList();
		}

		int toIndex = Math.min(fromIndex + size, allCharacters.size());

		return allCharacters.subList(fromIndex, toIndex);
	}

	@Override
	public long count() {
		return db.size();
	}

	@Override
	public void save(Character c) {
		if (c.getId() == null) {
			c.setId(idGenerator.incrementAndGet());
		} else {
			if (c.getId() > idGenerator.get()) {
				idGenerator.set(c.getId());
			}
		}
		db.put(c.getId(), c);
	}

	@Override
	public void update(Character c) {
		if (db.containsKey(c.getId())) {
			db.put(c.getId(), c);
		}
	}

	@Override
	public void deleteById(Integer id) {
		db.remove(id);
	}
}
