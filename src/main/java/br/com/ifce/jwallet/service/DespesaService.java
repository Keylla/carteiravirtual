package br.com.ifce.jwallet.service;

import java.util.Calendar;
import java.util.List;

import br.com.ifce.jwallet.dao.DespesaDao;
import br.com.ifce.jwallet.model.Despesa;
import br.com.ifce.jwallet.model.Despesa.EstadoDespesa;

public class DespesaService {
	
	DespesaDao dao = new DespesaDao();
	
	public void incluir(Despesa despesa){
		dao.insert(despesa);
	}

	public void alterar(Despesa despesa){
		dao.update(despesa);
	}
	
	public void excluir(Despesa despesa){
		dao.delete(despesa);
	}
	
	public List<Despesa> selecionarTodos(){
		
		Calendar dataAtual = Calendar.getInstance(); 
		dataAtual.set(Calendar.MINUTE, 0);  
		dataAtual.set(Calendar.HOUR_OF_DAY, 0);  
		dataAtual.set(Calendar.SECOND, 0);  
		dataAtual.set(Calendar.MILLISECOND, 0); 
		
		List<Despesa> despesas = dao.selectAll();
		
		for (Despesa despesa : despesas) {
			if (despesa.getEstadoDespesa().toString().equals("EM_ABERTO")){
				
				if( dataAtual.after(despesa.getDataVencimento()))
					despesa.setEstadoDespesa(EstadoDespesa.valueOf("EM_ATRASO"));
			}
		}
		
		
		return despesas;
	}
	
	public List<Despesa> selecionarPorPeriodo(int mes, int ano){
		
		Calendar dataAtual = Calendar.getInstance(); 
		dataAtual.set(Calendar.MINUTE, 0);  
		dataAtual.set(Calendar.HOUR_OF_DAY, 0);  
		dataAtual.set(Calendar.SECOND, 0);  
		dataAtual.set(Calendar.MILLISECOND, 0); 
		
		List<Despesa> despesas = dao.selectByPeriodo(mes, ano);
		
		for (Despesa despesa : despesas) {
			if (despesa.getEstadoDespesa().toString().equals("EM_ABERTO")){
				
				if( dataAtual.after(despesa.getDataVencimento()))
					despesa.setEstadoDespesa(EstadoDespesa.valueOf("EM_ATRASO"));
			}
		}
		
		
		return despesas;
	}	
	
	public Despesa selecionarDespesa(Long id){
		return dao.selectById(id);
	}
	
	public void efetuarPagamento(Long id, Double valorPago, Calendar dataPagamento){
		
		Despesa desp = dao.selectById(id);
		if(desp.getValorDespesa()<=valorPago){
		desp.setEstadoDespesa(EstadoDespesa.PAGO);
		}
		if(desp.getValorDespesa()>valorPago && valorPago>0){
			desp.setEstadoDespesa(EstadoDespesa.PAGO_PARCIAL);
		}
		if(valorPago==0){
			desp.setEstadoDespesa(EstadoDespesa.EM_ABERTO);
		}
		desp.setValorPago(valorPago);
		desp.setDataPagamento(dataPagamento);
		
		
		DespesaDao dao2 = new DespesaDao();
		dao2.update(desp);
		
		
	}
	
	// usado nos graficos
	public List<Despesa> selecionarPorPeriodo(Calendar periodoInicial, Calendar periodoFinal){
		
		List<Despesa> despesas = dao.selectByPeriodo(periodoInicial, periodoFinal);
		
		return despesas;
	}	
	
	// usado nos graficos
	public List<Despesa> selecionarSomaPorCompetencia(Calendar periodoInicial, Calendar periodoFinal){
		
		List<Despesa> despesas = dao.selectCompetenciaValorByPeriodo(periodoInicial, periodoFinal);
		
		return despesas;
	}
	
	public void incluirEmLote(List <Despesa> despesas){
		dao.insertLote(despesas);
	}
	
}
