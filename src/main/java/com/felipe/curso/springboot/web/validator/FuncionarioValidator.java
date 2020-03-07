package com.felipe.curso.springboot.web.validator;

import java.time.LocalDate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.felipe.curso.springboot.domain.Funcionario;

public class FuncionarioValidator implements Validator{

	/**
	 * Tem como princípio validar o objeto enviado à partir do formulário,
	 * com aquele que a classe deve validar.
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		
		return Funcionario.class.equals(clazz);
	}

	
	/**
	 * Object target - Objeto recebido do formulário
	 * Error errors - Objeto com o qual será lidado com a validação
	 */
	@Override
	public void validate(Object target, Errors errors) {
		
		Funcionario f = (Funcionario) target;
		
		LocalDate entrada = f.getDataEntrada();
		
		if(f.getDataSaida() != null) {
			if(f.getDataSaida().isBefore(entrada) ) {
				errors.rejectValue("dataSaida", "PosteriorDataEntrada.funcionario.dataSaida");
			}
		}
	}

}
