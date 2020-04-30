package br.com.addario.raspberryawards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.addario.raspberryawards.pojo.Filme;
import br.com.addario.raspberryawards.services.FilmeService;

@RestController
@RequestMapping("/awards")
public class RaspberryAwardsController {

	@Autowired
	private FilmeService filmeService;

	@GetMapping("/listafilmes")
	public List<Filme> getAll() {
		return filmeService.findAll();
	}

	@PostMapping("/adicionafilme")
	public Filme create(@RequestBody Filme filme) {
		return filmeService.save(filme);
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Filme> findById(@PathVariable long id) {
		return filmeService.findById(id).map(filme -> ResponseEntity.ok().body(filme))
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Filme> update(@PathVariable("id") long id, @RequestBody Filme filme) {
		return filmeService.findById(id).map(filmeObtido -> {
			filmeObtido.setTitle(filme.getTitle());
			filmeObtido.setYear(filme.getYear());
			filmeObtido.setStudios(filme.getStudios());
			filmeObtido.setProducers(filme.getProducers());
			filmeObtido.setWinner(filme.isWinner());
			Filme filmeAlterado = filmeService.save(filmeObtido);
			return ResponseEntity.ok().body(filmeAlterado);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		return filmeService.findById(id).map(filmeASerDeletado -> {
			filmeService.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
