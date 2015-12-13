var $JQuery = jQuery.noConflict()
	$JQuery(function() {
		$JQuery( ".calendario" ).datepicker({language: "pt-BR"});
		$JQuery( ".calendario" ).keypress(function(){
			formatar(this, '##/##/####');
		});
		
		$JQuery( ".calendario" ).keypress(function(){
			return doDateVenc(this.id,this.value, 4);
		});
		
	});


/* Formatação para qualquer mascara */

	function formatar(src, mask) 
	{
		var i = src.value.length;
		var saida = mask.substring(0,1);
		var texto = mask.substring(i)
			if (texto.substring(0,1) != saida) 
			{
				src.value += texto.substring(0,1);
			}
	}

	function checarDatas(data_1,data_2){ 
	   
	    var Compara01 = parseInt(data_1.split("/")[2].toString() + data_1.split("/")[1].toString() + data_1.split("/")[0].toString());
	    var Compara02 = parseInt(data_2.split("/")[2].toString() + data_2.split("/")[1].toString() + data_2.split("/")[0].toString());
	  
		  if (Compara01 <= Compara02) 
		    	return true;
		    else 
		    	return false;  
	}	

	/* Valida Data */
	
	var reDate4 = /^((0?[1-9]|[12]\d)\/(0?[1-9]|1[0-2])|30\/(0?[13-9]|1[0-2])|31\/(0?[13578]|1[02]))\/(19|20)?\d{2}$/;
	var reDate = reDate4;
	
	function doDateVenc(Id, pStr, pFmt){
	d = document.getElementById(Id);
	if (d.value !=""){ 
		if (d.value.length < 10){
			alert("Data Inválida!\nDigite corretamente a data: dd/mm/aaaa !");
			d.value="";
			d.focus(); 
			return false;
		}else{
		
			eval("reDate = reDate" + pFmt);
				if (reDate.test(pStr)) {
				return false;
				} else if (pStr != null && pStr != "") {
				alert("ALERTA DE ERRO!!\n\n" + pStr + " NÃO é uma data válida.");
				d.value="";
				d.focus(); 
				return false;
			}
		}	
	}else{
	return false;
  }
}
	