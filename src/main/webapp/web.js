function inscription() {
	var nom = $('#nom').val();
	var prenom = $('#prenom').val();
	var pseudo = $('#pseudo').val();
	var mdp = $('#mdp').val();
	
	if(mdp == "" || nom == "" || prenom == "" || pseudo == ""){
		alert("Champs manquants");
	} 
	
	else {
		$.ajax({
			type : 'POST',
			contentType : 'application/json',
			url : "v1/userdb/",
			dataType : "json",
			data : JSON.stringify({
				"nom" : $('#nom').val(),
				"prenom" : $('#prenom').val(),
				"pseudo" : $('#pseudo').val(),
				"mdp" : $('#mdp').val(), 
				"typeUser" : "",
				"id" : 0
			}),
			
			success : function(data, textStatus, jqXHR) {
				alert("Inscription réussie");
				window.location.href = "index.html" ;
				
			},
			
			error : function(jqXHR, textStatus, errorThrown) {
				$("#message").html("Inscription echouée !");
				alert('postUser error: ' + textStatus + " " + errorThrown);
			}
		});
	}
}
