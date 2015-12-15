<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="icon" href="<c:url value="/resource/images/icon_pagamento.ico"/>" type="image/x-icon" />
		<link rel="stylesheet" href="../resource/css/jquery-ui.css" />
		<link rel="stylesheet" href="../resource/css/formData.css" />
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
		<div id="alert" class="alert alert-danger alert-dismissible" role="alert" style="display:none">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>Data Final maior que Data da Inicial!
				<br>Por favor digite corretamente as datas!
			</strong>
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
							onclick="checaDataIniFinal()" 
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
		 
		<script type="text/javascript">
			function checaDataIniFinal(){
				 var dtIni = document.getElementById('periodoInicial').value;
				 var dtFim = document.getElementById('periodoFinal').value;
				 
					 var compData = checarDatas(dtIni, dtFim);
					if(compData == false){
						document.getElementById('alert').style.display='block';
						return false;
						}
					else
						document.getElementById('alert').style.display='none';
					    $("#bntGerarGelatorio").click(desenhaGrafico);	
					    return true;
				 	
			 } ;
		</script>
	</body>
</html>