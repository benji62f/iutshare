function insertTag(startTag, endTag, textareaId, tagType) {
	var field = document.getElementById(textareaId);
	var scroll = field.scrollTop;
	field.focus();
	
	
	if (window.ActiveXObject) {
		var textRange = document.selection.createRange();            
		var currentSelection = textRange.text;
	} else {
		var startSelection   = field.value.substring(0, field.selectionStart);
		var currentSelection = field.value.substring(field.selectionStart, field.selectionEnd);
		var endSelection     = field.value.substring(field.selectionEnd);
	}
	
	if (tagType) {
		switch (tagType) {
			case "lien":
					endTag = "</lien>";
					if (currentSelection) {
							if (currentSelection.indexOf("http://") == 0 || currentSelection.indexOf("https://") == 0 || currentSelection.indexOf("ftp://") == 0 || currentSelection.indexOf("www.") == 0) {
									var label = prompt("Quel est le libellé du lien ?") || "";
									startTag = "<lien url=\"" + currentSelection + "\">";
									currentSelection = label;
							} else {
									var URL = prompt("Quelle est l'url ?");
									startTag = "<lien url=\"" + URL + "\">";
							}
					} else {
							var URL = prompt("Quelle est l'url ?") || "";
							var label = prompt("Quel est le libellé du lien ?") || "";
							startTag = "<lien url=\"" + URL + "\">";
							currentSelection = label;                     
					}
			break;
			case "citation":
					endTag = "</citation>";
					if (currentSelection) {
							if (currentSelection.length > 30) {
									var auteur = prompt("Quel est l'auteur de la citation ?") || "";
									startTag = "<citation nom=\"" + auteur + "\">";
							} else {
									var citation = prompt("Quelle est la citation ?") || "";
									startTag = "<citation nom=\"" + currentSelection + "\">";
									currentSelection = citation;    
							}
					} else {
							var auteur = prompt("Quel est l'auteur de la citation ?") || "";
							var citation = prompt("Quelle est la citation ?") || "";
							startTag = "<citation nom=\"" + auteur + "\">";
							currentSelection = citation;    
					}
			break;	
		}
	}
	
	if (window.ActiveXObject) {
		textRange.text = startTag + currentSelection + endTag;
		textRange.moveStart('character', -endTag.length-currentSelection.length);
		textRange.moveEnd('character', -endTag.length);
		textRange.select();  
	} else { // Ce n'est pas IE
		field.value = startSelection + startTag + currentSelection + endTag + endSelection;
		field.focus();
		field.setSelectionRange(startSelection.length + startTag.length, startSelection.length + startTag.length + currentSelection.length);
	}  
	
	field.scrollTop = scroll;   
}

