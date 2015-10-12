<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="<c:url value="/resource/css/bootstrap.min.css"/>" rel="stylesheet">
		<link href="<c:url value="/resource/css/pager.css"/>" rel="stylesheet">
		<link href="<c:url value="/resource/css/divDinamica.css"/>" rel="stylesheet">
		<script src="../resource/js/pager.js"></script>
		<link rel="icon" href="<c:url value="/resource/images/icon_pagamento.ico"/>" type="image/x-icon" />
		<title>Categorias</title>
	</head>
	<body style="background-image: url('<c:url value="/resource/images/background6.png"/>');">

		<div class="smallDiv">
			<c:import url="../geral/cabecalho.jsp"></c:import>
		</div>

		<div class="container">
			<h1>Categoria</h1>
			<form action="adicionar" method="post">
				<div class="container">
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-primary">
						  		<div class="panel-heading">
						 			<table>
							 		 	<thead>
											<tr>
												<th>
													<input type="hidden"  class="form-control" name="id" disabled>
												</th>
												<th class="col-lg-12">
													<p>Descrição</p>
													<input type="text" class="form-control"	name="descricao">
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
									<table class="table" id="tbCategorias">
										<tbody>
											<c:forEach var="categoria" items="${categorias}">
												<tr class="active">
													<td>${categoria.descricao}</td>	                        
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
																	<a href="#" onclick="efetuaClick(${categoria.id}, '${categoria.descricao}')">
																	 	<span class="glyphicon glyphicon-pencil pull-right">
																	 	</span>Editar
																	</a>
										    					</li>
										    					<li>
										    						<a href="remover?id=${categoria.id}">
										    							<span class="glyphicon glyphicon-remove pull-right" style="color:red">
										    							</span>Excluir 
										    						</a>
										   						</li>
										  					</ul>
														</div>
			                          				</td>							   	
												</tr>
											</c:forEach>
										</tbody>
									</table>
								<div id="pageNavPosition"  align="center" class="paging-nav"></div>
								<br>
								</div>
							</div>
						</div>
					</div>
				</div>	
			</form>
		</div>
		<div id="transparencia">
			<div id="divPrincipal" ></div>
		</div>
		
		<script type="text/javascript">
				var pager = new Pager('tbCategorias', 5);
				pager.init();
				pager.showPageNav('pager', 'pageNavPosition');
				pager.showPage(1);
		</script>
		
		<script type="text/javascript"> 
			function efetuaClick(id,nome){
				var divPai = $('#divPrincipal');
				document.getElementById('transparencia').style.display='block';
				document.getElementById('divPrincipal').style.display='block';
				divPai.append("<input id='idCategoria' type='hidden' value='"+id+"'/> ");	
				divPai.append("<label>Descrição</label>");
				divPai.append("<br>");
				divPai.append("<input type='text' class='form-control'	id='descricao' value='"+nome+"'/>"); 
				divPai.append("<br>"); 
				divPai.append("<input id='salvar' type='submit' class='btn btn-success btn-md' value='Salvar'/>");
				divPai.append("<input id='cancelar' type='button' class='btn btn-danger btn-md' value='Cancelar'/>");
			}
		</script>
		
		<script type="text/javascript">
			 $('#divPrincipal').on('click', '#cancelar', function(e) {
				    $(this).closest("#divPrincipal").css('display','none');
				    $(this).closest("#transparencia").css('display','none');
				    $('#divPrincipal').empty();
				});
	    </script>
	    
	    <script type="text/javascript">
		 $('#divPrincipal').on('click', '#salvar', function(e) {
			 $.ajax({
		            type: "POST",
		            url: "editar",
		            data: {id: $('#idCategoria').val(), descricao : $('#descricao').val()},
			        success: function() {               
		                    window.location.href = "nova" 
		            }
		        });
	
			 $(this).closest("#divPrincipal").css('display','none');
			 $(this).closest("#transparencia").css('display','none');
			 
			});
	   </script>
	</body>
</html>