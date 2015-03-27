    function logRedirect(){
        adresse = window.location.href
        if (readCookie("login") == null) {
            window.location.href = "authent.html";
        }
        return adresse;
    }
    
    
    function decoSite(){
        var html ="<button id=\"deconnexion\" onclick=\"deconnexion()\" type=\"button\" class=\"btn btn-primary\">Deconnexion</button>"
        if (readCookie("login") != null) {
		$("#signIn").hide();
		$("#deco").html(html);
		$("#deco").show();
		$("#signIn2").hide();
		$("#deco2").html(html);
		$("#deco2").show();
        }else{
        $("#deco").hide();
        $("#signIn").show();
        $("#deco2").hide();
        $("#signIn2").show();
        }
    }
    
    function deconnexion() {
	eraseCookie("login");
	void window.location.reload();
    }

    function eraseCookie(name) {
	createCookie(name,"",-1);
    }
    
    function afficherSession() {
	var html = '<h2>Bonjour ';
	var htmlsuite = '<h3>Nom prenom</h3><h3>Age</br></br></h3><h3>Formation choisie</h3><h3>Editer votre Profil</h3>';
	var baratin = '<h3>Vous n\'êtes pas encore authentifié pour pouvoir accèder à cette rubrique, veuillez cliquer sur le bouton ci dessous pour le faire</h3>';
	if (readCookie("login") == null) {
		/*window.location.href = "accueil.html";*/
		$("#session").html(baratin)
	} else {
		html = html +readCookie("login")+"</h2> " + htmlsuite;
		$("#session").html(html);
	}
    }
    
    
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
	var login = $('#login').val();
	var mdp = $('#mdp').val();
	
	
	
	createCookie("login",login,7);
	    	 
	 $.getJSON("v1/user/"+login+"/"+mdp, function(data) {
		 if (data == null){
			 $("#erreur").html("</br><div class='alert alert-danger' role='alert'>Mauvais Identifiant</div>");
			 return 0;
		 }
		 createCookie("login",login,7);
		 window.location.href = "accueil.html";
		 /*if(logRedirect() === "mes_abonnements.html"){
		 window.location.href = "mes_abonnements.html";
		 }*/
		}).error(function() {
			$("#erreur").html("<div class='alert alert-danger' role='alert'>Mauvais Identifiant</div>");
		});
		
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
			
		});
	}
	
    }
