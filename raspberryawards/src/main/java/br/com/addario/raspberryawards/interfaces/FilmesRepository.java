package br.com.addario.raspberryawards.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.addario.raspberryawards.pojo.Filme;

public interface FilmesRepository extends JpaRepository<Filme, Long>{

}
