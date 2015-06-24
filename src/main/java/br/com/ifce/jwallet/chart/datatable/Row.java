package br.com.ifce.jwallet.chart.datatable;

import java.util.ArrayList;
import java.util.List;

public class Row {
	
	private List<Cell> c = new ArrayList<Cell>();
	
	Row(Object...values){
		
		for (Object value : values) {
			c.add(new Cell(value));
		}
		
	}

	public List<Cell> getC() {
		return c;
	}

}
