/**
 * 
 */

//carregando bibliotecas do google
google.load('visualization', '1', {'packages':['corechart']});

// carregar grafico ao carregar pagina		
//  		google.setOnLoadCallback(desenhaGrafico);

function desenhaGrafico() {
	var periodoInicial = document.getElementById("periodoInicial").value;
	var periodoFinal = document.getElementById("periodoFinal").value;
	
	var jsonData = $.ajax({
        url: "despesas-por-categoria",
        data: {pInicial: periodoInicial, pFinal: periodoFinal},
        dataType:"json",
        async: false
        }).responseText;
//	console.log(jsonData);

	var jsonDataDespesaPorMes = $.ajax({
        url: "despesas-por-mes",
        data: {pInicial: periodoInicial, pFinal: periodoFinal},
        dataType:"json",
        async: false
        }).responseText;
//	console.log(jsonData);
	
	
    // Create our data table out of JSON data loaded from server.
    var dataPorCategoria = new google.visualization.DataTable(jsonData);
	
	var dataPorMes = new google.visualization.DataTable(jsonDataDespesaPorMes);
    
    var options1 = {
            title: 'Despesas por categoria',
            width: 600,
            height: 400
          };
    
    var options2 = {
            title: 'Despesas por competencia',
            width: 600,
            height: 400
          };    
    // Instantiate and draw our chart, passing in some options.

	var chart1 = new google.visualization.PieChart(document.getElementById('chart_div1'));
	var chart2 = new google.visualization.LineChart(document.getElementById('chart_div2'));
    
	chart1.draw(dataPorCategoria, options1);
    chart2.draw(dataPorMes, options2);
}
