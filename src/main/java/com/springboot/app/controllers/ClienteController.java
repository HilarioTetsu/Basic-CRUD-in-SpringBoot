package com.springboot.app.controllers;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.springboot.app.models.dao.IClienteDao;
import com.springboot.app.models.entity.Cliente;
import com.springboot.app.models.service.IClienteService;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		
		model.addAttribute("clientes", clienteService.findAll());
		return "listar";
	}
	
	
	@GetMapping("/form")
	public String crear(Model model) {
		
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		return "crear";
	}
	
	@GetMapping("/form/{id}")
	public String editar(@PathVariable(value="id") Long id,Model model) {
		
		Cliente cliente = null;
		
		if(id > 0) {
			cliente = clienteService.findOne(id);
		} else {
			return "redirect:/listar";
		}
		model.addAttribute("cliente", cliente);
		
		return "crear";
	}
	

	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
		
		//@Model Atribute en caso de que el objeto Cliente cliente se llame diferente a como se pasa al modelo en el metodo GET
		if(result.hasErrors()) {
			
			return "crear";
		}
		
		clienteService.save(cliente);
		status.setComplete();
		return "redirect:/listar";
	}
	
	@GetMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		
		if (id>0) {
			clienteService.delete(id);
		}
		
		return "redirect:/listar";
	}
	
	
}
