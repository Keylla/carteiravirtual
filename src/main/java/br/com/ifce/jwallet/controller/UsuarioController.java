package br.com.ifce.jwallet.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.ifce.jwallet.model.GrupoUsuario;
import br.com.ifce.jwallet.model.Usuario;
import br.com.ifce.jwallet.service.GrupoUsuarioService;
import br.com.ifce.jwallet.service.UsuarioService;



@Controller
@RequestMapping("usuario")
public class UsuarioController {
	
	@RequestMapping("novousuario")
	public String nova(){
		return "usuario/novo-usuario";
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

	@RequestMapping("grupoUsuario")
	public ModelAndView grupo(HttpSession session){
		
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		
		GrupoUsuarioService grpService = new GrupoUsuarioService();
		List<GrupoUsuario> grupos = grpService.selecionarGruposDoUsuario(usuario);
		ModelAndView modelAndView = new ModelAndView("usuario/grupoUsuario");
		modelAndView.addObject("grupoUsuarioList", grupos);
		return modelAndView;
	}
	
	
	@RequestMapping(value="salvarGrupo", method= RequestMethod.POST)
	public String salvarGrupo(GrupoUsuario grupoUsuario, HttpSession session){
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		
		GrupoUsuarioService grpService = new GrupoUsuarioService();
		grpService.incluir(grupoUsuario, usuario);
		return "redirect:grupoUsuario";
	}
	
	@RequestMapping(value="remover")
	public String removerGrupo(GrupoUsuario grupoUsuario){
		GrupoUsuarioService grpService = new GrupoUsuarioService();
		grpService.excluir(grupoUsuario);
		return "redirect:grupoUsuario"; 
	}
	
	@RequestMapping(value="gerenciamentoGrupoUsuariosForm")
	public String gerenciamentoGrupoUsuariosForm(){
		return "gerenciamentoGrupoUsuarios";
	}
	
}
