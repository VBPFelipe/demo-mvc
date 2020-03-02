package com.felipe.curso.springboot.services;

import java.util.List;

import com.felipe.curso.springboot.domain.Cargo;

public interface CargoService {
	
	void salvar(Cargo cargo);
	
	void editar(Cargo cargo);
	
	void excluir(Long id);
	
	Cargo buscarPorID(Long id);
	
	List<Cargo> buscarTodos();
}
