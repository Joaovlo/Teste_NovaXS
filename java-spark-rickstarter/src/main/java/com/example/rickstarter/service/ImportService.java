
package com.example.rickstarter.service;

import com.example.rickstarter.model.Character;
import com.example.rickstarter.model.Episode;

import java.util.*;

public class ImportService {

    private final RickAndMortyClient client;

    public ImportService(RickAndMortyClient client) {
        this.client = client;
    }

    public List<Character> importAllCharacters() throws Exception {
        List<Character> chars = client.fetchAllCharacters();
        return chars;
    }

    public Episode importEpisode(String url) throws Exception {
        Episode episode = client.fetchEpisodeByUrl(url);
        return episode;
    }


}
