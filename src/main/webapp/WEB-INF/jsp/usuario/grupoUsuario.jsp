<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="<c:url value="/resource/css/bootstrap.min.css"/>" rel="stylesheet">
		<link rel="icon" href="<c:url value="/resource/images/icon_pagamento.ico"/>" type="image/x-icon" />	
		<script src="../resource/js/grupoUsuario.js"></script>	
		<title>Usuário-Grupos</title>
	</head>
	<body style="background-image: url('<c:url value="/resource/images/background6.png"/>');">

		<div class="smallDiv">
			<c:import url="../geral/cabecalho.jsp"></c:import>
		</div>
	    <div class="container">
				<div class="row">
					<div class="col-lg-22">
						<div class="panel panel-primary">
							<div class="panel-heading">Grupos Usuário</div>
								<div class="panel-body">
	
									<table>
								    	<thead>
								    	   <tr>
								    	   		<th class="col-lg-8">
											    	<input id="idGrupo" type="hidden" class="form-control" name="id"></input>
							    					<label>Descrição do Grupo</label>
													<input id="descGrupo" type="text" class="form-control"	name="nomeGrupo">
											    </th>
								    	   </tr>
	
											<tr>
												<th class="col-lg-6">
													<label>E-mail do Usuário</label>
													<input id="emailUsuario" type="text" class="form-control"	name="emailUsuario">
												</th>		
									
												<th class="col-lg-8">
													<label>Apelido do Usuário</label>
													<input id="apelidoUsuario" type="text" class="form-control"	name="apelidoUsuario">
												</th>
												<th>
												    <br>
													<button  class="btn button" style="background: transparent; border: none" onclick="adicionaUsuario()">
														<span title="Incluir" class="glyphicon glyphicon-plus-sign" style="color:green; font-size:20px; "></span>
													</button>
													
												</th>
											</tr>
										</thead>
									</table>
									<br><br>
									<table class="table">
										<thead>
											<tr >
												<th>
													<p align=center>Apelido do Usuário</p>
												</th>
												<th>
													<p align=center>e-mail Usuário</p>
												</th>
												<th>
													<p align=center>Ações</p>
												</th>
											</tr>
										</thead>
										<tbody id="tbBody">
													
										</tbody>		
									</table>
									<br>
									<div align=center>
										<button  class="btn btn-success btn-md">Salvar</button>
										<button  class="btn btn-danger btn-md">Cancelar</button>
									</div>							
								</div>
						</div>
					</div>
				</div>
		</div>	
			 	
	</body>
</html>