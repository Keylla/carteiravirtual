<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Despesa</title>
<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
   <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

	
</head>
	<body style="background-image: url('<c:url value="/resource/images/background6.png"/>'>

	
	<div class="page-header">
	<c:import url="../geral/cabecalho.jsp"></c:import>
	</div>
	
	
		<form action="adicionar" method="post" class="form-inline" role="form"  >
			<div class="container">
				<h1>Nova Despesa</h1>
			<div>		
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
				 	 <label>Data da Despesa </label><br> <input  style="width: 178px"  type="text"  class="form-control" name="dataDespesa" required="required" autofocus="autofocus"/>
				</div>
				<div class="form-group">
					<label>Data de Vencimento </label><br> <input type="date" dateFormat: "dd/mm/yyyy" style="width: 178px" type="text"  class="form-control" name="dataVencimento"/>
				</div>
				<div class="form-group">
					<input type="checkbox"  name="flagParcelado" onclick="document.getElementById('parcelas').disabled = !this.checked;"><label>Compra Parcelada</label>
					<br>
					<input style="width: 60px" type="number" class="form-control" id ="parcelas" name="numParcelas" min="1" max="99" disabled="disabled">
				</div>
			    <br>
				<div class="form-group">
				 	<label>Valor Pago</label> <br> <input style="width: 178px" type="text" class="form-control" name="valorPago">		
				</div>
				<div class="form-group">
					<label>Data do Pagamento</label> <br>  <input  style="width: 178px" type="text"  class="form-control" name="dataPagamento"/>	
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