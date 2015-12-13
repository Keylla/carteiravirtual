$.fn.message = function(options){
	
	/*
	 * text => messagem
	 * type => [success, info, warning, danger]
	 * 
	 * */
	
	this.html(options.text).addClass('alert fade in alert-' +(options.type || "warning"));
	this.attr('role','alert');
	 
}