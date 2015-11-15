function adicionaUsuario(){
var divPai = $('#tbBody');
divPai.append(" <tr>");
divPai.append(" <td align=center>"+$('#apelidoUsuario').val()+"</td>");	
divPai.append(" <td align=center>"+$('#emailUsuario').val()+"</td>");
divPai.append(" <td >"+
			  " <div align=center>"+
			  " <button  class='btn button' style='background: transparent; border: none'>"+
			  "	<span title='Remover' class='glyphicon glyphicon-minus-sign' style='color:red; font-size:12px'></span>"+
			  " </button>"+
			  " <button   class='btn button' style='background: transparent; border: none'>"+
			  "	<span title='Editar' class='glyphicon glyphicon-edit' style='font-size:12px'></span>"+
			  " </button>"+
			  " </div>"+
			  " </td>");
divPai.append(" </tr>");
document.getElementById('apelidoUsuario').value='';
document.getElementById('emailUsuario').value='';
}

function removeUsuario(id){
	

}