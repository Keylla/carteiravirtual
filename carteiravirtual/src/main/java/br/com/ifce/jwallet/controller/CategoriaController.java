package br.com.ifce.jwallet.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ifce.jwallet.dao.CategoriaDao;
import br.com.ifce.jwallet.model.Categoria;

@Controller
@RequestMapping("categoria")
public class CategoriaController {
	
	@RequestMapping("nova")
	public String novo(){
		return "categoria/nova-categoria";
	}
	
	@RequestMapping("form-altera")
	public String formAltera(Long id, Model model){
		CategoriaDao categoriaDao = new CategoriaDao();
		Categoria categoriaAltera = categoriaDao.selectById(id);
		model.addAttribute("categoria",categoriaAltera);		
		return "categoria/editar-categoria";
	}	
	
	@RequestMapping("adicionar")
	public String adicionar(Categoria categoria){

		CategoriaDao categoriaDao = new CategoriaDao();				
		categoriaDao.insert(categoria);
		
		return "redirect:listar";
	}
	
	@RequestMapping("editar")
	public String editar(Categoria categoria){
		
		CategoriaDao categoriaDao = new CategoriaDao();				
		categoriaDao.update(categoria);
		
		return "redirect:listar";
	}
	
	@RequestMapping("listar")
	public String listar(Model model){		
		CategoriaDao categoriaDao = new CategoriaDao();
		List<Categoria> categorias = categoriaDao.selectAll();
		model.addAttribute("categorias",categorias);
		return "categoria/listar-categoria";
		
	}
	
	@RequestMapping("remover")
	public String remover(Categoria categoria){		
		CategoriaDao categoriaDao = new CategoriaDao();
		categoriaDao.delete(categoria);
		return "redirect:listar";
		
	}		

}
