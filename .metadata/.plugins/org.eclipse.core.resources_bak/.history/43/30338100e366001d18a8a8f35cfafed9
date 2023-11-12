package pkTab;

import java.util.Arrays;

import javax.swing.*;

/***********************************************************************

Le fichier:			TabNo4.java

Projet:				Laboratoire sur les tableaux

Objectifs:			Faire un tableau personnalisé

Logiciel: 			Éclipse 4.16.0

Plateforme:			Windows

Auteur:				Nicolas Fleurent

Date de création:	2022-10-31

***********************************************************************/

public class TabNo4 
{

	public static void main(String[] args) 
	{
		final	int		MIN_TAB			= 1;															//Nombre minimal de donner dans le tableau.
		final	int		MAX_TAB			= 10;															//Nombre maximal de donner dans le tableau.
		final	int		VIDE			= 0;
		
				String	strMenu[]		= {	"1. Ajouter un nombre.",
											"2. Modifier un nombre.",
											"3. Supprimer un nombre.",
											"4. Afficher les nombres du tableau.",
											"5. Additionner un nombre à tous les nombres du tableau.",
											"6. Initialiser tous les nombres à une même valeur.",
											"7. Trier le tableau.",
											"8. Quitter l'application."};								//Menu principal
				String	strChoixMenu	= "";															//Choix dans le menu principal.
				
				String	strMenuAdd[]	= {	"1. Ajouter un nombre au début.",
											"2. Ajouter un nombre à la fin.",
											"3. Ajouter un nombre à un endroit spécifique."};			//Menu des additions
				String	strChoixMenuAdd	= "";															//Choix dans le menu des additions.
				
				String	strMenuSup[]	= {	"1. Supprimer le nombre au début.",
											"2. Supprimer le nombre à la fin.",
											"3. Supprimer le nombre à un endroit spécifique."};			//Menu des suppressions
				String	strChoixMenuSup	= "";															//Choix dans le menu des suppressions.
				String	strTab			= "";															//Variable pour afficher le tableau.
				
				char	cChoixMenu, cChoixMenuAdd, cChoixMenuSup;										//Conversion du choix.
		
				int		iLongTab;																		//Longueur du tableau décider par l'utilisateur.
				int		iTab[];																			//Tableau des nombres.
				int		iPosition;																		//Position choisit dans le tableau.
				int 	iCtr;																			//Compteur pour tableau.
				int		iNbr;																			//Nombre à ajouter à toutes les positions du tableau.

				boolean	bErreurTab;																		//Permets de valider que le tableau n'est pas plein.
				boolean	bErreurAdd;																		//Permets de valider que le tableau n'est pas plein.
				boolean	bErreurSup;																		//Permets de valider que le tableau n'est pas vide.
				boolean	bErreurMod;																		//Permets de valider que le tableau n'est pas vide.
				
		/***********************************************************************
		Message de bienvenue
		***********************************************************************/
		JOptionPane.showMessageDialog(null, "Bienvenue, cette application permet de contabiliser un tableau.");
		
		
		/***********************************************************************
		Cette boucle permet de revenir au menu lorsque la donner de base dépasse le max.
		***********************************************************************/
		do {
			bErreurTab = true;
			/***********************************************************************
			 Demande la longueur du tableau et l'ajuste
			 ***********************************************************************/
			iLongTab = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrer la longueur du tableau souhaité.", "Nombre de 1 à 10"));

			if (iLongTab <= MAX_TAB) 
			{

				iTab = new int[iLongTab];

				/***********************************************************************
				 Créer le menu
				 ***********************************************************************/
				do {

					strChoixMenu = (String) JOptionPane.showInputDialog(null,
																		"Sélectionnz votre choix parmi les options suivantes:", 
																		"Menu Principal",
																		JOptionPane.QUESTION_MESSAGE, 
																		null, 
																		strMenu, 
																		strMenu[0]);
					cChoixMenu = strChoixMenu.charAt(0);

					switch (cChoixMenu) 
					{
					case '1':
						/***********************************************************************
						 Vérifie que le tableau n'est pas plein
						 ***********************************************************************/
						bErreurAdd = true;

						for (iCtr = 0; iCtr < iLongTab; iCtr++)
							if (iTab[iCtr] == VIDE)
								bErreurAdd = false;

						if (bErreurAdd) 
							{
								JOptionPane.showMessageDialog(null, "Le tableau est plein, vous ne pouvez plus entrer de données.");
								break;
							}

						/***********************************************************************
						 Créer le menu des additions
						 ***********************************************************************/
						strChoixMenuAdd = (String) JOptionPane.showInputDialog(	null,
																				"Sélectionnz votre choix parmi les options suivantes:", 
																				"Menu Addition", 
																				JOptionPane.QUESTION_MESSAGE, 
																				null, 
																				strMenuAdd, 
																				strMenuAdd[0]);

						cChoixMenuAdd = strChoixMenuAdd.charAt(0);

						/***********************************************************************
						 Ajoute le nombre selon la position choisie
						 ***********************************************************************/
						switch (cChoixMenuAdd) 
						{
						case '1':
							if (iTab[0] == VIDE) 
								{
									iTab[0] = Integer.parseInt(JOptionPane.showInputDialog("Entrer le nombre entier."));
									JOptionPane.showMessageDialog(null, "Le nombre " + iTab[0] + " vient d'être ajouté au début du tableau.");
								} 
							else
								JOptionPane.showMessageDialog(null, "Il y a déjà un nombre au début du tableau.");

							break;

						case '2':
							if (iTab[iLongTab - 1] == VIDE) 
								{
									iTab[iLongTab - 1] = Integer
											.parseInt(JOptionPane.showInputDialog("Entrer le nombre entier."));
									JOptionPane.showMessageDialog(null, "Le nombre " + iTab[iLongTab - 1] + " vient d'être ajouté à la fin du tableau.");
								} 
							else
								JOptionPane.showMessageDialog(null, "Il y a déjà un nombre à la fin du tableau.");

							break;

						case '3':
							iPosition = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez la position à laquelle vous voulez ajouter un nombre.", "Nombre entre " + MIN_TAB + " et " + iLongTab));

							if(iPosition <= iLongTab)
								{
									if (iTab[iPosition - 1] == VIDE) 
										{
											iTab[iPosition - 1] = Integer.parseInt(JOptionPane.showInputDialog("Entrer le nombre entier."));
											JOptionPane.showMessageDialog(null, "Le nombre " + iTab[iPosition - 1] + " vient d'être ajouté à la position " + iPosition + " du tableau.");
										} 
									else
										JOptionPane.showMessageDialog(null, "Il y a déjà un nombre à cette position.");
								}
							else
								JOptionPane.showMessageDialog(null, "Cette position est hors des limites du tableau");
							

							break;
						}

						break;

					case '2':
						/***********************************************************************
						 Vérifie que le tableau n'est pas vide
						 ***********************************************************************/
						bErreurMod = true;

						for (iCtr = 0; iCtr < iLongTab; iCtr++)
							if (iTab[iCtr] != VIDE)
								bErreurMod = false;

						if (bErreurMod) 
							{
								JOptionPane.showMessageDialog(null, "Le tableau est vide, vous ne pouvez pas modifier de donnée.");
								break;
							}

						/***********************************************************************
						 Modifie le nombre selon la position choisie
						 ***********************************************************************/
						iPosition = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez la position à laquelle vous voulez ajouter un nombre.", "Nombre entre " + MIN_TAB + " et " + iLongTab));

						if(iPosition <= iLongTab)
							{
								if (iTab[iPosition - 1] != VIDE) 
									{
										iTab[iPosition - 1] = Integer.parseInt(JOptionPane.showInputDialog("Entrer le nombre entier."));
										JOptionPane.showMessageDialog(null, "On vient de modifier pour " + iTab[iPosition - 1] + " la position " + iPosition + " du tableau.");
									} 
								else
									JOptionPane.showMessageDialog(null, "Il n'y a pas de nombre à la position " + iPosition + " du tableau.");
							}
						else
							JOptionPane.showMessageDialog(null, "Cette position est hors des limites du tableau");

						break;

					case '3':
						/***********************************************************************
						 Vérifi que le tableau n'est pas vide
						 ***********************************************************************/
						bErreurSup = true;

						for (iCtr = 0; iCtr < iLongTab; iCtr++)
							if (iTab[iCtr] != VIDE)
								bErreurSup = false;

						if (bErreurSup) 
							{
								JOptionPane.showMessageDialog(null, "Le tableau est vide, vous ne pouvez pas supprimer de donnée.");
								break;
							}

						/***********************************************************************
						 Créer le menu des suppressions.
						 ***********************************************************************/
						strChoixMenuSup = (String) JOptionPane.showInputDialog(	null,
																				"Sélectionnz votre choix parmi les options suivantes:", 
																				"Menu Addition",
																				JOptionPane.QUESTION_MESSAGE, 
																				null, 
																				strMenuSup, 
																				strMenuSup[0]);

						cChoixMenuSup = strChoixMenuSup.charAt(0);

						/***********************************************************************
						 Supprime le nombre selon la position choisie.
						 ***********************************************************************/
						switch (cChoixMenuSup) 
						{
						case '1':
							if (iTab[0] != VIDE) 
								{
									JOptionPane.showMessageDialog(null, "Le nombre " + iTab[0] + " au début du tableau vient d'être supprimé.");
									iTab[0] = VIDE;
								} 
							else
								JOptionPane.showMessageDialog(null, "Il n'y a pas de nombre au début du tableau.");

							break;

						case '2':
							if (iTab[iLongTab - 1] != VIDE) 
								{
									JOptionPane.showMessageDialog(null, "Le nombre " + iTab[iLongTab - 1] + " à la fin du tableau vient d'être supprimé.");
									iTab[iLongTab - 1] = VIDE;
								} 
							else
								JOptionPane.showMessageDialog(null, "Il n'y a pas de nombre à la fin du tableau.");

							break;

						case '3':
							iPosition = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez la position à laquelle vous voulez ajouter un nombre.", "Nombre entre " + MIN_TAB + " et " + iLongTab));
							
							if(iPosition <= iLongTab)
								{
									if (iTab[iPosition - 1] != VIDE) 
										{
										JOptionPane.showMessageDialog(null, "Le nombre " + iTab[iPosition - 1] + " à la position " + iPosition + " du tableau vient d'être supprimé.");
										iTab[iPosition - 1] = VIDE;
										} 
									else
										JOptionPane.showMessageDialog(null, "Il n'y a pas de nombre à la position " + iPosition + " du tableau.");
								}
							else
								JOptionPane.showMessageDialog(null, "Cette position est hors des limites du tableau");
							
							
							break;
						}

						break;

					case '4':
						strTab = "Voici la valeur des nombres du tableau:";

						/***********************************************************************
						 Créer le message avec les valeurs du tableau.
						 ***********************************************************************/
						for (iCtr = 0; iCtr < iLongTab; iCtr++) {
							if (iTab[iCtr] != VIDE)
								strTab += "\nPosition " + (iCtr + 1) + " : " + iTab[iCtr];
							else
								strTab += "\nPosition " + (iCtr + 1) + " : Vide";
						}
						JOptionPane.showMessageDialog(null, strTab);
						break;

					case '5':
						iNbr = Integer.parseInt(JOptionPane .showInputDialog("Entrer un nombre entier à ajouter \nà tous les nombres du tableau."));

						/***********************************************************************
						 Ajoute la variable à toutes les positions
						 ***********************************************************************/
						for (iCtr = 0; iCtr < iLongTab; iCtr++)
							iTab[iCtr] += iNbr;
						break;

					case '6':
						/***********************************************************************
						 Initie toutes les positions avec la variable saisie.
						 ***********************************************************************/
						iNbr = Integer.parseInt(JOptionPane.showInputDialog("Entrer le nombre entier que vous désirez \nmettre à toutes les positions du tableau."));
						Arrays.fill(iTab, iNbr);
						break;

					case '7':
						/************************************************************************
						 Tri les données.
						 ***********************************************************************/
						Arrays.sort(iTab);
						break;
					}

				} while (cChoixMenu != '8');
				bErreurTab = false;
			}

		} while (bErreurTab);
		
		/***********************************************************************
		Message de fin.
		***********************************************************************/
		JOptionPane.showMessageDialog(null, "L'application va terminé, bonne journée.");
		
		System.exit(0);

	}

}
