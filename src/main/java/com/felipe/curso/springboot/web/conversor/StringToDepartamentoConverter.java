package com.felipe.curso.springboot.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.felipe.curso.springboot.domain.Departamento;
import com.felipe.curso.springboot.services.DepartamentoService;

@Component
public class StringToDepartamentoConverter implements Converter<String, Departamento>{
	
	@Autowired
	private DepartamentoService departamentoService;

	@Override
	public Departamento convert(String source) {
		if(source.isEmpty())
			return null;
		
		Long id= Long.valueOf(source);
		return departamentoService.buscarPorID(id);
	}
	
	
}
