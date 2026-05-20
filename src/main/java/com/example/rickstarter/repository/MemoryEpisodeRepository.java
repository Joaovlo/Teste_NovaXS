package com.example.rickstarter.repository;

import com.example.rickstarter.model.Episode;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryEpisodeRepository implements EpisodeRepository {
	// HASHMAP
	private static final Map<Integer, Episode> db = new LinkedHashMap<>();
	private static final AtomicInteger idGenerator = new AtomicInteger(0);

	@Override
	public Optional<Episode> findById(Integer id) {
		return Optional.ofNullable(db.get(id));
	}

	@Override
	public List<Episode> findPage(int page, int size) {
		List<Episode> allEpisodes = new ArrayList<>(db.values());
		// cálculo paginação
		int fromIndex = (page - 1) * size;
		if (db.isEmpty() || allEpisodes.size() <= fromIndex) {
			return Collections.emptyList();
		}
		int toIndex = Math.min(fromIndex + size, allEpisodes.size());
		return allEpisodes.subList(fromIndex, toIndex);
	}

	@Override
	public long count() {
		return db.size();
	}

	@Override
	public void save(Episode e) {
		if (e.getId() == null || e.getId() == 0) {
			e.setId(idGenerator.incrementAndGet());
		} else {
			if (e.getId() > idGenerator.get()) {
				idGenerator.set(e.getId());
			}
		}
		db.put(e.getId(), e);
	}

	@Override
	public void update(Episode e) {
		if (db.containsKey(e.getId())) {
			db.put(e.getId(), e);
		}
	}

	@Override
	public void deleteById(Integer id) {
		db.remove(id);
	}
}
