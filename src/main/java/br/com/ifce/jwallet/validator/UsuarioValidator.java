package br.com.ifce.jwallet.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.ifce.jwallet.model.Usuario;
import br.com.ifce.jwallet.service.UsuarioService;

public class UsuarioValidator implements Validator{
	Usuario usuarioBanco;
	@Override
	public boolean supports(Class clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Usuario usuario = (Usuario) target;
		this.usuarioBanco = new UsuarioService().selecionarUsuario(usuario.getUserName());
		
		if (usuarioBanco == null){
			errors.rejectValue("userName", "usuario.invalido");
		}else if(!usuarioBanco.getSenha().equals(usuario.getSenha())){
			errors.rejectValue("senha", "senha.invalida");
		}
		
	}
	
	public Usuario getusuarioBanco() {
		return this.usuarioBanco;
	}
		
}
