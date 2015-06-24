package br.com.ifce.jwallet.model;

import javax.validation.constraints.NotNull;

public class Credor {

	private Long id;
	@NotNull
	private String nomeFantasia;
	private String endereco;
	private Long id_usuario;
	
	public Credor(){
		
	}
	
	

	public Long getId_usuario() {
		return id_usuario;
	}



	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
