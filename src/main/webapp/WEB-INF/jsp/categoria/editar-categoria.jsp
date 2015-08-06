<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
<title>Cadastro de Categoria</title>
</head>
<body
	style="background-image: url('<c:url value="/resource/images/background6.png"/>');">


	<div class="smallDiv">
		<c:import url="../geral/cabecalho.jsp"></c:import>
	</div>

	<div class="container">

		<h1>Nova Categoria</h1>

		<form action="editar" method="post">

			<table>

				<tr>
					<td>Código</td>
					<td><input type="text" class="form-control" name="id" readonly
						value="${categoria.id}"></td>



				</tr>


				<tr>
					<td>Descrição</td>
					<td><input type="text" class="form-control" name="descricao"
						value="${categoria.descricao}"></td>
				</tr>

			</table>






			<input type="submit" class="btn btn-success" value=Salvar>

		</form>
	</div>

</body>
</html>