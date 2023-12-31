/***********************************************************************

Le fichier:			ErreurBiblio.java

Projet:				ProjetSynthese

Objectifs:			Cr�er des messages d'erreur personalis�

Logiciel: 			�clipse 4.16.0

Plateforme:			Windows

Auteur:				Nicolas Fleurent

Date de cr�ation:	2023-04-17

***********************************************************************/

package pkClasses;

public class ErreurBiblio extends Exception {
	
	private String typeErreur;
	
	public ErreurBiblio() {
		typeErreur = null;
	}
	
	public ErreurBiblio(String _typeErreur) {
		typeErreur = _typeErreur;
	}
	
	public String message() {
		if(typeErreur == "coteDewey")	
			return "La cote n'est pas valide, elle doit se situer entre 1 et 999 inclusivement.";
		else if(typeErreur == "negatif")
			return "Pour certains champs, le nombre ne peut pas �tre n�gatif, la valeur est alors d�fini � 0.";
		else if(typeErreur == "mois")
			return "Le mois n'est pas valide";
		else if(typeErreur == "jour")
			return "Le jour n'est pas valide";
		else
			return "L'op�ration n'est pas valide";
	}

}
