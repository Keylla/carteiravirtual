package br.com.ifce.jwallet.service;

import java.util.List;

import br.com.ifce.jwallet.dao.CategoriaDao;
import br.com.ifce.jwallet.model.Categoria;

public class CategoriaService {
	
	CategoriaDao dao = new CategoriaDao();
	
	public void incluir(Categoria categoria){
		dao.insert(categoria);
	}
	
	
	public void alterar(Categoria categoria){
		dao.update(categoria);
	}
	
	public void excluir(Categoria categoria){
		dao.delete(categoria);
	}
	
	public List<Categoria> selecionarTodos(){
		return dao.selectAll();
	}
	
	public Categoria selecionarCategoria(Long id){
		return dao.selectById(id);
	}

}
