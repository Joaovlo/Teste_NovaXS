
package com.example.rickstarter.controller;

import static spark.Spark.get;

import java.util.Map;

import com.example.rickstarter.service.ImportService;
import com.example.rickstarter.util.JsonUtil;

public class ImportController {
	private final ImportService service;

	public ImportController(ImportService service) {
		this.service = service;
		setup();
	}

	// Passado a responsabilidade para o ImportService
	private void setup() {
		{
			get("/import", (req, res) -> {
				res.type("application/json");
				try {
					String resultMessage = service.importAllData();
					return JsonUtil.toJson(Map.of("status", "success", "message", resultMessage));

				} catch (Exception e) {
					e.printStackTrace();
					res.status(500);
					return JsonUtil.toJson(Map.of("error", e.getMessage()));
				}
			});
			get("/speciesByEpisode", (req, res) -> {
				res.type("application/json");
				return JsonUtil.toJson(Map.of("message", "Não implementado"));
			});
		}
	}
}
