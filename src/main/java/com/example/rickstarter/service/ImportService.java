
package com.example.rickstarter.service;

import com.example.rickstarter.model.Character;
import com.example.rickstarter.model.Episode;
import com.example.rickstarter.repository.CharacterRepository;
import com.example.rickstarter.repository.EpisodeRepository;
import java.util.*;

public class ImportService {

	private final RickAndMortyClient client;
	private final CharacterRepository charRepo;
	private final EpisodeRepository epiRepo;

	public ImportService(RickAndMortyClient client, CharacterRepository charRepo, EpisodeRepository epiRepo) {
		this.client = client;
		this.charRepo = charRepo;
		this.epiRepo = epiRepo;
	}

	public String importAllData() {
		try {
			System.out.println("Buscando os personagens...");
			List<Character> chars = client.fetchAllCharacters();
			for (Character c : chars) {
				charRepo.save(c);
			}

			System.out.println("Buscando os episódios...");
			List<Episode> episodes = client.getListEpisodes();
			for (Episode e : episodes) {
				epiRepo.save(e);
			}

			return "Sucesso na importação: " + chars.size() + " personagens e " + episodes.size() + " episódios";
		} catch (Exception e) {
			e.printStackTrace();
			return "Não foi possível realizar a importação: " + e.getMessage();
		}

	}

	public List<Character> importAllCharacters() throws Exception {
		List<Character> chars = client.fetchAllCharacters();
		return chars;
	}

	public Episode importEpisode(String url) throws Exception {
		Episode episode = client.fetchEpisodeByUrl(url);
		return episode;
	}

}
