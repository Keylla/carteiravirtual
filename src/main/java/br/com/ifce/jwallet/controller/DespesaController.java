package br.com.ifce.jwallet.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ifce.jwallet.model.Categoria;
import br.com.ifce.jwallet.model.Credor;
import br.com.ifce.jwallet.model.Despesa;
import br.com.ifce.jwallet.model.GrupoUsuario;
import br.com.ifce.jwallet.model.Usuario;
import br.com.ifce.jwallet.service.CategoriaService;
import br.com.ifce.jwallet.service.CredorService;
import br.com.ifce.jwallet.service.DespesaService;
import br.com.ifce.jwallet.service.GrupoUsuarioService;


@Controller
@RequestMapping("despesas")
public class DespesaController {

	@RequestMapping("nova")
	public String nova(Model model, HttpSession session){
		
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		CredorService cs = new CredorService();
		CategoriaService catSerc = new CategoriaService(); 
		GrupoUsuarioService grupoUsuarioService = new GrupoUsuarioService();
		
		List<GrupoUsuario> gruposUsuario = grupoUsuarioService.selecionarGruposDoUsuario(usuario);
		
		List<Credor> credores =  cs.selecionarTodos();
		List<Categoria> categorias = catSerc.selecionarTodos();
		
		model.addAttribute("credores",credores);
		model.addAttribute("categorias",categorias);
		model.addAttribute("gruposUsuarioList", gruposUsuario);
		model.addAttribute("usuario", usuario);
		
		return "despesa/nova-despesa";
	}
	
	@RequestMapping(value="adicionar", method=RequestMethod.POST)
	public void adicionar(Despesa despesa){
		DespesaService ds = new DespesaService();
		ds.incluir(despesa);
	}
	
	@RequestMapping("listar-todas")
	public String listar(Model model, Integer mes, Integer ano){
		
		
		if (mes == null) {
			Calendar dataAtual = Calendar.getInstance(); 
			dataAtual.set(Calendar.MINUTE, 0);  
			dataAtual.set(Calendar.HOUR_OF_DAY, 0);  
			dataAtual.set(Calendar.SECOND, 0);  
			dataAtual.set(Calendar.MILLISECOND, 0); 
			ano = dataAtual.YEAR;
			mes = dataAtual.MONTH;
			mes = mes-1;
						
		}
		//Calendar dataAtual = Calendar.getInstance();
		//int mes = dataAtual.getTime().getMonth();
		DespesaService service = new DespesaService();
		List<Despesa> despesas = service.selecionarPorPeriodo(mes,ano);
		
		model.addAttribute("despesas", despesas);
		return "despesa/listar-despesas";
	}
	
	@RequestMapping("listar-periodo")
	public String listarPorPeriodo(Model model, Integer mes, Integer ano){

		if (mes == null) {
			Calendar dataAtual = Calendar.getInstance(); 
			dataAtual.set(Calendar.MINUTE, 0);  
			dataAtual.set(Calendar.HOUR_OF_DAY, 0);  
			dataAtual.set(Calendar.SECOND, 0);  
			dataAtual.set(Calendar.MILLISECOND, 0); 
			
			mes = dataAtual.MONTH;
			ano = dataAtual.YEAR;
			mes = mes-1;
						
		}
		
		DespesaService service = new DespesaService();
		List<Despesa> despesas = service.selecionarPorPeriodo(mes,ano);
		
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
	public String efetuarPagamento(Long id, Double valorPagamento, String dataPagamento){
		Calendar dataPag = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		Date date = new Date();
		try {
			date = formatter.parse(dataPagamento);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		dataPag.setTime(date);
		DespesaService ds = new DespesaService();
		ds.efetuarPagamento(id, valorPagamento, dataPag);
		
		return "redirect:listar-todas";
	}
	
}
