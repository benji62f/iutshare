    function creerAnnonce(){
	var titre = $('#titre').val();
	var description = $('#msg').val();
	var lieu = $('#lieu').val();
	
	if(titre == "" || description == "" || lieu == ""){
		alert("Champs manquants");
	} 
	
	else {
		$.ajax({
			type : 'POST',
			contentType : 'application/json',
			url : "v1/annonce/",
			dataType : "json",
			data : JSON.stringify({
				"titre" : $('#titre').val(),
				"msg" : $('#msg').val(),
				"lieu" : $('#lieu').val(),
				"type" : $('#selectType').val(), 
				"ano" : 0
			}),
			
			success : function(data, textStatus, jqXHR) {
				alert("votre annonce a été crée avec succès");
				window.location.href = "annonces.html" ;
				
			},
			
			error : function(jqXHR, textStatus, errorThrown) {
				$("#message").html("Probleme lors de la création de l'annonce !");
				alert('postUser error: ' + textStatus + " " + errorThrown);
			}
		});
	}
	}
