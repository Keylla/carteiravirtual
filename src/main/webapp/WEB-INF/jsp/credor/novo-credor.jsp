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

<title>Cadastro de Credor</title>
</head>
<body
	style="background-image: url('<c:url value="/resource/images/background6.png"/>');">


	<div class="smallDiv">
		<c:import url="../geral/cabecalho.jsp"></c:import>
	</div>

	<div class="container">
		<h1>Credor</h1>

		<form action="adicionar" method="post">



			<table>

				<tr>
					
					<td><input type="hidden"  class="form-control" name="id" disabled>
					</td>



				</tr>


				<tr>
					<td>Nome Fantasia</td>
					<td><input type="text" class="form-control"
						name="nomeFantasia"></td>
				</tr>

				<tr>
					<td>Endereço</td>
					<td><input type="text" class="form-control" name="endereco"
						value="${credor.endereco} "> <br></td>
				</tr>

			</table>
	<br><br>		
	<div class="container">

		<div class="row">
			<div class="col-lg-12">
				
				<table class="table table-striped">

					<thead>

						<tr>
							<th>
								<p>Nome Fantasia</p>
							</th>
					
							<th>
								<p>Endereço</p>
							</th>
						</tr>
						

					</thead>


					<tbody>
						<c:forEach var="credor" items="${credores}">

							<tr>
								<td>${credor.nomeFantasia}</td>
								<td>${credor.endereco}</td>
	                          
	                          <td>
	                          
	                           <div class="dropdown pull-right">
								  <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								    Ações
								    <span class="glyphicon glyphicon-collapse-down"></span>
								  </button>
								  
								  
								  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
									
									<li>
								    <a href="form-altera?id=${credor.id}"><span class="glyphicon glyphicon-pencil pull-right"></span>Editar</a>
								    </li>
								    <li>
								    <a href="remover?id=${credor.id}"><span class="glyphicon glyphicon-remove pull-right"></span>Excluir </a>
								    </li>
								  </ul>
								</div>
	                          </td>							   	
							</tr>
						</c:forEach>

					</tbody>


				</table>


			</div>
		</div>

	</div>

			<button type="submit" class="btn btn-success">Salvar</button>

		</form>

	</div>

</body>
</html>