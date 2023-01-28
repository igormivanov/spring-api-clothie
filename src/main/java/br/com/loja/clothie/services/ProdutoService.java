package br.com.loja.clothie.services;

import java.util.List;
import java.util.Optional;

import br.com.loja.clothie.models.ProdutoModel;


public interface ProdutoService{

	List<ProdutoModel> findAll();
	
	ProdutoModel save(ProdutoModel produtoModel);
	
	Optional<ProdutoModel> findById(Integer id);
	
	void delete(ProdutoModel produtoModel);
}
