<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resource/css/bootstrap.min.css"/>" rel="stylesheet">
<link rel="icon"  href="<c:url value="/resource/images/icon_pagamento.ico"/>" type="image/x-icon"/>

<title>Cadastro de Credor</title>
</head>
<body style="background-image: url('<c:url value="/resource/images/background6.png"/>');">  


<div class="smallDiv">
<c:import url="../geral/cabecalho.jsp"></c:import>
</div>

<div class="container">
<h1>Novo Credor</h1>

<form action="adicionar" method="post">



					<table >
					
					<tr>
						<td>  Código </td>
						<td>  <input type="text" class="form-control" name="id" disabled  > </td>
						
						
						
					</tr>
					

						<tr>
						<td>  Nome Fantasia  </td>
						<td>  <input type="text" class="form-control" name="nomeFantasia" >  </td>
						</tr>
					
						<tr>
						<td>  Endereço</td>
						<td>  <input type="text" class="form-control" name="endereco" value="${credor.endereco} "> <br> </td>
						</tr>
											
					</table>


 <button type="submit" class="btn btn-success">Salvar</button> 

</form>

</div>

</body>
</html>