package br.com.ifce.jwallet.chart.datatable;

import java.util.ArrayList;
import java.util.List;

public class DataTable {
	
	private List<Column> cols;
	private List<Row> rows;
	
	public DataTable(){
		cols = new ArrayList<Column>();
		rows = new ArrayList<Row>();
	}

	public List<Column> getCols() {
		return cols;
	}
	public void setCols(List<Column> cols) {
		this.cols = cols;
	}
	
	public void addColumn(String type, String label){
		cols.add(new Column(label, type));
	}
	
	public void insert(Object...values){
		Row row = new Row(values);
		getRows().add(row);
	}

	public List<Row> getRows() {
		return rows;
	}

}
