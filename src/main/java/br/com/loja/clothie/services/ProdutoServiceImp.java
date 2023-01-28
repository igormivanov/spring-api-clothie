package br.com.loja.clothie.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.loja.clothie.models.ProdutoModel;
import br.com.loja.clothie.repositories.ProdutoRepository;

@Service
public class ProdutoServiceImp implements ProdutoService{

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Override
	public List<ProdutoModel> findAll() {
		return produtoRepository.findAll();
	}

	@Override
	public ProdutoModel save(ProdutoModel produtoModel) {
		return produtoRepository.save(produtoModel);
	}

	@Override
	public Optional<ProdutoModel> findById(Integer id) {
		return produtoRepository.findById(id);
	}

	@Override
	public void delete(ProdutoModel produtoModel) {
		produtoRepository.delete(produtoModel);
	}

	
}
