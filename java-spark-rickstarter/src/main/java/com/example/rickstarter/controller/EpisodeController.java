
package com.example.rickstarter.controller;

import static spark.Spark.*;
import com.example.rickstarter.model.Episode;
import com.example.rickstarter.repository.EpisodeRepository;
import com.example.rickstarter.util.JsonUtil;
import java.util.Map;

public class EpisodeController {
	
	private final EpisodeRepository repository;

    public EpisodeController(EpisodeRepository repository) {
    	this.repository = repository;
        setup();
    }

    private void setup() {
    	// (Get) importação episodio com paginação
        get("/episodio", (req, res) -> {
            res.type("application/json");
            int page = req.queryParams("page") != null ? Integer.parseInt(req.queryParams("page")) : 1;
            int size = req.queryParams("size") != null ? Integer.parseInt(req.queryParams("size")) : 10;
            return JsonUtil.toJson(repository.findPage(page, size));
        });

        // (POST) criar episodio
        post("/episodio", (req, res) -> {
            res.type("application/json");
            try {
                Episode episode = JsonUtil.fromJson(req.body(), Episode.class);
                repository.save(episode);
                res.status(201);
                return JsonUtil.toJson(episode);
            } catch (Exception e) {
                res.status(400);
                return JsonUtil.toJson(Map.of("error", e.getMessage()));
            }
        });

        // (PUT) alterar episodio
        put("/episodio/:id", (req, res) -> {
            res.type("application/json");
            try {
                Integer id = Integer.parseInt(req.params(":id"));
                Episode episode = JsonUtil.fromJson(req.body(), Episode.class);
                episode.setId(id);
                repository.update(episode);
                return JsonUtil.toJson(episode);
            } catch (Exception e) {
                res.status(400);
                return JsonUtil.toJson(Map.of("error", "ID inválido"));
            }
        });

        // (DELETE) deletar episodio
        delete("/episodio/:id", (req, res) -> {
            res.type("application/json");
            try {
                Integer id = Integer.parseInt(req.params(":id"));
                repository.deleteById(id);
                res.status(204);
                return "";
            } catch (Exception e) {
                res.status(400);
                return JsonUtil.toJson(Map.of("error", "ID inválido"));
            }
        });
    }
}
