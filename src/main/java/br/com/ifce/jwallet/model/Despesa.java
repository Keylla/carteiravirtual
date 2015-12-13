package br.com.ifce.jwallet.model;

import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


public class Despesa {
	
	public enum EstadoDespesa {
		
		EM_ABERTO,EM_ATRASO,PAGO,PAGO_PARCIAL
		
	}

	private Long id;
	private Credor credor;
    private Categoria categoria;
	private EstadoDespesa estadoDespesa;
	private String detalhe;
	private Double valorDespesa;
	private Double valorPago;
	private String flagParcelado;
	private String flagMensal;
	private int numParcelas;
	private Usuario usuario;
	@DateTimeFormat(pattern = "dd/MM/yyyy")	
	private Calendar dataDespesa;
	@DateTimeFormat(pattern = "dd/MM/yyyy")	
	private Calendar dataVencimento;
	@DateTimeFormat(pattern = "dd/MM/yyyy")	
	private Calendar dataPagamento;
	private Long idUsuario;
	
	
	

	public String getFlagMensal() {
		return flagMensal;
	}

	public void setFlagMensal(String flagMensal) {
		this.flagMensal = flagMensal;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

	public String getFlagParcelado() {
		return flagParcelado;
	}

	public void setFlagParcelado(String flagParcelado) {
		this.flagParcelado = flagParcelado;
	}

	public int getNumParcelas() {
		return numParcelas;
	}

	public void setNumParcelas(int numParcelas) {
		this.numParcelas = numParcelas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@JsonFormat(pattern="dd/MM/yyyy", shape=JsonFormat.Shape.STRING, timezone="EST")
	public Calendar getDataDespesa() {
		return dataDespesa;
	}

	public void setDataDespesa(Calendar dataDespesa) {
		this.dataDespesa = dataDespesa;
	}

	public Calendar getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Credor getCredor() {
		return credor;
	}

	public void setCredor(Credor credor) {
		this.credor = credor;
	}


	public Double getValorDespesa() {
		return valorDespesa;
	}

	public void setValorDespesa(Double valorDespesa) {
		this.valorDespesa = valorDespesa;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}

	@JsonFormat(pattern="dd/MM/yyyy", shape=JsonFormat.Shape.STRING, timezone="EST")
	public Calendar getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Calendar dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public EstadoDespesa getEstadoDespesa() {
		return estadoDespesa;
	}

	public void setEstadoDespesa(EstadoDespesa estadoDespesa) {
		this.estadoDespesa = estadoDespesa;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	
}
