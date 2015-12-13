$(document).ready(function() {
	
	$('#btn-salvar').click(function(){
		
		$.ajax({
	         type: "post",
	         url: "salvarGrupo",
	         data: {nome : $('#nome').val()},
		        success: function(data) {  
		        	window.location.href = "grupoUsuario";
		        	
	         }
	     });
		
	});

	$(document).on("click",".tabela-usuarios tbody tr", function(){
		var usuario = $(this).data('usuario');
		
		$('.tabela-usuarios tr').css("background-color", "#FFF");
		$(this).css("background-color", "#f5f5f5");
		carregarPerfil(usuario);
	});
	
	$('#btn-excl-usuario').click(function(){
		var usuario = {	id : $('#usuario-id').val(),
						idGrupoUsuario : $('#grupo-id').val()}
		
		removerUsuarioDoGrupo(usuario)	
	});
	
	$('#btn-incluir-usuario').click(function(){
		
		$.ajax({
	         type: "post",
	         url: "../grupoUsuario/usuarioEmail",
	         data: {email : $('#emailNovoUsuario').val()},
		        success: function(usuario) { 
		        	
		        	if(usuario == null || usuario === ''){
		        		$('#criticaNovoUsuario').text('Usuário não encontrado.');
		        		$('.grupo-validacao').addClass('has-error')
		        	}else{
		        		$('#criticaNovoUsuario').text('');
		        		$('.grupo-validacao').removeClass('has-error');

		        		usuario.idGrupoUsuario = $('#grupo-id').val();
		        		usuario.apelido = $('#apelidoNovoUsuario').val();
		        		
		        		adicionarUsuarioNoGrupo(usuario);
		        		

		        		
		        	}
		        		
		        	
	         }

	     });
		

	});
	
    $('#grupo-admin').dialog({
        autoOpen: false,
        height: 560,
        width: 1140,
        modal: true
      });
    
});

function abrirGrupo(id, nome){
	
	$('#grupo-id').val(id);
	$('#grupo-nome').text(nome);
	carregarListaUsuarios({'id' : id});
	limparCampos();
	limparCamposPerfil();
	$('#grupo-admin').dialog( "open" );
}

function carregarListaUsuarios(grupoUsuario){
	
	$.get("../grupoUsuario/usuario/listarPorGrupo", grupoUsuario,function(listUsuario){
		
		$('.tabela-usuarios tr.resultado').remove();
		
		if(listUsuario.length != 0){
			
			$.each(listUsuario, function(i){
				
				usuario = listUsuario[i];
				
				var tr = $('<tr></tr>').addClass('resultado');
				$(tr).data('usuario',usuario);
				var td1 = $('<td> <input type="checkbox"> </td>');
				var td2 = $('<td> <img src="../resource/images/avatar/' +usuario.avatar +'-min.png" > </td>');
				var td3 = $('<td></td>').html(usuario.apelido);
				var td4 = $('<td></td>').html(usuario.email);
				var td5 = $('<td> <span class="glyphicon glyphicon-search"></span> </td>');

				$(tr).append(td1)
					.append(td2)
					.append(td3)
					.append(td4)
					.append(td5);
				
				$('.tabela-usuarios').append(tr);
				
			});
			
			
		}
		
	
	});
}

function carregarPerfil(usuario){
	$('#usuario-id').val(usuario.id);
	$('#usuario-foto').attr('src','../resource/images/avatar/'+ usuario.avatar +'.png')
	$('#usuario-foto').show();
	$('#usuario-apelido').text(usuario.apelido);
	$('#usuario-nome').text(usuario.userName);
	$('#usuario-email').text(usuario.email);
}

function limparCamposPerfil(){
	$('#usuario-id').val('');
	$('#usuario-foto').attr('src','');
	$('#usuario-foto').hide();
	$('#usuario-apelido').text('');
	$('#usuario-nome').text('');
	$('#usuario-email').text('');
}

function limparCampos(){
	$('#apelidoNovoUsuario').val('');
	$('#emailNovoUsuario').val('');
}

function adicionarUsuarioNoGrupo(usuario){
	
	$.ajax({
         type: "post",
         url: "../grupoUsuario/usuario/incluir",
         data: usuario,
         success : function(){
     		var grupoUsuario = {id : $('#grupo-id').val() } 
     		
     		limparCampos();
    		carregarListaUsuarios(grupoUsuario);
         },
	    error: function(XMLHttpRequest, textStatus, errorThrown) {
	        alert('Erro ao inserir usuario');
	   }
     });
	
}

function removerUsuarioDoGrupo(usuario){
	
	$.ajax({
         type: "post",
         url: "../grupoUsuario/usuario/excluir",
         data: usuario,
         success : function(){
     		var grupoUsuario = {id : $('#grupo-id').val() } 
    		
     		limparCamposPerfil();
    		carregarListaUsuarios(grupoUsuario);
         }
     });
	
}