<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon"href="<c:url value="/resource/images/icon_pagamento.ico"/>"type="image/x-icon" />

<title>Despesa</title>

<link href="<c:url value="../resource/css/bootstrap.min.css"/>"rel="stylesheet">
<link rel="stylesheet" href="../resource/css/jquery-ui.css" />
<link rel="stylesheet" href="../resource/css/despesas.css" />
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


	<form class="form-inline" autocomplete="off">
		<input id="id-usuario-logado" type="hidden" value="${usuario.id}">
		
		<div class="container">
		
			<div id="alert" class="alert alert-danger alert-dismissible" role="alert">
				<strong id="msg-critica" ></strong>
			</div>
			  
			<div id="mensagem"></div>
			
			<h1 class="cabecalho">Nova Despesa</h1>
			
			<div>
				<div>
                    				
					<input type="checkbox" id="flagMensal" name="flagMensal" >
					<label for="flagMensal" >Despesa Fixa Mensal</label>
				</div>

				<br>
				<div class="form-group" style="width: 768px; height: 34px; position: absolute;">
					<label>Categoria</label> 
						<select name="categoria.id" class="form-control" id="categoria">
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
					<label>Credor</label> 
					
					<select name="credor.id" class="form-control" id="credor">
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
					<input type="text" class="form-control" id="valorDespesa" name="valorDespesa"/>
				</div>
				<div class="form-group">
					<label>Data da Despesa </label><br> 
					<input id="dataDespesa" type="text" class="form-control calendario " name="dataDespesa" maxlength="10" />
				</div>
				<div class="form-group">
					<label>Data de Vencimento </label><br>
					 <input id="dataVencimento" style="width: 178px" type="text" class="form-control calendario" name="dataVencimento" maxlength="10" />
				</div>

				<div class="form-group" style="height: 61px">
					<input type="checkbox" id="flagParcelado" name="flagParcelado">
						<label for="flagParcelado" >Compra Parcelada</label> 
						<br>
						<input style="width: 60px" type="number" class="form-control" id="numParcelas" name="numParcelas" min="1" max="99" disabled="disabled">
				</div>
				<br>
                <input id="ckDespesaGrupo" type= "checkbox">
                <label for="ckDespesaGrupo">Despesa em Grupo</label>
                <br>				
				<div class="form-group grupo-despesa" >
					<label>Grupos do usuário</label> 
					<select id="combo-grupos-usuario" class="form-control">
						<option value="-1"></option>
						<c:forEach var="grupo" items="${gruposUsuarioList}">
							<option value="${grupo.id}">${grupo.nome}</option>
						</c:forEach>
					</select>
				</div>
				
                <div class="grupo-despesa ">

	                <table class="table table-hover tabela-usuarios">
	                    <thead>
	                        <tr>
	                            <th class="th-pequeno"></th>
	                            <th>Usuário</th>
	                            <th class="th-monetario">Valor Por Usuário (R$)</th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    </tbody>
	                </table>

                </div>

				<input id="btn-salvar" class="btn btn-success" type="button" value="Salvar">

			</div>
			
	</form>
	
	<script src="../resource/js/message.js" ></script>
	<script src="../resource/js/despesas.js" ></script>
</body>
</html>