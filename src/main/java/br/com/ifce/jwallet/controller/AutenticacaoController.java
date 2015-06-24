package br.com.ifce.jwallet.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ifce.jwallet.model.Usuario;
import br.com.ifce.jwallet.validator.UsuarioValidator;

@Controller
public class AutenticacaoController  {
	
	private static HttpSession session;

	@Autowired
	public AutenticacaoController(HttpSession session){
		this.session = session;
		
	}
	
	private void removerUsuarioSessao(){
		session.removeAttribute("usuarioLogado");
	}
	
	private void incluirUsuarioSessao(Usuario usuario){
		session.setAttribute("usuarioLogado", usuario);
	}
	
	public static HttpSession getUsuarioSessao(){
		return session;
	}
	
	@RequestMapping(value="/logon",method=RequestMethod.GET)
	public String loginForm(){
		return "/acesso/logon";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String autentica(@Valid Usuario usuario,BindingResult result){
		
		UsuarioValidator usuarioValidator = new UsuarioValidator();
		usuarioValidator.validate(usuario, result);
		
		if (result.hasErrors()){
			
			List<ObjectError> erros = result.getAllErrors();
			System.out.println(erros.get(0).getCode().toString());
			System.out.println(erros.get(0).getDefaultMessage());
					
			return "/acesso/logon";
		}else{
			incluirUsuarioSessao(usuarioValidator.getusuarioBanco());
			return "redirect:/despesas/listar-todas";
		}

	}
	
	@RequestMapping(value="/logout")
	public String logout(){
		removerUsuarioSessao();
		return "redirect:/logon";
	}
	
}
