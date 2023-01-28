package br.com.loja.clothie.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.loja.clothie.models.ProdutoModel;
import br.com.loja.clothie.services.ProdutoService;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	// ESTUDO - IMPLEMENTAR UM CRUD
	
	// CREATE
	@PostMapping(value = "/salvar")
	private ResponseEntity<ProdutoModel> saveProduto(@RequestBody ProdutoModel produtoModel){
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(produtoModel));
	}
	
	// READ
	@GetMapping(value = "/listar")
	private ResponseEntity<List<ProdutoModel>> getAllProducts(){
		return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());	
	}
	
	// READ 2
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> findByProduto(@PathVariable(value="id") Integer id){
		
		Optional<ProdutoModel> produtoModelOptional = produtoService.findById(id);
		if(!produtoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O id fornecido não foi encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(produtoModelOptional.get());
	}
	
	// UPDATE
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> updateProduto(@PathVariable(value="id") Integer id, 
			@RequestBody ProdutoModel produtoModel){
		
		Optional<ProdutoModel> produtoModelOptional = produtoService.findById(id);
		if(!produtoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O id fornecido não foi encontrado");
		}
		
		produtoModel.setId(produtoModelOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(produtoService.save(produtoModel));
	}
	
	// DELETE
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteProduto(@PathVariable(value="id") Integer id){
		
		Optional<ProdutoModel> produtoModelOptional = produtoService.findById(id);
		
		if(!produtoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O id fornecido não foi encontrado");
		}
		
		produtoService.delete(produtoModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("DELETADO");
	}
}


