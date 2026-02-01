
package com.example.rickstarter;

import static spark.Spark.*;
import com.example.rickstarter.controller.*;
import com.example.rickstarter.repository.*;
import com.example.rickstarter.service.*;

public class Main {
	public static void main(String[] args) {
		port(8080);

		CharacterRepository charRepo = new MemoryCharacterRepository();
		EpisodeRepository epRepo = new MemoryEpisodeRepository();
		RickAndMortyClient client = new RickAndMortyClient();
		ImportService importService = new ImportService(client, charRepo, epRepo);

		new ImportController(importService);
		new CharacterController(charRepo);
		new EpisodeController(epRepo);

		get("/health", (req, res) -> "OK");
		System.out.println("Servidor rodando...");
		System.out.println("Importar dados: http://localhost:8080/import");
		System.out.println("Listar personagens: http://localhost:8080/personagem");
	}
}
