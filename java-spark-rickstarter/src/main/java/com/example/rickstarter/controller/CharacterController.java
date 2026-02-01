
package com.example.rickstarter.controller;

import static spark.Spark.*;

public class CharacterController {

    public CharacterController() {
        setup();
    }

    private void setup() {
        get("/personagem", (req, res) -> {
            throw new Exception("Not implemented yet");
        });

        post("/personagem", (req, res) -> {
           throw new Exception("Not implemented yet");
        });

        put("/personagem/:id", (req, res) -> {
            throw new Exception("Not implemented yet");
        });

        delete("/personagem/:id", (req, res) -> { 
            throw new Exception("Not implemented yet");
        });
    }
}
