/***********************************************************************

Le fichier:			Dvd.java

Projet:				ProjetSynthese

Objectifs:			Cr�er des multimedia de type DVD

Logiciel: 			�clipse 4.16.0

Plateforme:			Windows

Auteur:				Nicolas Fleurent

Date de cr�ation:	2023-04-17

***********************************************************************/

package pkClasses;

import java.util.GregorianCalendar;

public class Dvd extends Multimedia {
	
	private String cineaste;

	public Dvd() {
		super();
		this.cineaste = "non-d�fini";
	}

	public Dvd(String titre, GregorianCalendar date, int _nbrCopies, String _coteDewey, double _valeur, String sujet,
			String isbn, double _duree, String cineaste) throws ErreurBiblio {
		super(titre, date, _nbrCopies, _coteDewey, _valeur, sujet, isbn, _duree);
		this.cineaste = cineaste;
	}

	public String getCineaste() {
		return cineaste;
	}

	public void setCineaste(String cineaste) {
		this.cineaste = cineaste;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cineaste == null) ? 0 : cineaste.hashCode());
		return result;
	}

	public boolean equals(Dvd obj) {
		return(	super.equals(obj) &&
				sujet.equals(obj.getCineaste()));
	}

	public String toString() {
		return super.toString() + "cineaste=" + cineaste;
	}
	
	
	
}
