<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

<title>jWallet - Credores</title>

<!--     Bootstrap core CSS -->
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">

<!--     Add custom CSS here -->
    <style>
	body {margin-top: 60px;} 
</style> 

  </head>

<body style="background-image: url('<c:url value="/resource/images/background6.png"/>');">  


<div class="page-header" >
<c:import url="../geral/cabecalho.jsp"></c:import>
</div>
	<div class="container">
	
	      <div class="row">
	        <div class="col-lg-12">
			  <h1>Categorias </h1>

					<table class="table table-striped">
					
					<thead>
					
					<tr>
						<th> <p> Descricao </p> </th>
						<th> <p> Editar </p> </th>
						<th> <p> Remover </p> </th>
						
					</tr>
					
					</thead>
					
					<tbody>
					
					<c:forEach var="categoria" items="${categorias}">
					
					
						<tr>
							<td> ${categoria.descricao} </td>
							<td> <a href="form-altera?id=${categoria.id}"> Editar </a> </td>
							<td> <a href="remover?id=${categoria.id}"> Remover </a> </td>			
				
						</tr>
					</c:forEach>
					
					</tbody>
					
																															
					
					</table>
					

	        </div>
	      </div>
	
	    </div>

</body>
</html>