<?php 

function parseZCode($content) {
	// Parsage des balises
	$zcode = array(  
		'`&lt;italique&gt;(.+)&lt;/italique&gt;`isU',  
		'`&lt;gras&gt;(.+)&lt;/gras&gt;`isU',
		'`&lt;barre&gt;(.+)&lt;/barre&gt;`isU',
		'`&lt;lien&gt;(.+)&lt;/lien&gt;`isU',
		'`&lt;lien url=&quot;(.+)&quot;&gt;(.+)&lt;/lien&gt;`isU',
		'`&lt;image&gt;(.+)&lt;/image&gt;`isU',
		'`&lt;citation&gt;(.+)&lt;/citation&gt;`isU',
		'`&lt;citation nom=&quot;(.+)&quot;&gt;(.+)&lt;/citation&gt;`isU',
		'`&lt;citation lien=&quot;(.+)&quot;&gt;(.+)&lt;/citation&gt;`isU',
		'`&lt;taille valeur=&quot;(.+)&quot;&gt;(.+)&lt;/taille&gt;`isU'
	);  
	
	$html = array(  
		'<em>$1</em>',  
		'<strong>$1</strong>',
		'<del>$1</del>', 
		'<a href="$1">$1</a>',
		'<a href="$1">$2</a>',
		'<img src="$1" alt="Image utilisateur" />',
		'<br /><span class="citation">Citation</span><div class="citation2">$1</div>',
		'<br /><span class="citation">Citation : $1</span><div class="citation2">$2</div>',
		'<br /><span class="citation"><a href="$1">Citation</a></span><div class="citation2">$2</div>',
		'<span class="$1">$2</span>'
	); 
	
	$content = htmlspecialchars($content, ENT_QUOTES);
	$content = preg_replace($zcode, $html, $content);
	
	// parsage des smilies
	$smiliesName = array(':magicien:', ':colere:', ':diable:', ':ange:', ':ninja:', '&gt;_&lt;', ':pirate:', ':zorro:', ':honte:', ':soleil:', ':\'\\(', ':waw:', ':\\)', ':D', ';\\)', ':p', ':lol:', ':euh:', ':\\(', ':o', ':colere2:', 'o_O', '\\^\\^', ':\\-°');
	$smiliesUrl  = array('magicien.png', 'angry.gif', 'diable.png', 'ange.png', 'ninja.png', 'pinch.png', 'pirate.png', 'zorro.png', 'rouge.png', 'soleil.png', 'pleure.png', 'waw.png', 'smile.png', 'heureux.png', 'clin.png', 'langue.png', 'rire.gif', 'unsure.gif', 'triste.png', 'huh.png', 'mechant.png', 'blink.gif', 'hihi.png', 'siffle.png');
	$smiliesPath = "http://www.siteduzero.com/Templates/images/smilies/";
	
	for ($i = 0, $c = count($smiliesName); $i < $c; $i++) {
		$content = preg_replace('`' . $smiliesName[$i] . '`isU', '<img src="' . $smiliesPath . $smiliesUrl[$i] . '" alt="smiley" />', $content);
	}
	
	// Rtours à la ligne
	$content = preg_replace('`\n`isU', '<br />', $content); 
	
	return $content;

}

if (isset($_POST["string"])) {
	$content = $_POST["string"];
	
	if (get_magic_quotes_gpc()) {
		$content = stripslashes($content);
	}

	echo parseZCode($content); // Ecriture du contenu parsé. 
}
?>