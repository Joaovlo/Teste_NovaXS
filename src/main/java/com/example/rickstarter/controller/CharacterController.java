package com.example.rickstarter.controller;

import com.example.rickstarter.model.Character;
import com.example.rickstarter.repository.CharacterRepository;
import com.example.rickstarter.util.JsonUtil;
import spark.Request;
import spark.Response;
import java.util.Map;

public class CharacterController {

	private final CharacterRepository repository;

	public CharacterController(CharacterRepository repository) {
		this.repository = repository;
	}

	public String listar(Request req, Response res) throws Exception {
		res.type("application/json");
		int page = req.queryParams("page") != null ? Integer.parseInt(req.queryParams("page")) : 1;
		int size = req.queryParams("size") != null ? Integer.parseInt(req.queryParams("size")) : 10;

		return JsonUtil.toJson(repository.findPage(page, size));
	}

	// (POST) Criar
	public String criar(Request req, Response res) throws Exception {
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
	}

	// (PUT) Atualizar
	public String atualizar(Request req, Response res) throws Exception {
		res.type("application/json");
		try {
			Integer id = Integer.parseInt(req.params(":id"));
			Character character = JsonUtil.fromJson(req.body(), Character.class);
			character.setId(id);
			repository.update(character);
			return JsonUtil.toJson(character);
		} catch (Exception e) {
			res.status(400);
			return JsonUtil.toJson(Map.of("error", "ID inválido"));
		}
	}

	// (DELETE) Deletar
	public String deletar(Request req, Response res) throws Exception {
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
	}
}