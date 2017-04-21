$(document).ready(function(){
		$('#logIn').click(function(){
			var user = $('#inputEmail').val();
			var password = $('#inputPassword').val();
			
			
			$.ajax({
				type:'POST',
				data: {user: user,password:password, tipoFuncion:"logIn"},
				url:'controlVista',
				success: function(resultado){
					alert(resultado);
				}
			});
		});
	});