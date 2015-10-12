package br.com.ifce.jwallet.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ifce.jwallet.dao.CategoriaDao;
import br.com.ifce.jwallet.model.Categoria;
import br.com.ifce.jwallet.service.CategoriaService;

@Controller
@RequestMapping("categoria")
public class CategoriaController {
	
	@RequestMapping("nova")
	public String novo(Model model){
		CategoriaService categoriaService = new CategoriaService();
		List<Categoria> categorias = categoriaService.selecionarTodos();
		model.addAttribute("categorias", categorias);
		return "categoria/nova-categoria";
	}
	
		
	@RequestMapping("adicionar")
	public String adicionar(Categoria categoria){

		CategoriaDao categoriaDao = new CategoriaDao();				
		categoriaDao.insert(categoria);
		
		return "redirect:nova";
	}
	
	@RequestMapping("editar")
	public String editar(Long id, String descricao){
		Categoria categoria = new Categoria();
		categoria.setId(id);
		categoria.setDescricao(descricao);
		CategoriaDao categoriaDao = new CategoriaDao();				
		categoriaDao.update(categoria);
		
		return "redirect:nova";
	}
	
		
	@RequestMapping("remover")
	public String remover(Categoria categoria){		
		CategoriaDao categoriaDao = new CategoriaDao();
		categoriaDao.delete(categoria);
		return "redirect:nova";
		
	}		

}
