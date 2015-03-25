    function createCookie(name,value,days) {
	if (days) {
		var date = new Date();
		date.setTime(date.getTime()+(days*24*60*60*1000));
		var expires = "; expires="+date.toGMTString();
	}
	else var expires = "";
	document.cookie = name+"="+value+expires+"; path=/";
    }

    function readCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++) {
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1,c.length);
		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
	}
	return null;
    }

    function connexion() {
	var pseudo = $('#pseudo').val();
	var mdp = $('#mdp').val();
	
	
	
	createCookie("pseudo",pseudo,7);
	    	 
	 $.getJSON("v1/user/"+pseudo+"/"+mdp, function(data) {
		 if (data == null){
			 $("#erreur").html("<div class='alert alert-danger' role='alert'>Mauvais Identifiant</div>");
			 return 0;
		 }
		 createCookie("pseudo",pseudo,7);
		 window.location.href = "accueil.html";
		}).error(function() {
			$("#erreur").html("<div class='alert alert-danger' role='alert'>Mauvais Identifiant</div>");
		});
		
		console.log("Creation cookie");
    }

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
			url : "v1/user/",
			dataType : "json",
			data : JSON.stringify({
				"nom" : $('#nom').val(),
				"prenom" : $('#prenom').val(),
				"login" : $('#login').val(),
				"mdp" : $('#mdp').val(),
				"type" : $('#type').val(), 
				"id" : 0
			}),
			
			success : function(data, textStatus, jqXHR) {
				alert("Inscription réussie");
				window.location.href = "authent.html" ;
				
			},
			
			error : function(jqXHR, textStatus, errorThrown) {
				$("#message").html("Inscription echouée !");
				alert('postUser error: ' + textStatus + " " + errorThrown);
			}
		});
	}
	
	function deconnexion() {
	eraseCookie("pseudo");
	window.location.href = "index.html";
    }

    function afficherSession() {
	var html = 'Tu es ';
	if (readCookie("pseudo") == null) {
		window.location.href = "acceuil.html";
	} else {
		html = html + readCookie("pseudo");
		$("#session").html(html);
	}
    }

    function eraseCookie(name) {
	createCookie(name,"",-1);
    }
}
