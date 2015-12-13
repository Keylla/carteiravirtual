package br.com.ifce.jwallet.rest;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifce.jwallet.model.GrupoUsuario;
import br.com.ifce.jwallet.model.Usuario;
import br.com.ifce.jwallet.service.GrupoUsuarioService;
import br.com.ifce.jwallet.service.UsuarioService;

@RestController
@RequestMapping("grupoUsuario")
public class GrupoUsuarioRest {
	
	@RequestMapping("usuarioEmail")
	public Usuario buscarUsuarioPorEmail(Usuario usuario){
		UsuarioService usuarioService = new UsuarioService();
		return usuarioService.selecionarPorEmail(usuario.getEmail());
	}

	@RequestMapping(value="usuario/incluir", method=RequestMethod.POST)
	public void incluirUsuarioNoGrupo(Usuario usuario){
		GrupoUsuarioService grupoUsrService = new GrupoUsuarioService();
		grupoUsrService.incluirUsuarioNoGrupo(usuario);
	}
	
	@RequestMapping(value="usuario/excluir", method=RequestMethod.POST)
	public void excluirUsuarioDoGrupo(Usuario usuario){
		GrupoUsuarioService grupoUsrService = new GrupoUsuarioService();
		grupoUsrService.excluirUsuarioDoGrupo(usuario);
	}	

	@RequestMapping(value="usuario/listarPorGrupo", method=RequestMethod.GET)
	public List<Usuario> selecionarUsuariosPorGrupo(GrupoUsuario grupoUsuario){
		GrupoUsuarioService grupoUsrService = new GrupoUsuarioService();
		return grupoUsrService.selecionarUsuariosPorGrupo(grupoUsuario);
	}
	
}
