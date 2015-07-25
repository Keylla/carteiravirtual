<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resource/css/bootstrap.min.css"/>" rel="stylesheet">
<!--<link href="/resource/css/bootstrap.min.css" rel="stylesheet">-->
<title>Carteira Virtual</title>
</head>
<body>
    
<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="<c:url value="/despesas/listar-todas" />">Carteira Virtual</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">

            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Credor <b class="caret"></b></a>
              <ul class="dropdown-menu">
			    <li><a href="<c:url value="/credor/novo" />">Cadastrar</a></li>
			    <li><a href="<c:url value="/credor/listar" />">Listar</a></li>    
              </ul>
            </li>
            
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Categoria <b class="caret"></b></a>
              <ul class="dropdown-menu">
			    <li><a href=" <c:url value="/categoria/nova" /> ">Cadastrar</a></li>
			    <li><a href=" <c:url value="/categoria/listar" />">Listar</a></li>
              </ul>
            </li>
 
                       
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Despesas <b class="caret"></b></a>
              <ul class="dropdown-menu">
			    <li><a href=" <c:url value="/despesas/nova" /> ">Cadastrar </a></li>
			    <li><a href=" <c:url value="/despesas/listar-todas" />">Listar Todas</a></li>
              </ul>
            </li>
       
			<li><a href="<c:url value="/grafico/despesas-mensais" />">Graficos</a></li>
			<li><a href="<c:url value="/logout" />">Logout</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
	
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="<c:url value="/resource/js/bootstrap.min.js"/>"></script>
   
    <!--<script src="/js/bootstrap.min.js"></script>-->
    
</body>
</html>