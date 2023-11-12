/***********************************************************************

Le fichier:			Livre.java

Projet:				ProjetSynthese

Objectifs:			Cr�er des volume de type livre

Logiciel: 			�clipse 4.16.0

Plateforme:			Windows

Auteur:				Nicolas Fleurent

Date de cr�ation:	2023-04-17

***********************************************************************/

package pkClasses;

import java.util.GregorianCalendar;

public class Livre extends Volume {

	private String 	categorie;
	private String 	collection;
	private String	auteur;
	private int		nbrPage;
	
	public Livre() {
		super();
		this.categorie = "non-d�fini";
		this.collection = "non-d�fini";
		this.auteur = "non-d�fini";
		this.nbrPage = 0;
	}

	public Livre(String titre, GregorianCalendar date, int _nbrCopies, String _coteDewey, double _valeur, String editeur,
			String isbn, String categorie, String collection, String auteur, int _nbrPage) throws ErreurBiblio {
		super(titre, date, _nbrCopies, _coteDewey, _valeur, editeur, isbn);
		this.categorie = categorie;
		this.collection = collection;
		this.auteur = auteur;
		setNbrPage(_nbrPage);
	}

	public String getCategorie() {
		return categorie;
	}
	
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	
	public String getCollection() {
		return collection;
	}
	
	public void setCollection(String collection) {
		this.collection = collection;
	}
	
	public String getAuteur() {
		return auteur;
	}
	
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	
	public int getNbrPage() {
		return nbrPage;
	}
	
	public void setNbrPage(int _nbrPage) throws ErreurBiblio {
		if(_nbrPage >= 0) {
			this.nbrPage = _nbrPage;
		}
		else {
			this.nbrPage = 0;
			throw new ErreurBiblio("negatif");
		}
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((auteur == null) ? 0 : auteur.hashCode());
		result = prime * result + ((categorie == null) ? 0 : categorie.hashCode());
		result = prime * result + ((collection == null) ? 0 : collection.hashCode());
		result = prime * result + nbrPage;
		return result;
	}

	public boolean equals(Livre obj) {
		return(	super.equals(obj) &&
				categorie.equals(obj.getCategorie()) &&
				collection.equals(obj.getCollection()) &&
				auteur.equals(obj.getAuteur()) &&
				nbrPage == obj.getNbrPage());
	}

	public String toString() {
		return super.toString() + "categorie=" + categorie + System.getProperty ("line.separator") 
				+ "collection=" + collection + System.getProperty ("line.separator") + "auteur=" + auteur 
				+ System.getProperty ("line.separator") + "nbrPage=" + nbrPage;
	}
	
}
