package br.com.ifce.jwallet.rest;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifce.jwallet.model.Categoria;
import br.com.ifce.jwallet.model.Despesa;
import br.com.ifce.jwallet.service.DespesaService;

@RestController
@RequestMapping("despesasRest")
public class DespesaRest {
	
	@RequestMapping(value="adicionar", method=RequestMethod.POST)
	public void adicionar(@RequestBody Despesa despesa){
		DespesaService ds = new DespesaService();
		ds.incluir(despesa);
	}
	
	@RequestMapping(value="mostrar", method=RequestMethod.GET)
	public Despesa mostrar(){
		
		Despesa despesa = new Despesa();
		Categoria categoria = new Categoria();
		categoria.setId(1L);
		categoria.setDescricao("ALIMENTACAO");
		despesa.setCategoria(categoria);
		
		return despesa;
	}

	@RequestMapping(value="adicionarLote", method=RequestMethod.POST)
	public void adicionar(@RequestBody List<Despesa> despesas){
		DespesaService ds = new DespesaService();
		ds.incluirEmLote(despesas);
	}
}
