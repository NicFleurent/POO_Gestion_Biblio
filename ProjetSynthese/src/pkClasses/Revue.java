/***********************************************************************

Le fichier:			Revue.java

Projet:				ProjetSynthese

Objectifs:			Cr�er des periodiques de type revue

Logiciel: 			�clipse 4.16.0

Plateforme:			Windows

Auteur:				Nicolas Fleurent

Date de cr�ation:	2023-04-17

***********************************************************************/

package pkClasses;

import java.util.GregorianCalendar;

public class Revue extends Periodique {
	
	private String sujet;

	public Revue() {
		super();
		this.sujet = "non-d�fini";
	}

	public Revue(String titre, GregorianCalendar date, int nbrCopies, String coteDewey, double valeur, String periodicite,
			 String _numPeriode, String issn, String _sujet) throws ErreurBiblio {
		super(titre, date, nbrCopies, coteDewey, valeur, periodicite, issn, _numPeriode);
		this.sujet = _sujet;
	}

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((sujet == null) ? 0 : sujet.hashCode());
		return result;
	}

	public boolean equals(Revue obj) {
		return (super.equals(obj) &&
				sujet.equals(obj.getSujet()));
	}

	public String toString() {
		return super.toString() + System.getProperty ("line.separator") + "sujet=" + sujet;
	}
	
	

}
