package com.felipe.curso.springboot.services;

import java.time.LocalDate;
import java.util.List;

import com.felipe.curso.springboot.domain.Funcionario;

public interface FuncionarioService {
	
	void salvar(Funcionario funcionario);
	
	void editar(Funcionario funcionario);
	
	void excluir(Long id);
	
	Funcionario buscarPorID(Long id);
	
	List<Funcionario> buscarTodos();

	List<Funcionario> buscaPorNome(String nome);

	List<Funcionario> buscaPorCargo(Long id);

	List<Funcionario> buscaPorDatas(LocalDate entrada, LocalDate saida);
}
