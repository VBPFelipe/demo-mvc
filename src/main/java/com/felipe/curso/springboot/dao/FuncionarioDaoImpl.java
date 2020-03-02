package com.felipe.curso.springboot.dao;

import org.springframework.stereotype.Repository;

import com.felipe.curso.springboot.domain.Funcionario;

@Repository
public class FuncionarioDaoImpl extends AbstractDao<Funcionario, Long> implements FuncionarioDao {

}
