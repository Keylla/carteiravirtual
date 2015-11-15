<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="icon" href="<c:url value="/resource/images/icon_pagamento.ico"/>"type="image/x-icon" />
		<link href="<c:url value="/resource/css/bootstrap.min.css"/>"rel="stylesheet">
		<link href="<c:url value="/resource/css/signin.css"/>" rel="stylesheet">
		<title>Carteira Virtual-Web</title>
	</head>

	<body style="background-image: url('<c:url value="/resource/images/background6.png"/>');">
		<div class="container">
	        <div class="card card-container"> 
		   		<form class="form-signin " action="login" method="POST" >
					<div align="center">
						<img alt="Economia"
							 class="profile-img-card"
							 src="<c:url value="/resource/images/economizar.png"/>"
							 width=90
						     height=70>
					</div>
					<h2 class="form-signin-heading" align="center">
						<FONT FACE="Tahoma" SIZE="4,5" COLOR="white">Bem Vindo a Carteira Virtual!</FONT>
					</h2>
					<label for="inputEmail" class="sr-only">username</label> 
					<input id="inputEmail" 
						   class="form-control" 
						   placeholder="usuario"
					       required="required" 
					       autofocus="autofocus"
					       type="text"
						   name="userName"/> 
					<label for="inputPassword" class="sr-only">senha</label>
					<input id="inputPassword" 
						   class="form-control" 
						   placeholder="senha"
					       required="required" 
					       autofocus="autofocus"
					       type="password" 
					       name="senha"/>

					<div class="has-error">
						<form:errors path="usuario.userName" cssClass="alert alert-danger alert-dismissible" element="div"/>
						<form:errors path="usuario.senha" cssClass="alert alert-danger alert-dismissible" element="div"/>
	                </div>
					<br>
					<button class="btn btn-lg btn-primary btn-block" 
							type="submit"
							style="border: outset; 2px; #000;">Logar
					</button>
				
					<div align="center">
						<a href=" <c:url value="/usuario/novousuario" /> ">
							<FONT COLOR="white">Cadastrar-se</FONT>
						</a>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>