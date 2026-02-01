
package com.example.rickstarter.controller;

import static spark.Spark.*;
import com.example.rickstarter.model.Character;
import com.example.rickstarter.repository.CharacterRepository;
import com.example.rickstarter.util.JsonUtil;
import java.util.Map;

public class CharacterController {

	private final CharacterRepository repository;

	public CharacterController(CharacterRepository repository) {
		this.repository = repository;
		setup();
	}

	private void setup() {
		// (GET) importacao personagem com paginação
        get("/personagem", (req, res) -> {
            res.type("application/json");
            int page = req.queryParams("page") != null ? Integer.parseInt(req.queryParams("page")) : 1;
            int size = req.queryParams("size") != null ? Integer.parseInt(req.queryParams("size")) : 10;
            
            return JsonUtil.toJson(repository.findPage(page, size));
        });

        // (POST) criação de personagem
        post("/personagem", (req, res) -> {
            res.type("application/json");
            try {
                
                Character character = JsonUtil.fromJson(req.body(), Character.class);
                
                repository.save(character);
                
                res.status(201);
                return JsonUtil.toJson(character);
            } catch (Exception e) {
                res.status(400);
                return JsonUtil.toJson(Map.of("error", "Erro ao criar personagem: " + e.getMessage()));
            }
        });

        // (PUT) atualizar personagem
        put("/personagem/:id", (req, res) -> {
            res.type("application/json");
            try {
                Integer id = Integer.parseInt(req.params(":id"));
                Character character = JsonUtil.fromJson(req.body(), Character.class);
                // tem que ser o mesmo id da url
                character.setId(id);
                
                repository.update(character);
                
                return JsonUtil.toJson(character);
            } catch (NumberFormatException e) {
                res.status(400);
                return JsonUtil.toJson(Map.of("error", "ID inválido"));
            }
        });
        
        // (DELETE) deletar personagem
        delete("/personagem/:id", (req, res) -> {
            res.type("application/json");
            try {
                Integer id = Integer.parseInt(req.params(":id"));
                repository.deleteById(id);
                
                res.status(204);
                return ""; 
            } catch (NumberFormatException e) {
                res.status(400);
                return JsonUtil.toJson(Map.of("error", "ID inválido"));
            }
        });
    	}
    }

