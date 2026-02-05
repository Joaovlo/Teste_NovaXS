package com.example.rickstarter.controller;

import com.example.rickstarter.model.Episode;
import com.example.rickstarter.model.EpisodeStatsDTO;
import com.example.rickstarter.model.Character;
import com.example.rickstarter.repository.CharacterRepository;
import com.example.rickstarter.repository.EpisodeRepository;
import com.example.rickstarter.util.JsonUtil;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;

public class EpisodeController {

    private final EpisodeRepository epRepo;
    private final CharacterRepository charRepo;

    public EpisodeController(EpisodeRepository epRepo, CharacterRepository charRepo) {
        this.epRepo = epRepo;
        this.charRepo = charRepo;
    }

    public String listar(Request req, Response res) throws Exception {
        return JsonUtil.toJson(epRepo.findPage(1, 20));
    }

    public String getSpeciesStats(Request req, Response res) {
        System.out.println("DEBUG: Rota /speciesByEpisode foi chamada!"); 

        res.type("application/json");
        List<Episode> todosEpisodios = epRepo.findPage(1, 1000);
        System.out.println("Encontrei " + todosEpisodios.size() + " episódios no banco.");

        List<EpisodeStatsDTO> relatorio = new ArrayList<>();

        for (Episode ep : todosEpisodios) {
            EpisodeStatsDTO stats = new EpisodeStatsDTO(ep.getId(), ep.getName(), ep.getEpisode());

            if (ep.getCharacters() != null) {
                for (String charUrl : ep.getCharacters()) {
                    Integer charId = extrairIdDaUrl(charUrl);
                    if (charId != null) {
                        Character p = charRepo.findById(charId).orElse(null);
                        if (p != null && p.getSpecies() != null) {
                            stats.addSpecies(p.getSpecies());
                        }
                    }
                }
            }
            relatorio.add(stats);
        }

        try {
            String jsonFinal = JsonUtil.toJson(relatorio);
            System.out.println("DEBUG: JSON gerado com sucesso. Tamanho: " + jsonFinal.length());
            return jsonFinal;
        } catch (Exception e) {
            e.printStackTrace();
            res.status(500);
            return "{\"erro\": \"Erro interno: " + e.getMessage() + "\"}";
        }
    }

    private Integer extrairIdDaUrl(String url) {
        try {
            if (url == null || url.isEmpty()) return null;
            String idStr = url.substring(url.lastIndexOf("/") + 1);
            return Integer.parseInt(idStr);
        } catch (Exception e) {
            return null;
        }
    }
}