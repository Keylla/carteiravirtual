package br.com.ifce.jwallet.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ifce.jwallet.dao.ConnectionFactory;
import br.com.ifce.jwallet.dao.UsuarioDao;
import br.com.ifce.jwallet.exception.DaoException;
import br.com.ifce.jwallet.model.Credor;
import br.com.ifce.jwallet.model.Usuario;
import br.com.ifce.jwallet.service.CredorService;
import br.com.ifce.jwallet.service.UsuarioService;



@Controller
@RequestMapping("usuario")
public class UsuarioController {
	
	@RequestMapping("novousuario")
	public String nova(){
		return "usuario/novo-usuario";
	}
	
	@RequestMapping("grupoUsuario")
	public String grupo(){
		return "usuario/grupoUsuario";
	}
	
	@RequestMapping("adicionar")
	public String adicionar(@Valid Usuario usuario, BindingResult result ){
		
		if(result.hasFieldErrors("username")) {
			return "usuario/novo-usuario";
		}
		
		UsuarioService usuarioService = new UsuarioService();
		usuarioService.incluir(usuario);		
		return "redirect:/logon";
	}
	
	@RequestMapping("perfil")
	public String perfil(Model model){	
		HttpSession session;
		Usuario userName;
		Usuario user;
		session = AutenticacaoController.getUsuarioSessao();
		userName = (Usuario) session.getAttribute("usuarioLogado");
		Long id = userName.getId();
		UsuarioService userService = new UsuarioService();
		user = userService.selecionarUsuario(id);
		model.addAttribute("usuario",user);	
		return "usuario/perfil";	
	}
	
	@RequestMapping("editar")
	public String editar(@Valid Usuario usuario){	
		UsuarioService usuarioService = new UsuarioService();			
		usuarioService.alterar(usuario);
		return "usuario/perfil";	
	}
	
	@RequestMapping("listar")
	public void listar(Model model, Usuario user){		
		UsuarioService usuarioService = new UsuarioService();
		Usuario usuario = usuarioService.selecionarUsuario(user.getUserName());
		model.addAttribute("usuario",usuario);
		
	}

}
