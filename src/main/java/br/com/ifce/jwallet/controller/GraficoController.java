package br.com.ifce.jwallet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GraficoController {
	
	@RequestMapping(value="grafico/despesas-mensais")
	public String graficoPie(){
		return "graficos/chart-despesas-mensais";
	}	
}
