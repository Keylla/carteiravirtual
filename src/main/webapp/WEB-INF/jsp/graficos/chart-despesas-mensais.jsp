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
<link rel="icon"  href="<c:url value="/resource/images/icon_pagamento.ico"/>" type="image/x-icon"/>

<script language="JavaScript">
/* Formatação para qualquer mascara */

	function formatar(src, mask) 
	{
		var i = src.value.length;
		var saida = mask.substring(0,1);
		var texto = mask.substring(i)
			if (texto.substring(0,1) != saida) 
			{
				src.value += texto.substring(0,1);
			}
	}
	
	/* Valida Data */
	
	var reDate4 = /^((0?[1-9]|[12]\d)\/(0?[1-9]|1[0-2])|30\/(0?[13-9]|1[0-2])|31\/(0?[13578]|1[02]))\/(19|20)?\d{2}$/;
	var reDate = reDate4;
	
	function doDateVenc(Id, pStr, pFmt){
	d = document.getElementById(Id);
	if (d.value != ""){ 
		if (d.value.length < 10){
			alert("Data Inválida!\nDigite corretamente a data: dd/mm/aaaa !");
			d.value="";
			d.focus(); 
			return false;
		}else{
		
			eval("reDate = reDate" + pFmt);
				if (reDate.test(pStr)) {
				return false;
				} else if (pStr != null && pStr != "") {
				alert("ALERTA DE ERRO!!\n\n" + pStr + " NÃO é uma data válida.");
				d.value="";
				d.focus(); 
				return false;
			}
		}	
	}else{
	return false;
	}
	}
</script>
</head>

<body style="background-image: url('<c:url value="/resource/images/background6.png"/>');">  


<div class="smallDiv">
	<c:import url="../geral/cabecalho.jsp"></c:import>
</div>

	<div class="container">
		<h3>Graficos Despesas por periodo</h3>
		<p>
		
		<div class="form-group col-md-4">
		<input id="periodoInicial" value= "01/06/2015" type="text" class="form-control" placeholder="Periodo Inicial" maxlength="10" OnKeyPress="formatar(this, '##/##/####')" onBlur="return doDateVenc(this.id,this.value, 4);">
		</div>
		
		<p>
		<div class="form-group col-md-4">
		<input id="periodoFinal" value= "30/06/2015" type="text" class="form-control" placeholder="Periodo Final" maxlength="10" OnKeyPress="formatar(this, '##/##/####')" onBlur="return doDateVenc(this.id,this.value, 4);">
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

<script language="JavaScript" > 
 $(document).ready(function(){
     $("#bntGerarGelatorio").click(desenhaGrafico);
 });
</script>

</body>
</html>