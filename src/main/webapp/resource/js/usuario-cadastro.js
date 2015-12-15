$(document).ready(function() {
	
    $('.img-usuario-lg').hide();
    
    $('.ico-usuario').width('auto');
    
    $('.ico-usuario').click(function(){
        $('.ico-usuario').css('border','2px solid #f5f5f5');
        $(this).css('border','2px solid #428bca');
        var imagem = document.getElementById('imagSelec');
        imagem.value = $(this).attr('id');
        carregarUsuario($(this).find('img').attr('src'));
        
    });
        
    
});

function carregarUsuario(path){
    
    path = path.replace('-min','');
    $('.img-usuario-lg').attr('src',path);
    $('.img-usuario-lg').show();

}


