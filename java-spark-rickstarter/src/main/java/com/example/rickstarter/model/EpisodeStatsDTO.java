package com.example.rickstarter.model;

import java.util.HashMap;
import java.util.Map;

public class EpisodeStatsDTO {
	private Integer idEpisode;
	private String name;
	private String episodeCode;
	private Map<String, Integer> speciesCount;

	public EpisodeStatsDTO(Integer id, String name, String code) {
		this.idEpisode = id;
		this.name = name;
		this.episodeCode = code;
		this.speciesCount = new HashMap<>();
	}

	public void addSpecies(String species) {
		// Se já existe soma 1, se não começa com 1
		speciesCount.merge(species, 1, Integer::sum);
	}

	public Integer getEpisodeId() {
		return idEpisode;
	}

	public String getName() {
		return name;
	}

	public String getEpisodeCode() {
		return episodeCode;
	}

	public Map<String, Integer> getSpeciesCount() {
		return speciesCount;
	}

}
