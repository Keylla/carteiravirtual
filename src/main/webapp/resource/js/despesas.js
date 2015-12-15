$(document).ready(function() {
    
    $('.grupo-despesa').css("display","none");
    

    $('#flagParcelado').click(function(){
    	$('#numParcelas').attr('disabled',!$(this).is(':checked'));
    	$('#flagMensal').attr('disabled',$(this).is(':checked'));
    });
    
    $('#flagMensal').click(function(){
    	$('#flagParcelado').attr('disabled',$(this).is(':checked'));
    });
    
    $('#btn-salvar').click(function(){

    	
    	if (criticarCamposObrigatorio()){
    		return;
    	}
    	
    	if(!checaDataValDesp()){
    		mostrarCritica('Data Vencimento maior que Data da Despesa! <br>'+
    				'Por favor digite corretamente as datas!');
    		
    		return;
    	}
    	
    	var despesa = carregarObjDespesa();
    	var listDespesa = [];
    	
    	
    	if ($('#ckDespesaGrupo').is(':checked') ){
    		
    		if( $('#combo-grupos-usuario').val() == '-1'){
    			mostrarCritica('Selectione um grupo de usuário');
    			return;
    		}
    		
    		
    	}
    	
    	if (isDespesaEmGrupo()){
    		
    		totalUsuarios = $('.tabela-usuarios tr.resultado').length;

    		var valorTotal = 0;

    		for(i = 0; i < totalUsuarios; i++){

    			usuario_id_tag = $('.tabela-usuarios tr.resultado #usuario-id')[i]
    			usaurio_valor_tag = $('.tabela-usuarios tr.resultado #usuario-valor')[i];
    			
    			var valor_usuario = Number((parseFloat($(usaurio_valor_tag).val().replace(/,/g,'.'))).toFixed(2)); 
    			
    			valorTotal = valorTotal +valor_usuario;
    			
    			
    		}
    		
    		var vl_total_despesa = Number((parseFloat($('#valorDespesa').val().replace(/,/g,'.'))).toFixed(2));
    		
    		if(vl_total_despesa != valorTotal){
    			mostrarCritica('Valor total por usuário diferente do valor total da despesa.')
    			return
    		}

    		for(i = 0; i < totalUsuarios; i++){
    			
    			usuario_id_tag = $('.tabela-usuarios tr.resultado #usuario-id')[i]
    			usaurio_valor_tag = $('.tabela-usuarios tr.resultado #usuario-valor')[i];
    			
    			desp = carregarObjDespesa();
    			desp['idUsuario'] = $(usuario_id_tag).val();
    			desp['valorDespesa'] = $(usaurio_valor_tag).val();
    			
    			listDespesa.push(desp);
    			
    		}
    		
    		var despesas = listDespesa;
    		
    		salvarDespesaEmLote(despesas);
    		
    		
    	}else{
    		salvarDespesa(despesa);
    	}
    		
    		
    });
    
    
    $('#ckDespesaGrupo').click(function(){
        
        if($(this).is(':checked')){
            $('.grupo-despesa').show();
        }else{
            $('.grupo-despesa').hide();
        }
        
    });

    $('#combo-grupos-usuario').change(function(){
    	
    	if ( $(this).val() != ''){
    		carregarListaUsuarios({id : $(this).val()})
    	}
    });

});

function checaDataValDesp(){
	 var dtVenc = document.getElementById('dataVencimento').value;
	 var dtDesp = document.getElementById('dataDespesa').value;
	 var compData = checarDatas(dtDesp, dtVenc);
	 
	 return compData;

} 

function carregarListaUsuarios(grupoUsuario){
	
	$.get("../grupoUsuario/usuario/listarPorGrupo", grupoUsuario,function(listUsuario){
		
		$('.tabela-usuarios tr.resultado').remove();
		
		if(listUsuario.length != 0){
			
			$.each(listUsuario, function(i){
				
				usuario = listUsuario[i];
				
				var tr = $('<tr></tr>').addClass('resultado');
				$(tr).data('usuario',usuario);
				var td1 = $('<td> <img src="../resource/images/avatar/' +usuario.avatar +'-min.png" > </td>');
				if (usuario.apelido !=null){ 
					var td2 = $('<td></td>').html(usuario.apelido);
					}
					else{
						var td2 = $('<td>Eu</td>')	
					}
				var td3 = $('<td> <input id="usuario-id" type="hidden" value="'+usuario.id +'">  <input id="usuario-valor" value="0,00"  type="text" class="form-control monetario" value="0,00"> </td>');

				$(tr).append(td1)
					.append(td2)
					.append(td3);
				
				$('.tabela-usuarios').append(tr);
				
			});
			
			
		}
		
	
	});
	
}

