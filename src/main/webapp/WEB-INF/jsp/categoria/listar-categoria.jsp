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

<title>Carteira Virtual - Credores</title>

<!--     Bootstrap core CSS -->
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">

<!--     Add custom CSS here -->
    <style>
	body {margin-top: 60px;} 
</style> 

<script type="text/javascript">
function altRows(id){
	if(document.getElementsByTagName){  
		
		var table = document.getElementById(id);  
		var rows = table.getElementsByTagName("tr"); 
		 
		for(i = 0; i < rows.length; i++){          
			if(i % 2 == 0){
				rows[i].className = "evenrowcolor";
			}else{
				rows[i].className = "oddrowcolor";
			}      
		}
	}
}
window.onload=function(){
	altRows('alternatecolor');
}
</script>

<!-- CSS goes in the document HEAD or added to your external stylesheet -->
<style type="text/css">
table.altrowstable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #a9c6c9;
	border-collapse: collapse;
}
table.altrowstable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
table.altrowstable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
.oddrowcolor{
	background-color:#d4e3e5;
}
.evenrowcolor{
	background-color:#C2C2C2;
}
</style>
  </head>

<body style="background-image: url('<c:url value="/resource/images/background6.png"/>');">  


<div class="smallDiv" >
<c:import url="../geral/cabecalho.jsp"></c:import>
</div>
	<div class="container">
	
	      <div class="row">
	        <div class="table-responsive">
			  <h1>Categorias </h1>

					<table  id="alternatecolor" class="table table-striped">
					
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