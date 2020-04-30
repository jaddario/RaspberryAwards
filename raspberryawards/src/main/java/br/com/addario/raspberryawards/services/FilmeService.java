package br.com.addario.raspberryawards.services;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.addario.raspberryawards.interfaces.FilmesRepository;
import br.com.addario.raspberryawards.pojo.Filme;

@Service
public class FilmeService {

	private static final String[] HEADER = { "year", "title", "studios", "producers", "winner" };

	private Logger LOGGER = Logger.getLogger(FilmeService.class.getName());

	@Autowired
	private FilmesRepository repository;

	@PostConstruct
	public void initDataBase() {
		LOGGER.info("iniciando database");
		try {
			Iterable<CSVRecord> reader = CSVFormat.DEFAULT.withDelimiter(';').withHeader(HEADER)
					.withFirstRecordAsHeader().parse(new FileReader(new File(
							"/home/addario/Documentos/01 - Testes & Desafios Técnicos/Texo IT/Backend/movielist.csv")));

			reader.forEach(record -> {
				Filme filme = new Filme();
				filme.setYear(Integer.parseInt(record.get("year")));
				filme.setTitle(record.get("title"));
				filme.setStudios(Arrays.asList(record.get("studios").trim().split(",")));
				filme.setWinner(record.get("winner").equals("yes") ? true : false);
				this.save(filme);
			});

		} catch (IOException e) {
			e.printStackTrace();
		}

		LOGGER.info("database iniciada");
	}

	public Optional<Filme> findById(Long id) {
		return repository.findById(id);
	}

	public List<Filme> findAll() {
		return repository.findAll();
	}

	public Filme save(Filme filme) {
		return repository.save(filme);
	}

	public void deleteById(long id) {
		repository.deleteById(id);
	}

	public void deleteAll() {
		repository.deleteAll();
	}

}
