<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon"
	href="<c:url value="/resource/images/icon_pagamento.ico"/>"
	type="image/x-icon" />

<title>Carteira Virtual - Credores</title>

<!--     Bootstrap core CSS -->
<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">

<!--     Add custom CSS here -->
<style>
body {
	margin-top: 60px;
}
</style>

</head>

<body
	style="background-image: url('<c:url value="/resource/images/background6.png"/>');">


	<div class="smallDiv">
		<c:import url="../geral/cabecalho.jsp"></c:import>
	</div>
	<div class="container">

		<div class="row">
			<div class="col-lg-12">
				<h1>Credores</h1>


				<table class="table table-striped">

					<thead>

						<tr>
							<th>
								<p>Nome Fantasia</p>
							</th>
							<th>
								<p></p>
							</th>
							<th>
								<p></p>
							</th>

						</tr>

					</thead>


					<tbody>
						<c:forEach var="credor" items="${credores}">

							<tr>
								<td>${credor.nomeFantasia}</td>
								<td><a href="form-altera?id=${credor.id}"> Editar </a></td>
								<td><a href="remover?id=${credor.id}"> Excluir </a></td>

							</tr>
						</c:forEach>

					</tbody>


				</table>


			</div>
		</div>

	</div>

</body>
</html>