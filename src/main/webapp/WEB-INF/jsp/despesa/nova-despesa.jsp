<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
<title>Despesa</title>
 
  <link href="<c:url value="../resource/css/bootstrap.min.css"/>" rel="stylesheet">
   <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
   <script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script> 
   <script type="text/javascript" src="../resource/js/datepickerPB.js" charset="UTF-8"></script>
  <script type="text/javascript">
  var $JQuery = jQuery.noConflict()
	$JQuery(function() {
		$JQuery( ".calendario" ).datepicker({language: "pt-BR"});
	});
</script> 


 
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
	if (d.value !=""){ 
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
	
	
		<form action="adicionar" method="post" class="form-inline" role="form"  >
			<div class="container">
				<h1>Nova Despesa</h1>
			<div>
			   	<div>
				    <input type="checkbox" autofocus="autofocus"  id="flagMensal" name="flagMensal" onclick="document.getElementById('flagParcelado').disabled = this.checked;" ><label>Despesa Fixa Mensal</label>			
				</div>		
				<div  class="form-group" style="width: 768px; height: 34px; position: absolute;">
				<label>Categoria</label> 
						 <select name="categoria.id" class="form-control" id="categoria" >
						  	<option></option>
						    <c:forEach var="categoria" items="${categorias}">
						      <option value="${categoria.id}">${categoria.descricao}</option>
						     </c:forEach>
						   </select>
				</div>	
				
				<br><br><br>   
				<div  class="form-group" style="width: 768px; height: 34px; position: absolute;">		   
				<label>Credor</label> 
					    <select name="credor.id" class="form-control" id="credor" >
					    	<option></option>
					        <c:forEach var="credor" items="${credores}">
					            <option value="${credor.id}">${credor.nomeFantasia}</option>
					        </c:forEach>
					    </select>			
				</div>
				<br><br><br>
			
				<div class="form-group" >
				 	<label>Valor Despesa</label><br><input style="width: 178px" type="text" class="form-control" name="valorDespesa" required="required" autofocus="autofocus" />  
				</div>
				<div class="form-group">
				 	 <label>Data da Despesa </label><br> <input  style="width: 178px"  type="text"  class="form-control calendario" name="dataDespesa" required="required" autofocus="autofocus" maxlength="10" OnKeyPress="formatar(this, '##/##/####')" onBlur="return doDateVenc(this.id,this.value, 4);"/>
				</div>
				<div class="form-group">
					<label>Data de Vencimento </label><br> <input  style="width: 178px" type="text"  class="form-control calendario" name="dataVencimento" maxlength="10" OnKeyPress="formatar(this, '##/##/####')" onBlur="return doDateVenc(this.id,this.value, 4);"/>
				</div>
			
				<div class="form-group" style="height: 61px">
						<input type="checkbox" id="flagParcelado"  name="flagParcelado" onclick="document.getElementById('parcelas').disabled = !this.checked; document.getElementById('flagMensal').disabled = this.checked;"><label>Compra Parcelada</label>
						<br>
						<input style="width: 60px" type="number" class="form-control" id ="parcelas" name="numParcelas" min="1" max="99" disabled="disabled">
					</div>	
			    <br>
				<div class="form-group">
				 	<label>Valor Pago</label> <br> <input style="width: 178px" type="text" class="form-control" name="valorPago">		
				</div>
				<div class="form-group">
					<label>Data do Pagamento</label> <br>  <input  style="width: 178px" type="text"  class="form-control calendario"  name="dataPagamento" maxlength="10" OnKeyPress="formatar(this, '##/##/####')" onBlur="return doDateVenc(this.id,this.value, 4);"/>	
				</div>

				
		<div>
		  <label> Detalhe</label> <br> <textarea class="form-control" name="detalhe" rows="5" style="width: 768px;"> </textarea> <br>
		  <br>
		  <button type="submit" class="btn btn-success">Salvar</button> 
		</div>			
												
	</div>
	
</form>


</body>
</html>