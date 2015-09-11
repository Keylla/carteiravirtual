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
<div class="container">

		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-primary">
				  <div class="panel-heading">
				 		 <table >
				 		 <thead>
				
										<tr>
											<th>
												<input type="hidden"  class="form-control" name="id" disabled>
													</th>
											
													<th class="col-lg-6">
														<p>Nome Fantasia</p>
														<input type="text" class="form-control"
														name="nomeFantasia">
													</th>
													<th class="col-lg-6" >
														<p>Endereço</p>
														<input type="text" class="form-control" name="endereco"
														value="${credor.endereco} "> 
													</th>
													<th>
													<p><br></p>
													<button type="submit" class="form-control btn btn-success">Salvar</button>
													</th>
												</tr>
												
						
											</thead>
						
											
									</table>
									
								</div>
						  <div class="panel-body">
		
	
				
				<table class="table">

					<tbody>
						<c:forEach var="credor" items="${credores}">

							<tr class="active">
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
								    <a href="remover?id=${credor.id}"><span class="glyphicon glyphicon-remove pull-right" style="color:red"></span>Excluir </a>
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
	</div>
	</div>

			

		</form>

	</div>

</body>
</html>