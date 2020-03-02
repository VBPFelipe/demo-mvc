package com.felipe.curso.springboot.dao;

import org.springframework.stereotype.Repository;

import com.felipe.curso.springboot.domain.Cargo;

@Repository
public class CargoDaoImpl extends AbstractDao<Cargo, Long> implements CargoDao {

}
