package br.com.ifce.jwallet.service;

import java.util.List;

import br.com.ifce.jwallet.dao.UsuarioDao;
import br.com.ifce.jwallet.model.Usuario;

public class UsuarioService {
	
	UsuarioDao dao = new UsuarioDao();

	public void incluir(Usuario usuario){
		dao.insert(usuario);
	}
	
	public void alterar(Usuario usuario){
		dao.update(usuario);
	}
	
	public void excluir(Usuario usuario){
		dao.delete(usuario);
	}
	
	public List<Usuario> selecionarTodos(){
		return dao.selectAll();
	}
	
	public Usuario selecionarUsuario(Long id){
		return dao.selectById(id);
	}
	
	public Usuario selecionarUsuario(String userName){
		return dao.selectByUserName(userName);
	}
}
