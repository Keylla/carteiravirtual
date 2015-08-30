<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resource/css/bootstrap.min.css"/>"
	rel="stylesheet">
<link rel="icon"
	href="<c:url value="/resource/images/icon_pagamento.ico"/>"
	type="image/x-icon" />

<title>Perfil</title>
</head>
<body
	style="background-image: url('<c:url value="/resource/images/background6.png"/>');">

	<div class="smallDiv">
		<c:import url="../geral/cabecalho.jsp"></c:import>
	</div>

	<div class="container">
		<h1>Perfil</h1>

		<form action="editar" method="post">
			<table>
			   <tr>
					<td><input required="required" autofocus="autofocus"
						type='hidden' class="form-control" name="id" value="${usuario.id}"></td>
				</tr>
				<tr>
					<td>Nome</td>
					<td><input required="required" autofocus="autofocus"
						type="text" class="form-control" name="NomeDoUsuario" value="${usuario.nomeDoUsuario}"></td>
				</tr>
				<tr>
					<td>e-mail</td>
					<td><input required="required" autofocus="autofocus"
						type="email" class="form-control" name="eMail" value="${usuario.eMail}"></td>
				</tr>
				<tr>
					<td>Login</td>
					<td><input required="required" autofocus="autofocus"
						type="text" class="form-control" name="userName" value="${usuario.userName}"></td>
				</tr>

				<tr>
					<td>Senha</td>
					<td><input required="required" autofocus="autofocus"
						type="password" class="form-control" name="senha" value="${usuario.senha}"></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<div align="center">
							<button type="submit" class="btn btn-success btn-md"
								style="width: 200px; height: 35px">Salvar</button>
							<button type="button" class="btn btn-danger btn-md"
								onclick="window.location.href='../despesas/listar-todas'"
								style="width: 200px; height: 35px">Cancelar</button>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>