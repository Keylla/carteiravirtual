package br.com.ifce.jwallet.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ifce.jwallet.model.Credor;
import br.com.ifce.jwallet.service.CredorService;

@Controller
@RequestMapping("credor")
public class CredorController  {
	
	
	@RequestMapping("novo")
	public String nova(Model model){
		CredorService credorService = new CredorService();
		List<Credor> credores = credorService.selecionarTodos();
		model.addAttribute("credores",credores);
		return "credor/novo-credor";
	}
	
	@RequestMapping("form-altera")
	public String formAltera(Long id, Model model){
		CredorService credorService = new CredorService();
		Credor credorAltera = credorService.selecionarCredor(id);
		model.addAttribute("credor",credorAltera);		
		return "credor/editar-credor";
	}	
	
	@RequestMapping("adicionar")
	public String adicionar(@Valid Credor credor, BindingResult result ){
		
		if(result.hasFieldErrors("nomeFantasia")) {
			return "credor/novo-credor";
		}
		
		CredorService credorService = new CredorService();
		credorService.incluir(credor);
		
		return "redirect:novo";
	}
	
	
	@RequestMapping("editar")
	public String editar(Long id, String nomeFantasia, String endereco){
		Credor credor = new Credor();
		credor.setId(id);
		credor.setNomeFantasia(nomeFantasia);
		credor.setEndereco(endereco);
		CredorService credorService = new CredorService();				
		credorService.alterar(credor);
		
		return "redirect:novo";
	}
	
	@RequestMapping("listar")
	public String listar(Model model){		
		CredorService credorService = new CredorService();
		List<Credor> credores = credorService.selecionarTodos();
		model.addAttribute("credores",credores);
		return "credor/novo-credor";
		
	}
	

	@RequestMapping("remover")
	public String remover(Credor credor){		
		CredorService credorService = new CredorService();
		credorService.excluir(credor);
		return "redirect:novo";
		
	}	


}
