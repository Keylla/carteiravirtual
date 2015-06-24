package br.com.ifce.jwallet.chart.datatable;

public class Column {
	
	private String label;
	private String type;
	
	Column(String label, String type){
		this.label = label;
		this.type  = type;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
