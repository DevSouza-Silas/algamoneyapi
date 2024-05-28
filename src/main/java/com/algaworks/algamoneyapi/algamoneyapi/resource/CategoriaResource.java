package com.algaworks.algamoneyapi.algamoneyapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoneyapi.algamoneyapi.model.Categoria;
import com.algaworks.algamoneyapi.algamoneyapi.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaRepository repository;

	@GetMapping
	public List<Categoria> list(){
		return repository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void criar(@RequestBody Categoria categoria) {
		repository.save(categoria);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
	return this.repository.findById(codigo)
	  .map(categoria -> ResponseEntity.ok(categoria))
	  .orElse(ResponseEntity.notFound().build());
	}
}
