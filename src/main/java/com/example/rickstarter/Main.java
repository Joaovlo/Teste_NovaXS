
package com.example.rickstarter;

import static spark.Spark.*;
import com.example.rickstarter.controller.*;
import com.example.rickstarter.repository.*;
import com.example.rickstarter.service.*;

public class Main {
	public static void main(String[] args) {
		stop();
		port(8080);

		CharacterRepository charRepo = new MemoryCharacterRepository();
		EpisodeRepository epRepo = new MemoryEpisodeRepository();
		RickAndMortyClient client = new RickAndMortyClient();
		ImportService importService = new ImportService(client, charRepo, epRepo);
		CharacterController charCtrl = new CharacterController(charRepo);
		EpisodeController epiCtrl = new EpisodeController(epRepo, charRepo);

		get("/import", (req, res) -> importService.importAllData());
		get("/speciesByEpisode", (req, res) -> epiCtrl.getSpeciesStats(req, res));
		get("/personagem", (req, res) -> charCtrl.listar(req, res));
		post("/personagem", (req, res) -> charCtrl.criar(req, res));
		put("/personagem/:id", (req, res) -> charCtrl.atualizar(req, res));
		delete("/personagem/:id", (req, res) -> charCtrl.deletar(req, res));

		get("/health", (req, res) -> "OK");
		System.out.println("Servidor rodando...");
		System.out.println("Importar dados: http://localhost:8080/import");
		System.out.println("Listar personagens: http://localhost:8080/personagem");
		System.out.println("Relatório: http://localhost:8080/speciesByEpisode");
	}
}
