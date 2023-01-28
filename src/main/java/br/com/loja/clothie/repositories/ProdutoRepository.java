package br.com.loja.clothie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.loja.clothie.models.ProdutoModel;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Integer>{

}
