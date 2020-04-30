package br.com.addario.raspberryawards.pojo;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "filme")
public class Filme {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "year", nullable = false)
	private int year;
	@Column(name = "title", nullable = false)
	private String title;

	@ElementCollection
	@CollectionTable(name = "studios", joinColumns = @JoinColumn(name = "filme_id"))
	private List<String> studios;

	@ElementCollection
	@CollectionTable(name = "producers", joinColumns = @JoinColumn(name = "filme_id"))
	private List<String> producers;

	@Column(name = "winner", nullable = true)
	private boolean winner;

	public Filme() {
	}

	public Filme(long id, int year, String title, List<String> studios, List<String> producers, boolean winner) {
		this.id = id;
		this.year = year;
		this.title = title;
		this.studios = studios;
		this.producers = producers;
		this.winner = winner;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getStudios() {
		return studios;
	}

	public void setStudios(List<String> studios) {
		this.studios = studios;
	}

	public List<String> getProducers() {
		return producers;
	}

	public void setProducers(List<String> producers) {
		this.producers = producers;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

}
