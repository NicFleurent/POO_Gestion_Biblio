/***********************************************************************

Le fichier:			BDItems.java

Projet:				ProjetSynthese

Objectifs:			Cr�er une base de donn�es pour les items

Logiciel: 			�clipse 4.16.0

Plateforme:			Windows

Auteur:				Nicolas Fleurent

Date de cr�ation:	2023-04-17

***********************************************************************/

package pkClasses;

import java.io.Serializable;
import java.util.ArrayList;

public class BDItems implements Serializable {
	
	protected ArrayList<Item> listeItems;
	
	public BDItems() {
		listeItems = new ArrayList<Item>();
	}
	
	public BDItems(Item tabItems[]) {
		listeItems = new ArrayList<Item>();
		
		for(int i = 0 ; i < tabItems.length ; i++) {
			listeItems.add(tabItems[i]);
		}
	}
	
	public ArrayList<Item> getListeItems() {
		return listeItems;
	}
	
	public void setListeItem(ArrayList<Item> uneListeItems) {
		listeItems = uneListeItems;
	}
	
	public void ajouter(Item unItem) {
		listeItems.add(unItem);
	}
	
	public void supprimer(Item unItem) {
		listeItems.remove(unItem);
	}
	
	public void supprimer(int position) {
		listeItems.remove(position);
	}
	
	public void modifier(int position, Item unItem) {
		listeItems.set(position, unItem);
	}
	
	public int indexOf(Item unItem) {
		return listeItems.indexOf(unItem);
	}
	
	public Item lire(Item unItem) {
		return listeItems.get(this.indexOf(unItem));
	}
	
	public Item lire(int position) {
		return listeItems.get(position);
	}
	
	public int size() {
		return listeItems.size();
	}
	
	public String toString() {
		return listeItems.toString();
	}

}
