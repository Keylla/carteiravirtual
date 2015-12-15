<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" href="<c:url value="/resource/images/icon_pagamento.ico"/>" type="image/x-icon" />
      
<link rel="stylesheet" href="../resource/css/bootstrap.min.css" />
<link rel="stylesheet" href="<c:url value="../resource/css/usuario-cadastro.css"/>" />
     
    
<title>Perfil do Usuário</title>
</head>
<body
	style="background-image: url('<c:url value="/resource/images/background6.png"/>');">

	<div class="smallDiv">
		<c:import url="../geral/cabecalho.jsp"></c:import>
	</div>
	
	<div class="container">
	<div class="panel panel-primary">
	 <div class="panel-heading">
		<h1>Perfil</h1>
	</div>
	<div class="panel-body">
		<form action="editar" method="post" class="form-inline">
		    
          <div class="container form-group">
              <div class='molde-img form-group'>  
                <img class="img-usuario-lg"  src="" alt="..." class="img-circle">
              </div>
              
              <div class="container form-group">	
			<table>
				<tr>
					<td><input required="required" autofocus="autofocus"
					type='hidden' class="form-control" name="id" value="${usuario.id}"></td>
				</tr>
			    <tr>
			    	<td><input id="imagSelec" type="hidden" name="avatar" value="${usuario.avatar}" ></td>
			    </tr>
				<tr>
					<td><input required="required" autofocus="autofocus"
						type="text" class="form-control" name="NomeDoUsuario" placeholder="Nome" style="width: 500px" value="${usuario.nomeDoUsuario}"></td>
				</tr>
				<tr>
					<td><input required="required" autofocus="autofocus"
						type="email" class="form-control" name="email" placeholder="E-mail" value="${usuario.email}"></td>
				</tr>
				<tr>
					<td><input required="required" autofocus="autofocus"
						type="text" class="form-control" name="userName" placeholder="Login" value="${usuario.userName}"></td>
				</tr>

				<tr>
					<td><input required="required" autofocus="autofocus"
						type="password" class="form-control" name="senha" placeholder="Senha" value="${usuario.senha}"></td>
				</tr>
				
			</table>
			
           </div> 
                
              <div class="row">
                  <div id="chunli" class="col-md-2 ico-usuario">
                    <img class=" img-usuario" src="../resource/images/avatar/chunli-min.png" title="Chunli">
                  </div>
                                 
                  <div id="deadpool" class="col-md-2 ico-usuario">
                    <img class="img-usuario" src="../resource/images/avatar/deadpool-min.png" title="Deadpool">
                  </div>        
                  
                  <div  id="dhalsim"  class="col-md-2 ico-usuario">
                    <img class="img-usuario" src="../resource/images/avatar/dhalsim-min.png" title="Dhalsim">
                  </div> 
               </div>                         
              <div class="row">    
                  <div id="ironman"  class="col-md-2 ico-usuario">
                    <img class="img-usuario" src="../resource/images/avatar/ironman-min.png" title="Iron man">
                  </div>     
                  
                  <div id="joker"  class="col-md-2 ico-usuario">
                    <img class="img-usuario" src="../resource/images/avatar/joker-min.png" title="Joker">
                  </div>  
                                    
                  <div id="sakura"  class="col-md-2 ico-usuario">
                    <img class="img-usuario" src="../resource/images/avatar/sakura-min.png" title="Sakura">
                  </div>                       
              </div>   

              <div class="row">
                  <div id="shadowcat"  class="col-md-2 ico-usuario">
                    <img class=" img-usuario" src="../resource/images/avatar/shadowcat-min.png" title="Shadowcat">
                  </div>
                                 
                  <div id="smith"  class="col-md-2 ico-usuario">
                    <img class="img-usuario" src="../resource/images/avatar/smith-min.png" title="Smith">
                  </div>        
                  
                  <div id="spiderwoman"  class="col-md-2 ico-usuario">
                    <img class="img-usuario" src="../resource/images/avatar/spiderwoman-min.png" title="Spiderwoman">
                  </div>            
                  </div>           
                  <div class="row">  
                  <div id="thor"  class="col-md-2 ico-usuario">
                    <img class="img-usuario" src="../resource/images/avatar/thor-min.png" title="Thor">
                  </div>     
                  
                  <div id="wolverine"  class="col-md-2 ico-usuario">
                    <img class="img-usuario" src="../resource/images/avatar/wolverine-min.png" title="Wolverine">
                  </div>  
                                    
                  <div id="woody"  class="col-md-2 ico-usuario">
                    <img class="img-usuario" src="../resource/images/avatar/woody-min.png" title="Woody">
                  </div>                       
              </div>
              <br><br>
              <div align="center">
							<button type="submit" class="btn btn-success btn-md"
								style="width: 200px; height: 35px">Salvar</button>
							<button type="button" class="btn btn-danger btn-md"
								onclick="window.location.href='../despesas/listar-todas'"
								style="width: 200px; height: 35px">Cancelar</button>
						</div> 
              </div>
              
           
		</form>
		
		</div>			
	</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="../resource/js/bootstrap.min.js"></script>
	<script src="../resource/js/usuario-cadastro.js"></script>  
	 <script type="text/javascript">
		window.onload = function(){
			var path = "../resource/images/avatar/"+document.getElementById('imagSelec').value+".png";
			carregarUsuario(path);
		}
	</script> 
</body>
</html>