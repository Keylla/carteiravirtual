package br.com.ifce.jwallet.main;

import java.util.Calendar;

public class JDBCTest {
	
	
	public static void main(String[] args) {
		

	Calendar periodoInicial = Calendar.getInstance();
	periodoInicial.set(Calendar.DAY_OF_MONTH,1);
	periodoInicial.set(Calendar.MONDAY,1);
	
	Calendar periodoFinal = Calendar.getInstance();
	periodoFinal.set(Calendar.DAY_OF_MONTH,1);
	periodoFinal.set(Calendar.MONDAY,1);
	
	
	periodoFinal.add(Calendar.MONTH, 1);
	periodoFinal.add(Calendar.DATE, -1);
	
	
	System.out.println("Periodo Inicial: " +new java.sql.Date(periodoInicial.getTimeInMillis()));
	System.out.println("Periodo Final: " +new java.sql.Date(periodoFinal.getTimeInMillis()));
		
		
		
	}
	

		
}
