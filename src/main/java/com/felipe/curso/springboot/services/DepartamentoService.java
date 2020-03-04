package com.felipe.curso.springboot.services;

import java.util.List;

import com.felipe.curso.springboot.domain.Departamento;

public interface DepartamentoService {
	
	void salvar(Departamento departamento);
	
	void editar(Departamento departamento);
	
	void excluir(Long id);
	
	Departamento buscarPorID(Long id);
	
	List<Departamento> buscarTodos();

	boolean departamentoTemCargos(Long id);
}
