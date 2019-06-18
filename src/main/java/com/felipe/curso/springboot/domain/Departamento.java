package com.felipe.curso.springboot.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="tb_departamentos")
public class Departamento extends AbstractEntity<Long> {
	
	@Column(name="dep_nome", nullable=false, unique= true, length=60)
	private String nome;
	
	//Relação bidirecional. O lado forte da relação é mapeado
	@OneToMany(mappedBy= "departamento")
	private List<Cargo> cargos = new ArrayList<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

}
