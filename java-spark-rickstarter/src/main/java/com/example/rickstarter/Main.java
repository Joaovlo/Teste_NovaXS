
package com.example.rickstarter;

import static spark.Spark.*;

import com.example.rickstarter.controller.*;
import com.example.rickstarter.repository.CharacterRepository;
import com.example.rickstarter.repository.EpisodeRepository;
import com.example.rickstarter.service.*;

public class Main {
    public static void main(String[] args) {
        port(8080);
        
//        CharacterRepository charRepo = new MemoryCharacterRepository();
//        EpisodeRepository epRepo = new MemoryEpisodeRepository();
        RickAndMortyClient client = new RickAndMortyClient();
        // item temporário até organizar os controllers
        ImportService importService = new ImportService(client, null,null);

        new ImportController(importService);
//        new CharacterController();
        new EpisodeController();

        get("/health", (req, res) -> "OK");
        System.out.println("Server started at http://localhost:8080");
    }
}
