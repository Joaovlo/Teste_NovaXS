
package com.example.rickstarter;

import static spark.Spark.*;

import com.example.rickstarter.controller.*;
import com.example.rickstarter.service.*;

public class Main {
    public static void main(String[] args) {
        port(8080);

        RickAndMortyClient client = new RickAndMortyClient();
        ImportService importService = new ImportService(client);

        new ImportController(importService);
        new CharacterController();
        new EpisodeController();

        get("/health", (req, res) -> "OK");
        System.out.println("Server started at http://localhost:8080");
    }
}
