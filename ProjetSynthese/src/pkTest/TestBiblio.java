/***********************************************************************

Le fichier:			TestBiblio.java

Projet:				ProjetSynthese

Objectifs:			Créer le frame principal (Launcher)

Logiciel: 			Éclipse 4.16.0

Plateforme:			Windows

Auteur:				Nicolas Fleurent

Date de création:	2023-04-17

***********************************************************************/

package pkTest;

import java.awt.EventQueue;

public class TestBiblio {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainFrame main = new MainFrame();
			}
		});

	}

}
