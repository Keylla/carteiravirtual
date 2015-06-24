package br.com.ifce.jwallet.chart.datatable;

public enum ColumnType {

	STRING("string"), NUMBER("number");
	
	private String value;
	
	private ColumnType(String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
	
}
