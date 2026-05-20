
package com.example.rickstarter.model;

import java.util.List;
import java.util.Objects;

public class Character {
	private Integer id;
	private String name;
	private String status;
	private String species;
	private List<String> episode;

	public Character() {
	}

	public Character(Integer id, String name, String status, String species, List<String> episode) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.species = species;
		this.episode = episode;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public List<String> getEpisode() {
		return episode;
	}

	public void setEpisode(List<String> episode) {
		this.episode = episode;
	}

	@Override
	public String toString() {
		return "Character [id=" + id + ", name=" + name + ", status=" + status + ", specie= " + species
				+ ", episodes=  " + episode + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Character character = (Character) o;
		return Objects.equals(id, character.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}