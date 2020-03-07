package com.felipe.curso.springboot.web.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.felipe.curso.springboot.domain.Cargo;
import com.felipe.curso.springboot.domain.Funcionario;
import com.felipe.curso.springboot.domain.UF;
import com.felipe.curso.springboot.services.CargoService;
import com.felipe.curso.springboot.services.FuncionarioService;
import com.felipe.curso.springboot.web.validator.FuncionarioValidator;

@Controller
@RequestMapping(path = "/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private CargoService cargoService;
	
	/**
	 * 
	 * 
	 * @InitBinder - Faz com que este seja o primeiro método da classe a ser executado
	 * Ativa a validação e o Spring MVC vai na classe FuncionarioValidator antes de liberar
	 * o acesso da requisição ao método Salvar/editar;
	 * 
	 * @param binder -  
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators( new FuncionarioValidator() );
	}
	
	@GetMapping("/cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return "funcionario/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.buscarTodos());
		return "funcionario/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors()) {
			return "funcionario/cadastro";
		}
		
		funcionarioService.salvar(funcionario);
		attr.addFlashAttribute("success", "Funcionário inserido com sucesso.");
		return "redirect:/funcionarios/cadastrar";
	}
	
	@ModelAttribute("cargos")
	public List<Cargo> getCargos(){
		return cargoService.buscarTodos();
	}
	
	@ModelAttribute("ufs")
	public UF[] getUFs() {
		return UF.values();
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap map) {
		map.addAttribute("funcionario", funcionarioService.buscarPorID(id));
		return "funcionario/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr) {
		
		if(result.hasErrors())
			return "funcionario/cadastro";
		
		funcionarioService.editar(funcionario);
		attr.addFlashAttribute("success", "Funcionario alterado com sucesso");
		return "redirect:/funcionarios/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		funcionarioService.excluir(id);
		attr.addFlashAttribute("success", "Funcionario removido com sucesso");
		return "redirect:/funcionarios/listar";
	}
	
	/**
	 * RequestParam - O valor do nome não vem como um trecho da URL, mas como atributo
	 * 		parâmetro da requisição 
	 * @return
	 */
	@GetMapping("/buscar/nome")
	public String getPorNome(@RequestParam("nome") String nome, ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.buscaPorNome(nome) );
		return "/funcionario/lista";
	}
	
	@GetMapping("/buscar/cargo")
	public String getPorCargo(@RequestParam("id") Long id, ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.buscaPorCargo(id));
		return "/funcionario/lista";
	}
	
	@GetMapping("/buscar/data")
	public String getPorDatas(@RequestParam("entrada") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate entrada,
								@RequestParam("saida") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate saida, 
								ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.buscaPorDatas(entrada, saida));
		return "/funcionario/lista";
	}
}
