<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<link href="<c:url value="/resource/css/bootstrap.min.css"/>" rel="stylesheet">
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
		<link href="<c:url value="/resource/css/grupoUsuario.css"/>" rel="stylesheet">
		<link rel="icon" href="<c:url value="/resource/images/icon_pagamento.ico"/>" type="image/x-icon" />	
		<title>Grupo usuário</title>
	</head>
	<body style="background-image: url('<c:url value="/resource/images/background6.png"/>');">

		<div class="smallDiv">
			<c:import url="../geral/cabecalho.jsp"></c:import>
		</div>
	    <div class="container">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-primary">
							<div class="panel-heading">Grupos Usuário</div>
								<div class="panel-body">
	
									<label for="nome" for="form-control" >Nome</label>
									<div class="input-group">
									  <input type="text" class="form-control" id="nome" name="nome" placeholder="Novo grupo...">
									  <span class="input-group-btn">
									    <button class="btn btn-success" id="btn-salvar" type="button">Salvar</button>
									  </span>
									</div>
  								</div>
								  <p style="background-color:#d4d4d4; padding: 10px; margin-top: 10px">Listagem de grupos</p>
									<table class="table table-hover">
										<thead>
											<tr>
												<th>Nome</th>
											</tr>	
										</thead>
										<tbody>
											<c:forEach var="grupoUsuario" items="${grupoUsuarioList}">
												<tr>
													<td>${grupoUsuario.nome} (10)</td>
												
			                          				<td>	                          
			                           					<div class="dropdown pull-right">
										  					<button class="btn btn-primary dropdown-toggle"
										  							type="button" id="dropdownMenu1" 
										  							data-toggle="dropdown" 
										  							aria-haspopup="true"
										  							 aria-expanded="true">Ações
										    					<span class="glyphicon glyphicon-collapse-down"></span>
										 					</button>
										  					<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
																<li>
																	<a href="#" onclick="efetuaClick(${credor.id}, '${credor.nomeFantasia}','${credor.endereco}')">
																	 	<span class="glyphicon glyphicon-pencil pull-right">
																	 	</span>Editar
																	</a>
										    					</li>
										    					<li>
										    						<a href="remover?id=${grupoUsuario.id}">
										    							<span class="glyphicon glyphicon-remove pull-right" style="color:red">
										    							</span>Excluir 
										    						</a>
										   						</li>
										    					<li>
										    						<a href="#" onclick='abrirGrupo(${grupoUsuario.id}, "${grupoUsuario.nome}")'>
										    							<span class="glyphicon glyphicon-user pull-right">
										    							</span>Usuários 
										    						</a>
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
		
		<div id="grupo-admin" style="background-color: #FFF; display:none;"  title="Usuários do grupo">
				<div class="container conteudo-principal">
				
				    <div class="cabecalho">
				    	<input id="grupo-id" type="hidden">
				        <p id="grupo-nome"></p>
				
				    </div>
				    
				    <div id="grupo-control">
				                
				
				        <table class="tabela-usuarios table">
				            <thead>
				                <tr>
				                    <th class="th-pequeno"></th>
				                    <th class="th-pequeno"></th>
				                    <th></th>
				                    <th></th>
				                    <th></th>
				                    
				                </tr>
				            </thead>
				            <tbody>
				         
				            </tbody>
				        </table>
				        
				
				        <div class="row novo-usuario">
				          <div class="col-lg-6">
				            
						  <div class="form-group grupo-validacao">
						    <input type="text" class="form-control" id="emailNovoUsuario" placeholder="email....">
						    <label id="criticaNovoUsuario" class="control-label" for="emailNovoUsuario"></label>
						  </div>		
		            
				            
				          </div><!-- /.col-lg-6 -->
				          <div class="col-lg-4">

						  <div class="form-group">
							 <input id="apelidoNovoUsuario" type="text" class="form-control" placeholder="apelido....">
						  </div>					            
				          </div><!-- /.col-lg-6 -->
				          <div class="col-lg-2">
				            <div class="input-group">
				            <button id="btn-incluir-usuario" class="form-control btn btn-success">Incluir</button>
				            </div><!-- /input-group -->
				          </div><!-- /.col-lg-6 -->                    
				        </div><!-- /.row -->
				
				
				    </div>
				    
				    <div id="usuario-control">
				    	<input id="usuario-id" type="hidden">
				        <p id="usuario-apelido" class="usuario-titulo" ></p>
				        <img style="display: none" id="usuario-foto" src=""  class="usuario-margem img-rounded">
				        <p id="usuario-nome" class="usuario-margem usuario-apelido"></p>
				        <p id="usuario-email" class="usuario-margem usuario-email"></p>
				        <button id="btn-excl-usuario" class="usuario-margem btn btn-danger">Excluir do grupo</button>
				    </div>        
				
				    
				</div>
		</div>		
		

		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
		<script src="../resource/js/grupoUsuario.js"></script>	 	
	</body>
</html>