package br.com.ifce.jwallet.service.chart;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifce.jwallet.chart.datatable.DataTable;
import br.com.ifce.jwallet.model.Despesa;
import br.com.ifce.jwallet.service.DespesaService;

@RestController
public class GraficoDespesaService {
	
	@RequestMapping(value="grafico/despesas-por-categoria",produces="application/json")
	public DataTable graficoDespesasPorCategoria(String pInicial, String pFinal) throws ParseException{
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		Calendar periodoInicial = Calendar.getInstance();
		periodoInicial.setTime(df.parse(pInicial));
		
		Calendar periodoFinal = Calendar.getInstance();
		periodoFinal.setTime(df.parse(pFinal));
 
		DespesaService despService = new DespesaService();
		List<Despesa> despesasPorPeriodo = despService.selecionarPorPeriodo(periodoInicial, periodoFinal);
		
		DataTable dt = new DataTable();
		dt.addColumn("string","Categoria");
		dt.addColumn("number","Valor (R$)");
		
		for(Despesa despesa : despesasPorPeriodo){
			String categoria = despesa.getCategoria().getDescricao();
			Double valor = despesa.getValorDespesa();
			
			dt.insert(categoria,valor);
		}
		
		return dt;
	}	
	
	@RequestMapping(value="grafico/despesas-por-mes",produces="application/json")
	public DataTable graficoDespesasPorMes(String pInicial, String pFinal) throws ParseException{
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		Calendar periodoInicial = Calendar.getInstance();
		periodoInicial.setTime(df.parse(pInicial));
		
		Calendar periodoFinal = Calendar.getInstance();
		periodoFinal.setTime(df.parse(pFinal));
 
		DespesaService despService = new DespesaService();
		List<Despesa> despesasPorPeriodo = despService.selecionarSomaPorCompetencia(periodoInicial, periodoFinal);
		
		DataTable dt = new DataTable();
		dt.addColumn("string","Mês/Ano");
		dt.addColumn("number","Valor (R$)");
		
		for(Despesa despesa : despesasPorPeriodo){
			
			String competencia  = Integer.toString(despesa.getDataVencimento().get(Calendar.MONTH)+1)+"/"+Integer.toString(despesa.getDataVencimento().get(Calendar.YEAR));
			Double valor = despesa.getValorDespesa();
			
			dt.insert(competencia, valor);
		}
		
		return dt;
	}

}
