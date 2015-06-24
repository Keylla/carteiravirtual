package br.com.ifce.jwallet.service;

import br.com.ifce.jwallet.exception.AutenticaException;
import br.com.ifce.jwallet.model.Usuario;

public class AutenticacaoService {

	private UsuarioService service = new UsuarioService();
	
	public boolean autenticaUsuario(Usuario usuario) throws AutenticaException {
		Usuario usuarioBanco = service.selecionarUsuario(usuario.getUserName());
		
		if (usuarioBanco == null){
			throw new AutenticaException("Usuario invalido.");
		}else if(!usuarioBanco.getSenha().equals(usuario.getSenha())){
			throw new AutenticaException("Senha inválida.");
		}else{
			return true;
		}
		
	}

}
