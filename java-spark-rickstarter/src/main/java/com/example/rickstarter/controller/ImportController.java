
package com.example.rickstarter.controller;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.List;

import com.example.rickstarter.model.Character;
import com.example.rickstarter.model.Episode;
import com.example.rickstarter.service.ImportService;
import com.example.rickstarter.util.JsonUtil;

public class ImportController {
    private final ImportService service;

    public ImportController(ImportService service) {
        this.service = service;
        setup();
    }

    private void setup() {
        get("/import", (req, res) -> {
            res.type("application/json");
            try {                
                List<Character> characters = service.importAllCharacters();
                List<Episode> episodes = new java.util.ArrayList<>();
                for (Character character : characters) {
                    for (String episodeUrl : character.getEpisode()) {
                        //TODO: evitar a duplicação de episodios
                        Episode episode = service.importEpisode(episodeUrl);
                        episodes.add(episode);
                    }
                }
                return JsonUtil.toJson(characters);
            } catch (Exception e) {
                e.printStackTrace();
                res.status(500);
                return JsonUtil.toJson(java.util.Map.of("error", e.getMessage()));
            }
        });
        get("/speciesByEpisode", (req, res) -> {
            throw new Exception("Not implemented yet");
        });
    }
}
