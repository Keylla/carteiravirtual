package br.com.ifce.jwallet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class Usuario {

	private Long id;
	private String nomeDoUsuario;
	private String email;
	private String userName;
	private String senha;
	private String apelido;
	private Long idGrupoUsuario;
	private String avatar;

	public String getNomeDoUsuario() {
		return nomeDoUsuario;
	}
	public void setNomeDoUsuario(String nomeDoUsuario) {
		this.nomeDoUsuario = nomeDoUsuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@JsonIgnore
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public Long getIdGrupoUsuario() {
		return idGrupoUsuario;
	}
	public void setIdGrupoUsuario(Long idGrupoUsuario) {
		this.idGrupoUsuario = idGrupoUsuario;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}