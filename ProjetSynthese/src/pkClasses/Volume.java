/***********************************************************************

Le fichier:			Volume.java

Projet:				ProjetSynthese

Objectifs:			Cr�er un caneva pour les volumes

Logiciel: 			�clipse 4.16.0

Plateforme:			Windows

Auteur:				Nicolas Fleurent

Date de cr�ation:	2023-04-17

***********************************************************************/

package pkClasses;

import java.util.GregorianCalendar;

public abstract class Volume extends Item {

	protected String editeur;
	protected String isbn;
	
	public Volume() {
		super();
		this.editeur = "non-d�fini";
		this.isbn = "non-d�fini";
	}

	public Volume(String titre, GregorianCalendar date, int _nbrCopies, String _coteDewey, double _valeur, String editeur, String isbn)
			throws ErreurBiblio {
		super(titre, date, _nbrCopies, _coteDewey, _valeur);
		this.editeur = editeur;
		this.isbn = isbn;
	}

	public String getEditeur() {
		return editeur;
	}

	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((editeur == null) ? 0 : editeur.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		return result;
	}

	public boolean equals(Volume obj) {
		return(	super.equals(obj) &&
				editeur.equals(obj.getEditeur()) &&
				isbn.equals(obj.getIsbn()));
	}

	public String toString() {
		return super.toString() + "editeur=" + editeur + System.getProperty ("line.separator") + "isbn=" + isbn + System.getProperty ("line.separator");
	}
	
	
	
}
