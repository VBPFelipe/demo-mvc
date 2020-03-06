package com.felipe.curso.springboot.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.felipe.curso.springboot.domain.Departamento;
import com.felipe.curso.springboot.services.DepartamentoService;

@Controller
@RequestMapping(path = "/departamentos")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService departamentoService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Departamento departamento) {
		return "departamento/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("departamentos", departamentoService.buscarTodos());
		return "departamento/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Departamento departamento, BindingResult bindingResult, RedirectAttributes attr) {
		
		if(bindingResult.hasErrors())
			return "departamento/cadastro";
		
		departamentoService.salvar(departamento);
		attr.addFlashAttribute("success", "Departamento inserido com sucesso.");
		return "redirect:/departamentos/cadastrar";
	}
	
	 
	/**
	 * @PathVariable -Consegue recuperar da URL o Id
	 * @return
	 */
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("departamento", departamentoService.buscarPorID(id) );
		return "departamento/cadastro";
	}
	
	/**
	 * 
	 * @param departamento
	 * @param attr - RedirectAttributes pois no método há uma ação de redirecionar
	 * @return
	 */
	@PostMapping("/editar")
	public String editar(@Valid Departamento departamento, BindingResult bindingResult, RedirectAttributes attr) {
		
		if(bindingResult.hasErrors())
			return "departamento/cadastro";
		
		departamentoService.editar(departamento);
		attr.addFlashAttribute("success", "Departamento alterado com sucesso.");
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
				
		if(departamentoService.departamentoTemCargos(id)) 
			model.addAttribute("fail", "Departamento não removido. Possui cargo(s) vinculado(s).");
		else {
			departamentoService.excluir(id);
			model.addAttribute("Success", "Departamento removido com sucesso.");
		}
//		return "redirect:departamentos/listar";
		return listar(model);
	}
}
