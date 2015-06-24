package br.com.ifce.jwallet.service;

import java.util.List;

import br.com.ifce.jwallet.dao.CredorDao;
import br.com.ifce.jwallet.model.Credor;

public class CredorService {
	
	CredorDao dao = new CredorDao();

	public void incluir(Credor credor){
		dao.insert(credor);
	}
	
	public void alterar(Credor credor){
		dao.update(credor);
	}
	
	public void excluir(Credor credor){
		dao.delete(credor);
	}
	
	public List<Credor> selecionarTodos(){
		return dao.selectAll();
	}
	
	public Credor selecionarCredor(Long id){
		return dao.selectById(id);
	}
}
