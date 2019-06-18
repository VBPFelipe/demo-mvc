package com.felipe.curso.springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="tb_enderecos")
public class Endereco extends AbstractEntity<Long>{
	
	@Column(name="end_logradouro", nullable = false)
	private String logradouro;

	@Column(name="end_bairro", nullable = false)
	private String bairro;

	@Column(name="end_cidade", nullable = false)
	private String cidade;

	@Column(name="end_uf", nullable = false, length=2)
	@Enumerated(EnumType.STRING)
	private UF uf;
	
	@Column(name="end_cep", nullable = false, length=9)
	private String cep;
	
	@Column(name="end_numero", nullable = false, length=5)	
	private Integer numero;
	
	@Column(name="end_complemento")
	private String complemento;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	
}
