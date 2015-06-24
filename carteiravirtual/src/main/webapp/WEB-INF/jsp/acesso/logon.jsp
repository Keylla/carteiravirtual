<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en"><head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>jwallet-web</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resource/css/bootstrap.min.css"/>" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value="/resource/css/signin.css"/>" rel="stylesheet">

  </head>

  <body style="background-image: url('/carteiravirtual/resource/images/background6.png');">

    <div class="container">
    

      <form class="form-signin" action="login" method="POST">
        <div align="center"><img alt="Economia" src="/carteiravirtual/resource/images/economizar.png" width=200 height=200 ></div>
        <h2 class="form-signin-heading" align="center"><FONT FACE="Tahoma" SIZE="4,5" COLOR="white">Bem Vindo a Carteira Virtual!</FONT></h2>
        <label for="inputEmail" class="sr-only">username</label>    
        <input id="inputEmail" class="form-control" placeholder="usuario" required="required" autofocus="autofocus" type="text" name="userName">
        <label for="inputPassword" class="sr-only">senha</label>
        <input id="inputPassword" class="form-control" placeholder="senha" required="required" type="password" name="senha"> 
        <form:errors path="senha" cssstyle="color: red;"> </form:errors> <br>
        <button class="btn btn-lg btn-primary btn-block" type="submit" style="border: outset; 2px; #000;">Logar</button>
        <div align="center"><a  href=" <c:url value="/usuario/novousuario" /> "><FONT  COLOR="white" >Cadastrar-se</FONT></a></div>
      </form>

    </div>

</body></html>