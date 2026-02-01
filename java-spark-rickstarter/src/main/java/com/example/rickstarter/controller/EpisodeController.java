
package com.example.rickstarter.controller;

import static spark.Spark.*;

public class EpisodeController {

    public EpisodeController() {
        setup();
    }

    private void setup() {
        get("/episodio", (req, res) -> {
            throw new Exception("Not implemented yet");
        });

        post("/episodio", (req, res) -> {
            throw new Exception("Not implemented yet");
        });

        put("/episodio/:id", (req, res) -> {
            throw new Exception("Not implemented yet"); 
        });

        delete("/episodio/:id", (req, res) -> {
            throw new Exception("Not implemented yet");
        });
    }
}