function isDespesaParcelada(){
	return $('#flagParcelado').is(':checked')
}

function isDespesaMensal(){
	return $('#flagMensal').is(':checked')
}


function isDespesaEmGrupo(){

	if ($('#ckDespesaGrupo').is(':checked') && ($('.tabela-usuarios tr.resultado').length > 0) ){
		return true 
	}else{
		return false
	}
}

function carregarObjDespesa(){

	if (isDespesaMensal()){
		_flagMensal = 'on'
	}else{
		_flagMensal = 'off'
	}
	
	if (isDespesaParcelada()){
		_flagParcelada = 'on'
	}else{
		_flagParcelada = 'off'
	}
	
	
	var despesa = {
		credor 	: { id : $('#credor').val()},
		categoria : { id : $('#categoria').val()},
		valorDespesa	: $('#valorDespesa').val(),
		flagParcelado	:	_flagParcelada,
		flagMensal	: _flagMensal,
		numParcelas	: $('#numParcelas').val(),
		dataDespesa	: $('#dataDespesa').val(),
		dataVencimento : $('#dataVencimento').val()
	}
	
	
	return despesa;
	
	
}



function salvarDespesa(despesa){
	
	$.ajax({
         type: "post",
         url: "../despesasRest/adicionar",
         data: JSON.stringify(despesa),
         contentType:"application/json; charset=utf-8",
 	     error: function(xhr, XMLHttpRequest, textStatus, errorThrown) {
	        alert(errorThrown);
	     },
         success : function(){
        	 
        	 	limparCampos();
        	 
	        	$('#mensagem').message({
	        		text: 'Despesa salvo com sucesso  !',
	        		type: 'success'
	        	});
	        	
	        	var $alert = $('#mensagem');
	        	
	        	$alert.fadeIn( "slow", function() {

		        	setTimeout(function () {
		        		$alert.html();
		        		$alert.hide();
		        	}, 3000)
	        		
        		  });
        	 
         }

     });
	
}

function salvarDespesaEmLote(listDespesa){
	
	
	$.ajax({
         type: "post",
         url: "../despesasRest/adicionarLote",
         data: JSON.stringify(listDespesa),
         contentType:"application/json; charset=utf-8",
 	     error: function(xhr, XMLHttpRequest, textStatus, errorThrown) {
	        alert(errorThrown);
	     },
         success : function(){
        	 
        	 	limparCampos();
        	 
	        	$('#mensagem').message({
	        		text: 'Despesas salvas com sucesso  !',
	        		type: 'success'
	        	});
	        	
	        	var $alert = $('#mensagem');
	        	
	        	$alert.fadeIn( "slow", function() {

		        	setTimeout(function () {
		        		$alert.html();
		        		$alert.hide();
		        	}, 3000)
	        		
        		  });
        	 
         }

     });
	
}

function limparCampos(){
	$('#credor').val('');
	$('#categoria').val('');
	$('#valorDespesa').val('');
	$('#flagParcelado').val();
	$('#flagMensal').val();
	$('#numParcelas').val();
	$('#dataDespesa').val('');
	$('#dataVencimento').val('');	
	
	$('#ckDespesaGrupo').attr('checked',false);
	$('#flagMensal').attr('checked',false);
	$('#flagParcelado').attr('checked',false);
	
	$('.tabela-usuarios tr.resultado').remove();
	$('.grupo-despesa').hide();
	$('#combo-grupos-usuario').val('');
}

function criticarCamposObrigatorio(){
	
	var critica = '';

	if ($('#categoria').val() === ''){
		critica = critica + '<li>Categoria </li>';
	}
	
	if ($('#valorDespesa').val() === ''){
		critica = critica + '<li>Valor da Despesa </li>';
	}

	if ($('#dataDespesa').val() === ''){
		critica = critica + '<li>Data da Despesa </li>';
	}

	if ($('#dataVencimento').val() === ''){
		critica = critica + '<li>Data do Vencimento </li>';
	}
	
	if(critica != ''){
		mostrarCritica('Preencha os campos: <br> <ul>' +critica +'</ul>');
		return true; 
	}else{
		return false;
	}
}

function mostrarCritica(msg){
	$('.alert #msg-critica').html(msg);
	
	$('.alert').fadeIn( "slow", function() {

    	setTimeout(function () {
    		$('.alert').html();
    		$('.alert').hide();
    	}, 5000)
		
	  });
}