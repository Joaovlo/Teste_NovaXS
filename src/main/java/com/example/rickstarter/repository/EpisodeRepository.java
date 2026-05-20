
package com.example.rickstarter.repository;

import com.example.rickstarter.model.Episode;
import java.util.*;

public interface EpisodeRepository {
    Optional<Episode> findById(Integer id);
    List<Episode> findPage(int page, int size);
    long count();
    void save(Episode e);
    void update(Episode e);
    void deleteById(Integer id);
}
