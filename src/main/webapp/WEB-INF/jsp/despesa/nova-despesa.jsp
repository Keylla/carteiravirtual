<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon"href="<c:url value="/resource/images/icon_pagamento.ico"/>"type="image/x-icon" />

<title>Despesa</title>

<link href="<c:url value="../resource/css/bootstrap.min.css"/>"rel="stylesheet">
<link rel="stylesheet" href="../resource/css/jquery-ui.css" />
<script src="../resource/js/jquery-1.11.3.js"></script>
<script src="../resource/js/jquery-ui.js"></script>
<script type="text/javascript" src="../resource/js/datepickerPB.js" charset="UTF-8"></script>
<script type="text/javascript" src="../resource/js/formataData.js" charset="UTF-8"></script>




</head>
<body
	style="background-image: url('<c:url value="/resource/images/background6.png"/>');">

	<div class="smallDiv">
		<c:import url="../geral/cabecalho.jsp"></c:import>
	</div>


	<form action="adicionar" method="post" class="form-inline" role="form">
		<div class="container">
			<h1>Nova Despesa</h1>
			<div>
				<div>
					<input type="checkbox" autofocus="autofocus" id="flagMensal"
						name="flagMensal"
						onclick="document.getElementById('flagParcelado').disabled = this.checked;"><label>Despesa
						Fixa Mensal</label>
				</div>
				<div class="form-group"
					style="width: 768px; height: 34px; position: absolute;">
					<label>Categoria</label> <select name="categoria.id"
						class="form-control" id="categoria">
						<option></option>
						<c:forEach var="categoria" items="${categorias}">
							<option value="${categoria.id}">${categoria.descricao}</option>
						</c:forEach>
					</select>
				</div>

				<br>
				<br>
				<br>
				<div class="form-group"
					style="width: 768px; height: 34px; position: absolute;">
					<label>Credor</label> <select name="credor.id" class="form-control"
						id="credor">
						<option></option>
						<c:forEach var="credor" items="${credores}">
							<option value="${credor.id}">${credor.nomeFantasia}</option>
						</c:forEach>
					</select>
				</div>
				<br>
				<br>
				<br>

				<div class="form-group">
					<label>Valor Despesa</label><br>
					<input style="width: 178px" type="text" class="form-control"
						name="valorDespesa" required="required" autofocus="autofocus" />
				</div>
				<div class="form-group">
					<label>Data da Despesa </label><br> <input
						style="width: 178px" type="text" class="form-control calendario"
						name="dataDespesa" required="required" autofocus="autofocus"
						maxlength="10" OnKeyPress="formatar(this, '##/##/####')"
						onBlur="return doDateVenc(this.id,this.value, 4);" />
				</div>
				<div class="form-group">
					<label>Data de Vencimento </label><br> <input
						style="width: 178px" type="text" class="form-control calendario"
						name="dataVencimento" maxlength="10"
						OnKeyPress="formatar(this, '##/##/####')"
						onBlur="return doDateVenc(this.id,this.value, 4);" />
				</div>

				<div class="form-group" style="height: 61px">
					<input type="checkbox" id="flagParcelado" name="flagParcelado"
						onclick="document.getElementById('parcelas').disabled = !this.checked; document.getElementById('flagMensal').disabled = this.checked;"><label>Compra
						Parcelada</label> <br> <input style="width: 60px" type="number"
						class="form-control" id="parcelas" name="numParcelas" min="1"
						max="99" disabled="disabled">
				</div>
				<br>
				<div class="form-group">
					<label>Valor Pago</label> <br> <input style="width: 178px"
						type="text" class="form-control" name="valorPago">
				</div>
				<div class="form-group">
					<label>Data do Pagamento</label> <br> <input
						style="width: 178px" type="text" class="form-control calendario"
						name="dataPagamento" maxlength="10"
						OnKeyPress="formatar(this, '##/##/####')"
						onBlur="return doDateVenc(this.id,this.value, 4);" />
				</div>


				<div>
					<label> Detalhe</label> <br>
					<textarea class="form-control" name="detalhe" rows="5"
						style="width: 768px;"> </textarea>
					<br> <br>
					<button type="submit" class="btn btn-success">Salvar</button>
				</div>

			</div>
	</form>


</body>
</html>