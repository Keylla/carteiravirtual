<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="<c:url value="/resource/css/bootstrap.min.css"/>" rel="stylesheet">
		<title>Cadastro de Usuário</title>
	</head>
	<body>
		<div class="page-header">
		<c:import url="../geral/cabecalhoCadUsuario.jsp"></c:import>
		</div>
			<div class="container">
				<h1>Novo Usuario</h1>
	
				<form action="adicionar" method="post">
						<table >				
							<tr>
								<td>  Código </td>
								<td>  <input type="text" class="form-control" name="id" disabled  > </td>			
							</tr>
					        <tr>
								<td>  Nome   </td>
								<td>  <input required="required" autofocus="autofocus" type="text" class="form-control" name="NomeDoUsuario" ></td>
							</tr>
							<tr>
								<td>  e-mail   </td>
								<td>  <input required="required" autofocus="autofocus" type="email" class="form-control" name="eMail" ></td>
							</tr>
							<tr>
								<td>  Login   </td>
								<td>  <input required="required" autofocus="autofocus" type="text" class="form-control" name="userName" ></td>
							</tr>
										
							<tr>
								<td>  Senha</td>
								<td> <input required="required" autofocus="autofocus" type="password" class="form-control" name="senha" >	</td>
							</tr>	
							<tr>
								<td> </td>
								<td>
								 <div align="center">
								     <button type="submit" class="btn btn-success btn-md" style="width: 200px; height: 35px">Salvar</button> 
								     <button type="button" class="btn btn-danger btn-md" onclick="window.location.href='/logon'" style="width: 200px; height: 35px">Cancelar</button> 				     				     
				  				 </div>	
				 		  </td>
						</tr>						
					</table>
				</form>
		</div>
	</body>
</html>