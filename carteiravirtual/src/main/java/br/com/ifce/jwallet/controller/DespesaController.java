package br.com.ifce.jwallet.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ifce.jwallet.model.Categoria;
import br.com.ifce.jwallet.model.Credor;
import br.com.ifce.jwallet.model.Despesa;

import br.com.ifce.jwallet.service.CategoriaService;
import br.com.ifce.jwallet.service.CredorService;
import br.com.ifce.jwallet.service.DespesaService;


@Controller
@RequestMapping("despesas")
public class DespesaController {
	
	@RequestMapping("nova")
	public String nova(Model model){
		CredorService cs = new CredorService();
		CategoriaService catSerc = new CategoriaService(); 
		
		List<Credor> credores =  cs.selecionarTodos();
		List<Categoria> categorias = catSerc.selecionarTodos();
		
		model.addAttribute("credores",credores);
		model.addAttribute("categorias",categorias);
		return "despesa/nova-despesa";
	}
	
	
	
	@RequestMapping("adicionar")
	public String adicionar(Despesa despesa){

		DespesaService ds = new DespesaService();
		ds.incluir(despesa);

		return "redirect:listar-todas";
	}
	
	@RequestMapping("listar-todas")
	public String listar(Model model){
		
		DespesaService service = new DespesaService();
		
		Calendar dataAtual = Calendar.getInstance();
		int mes = dataAtual.getTime().getMonth();
		List<Despesa> despesas = service.selecionarPorPeriodo(mes);
		
		model.addAttribute("despesas", despesas);
		return "despesa/listar-despesas";
	}
	
	@RequestMapping("listar-periodo")
	public String listarPorPeriodo(Model model, Integer mes){

		if (mes == null) {
			Calendar dataAtual = Calendar.getInstance(); 
			dataAtual.set(Calendar.MINUTE, 0);  
			dataAtual.set(Calendar.HOUR_OF_DAY, 0);  
			dataAtual.set(Calendar.SECOND, 0);  
			dataAtual.set(Calendar.MILLISECOND, 0); 
			
			mes = dataAtual.MONTH;
						
		}
		
		DespesaService service = new DespesaService();
		List<Despesa> despesas = service.selecionarPorPeriodo(mes);
		
		model.addAttribute("despesas", despesas);
		return "despesa/listar-despesas-por-periodo";
	}
	
	@RequestMapping("remover")
	public String remover(Despesa despesa){
		DespesaService service = new DespesaService();
		service.excluir(despesa);
		
		return "redirect:listar-todas";		
	}
	
	@RequestMapping("efetuar-pagamento")
	public String efetuarPagamento(Long id){
		DespesaService ds = new DespesaService();
		ds.efetuarPagamento(id);
		
		return "redirect:listar-todas";
	}
	
}
