<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resource/css/bootstrap.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resource/css/sidebar.css"/>" rel="stylesheet">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="<c:url value="/resource/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resource/js/sidebar.js"/>"></script>

<title>Carteira Virtual</title>
</head>
<body>
	<div id="wrapper">
		<div class="overlay"></div>

		<!-- Sidebar -->
		<nav class="navbar navbar-inverse navbar-fixed-top"
			id="sidebar-wrapper" role="navigation">
		<ul class="nav sidebar-nav">
			<li class="sidebar-brand"><a
				href="<c:url value="/despesas/listar-todas" />"> Carteira
					Virtual </a></li>
					
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown">Usuário <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href=" <c:url value="/usuario/perfil" /> ">Perfil</a></li>
					<li><a href=" <c:url value="/usuario/grupoUsuario" />">Grupos Usuário</a></li>
				</ul></li>		
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown">Credor <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="<c:url value="/credor/novo" />">Cadastrar</a></li>
					<li><a href="<c:url value="/credor/listar" />">Listar</a></li>
				</ul></li>

			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown">Categoria <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href=" <c:url value="/categoria/nova" /> ">Cadastrar</a></li>
					<li><a href=" <c:url value="/categoria/listar" />">Listar</a></li>
				</ul></li>


			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown">Despesas <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href=" <c:url value="/despesas/nova" /> ">Cadastrar
					</a></li>
					<li><a href=" <c:url value="/despesas/listar-todas" />">Listar
							Todas</a></li>
				</ul></li>

			<li><a href="<c:url value="/grafico/despesas-mensais" />">Graficos</a></li>
			<li><a href="<c:url value="/logout" />">Logout</a></li>
		</ul>
		</nav>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">
			<button type="button" class="hamburger is-closed"
				data-toggle="offcanvas">
				<span class="hamb-top"></span> <span class="hamb-middle"></span> <span
					class="hamb-bottom"></span>
			</button>
		</div>
		<!-- /#page-content-wrapper -->

	</div>
	<!-- /#wrapper -->



</body>
</html>