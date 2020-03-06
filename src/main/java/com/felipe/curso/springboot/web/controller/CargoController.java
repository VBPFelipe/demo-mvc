package com.felipe.curso.springboot.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.felipe.curso.springboot.domain.Cargo;
import com.felipe.curso.springboot.domain.Departamento;
import com.felipe.curso.springboot.services.CargoService;
import com.felipe.curso.springboot.services.DepartamentoService;

@Controller
@RequestMapping(path = "/cargos")
public class CargoController {
	
	@Autowired
	private CargoService cargoService;
	
	@Autowired
	private DepartamentoService departamentoService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo) {
		return "cargo/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("cargos", cargoService.buscarTodos() );
		return "cargo/lista";
	}
	
	/**
	 * 
	 * @param cargo
	 * @param bindingResult - Da Classe BindingResult do Spring
	 * 		Verifica se houve algum problema referente as validações
	 * @param attr
	 * @return
	 */
	@PostMapping("/salvar")
	public String salvar(@Valid Cargo cargo, BindingResult bindingResult, RedirectAttributes attr) {
		
		if(bindingResult.hasErrors())
			return "cargo/cadastro";
		
		cargoService.salvar(cargo);
		attr.addFlashAttribute("success", "Cargo inserido com sucesso.");
		return "redirect:/cargos/cadastrar";
	}
	
	 
	/**
	 * @PathVariable -Consegue recuperar da URL o Id
	 * @return
	 */
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cargo", cargoService.buscarPorID(id) );
		return "cargo/cadastro";
	}
	
	/**
	 * 
	 * @param cargo
	 * @param attr - RedirectAttributes pois no método há uma ação de redirecionar
	 * @return
	 */
	@PostMapping("/editar")
	public String editar(@Valid Cargo cargo, BindingResult bindingResult, RedirectAttributes attr) {

		if(bindingResult.hasErrors())
			return "cargo/cadastro";
		
		cargoService.editar(cargo);
		attr.addFlashAttribute("success", "Cargo alterado com sucesso.");
		return "redirect:/cargos/cadastrar";
	}
	
	@ModelAttribute("departamentos")
	public List<Departamento> listaDeDepartamentos() {
		return departamentoService.buscarTodos();
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
				
		if(cargoService.cargoTemFuncionarios(id)) 
			model.addAttribute("fail", "Cargo não removido. Possui funcionário(s) vinculado(s).");
		else {
			cargoService.excluir(id);
			model.addAttribute("Success", "Cargo removido com sucesso.");
		}
		
		return listar(model);
	}
	
}
