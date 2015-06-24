<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Grafico</title>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="<c:url value="/resource/js/charts.js"/>"></script>

</head>

<body style="background-image: url('<c:url value="/resource/images/background6.png"/>');">  


<div class="page-header">
	<c:import url="../geral/cabecalho.jsp"></c:import>
</div>

	<div class="container">
		<h3>Graficos Despesas por periodo</h3>
		<p>
		
		<div class="form-group col-md-4">
		<input id="periodoInicial" type="text" class="form-control" placeholder="Periodo Inicial" value="01/04/2015">
		</div>
		
		<p>
		<div class="form-group col-md-4">
		<input id="periodoFinal" type="text" class="form-control" placeholder="Periodo Final" value="30/04/2015">
		</div>
		
		<p>
		<button id="bntGerarGelatorio"  type="submit" class="btn btn-success">Gerar Graficos</button>
		
	</div>

	<div class="row">
		<div id="chart_left" class="col-md-5">
			<div id=chart_div1>
			</div>
		</div>
		
		<div id="chart_right" class="col-md-7">
			<div id=chart_div2>
			</div>		
		</div>		
	</div>

<script> -->
 $(document).ready(function(){
     $("#bntGerarGelatorio").click(desenhaGrafico);
 });
</script>

</body>
</html>