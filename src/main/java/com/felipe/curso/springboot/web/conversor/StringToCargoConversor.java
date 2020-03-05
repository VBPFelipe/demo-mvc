package com.felipe.curso.springboot.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.felipe.curso.springboot.domain.Cargo;
import com.felipe.curso.springboot.services.CargoService;

@Component
public class StringToCargoConversor implements Converter<String, Cargo>{
	
	@Autowired
	private CargoService cargoService;

	@Override
	public Cargo convert(String source) {
		if(source.isEmpty())
			return null;
		
		Long id = Long.valueOf(source);
		return cargoService.buscarPorID(id);
	}

}
