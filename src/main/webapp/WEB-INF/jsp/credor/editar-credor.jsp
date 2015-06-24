<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
<title>Cadastro de Credor</title>
</head>
<body>

<div class="page-header">
<c:import url="../geral/cabecalho.jsp"></c:import>
</div>

<div class="container">

<h1>Novo Credor</h1>

<form action="editar" method="post">

	<table >
	
	<tr>
		<td>  Codigo </td>
		<td>  <input type="text" class="form-control" name="id" readonly value="${credor.id}" > </td>
	</tr>
	

		<tr>
		<td>  Nome Fantasia </td>
		<td>  <input type="text" class="form-control" name="nomeFantasia" value="${credor.nomeFantasia}">  </td>
		</tr>
	
		<tr>
		<td>  Endereço </td>
		<td>  <input type="text" class="form-control" name="endereco" value="${credor.endereco}"> <br> </td>
		</tr>
							
	</table>


<input type="submit" class="btn btn-success" value=Salvar >

</form>

</div>

</body>
</html>