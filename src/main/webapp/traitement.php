<?php
if (isset($_FILES['fichier']))
{
	//$_FILES existe on récupère les infos qui nous intéressent
	$fichier=$_FILES['fichier']['name'];//nom réel de l'image
	$size=$_FILES['fichier']['size']; //poid de l'image en octets
	$tmp=$_FILES['fichier']['tmp_name'];//nom temporaire de l'image (sur le serveur)
	$type=$_FILES['fichier']['type'];//type de l'image
	$error = $_FILES['fichier']['error'];
	$retour = up_error($error,$fichier);
	if ($retour[0] === true) {
		//On récupère la taille de l'image
		list($width,$height)=getimagesize($tmp);
		if (is_uploaded_file($tmp)) //permet de vérifier si le fichier a été uploadé via http
		{
			//vérification du type de l'img, son poids et sa taille
			if ($type=="image/gif" && $size<="20500" && $width<="100" && $height<="100" )
			{
				// type mime gif, poids < à 20500 octets soit environ 20Ko, largeur = hauteur = 100px
				//Pour supprimer les espaces dans les noms de fichiers car celà entraîne une erreur lorsque vous voulez l'afficher
				$fichier = preg_replace ("` `i","",$fichier);//ligne facultative :)
				//On vérifie s'il existe une image qui a le même nom dans le répertoire
				if (file_exists('./images_up/'.$fichier))
				{
					//Le fichier existe on rajoute dans son nom le timestamp du moment pour le différencier de la première (comme cela on est sur de ne pas avoir 2 images avec le même nom :) )
					$nom_final= preg_replace("`.gif`is",date("U").".gif",$fichier);
				}
				else {
					$nom_final=$fichier; //l'image n'existe pas on garde le même nom
				}
				//on déplace l'image dans le répertoire final
				move_uploaded_file($tmp,'./images_up/'.$nom_final);
				//Message indiquant que tout s'est bien passé
				echo "L'image a été uploadée avec succès<br/>";
			}
			else {
				//Le type mime, ou la taille ou le poids est incorrect
				echo 'Votre image a été rejetée (poids, taille ou type incorrect)';
			}
		}
		else {
			echo $retour[1],'<br />';
		}
	}
}
//Pour tester si l'image est bien à sa place
echo '<img src="./images_up/'.$nom_final.'" border="0" />';
echo '<br/>';
echo '<a href="javascript:history.back();">Retour</a>';
?>
