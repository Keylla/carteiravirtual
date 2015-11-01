<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="icon" href="<c:url value="/resource/images/icon_pagamento.ico"/>" type="image/x-icon" />
		<link rel="stylesheet" href="../resource/css/jquery-ui.css" />
		<script type="text/javascript" src="https://www.google.com/jsapi"></script>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="<c:url value="/resource/js/charts.js"/>"></script>
		<script src="../resource/js/jquery-1.11.3.js"></script>
		<script src="../resource/js/jquery-ui.js"></script>
		<script type="text/javascript" src="../resource/js/datepickerPB.js" charset="UTF-8"></script>
		<script type="text/javascript" src="../resource/js/formataData.js" charset="UTF-8"></script>
		<title>Gráfico</title>
	</head>

	<body style="background-image: url('<c:url value="/resource/images/background6.png"/>');">
		<div class="smallDiv">
			<c:import url="../geral/cabecalho.jsp"></c:import>
		</div>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="container">
					<h3>Gráficos Despesas por período</h3>
					<p>
					<div class="form-group col-md-4">
						<input id="periodoInicial" 
							   type="text" 
						       class="form-control calendario"
						       placeholder="Periodo Inicial" 
						       maxlength="10"
						       OnKeyPress="formatar(this, '##/##/####')"
						       onBlur="return doDateVenc(this.id,this.value, 4);"/>
					</div>
					<p>
					<div class="form-group col-md-4">
						<input id="periodoFinal"  
							   type="text" 
						       class="form-control calendario" 
						       placeholder="Periodo Final" 
						       maxlength="10"
							   OnKeyPress="formatar(this, '##/##/####')"
						       onBlur="return doDateVenc(this.id,this.value, 4);"/>
					</div>
					<p>
					<button id="bntGerarGelatorio"
							onclick="checarDatas(document.getElementById('periodoInicial').value,document.getElementById('periodoFinal').value,1)" 
							type="submit"
							class="btn btn-success">
						Gerar Gráficos
					</button>
				</div>
			</div>
			<div class="panel-body">
				<div class="row">
					<div id="chart_left" class="col-md-5">
						<div id=chart_div1></div>
					</div>
		
					<div id="chart_right" class="col-md-7">
						<div id=chart_div2></div>
					</div>
				</div>
			</div>
		</div>
		 

	</body>
</html>