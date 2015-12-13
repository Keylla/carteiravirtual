package br.com.ifce.jwallet.service;

import java.util.List;

import br.com.ifce.jwallet.dao.GrupoUsuarioDao;
import br.com.ifce.jwallet.model.GrupoUsuario;
import br.com.ifce.jwallet.model.Usuario;

public class GrupoUsuarioService {
	
	private GrupoUsuarioDao dao = new GrupoUsuarioDao();
	
	public void incluir(GrupoUsuario grupoUsuario, Usuario usuario){
		dao.insert(grupoUsuario, usuario);
	}
	
	public void alterar(GrupoUsuario grupoUsuario){
		dao.update(grupoUsuario);
	}
	
	public void excluir(GrupoUsuario grupoUsuario){
		dao.delete(grupoUsuario);
	}
	
	public List<GrupoUsuario> selecionarTodos(){
		return dao.selectAll();
	}
	
	public void incluirUsuarioNoGrupo(Usuario usuario){
		dao.insertUsuarioNoGrupo(usuario);
	}
	
	public List<Usuario> selecionarUsuariosPorGrupo(GrupoUsuario grupoUsuario){
		return dao.selectPorGrupoUsuario(grupoUsuario);
	}
	
	public void excluirUsuarioDoGrupo(Usuario usuario){
		dao.deleteUsuarioDoGrupo(usuario);
	}
	
	public List<GrupoUsuario> selecionarGruposDoUsuario(Usuario usuario){
		return dao.selectGruposDoUsuario(usuario);
	}
}