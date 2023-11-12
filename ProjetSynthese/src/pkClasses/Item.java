/***********************************************************************

Le fichier:			Item.java

Projet:				ProjetSynthese

Objectifs:			Cr�er un caneva pour les items de la biblioth�que

Logiciel: 			�clipse 4.16.0

Plateforme:			Windows

Auteur:				Nicolas Fleurent

Date de cr�ation:	2023-04-17

***********************************************************************/

package pkClasses;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

public abstract class Item implements Comparable<Item>, Comparator<Item>, Serializable {
	
	protected String 			titre;
	protected String			coteDewey;
	protected GregorianCalendar date;
	protected int				nbrCopies;
	protected double			valeur;
	
	public Item() {
		this.titre = "non-d�fini";
		this.date = new GregorianCalendar();
		this.nbrCopies = 0;
		this.coteDewey = "non-d�fini";
		this.valeur = 0.0;
	}
	
	public Item(String titre, GregorianCalendar date, int _nbrCopies, String _coteDewey, double _valeur) throws ErreurBiblio {
		this.titre = titre;
		this.date = date;
		setNbrCopies(_nbrCopies);
		this.coteDewey = _coteDewey;
		setValeur(_valeur);
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public GregorianCalendar getDate() {
		return date;
	}

	public void setDate(GregorianCalendar date) {
		this.date = date;
	}

	public int getNbrCopies() {
		return nbrCopies;
	}

	public void setNbrCopies(int _nbrCopies) throws ErreurBiblio {
		if(_nbrCopies >= 0) {
			this.nbrCopies = _nbrCopies;
		}
		else {
			this.nbrCopies = 0;
			throw new ErreurBiblio("negatif");
		}
	}

	public String getCoteDewey() {
		return coteDewey;
	}
	
	public void setCoteDewey(String _coteDewey) throws ErreurBiblio {
		this.coteDewey = _coteDewey;
	}

	public double getValeur() {
		return valeur;
	}

	public void setValeur(double _valeur) throws ErreurBiblio {
		if(_valeur >= 0) {
			this.valeur = _valeur;
		}
		else {
			this.valeur = 0;
			throw new ErreurBiblio("negatif");
		}
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coteDewey == null) ? 0 : coteDewey.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + nbrCopies;
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valeur);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	public boolean equals(Item obj) {
		return(	titre.equals(obj.getTitre()) &&
				afficherDate().equals(obj.afficherDate()) &&
				coteDewey.equals(obj.getCoteDewey()) &&
				valeur == obj.getValeur());
	}
	
	public String afficherDate() {
		int annee = date.get(Calendar.YEAR);
		int mois = date.get(Calendar.MONTH) + 1;
		int jour = date.get(Calendar.DATE);
		DecimalFormat deuxChiffres = new DecimalFormat("00");
		
		return annee + "-" + deuxChiffres.format(mois) + "-" + deuxChiffres.format(jour);
	}

	public String toString() {
		return "titre=" + titre + System.getProperty ("line.separator") + "date=" + afficherDate() + System.getProperty ("line.separator")
		+ "nbrCopies=" + nbrCopies + System.getProperty ("line.separator") + "coteDewey=" + coteDewey
				+ System.getProperty ("line.separator") + "valeur=" + valeur + System.getProperty ("line.separator");
	}
	
	public int compareTo(Item obj) {
		return titre.compareTo(obj.getTitre());
	}
	
	public int compare(Item unItem, Item deuxItem) {
		return unItem.getCoteDewey().compareTo(deuxItem.getCoteDewey());
	}
	
}
