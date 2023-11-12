/***********************************************************************

Le fichier:			CompareDate.java

Projet:				ProjetSynthese

Objectifs:			Permet de trier selon les dates des items

Logiciel: 			Éclipse 4.16.0

Plateforme:			Windows

Auteur:				Nicolas Fleurent

Date de création:	2023-04-17

***********************************************************************/

package pkClasses;

import java.util.Comparator;

public class CompareDate implements Comparator<Item> {
	
	public int compare(Item unItem, Item deuxItem) {
		int resultat;
		
		if(unItem.getDate().before(deuxItem.getDate())) {
			resultat = -1;
		}
		else if(unItem.getDate().equals(deuxItem.getDate())) {
			resultat = unItem.getTitre().compareTo(deuxItem.getTitre());
		}
		else
			resultat = 1;
		
		return resultat;
	}

}
