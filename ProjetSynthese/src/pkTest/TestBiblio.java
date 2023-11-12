/***********************************************************************

Le fichier:			TestBiblio.java

Projet:				ProjetSynthese

Objectifs:			Cr�er le frame principal (Launcher)

Logiciel: 			�clipse 4.16.0

Plateforme:			Windows

Auteur:				Nicolas Fleurent

Date de cr�ation:	2023-04-17

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
