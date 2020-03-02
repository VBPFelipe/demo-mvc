package com.felipe.curso.springboot.dao;

import java.util.List;

import com.felipe.curso.springboot.domain.Cargo;

public interface CargoDao {

	void save(Cargo cargo);
	
	void update(Cargo cargo);
	
	void delete(Long id);
	
	Cargo findById(Long id);
	
	List<Cargo> findAll();
}