function preview(textareaId, previewDiv) {
	var field = textareaId.value;
	if (document.getElementById('previsualisation').checked && field) {
		
		var smiliesName = new Array(':magicien:', ':colere:', ':diable:', ':ange:', ':ninja:', '&gt;_&lt;', ':pirate:', ':zorro:', ':honte:', ':soleil:', ':\'\\(', ':waw:', ':\\)', ':D', ';\\)', ':p', ':lol:', ':euh:', ':\\(', ':o', ':colere2:', 'o_O', '\\^\\^', ':\\-°');
		var smiliesUrl  = new Array('magicien.png', 'angry.gif', 'diable.png', 'ange.png', 'ninja.png', 'pinch.png', 'pirate.png', 'zorro.png', 'rouge.png', 'soleil.png', 'pleure.png', 'waw.png', 'smile.png', 'heureux.png', 'clin.png', 'langue.png', 'rire.gif', 'unsure.gif', 'triste.png', 'huh.png', 'mechant.png', 'blink.gif', 'hihi.png', 'siffle.png');
		var smiliesPath = "http://www.siteduzero.com/Templates/images/smilies/";
	
		field = field.replace(/&/g, '&amp;');
		field = field.replace(/</g, '&lt;').replace(/>/g, '&gt;');
		field = field.replace(/\n/g, '<br />').replace(/\t/g, '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');
		
		field = field.replace(/&lt;gras&gt;([\s\S]*?)&lt;\/gras&gt;/g, '<strong>$1</strong>');
		field = field.replace(/&lt;italique&gt;([\s\S]*?)&lt;\/italique&gt;/g, '<em>$1</em>');
		field = field.replace(/&lt;lien&gt;([\s\S]*?)&lt;\/lien&gt;/g, '<a href="$1">$1</a>');
		field = field.replace(/&lt;lien url="([\s\S]*?)"&gt;([\s\S]*?)&lt;\/lien&gt;/g, '<a href="$1" title="$2">$2</a>');
		field = field.replace(/&lt;image&gt;([\s\S]*?)&lt;\/image&gt;/g, '<img src="$1" alt="Image" />');
		field = field.replace(/&lt;citation nom=\"(.*?)\"&gt;([\s\S]*?)&lt;\/citation&gt;/g, '<br /><span class="citation">Citation : $1</span><div class="citation2">$2</div>');
		field = field.replace(/&lt;citation lien=\"(.*?)\"&gt;([\s\S]*?)&lt;\/citation&gt;/g, '<br /><span class="citation"><a href="$1">Citation</a></span><div class="citation2">$2</div>');
		field = field.replace(/&lt;citation nom=\"(.*?)\" lien=\"(.*?)\"&gt;([\s\S]*?)&lt;\/citation&gt;/g, '<br /><span class="citation"><a href="$2">Citation : $1</a></span><div class="citation2">$3</div>');
		field = field.replace(/&lt;citation lien=\"(.*?)\" nom=\"(.*?)\"&gt;([\s\S]*?)&lt;\/citation&gt;/g, '<br /><span class="citation"><a href="$1">Citation : $2</a></span><div class="citation2">$3</div>');
		field = field.replace(/&lt;citation&gt;([\s\S]*?)&lt;\/citation&gt;/g, '<br /><span class="citation">Citation</span><div class="citation2">$1</div>');
		field = field.replace(/&lt;taille valeur=\"(.*?)\"&gt;([\s\S]*?)&lt;\/taille&gt;/g, '<span class="$1">$2</span>');
		
		for (var i=0, c=smiliesName.length; i<c; i++) {
			field = field.replace(new RegExp(" " + smiliesName[i] + " ", "g"), "&nbsp;<img src=\"" + smiliesPath + smiliesUrl[i] + "\" alt=\"" + smiliesUrl[i] + "\" />&nbsp;");
		}
		
		document.getElementById(previewDiv).innerHTML = field;
	}
}

function getXMLHttpRequest() {
	var xhr = null;
	
	if (window.XMLHttpRequest || window.ActiveXObject) {
		if (window.ActiveXObject) {
			try {
				xhr = new ActiveXObject("Msxml2.XMLHTTP");
			} catch(e) {
				xhr = new ActiveXObject("Microsoft.XMLHTTP");
			}
		} else {
			xhr = new XMLHttpRequest();
		}
	} else {
		alert("Votre navigateur ne supporte pas l'objet XMLHTTPRequest...");
		return null;
	}
	
	return xhr;
}


function view(textareaId, viewDiv){
	var content = encodeURIComponent(document.getElementById(textareaId).value);
	var xhr = getXMLHttpRequest();
	
	if (xhr && xhr.readyState != 0) {
		xhr.abort();
		delete xhr;
	}
	
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200){
			document.getElementById(viewDiv).innerHTML = xhr.responseText;
		} else if (xhr.readyState == 3){
			document.getElementById(viewDiv).innerHTML = "<div style=\"text-align: center;\">Chargement en cours...</div>";
		}
	}
	
	xhr.open("POST", "view.php", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("string=" + content);
}