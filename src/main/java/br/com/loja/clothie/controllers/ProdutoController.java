package br.com.loja.clothie.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/produto")
@Api(value = "API REST Produtos")
@CrossOrigin(origins="*")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	// ESTUDO - IMPLEMENTAR UMA API COM OS PRINCIPAIS MÉTODOS HTTP
	
	
	@PostMapping
	@ApiOperation(value="Salvar um produto")
	private ResponseEntity<ProdutoModel> saveProduto(@RequestBody ProdutoModel produtoModel){
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(produtoModel));
	}
	
	@GetMapping
	@ApiOperation(value="Retornar uma lista de produtos")
	private ResponseEntity<List<ProdutoModel>> getAllProducts(){
		return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());	
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value="Retornar um produto por id")
	public ResponseEntity<Object> findByProduto(@PathVariable(value="id") Integer id){
		
		Optional<ProdutoModel> produtoModelOptional = produtoService.findById(id);
		if(!produtoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto id not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(produtoModelOptional.get());
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value="Atualizar um produto")
	public ResponseEntity<Object> updateProduto(@PathVariable(value="id") Integer id, 
			@RequestBody ProdutoModel produtoModel){
		
		Optional<ProdutoModel> produtoModelOptional = produtoService.findById(id);
		if(!produtoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto id not found");
		}
		
		produtoModel.setId(produtoModelOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(produtoService.save(produtoModel));
	}
	
	
	@DeleteMapping("/{id}")
	@ApiOperation(value="Deletar um produto")
	public ResponseEntity<Object> deleteProduto(@PathVariable(value="id") Integer id){
		
		Optional<ProdutoModel> produtoModelOptional = produtoService.findById(id);
		
		if(!produtoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto id not found");
		}
		
		produtoService.delete(produtoModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Produto deleted successfully");
	}
}


