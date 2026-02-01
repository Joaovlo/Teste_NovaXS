
package com.example.rickstarter.service;

import com.example.rickstarter.model.Character;
import com.example.rickstarter.model.Episode;
import com.example.rickstarter.util.JsonUtil;
import com.google.gson.*;
import okhttp3.*;
import java.util.*;

public class RickAndMortyClient {
	private static final String BASE = "https://rickandmortyapi.com/api";
	private final OkHttpClient client = new OkHttpClient();

	public List<Character> fetchAllCharacters() throws Exception {
		List<Character> out = new ArrayList<>();
		String url = BASE + "/character";

		while (url != null) {
			Request req = new Request.Builder().url(url).get().build();
			try (Response resp = client.newCall(req).execute()) {
				String body = resp.body().string();
				JsonElement tree = JsonUtil.readTree(body);
				JsonObject root = tree.getAsJsonObject();
				JsonArray results = root.getAsJsonArray("results");
				if (results != null) {
					for (JsonElement ch : results) {
						Character c = JsonUtil.treeToValue(ch, Character.class);
						out.add(c);
					}
				}

				JsonObject info = root.has("info") ? root.getAsJsonObject("info") : null;
				JsonElement next = (info == null) ? null : info.get("next");
				url = (next == null || next.isJsonNull()) ? null : next.getAsString();
			}
		}
		return out;
	}

	public List<Episode> getListEpisodes() throws Exception {
		List<Episode> episodes = new ArrayList<>();
		String url = BASE + "/episode";

		while (url != null) {
			Request req = new Request.Builder().url(url).get().build();
			try (Response resp = client.newCall(req).execute()) {
				String body = resp.body().string();
				JsonElement tree = JsonUtil.readTree(body);
				JsonObject root = tree.getAsJsonObject();
				JsonArray results = root.getAsJsonArray("results");
				if (results != null) {
					for (JsonElement ep : results) {
						Episode e = JsonUtil.treeToValue(ep, Episode.class);
						episodes.add(e);
					}
				}
				// Paginação
				JsonObject infor = root.has("info") ? root.getAsJsonObject("info") : null;
				JsonElement next = (infor == null) ? null: infor.get("next");
				url = (next == null || next.isJsonNull()) ? null: next.getAsString();
			}
			
		}
		return episodes;
	}

	public Episode fetchEpisodeByUrl(String url) throws Exception {
		Request req = new Request.Builder().url(url).get().build();
		try (Response resp = client.newCall(req).execute()) {
			String body = resp.body().string();
			return JsonUtil.fromJson(body, Episode.class);
		}
	}
}
