
package com.example.rickstarter.model;

import java.util.List;
import java.util.Objects;

public class Episode {
	private Integer id;
	private String name;
	private String episode;
	private List<String> characters;

	public Episode() {

	}

	public Episode(Integer id, String name, String episode, List<String> characters) {
		this.id = id;
		this.name = name;
		this.episode = episode;
		this.characters = characters;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEpisode() {
		return episode;
	}

	public void setEpisode(String episode) {
		this.episode = episode;
	}

	public List<String> getCharacters() {
		return characters;
	}

	public void setCharacters(List<String> characters) {
		this.characters = characters;
	}

	@Override
	public String toString() {
		return "Episode [id=" + id + ", name=" + name + ", code=" + episode + ", characters= " + characters + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Episode other = (Episode) o;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
