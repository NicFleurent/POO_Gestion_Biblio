/***********************************************************************

Le fichier:			MainFrame.java

Projet:				ProjetSynthese

Objectifs:			Cr�er les affichages et effectuer le programme

Logiciel: 			�clipse 4.16.0

Plateforme:			Windows

Auteur:				Nicolas Fleurent

Date de cr�ation:	2023-04-17

***********************************************************************/

package pkTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import pkClasses.BDItems;
import pkClasses.CompareDate;
import pkClasses.Dictionnaire;
import pkClasses.DisqueCompact;
import pkClasses.Dvd;
import pkClasses.ErreurBiblio;
import pkClasses.Item;
import pkClasses.Journal;
import pkClasses.Livre;
import pkClasses.Multimedia;
import pkClasses.Periodique;
import pkClasses.Revue;
import pkClasses.Volume;

public class MainFrame implements ActionListener {
	
	private JFrame frame;
	
	private JPanel menu;
	private JPanel pnlAccueil;
	private JPanel pnlAjouter;
	private JPanel pnlSupprimer;
	private JPanel pnlModifier;
	private JPanel pnlRechercher;
	private JPanel pnlAfficherConsole;
	private JPanel pnlArchiver;
	
	private JButton btnAjouter;
	private JButton btnSupprimer;
	private JButton btnModifer;
	private JButton btnRechercher;
	private JButton btnAfficherConsole;
	private JButton btnArchiver;
	private JButton btnQuitter;
	
	private JLabel lblAccueil;
	
	private BDItems bdBiblio;
	
	private Revue revueValid = new Revue();
	private Journal journalValid = new Journal();
	private Dvd dvdValid = new Dvd();
	private DisqueCompact cdValid = new DisqueCompact();
	private Livre livreValid = new Livre();
	private Dictionnaire dictionnaireValid = new Dictionnaire();
	
	private boolean modifierActif;
	
	private JLabel lblAjoutTypeDoc = new JLabel("Quel type de document souhaitez-vous ajouter?");
	private String[] strListeTypeDoc = {"Revue", "Journal", "Dvd", "Disque Compact", "Livre", "Dictionnaire"};
	private JComboBox<String> jcbListeTypeDoc = new JComboBox<String>(strListeTypeDoc);
	private JLabel lblAjoutEspaceur1 = new JLabel(" ");

	private String type;
	
	private JTextField tfAjoutTitre = new JTextField(10);
	private JTextField tfAjoutDate = new JTextField(10);
	private JTextField tfAjoutNbrCopie = new JTextField(10);
	private JTextField tfAjoutCoteDewey = new JTextField(10);
	private JTextField tfAjoutValeur = new JTextField(10);
	private JTextField tfAjoutPeriodicite = new JTextField(10);
	private JTextField tfAjoutNumPeriode = new JTextField(10);
	private JTextField tfAjoutIssn = new JTextField(10);
	private JTextField tfAjoutRegion = new JTextField(10);
	private JTextField tfAjoutSujet = new JTextField(10);
	private JTextField tfAjoutDuree = new JTextField(10);
	private JTextField tfAjoutIsbn = new JTextField(10);
	private JTextField tfAjoutCineaste = new JTextField(10);
	private JTextField tfAjoutAuteur = new JTextField(10);
	private JTextField tfAjoutGroupe = new JTextField(10);
	private JTextField tfAjoutNbrPiste = new JTextField(10);
	private JTextField tfAjoutEditeur = new JTextField(10);
	private JTextField tfAjoutCategorie = new JTextField(10);
	private JTextField tfAjoutCollection = new JTextField(10);
	private JTextField tfAjoutNbrPage = new JTextField(10);
	private JTextField tfAjoutType = new JTextField(10);
	
	private Item itemTemp;
	
	public MainFrame() {
		initialize();
	}
	
	//Pr�pare la fen�tre principal avec le menu et la page d'accueil
	private void initialize() {
		
		File testBD = new File("Biblio.ser");
		
		if(testBD.exists()) {
			recupererBD();
		}
		else {
			creerbdBase();
		}
		
		frame = new JFrame();
		frame.setTitle("Biblio Manager Pro FR");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setSize(1600, 800);
		frame.setLayout(new BorderLayout(10, 10));
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent windowEvent) {
		        quitterApp();
		    }
		});
		
		menu = new JPanel();
		menu.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		menu.setBackground(Color.GRAY);
		
		btnAjouter = new JButton("Ajouter un document");
		btnAjouter.addActionListener(this);
		menu.add(btnAjouter);
		
		btnSupprimer = new JButton("Supprimer un document");
		btnSupprimer.addActionListener(this);
		menu.add(btnSupprimer);
		
		btnModifer = new JButton("Modifier un document");
		btnModifer.addActionListener(this);
		menu.add(btnModifer);
		
		btnRechercher = new JButton("Rechercher un document");
		btnRechercher.addActionListener(this);
		menu.add(btnRechercher);
		
		btnAfficherConsole = new JButton("Afficher les documents");
		btnAfficherConsole.addActionListener(this);
		menu.add(btnAfficherConsole);
		
		btnArchiver = new JButton("Archiver les documents");
		btnArchiver.addActionListener(this);
		menu.add(btnArchiver);
		
		btnQuitter = new JButton("Quitter l'application");
		btnQuitter.addActionListener(this);
		menu.add(btnQuitter);
		
		pnlAccueil = new JPanel();
		pnlAccueil.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		
		lblAccueil = new JLabel("Bienvenue dans l'application de gestion de la biblioth�que");
		lblAccueil.setFont(new Font("Sans-serif", Font.BOLD, 36));
		
		pnlAccueil.add(lblAccueil);
		
		frame.add(menu, BorderLayout.NORTH);
		frame.add(pnlAccueil, BorderLayout.CENTER);
		frame.setVisible(true);
		
	}
	
	//***********************************************************************************************
	//Donne les effets aux boutons du menu
	//***********************************************************************************************
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if(s.equals("Ajouter un document")) {
			modifierActif = false;
			ajouterDocument();
		}
		else if(s.equals("Supprimer un document")) {
			modifierActif = false;
			supprimerDocument();
		}
		else if(s.equals("Modifier un document")) {
			modifierActif = true;
			supprimerDocument();
		}
		else if(s.equals("Rechercher un document")) {
			rechercherDocument();
		}
		else if(s.equals("Afficher les documents")) {
			afficher();
		}
		else if(s.equals("Archiver les documents")) {
			archiverDocuments();
		}
		else if(s.equals("Quitter l'application")) {
			quitterApp();
		}
	}
	
	//***********************************************************************************************
	//Cr�er la base de donn�e initiale
	//***********************************************************************************************
	public void creerbdBase() {
		bdBiblio = new BDItems();
		Revue uneRevue = new Revue();
		Revue deuxRevue = new Revue();
		Revue troisRevue = new Revue();
		Revue quatreRevue = new Revue();
		
		Journal unJournal = new Journal();
		Journal deuxJournal = new Journal();
		Journal troisJournal = new Journal(); 
		Journal quatreJournal = new Journal();
		
		Dvd unDvd = new Dvd(); 
		Dvd deuxDvd = new Dvd();
		Dvd troisDvd = new Dvd(); 
		Dvd quatreDvd = new Dvd();
		
		DisqueCompact unDisqueCompact = new DisqueCompact();
		DisqueCompact deuxDisqueCompact = new DisqueCompact(); 
		DisqueCompact troisDisqueCompact = new DisqueCompact(); 
		DisqueCompact quatreDisqueCompact = new DisqueCompact();
		
		Livre unLivre = new Livre();
		Livre deuxLivre = new Livre();
		Livre troisLivre = new Livre();
		Livre quatreLivre = new Livre();
		
		Dictionnaire unDictionnaire = new Dictionnaire(); 
		Dictionnaire deuxDictionnaire = new Dictionnaire();
		Dictionnaire troisDictionnaire = new Dictionnaire();
		Dictionnaire quatreDictionnaire = new Dictionnaire();
		
		try{
			uneRevue = new Revue("7 jours", new GregorianCalendar(2023,3,28), 10, "795.564", 4.99, "Hebdomadaire", "Vol.34 no.27", "2049-3630", "Art et culture");
			deuxRevue = new Revue("Hockey le magazine", new GregorianCalendar(2022,9,1), 2, "790.5", 12.95, "Bimestriel", "Volume 17 num�ro 2", "2434-561X", "Sport");
			troisRevue = new Revue("Coup de pouce", new GregorianCalendar(2023,2,1), 6, "370.87", 5.99, "Mensuelle", "Vol.40 no.1", "5890-357F", "Cuisine");
			quatreRevue = new Revue("Fine woodworking", new GregorianCalendar(1994,3,1), 3, "688.546", 6.95, "Bimestriel", "No.105", "8754-9867", "�b�nisterie");
			
			unJournal = new Journal("Le Nouvelliste", new GregorianCalendar(2022,9,1), 2, "70.575", 0.5, "Quotidienne", "12 decembre 2012", "5643-903E", "Mauricie");
			deuxJournal = new Journal("La Presse", new GregorianCalendar(2003,3,30), 1, "70.556", 0.65, "Quotidienne", "30 avril 2003", "8576-942KE", "Qu�bec(Province)");
			troisJournal = new Journal("La Nouvelle Union", new GregorianCalendar(2023,4,15), 15, "70.576", 0.25, "Hebdomadaire", "15 mai 2023", "5196-5876", "Arthabaska/�rable");
			quatreJournal = new Journal("Le Journal de Montr�al", new GregorianCalendar(1997,6,17), 1, "70.558", 0.75, "Quotidienne", "17 juillet 1997", "7548-985G", "Montr�al");
			
			unDvd = new Dvd("Avatar", new GregorianCalendar(2009,11,18), 2, "520.568", 14.99, "Science-Fiction", "978-3-10-145687-0", 162, "James Cameron");
			deuxDvd = new Dvd("Star Wars, �pisode VI : Le Retour du Jedi", new GregorianCalendar(1983,4,25), 3, "520.56", 129.99, "Science-Fiction", "978-3-11-587412-8", 135, "George Lucas");
			troisDvd = new Dvd("Les Minions", new GregorianCalendar(2015,6,10), 5, "790.867", 19.99, "Animation-Com�die", "978-4-12-876952-5", 91, "Kyle Balda");
			quatreDvd = new Dvd("Les Benchwarmers", new GregorianCalendar(2006,3,7), 1, "790.584", 4.99, "Baseball-Com�die", "978-3-43-654897-2", 82, "Dennis Dugan");
			
			unDisqueCompact = new DisqueCompact("Divisions", new GregorianCalendar(2019,8,13), 2, "780.589", 14.99, "Hard rock", "978-4-55-544354-6", 58.5, "Dustin Bates et Peter David", "Starset", 13);
			deuxDisqueCompact = new DisqueCompact("Nous autres", new GregorianCalendar(2015,3,14), 2, "780.784", 14.99, "Pop", "978-4-66-435627-7", 43.8, "Erik et Sonny Caouette", "2 Freres", 12);
			troisDisqueCompact = new DisqueCompact("Living Things", new GregorianCalendar(2012,5,26), 3, "780.595", 14.99, "Alternative rock", "978-4-65-642322-7", 36.99, "Chester Bennington et Mike Shinoda", "Linkin Park", 12);
			quatreDisqueCompact = new DisqueCompact("Moon landing", new GregorianCalendar(2013,9,18), 10, "780.444", 18.99, "Soft rock", "978-4-56-876956-3", 43.25, "James Blunt", "James Blunt", 11);
			
			unLivre = new Livre("La pierre du destin", new GregorianCalendar(2008,8,2), 5, "C840.568", 24.95, "�dition Michel Quintin", "978-2-89435-384-4", "Fantastique", "Les messagers de ga�a", "Fredrick D'Anterny", 336);
			deuxLivre = new Livre("Les 3 mousquetaires", new GregorianCalendar(2016,7,2), 3, "840.548", 29.95, "Groupe Archambault", "622-2-091525-80", "Romans �trangers", "non-applicable", "Alexandre Dumas", 620);
			troisLivre = new Livre("Pierre Gervais : au coeur du vestiaire", new GregorianCalendar(2022,10,18), 5, "C920.547", 29.95, "Ovations M�dias", "978-2-98211-600-9", "Sports", "non-applicable", "Mathias Brunet", 292);
			quatreLivre = new Livre("Sauces barbecue : marinades pour les grillades", new GregorianCalendar(2023,3,17), 5, "370.558", 34.95, "Homme", "978-2-761961-882", "Bases & Techniques", "non-applicable", "Steven Raichlen", 216);
			
			unDictionnaire = new Dictionnaire("Le Petit Robert de la langue fran�aise 2023", new GregorianCalendar(2022,1,1), 25, "440.589", 84.95, "Robert Ed LE", "978-2-32101-763-9", "francais");
			deuxDictionnaire = new Dictionnaire("Le Robert & Collins poche anglais : anglais-fran�ais", new GregorianCalendar(2021,6,1), 10, "440.998", 12.95, "Robert Ed LE", "978-2-32101-661-8", "anglais-fran�ais");
			troisDictionnaire = new Dictionnaire("Le Petit Larousse illustr� 2023", new GregorianCalendar(2022,6,1), 25, "440.598", 56.95, "Larousse", "978-2-03593-868-8", "francais");
			quatreDictionnaire = new Dictionnaire("Dictionnaire de poche fran�ais-espagnol", new GregorianCalendar(2022,7,1), 25, "440.788", 13.95, "Larousse", "978-2-03602-187-7", "espagnole-fran�ais");
		}
		catch(ErreurBiblio e1) {}
		
		bdBiblio.ajouter(uneRevue);
		bdBiblio.ajouter(deuxRevue);
		bdBiblio.ajouter(troisRevue);
		bdBiblio.ajouter(quatreRevue);
		bdBiblio.ajouter(unJournal);
		bdBiblio.ajouter(deuxJournal);
		bdBiblio.ajouter(troisJournal);
		bdBiblio.ajouter(quatreJournal);
		bdBiblio.ajouter(unDvd);
		bdBiblio.ajouter(deuxDvd);
		bdBiblio.ajouter(troisDvd);
		bdBiblio.ajouter(quatreDvd);
		bdBiblio.ajouter(unDisqueCompact);
		bdBiblio.ajouter(deuxDisqueCompact);
		bdBiblio.ajouter(troisDisqueCompact);
		bdBiblio.ajouter(quatreDisqueCompact);
		bdBiblio.ajouter(unLivre);
		bdBiblio.ajouter(deuxLivre);
		bdBiblio.ajouter(troisLivre);
		bdBiblio.ajouter(quatreLivre);
		bdBiblio.ajouter(unDictionnaire);
		bdBiblio.ajouter(deuxDictionnaire);
		bdBiblio.ajouter(troisDictionnaire);
		bdBiblio.ajouter(quatreDictionnaire);
	}

	//***********************************************************************************************
	//Recup�re la base de donner existante
	//***********************************************************************************************
	private void recupererBD() {
		bdBiblio = null;
		
		Path pathBD = Paths.get("Biblio.ser");
		Path pathBDAbsolu = pathBD.toAbsolutePath();
		String strPathBDAbsolu = pathBDAbsolu.toString();
		
		FileInputStream fileIn;
		ObjectInputStream in;
		try {
			fileIn = new FileInputStream(strPathBDAbsolu);
			in = new ObjectInputStream(fileIn);
			bdBiblio = (BDItems) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//***********************************************************************************************
	//Cr�ation de la page ajouter
	//***********************************************************************************************
	private void ajouterDocument() {
		resetFrame();
		
		pnlAjouter = new JPanel();
		pnlAjouter.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		
		jcbListeTypeDoc.setSelectedIndex(0);
		lblAjoutEspaceur1.setPreferredSize(new Dimension(1500, 0));
		
		pnlAjouter.removeAll();
		pnlAjouter.add(lblAjoutTypeDoc);
		pnlAjouter.add(jcbListeTypeDoc);
		pnlAjouter.add(lblAjoutEspaceur1);
		
		frame.add(pnlAjouter, BorderLayout.CENTER);
		frame.revalidate();
		frame.repaint();
		
		//***********************************************************************************************
		//Changement de la page selon le type de document
		//***********************************************************************************************
		jcbListeTypeDoc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("rawtypes")
				JComboBox cb = (JComboBox) e.getSource();
				type = (String) cb.getSelectedItem();
				
				creerPanelAjouter();
				
			}
			
		});		
	}
	
	private void creerPanelAjouter() {
		pnlAjouter.removeAll();
				
		JLabel lblAjoutTitre = new JLabel("Quel est le titre?");
		tfAjoutTitre.setText("");
		JLabel lblAjoutDate = new JLabel("Quel est la date de cr�ation?");
		tfAjoutDate.setText("AAAA-MM-JJ");
		JLabel lblAjoutNbrCopie = new JLabel("Combien de copies poss�dons-nous?");
		tfAjoutNbrCopie.setText("");
		JLabel lblAjoutCoteDewey = new JLabel("Quel est la cote Dewey?");
		tfAjoutCoteDewey.setText("");
		JLabel lblAjoutValeur = new JLabel("Quel est la valeur?");
		tfAjoutValeur.setText("");
		JLabel lblAjoutEspaceurItem = new JLabel(" ");
		lblAjoutEspaceurItem.setPreferredSize(new Dimension(1500, 0));
		JLabel lblAjoutPeriodicite = new JLabel("Quel est la periodicit�?");
		tfAjoutPeriodicite.setText("");
		JLabel lblAjoutNumPeriode = new JLabel("Quel est le num�ro de p�riode?");
		tfAjoutNumPeriode.setText("");
		JLabel lblAjoutIssn = new JLabel("Quel est l'issn?");
		tfAjoutIssn.setText("");
		JLabel lblAjoutRegion = new JLabel("Quel est la r�gion?");
		tfAjoutRegion.setText("");
		JLabel lblAjoutSujet = new JLabel("Quel est le sujet?");
		tfAjoutSujet.setText("");
		JLabel lblAjoutDuree = new JLabel("Quel est la dur�e?");
		tfAjoutDuree.setText("");
		JLabel lblAjoutIsbn = new JLabel("Quel est l'isbn?");
		tfAjoutIsbn.setText("");
		JLabel lblAjoutCineaste = new JLabel("Quel est le cin�aste ayant r�aliser ce film?");
		tfAjoutCineaste.setText("");
		JLabel lblAjoutAuteur = new JLabel("Qui est l'auteur?");
		tfAjoutAuteur.setText("");
		JLabel lblAjoutGroupe = new JLabel("Quel est le groupe?");
		tfAjoutGroupe.setText("");
		JLabel lblAjoutNbrPiste = new JLabel("Combien y a-t-il de pistes?");
		tfAjoutNbrPiste.setText("");
		JLabel lblAjoutEditeur = new JLabel("Qui est l'�diteur?");
		tfAjoutEditeur.setText("");
		JLabel lblAjoutCategorie = new JLabel("Quel est la cat�gorie?");
		tfAjoutCategorie.setText("");
		JLabel lblAjoutCollection = new JLabel("Quel est la collection?");
		tfAjoutCollection.setText("");
		JLabel lblAjoutNbrPage = new JLabel("Combien de pages contient ce livre?");
		tfAjoutNbrPage.setText("");
		JLabel lblAjoutType = new JLabel("Quel est le type du dictionnaire?");
		tfAjoutType.setText("");
		
		JButton btnEnvoiAjout;
		
		if(!modifierActif) {
			btnEnvoiAjout = new JButton("Ajouter");
			pnlAjouter.add(lblAjoutTypeDoc);
			pnlAjouter.add(jcbListeTypeDoc);
			pnlAjouter.add(lblAjoutEspaceur1);
		}
		else {
			btnEnvoiAjout = new JButton("Modifier");
			
			JLabel lblModifierTitre = new JLabel("Entrer les nouvelles informations :");
			lblModifierTitre.setFont(new Font("Arial", Font.BOLD, 16));
			pnlAjouter.add(lblModifierTitre);
			
			JLabel lblAjoutEspaceurMod = new JLabel(" ");
			lblAjoutEspaceurMod.setPreferredSize(new Dimension(1500, 0));
			pnlAjouter.add(lblAjoutEspaceurMod);
			
		}
		btnEnvoiAjout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajouterItemBD();
			}
		});
		
		pnlAjouter.add(lblAjoutTitre);
		pnlAjouter.add(tfAjoutTitre);
		pnlAjouter.add(lblAjoutDate);
		pnlAjouter.add(tfAjoutDate);
		pnlAjouter.add(lblAjoutNbrCopie);
		pnlAjouter.add(tfAjoutNbrCopie);
		pnlAjouter.add(lblAjoutCoteDewey);
		pnlAjouter.add(tfAjoutCoteDewey);
		pnlAjouter.add(lblAjoutValeur);
		pnlAjouter.add(tfAjoutValeur);
		pnlAjouter.add(lblAjoutEspaceurItem);
		
		
		if(type == "Revue") {
			pnlAjouter.add(lblAjoutPeriodicite);
			pnlAjouter.add(tfAjoutPeriodicite);
			pnlAjouter.add(lblAjoutNumPeriode);
			pnlAjouter.add(tfAjoutNumPeriode);
			pnlAjouter.add(lblAjoutIssn);
			pnlAjouter.add(tfAjoutIssn);
			pnlAjouter.add(lblAjoutSujet);
			pnlAjouter.add(tfAjoutSujet);
			pnlAjouter.add(btnEnvoiAjout);
		}
		else if(type == "Journal") {
			
			pnlAjouter.add(lblAjoutPeriodicite);
			pnlAjouter.add(tfAjoutPeriodicite);
			pnlAjouter.add(lblAjoutNumPeriode);
			pnlAjouter.add(tfAjoutNumPeriode);
			pnlAjouter.add(lblAjoutIssn);
			pnlAjouter.add(tfAjoutIssn);
			pnlAjouter.add(lblAjoutRegion);
			pnlAjouter.add(tfAjoutRegion);
			pnlAjouter.add(btnEnvoiAjout);
		}
		else if(type == "Dvd") {
			
			pnlAjouter.add(lblAjoutSujet);
			pnlAjouter.add(tfAjoutSujet);
			pnlAjouter.add(lblAjoutDuree);
			pnlAjouter.add(tfAjoutDuree);
			pnlAjouter.add(lblAjoutIsbn);
			pnlAjouter.add(tfAjoutIsbn);
			pnlAjouter.add(lblAjoutCineaste);
			pnlAjouter.add(tfAjoutCineaste);
			pnlAjouter.add(btnEnvoiAjout);
		}
		else if(type == "Disque Compact") {
			
			pnlAjouter.add(lblAjoutSujet);
			pnlAjouter.add(tfAjoutSujet);
			pnlAjouter.add(lblAjoutDuree);
			pnlAjouter.add(tfAjoutDuree);
			pnlAjouter.add(lblAjoutIsbn);
			pnlAjouter.add(tfAjoutIsbn);
			pnlAjouter.add(lblAjoutAuteur);
			pnlAjouter.add(tfAjoutAuteur);
			pnlAjouter.add(lblAjoutGroupe);
			pnlAjouter.add(tfAjoutGroupe);
			pnlAjouter.add(lblAjoutNbrPiste);
			pnlAjouter.add(tfAjoutNbrPiste);
			pnlAjouter.add(btnEnvoiAjout);
		}
		else if(type == "Livre") {
			JLabel lblAjoutEspaceur2 = new JLabel(" ");
			lblAjoutEspaceur2.setPreferredSize(new Dimension(1500, 0));
			
			pnlAjouter.add(lblAjoutEditeur);
			pnlAjouter.add(tfAjoutEditeur);
			pnlAjouter.add(lblAjoutIsbn);
			pnlAjouter.add(tfAjoutIsbn);
			pnlAjouter.add(lblAjoutCategorie);
			pnlAjouter.add(tfAjoutCategorie);
			pnlAjouter.add(lblAjoutCollection);
			pnlAjouter.add(tfAjoutCollection);
			pnlAjouter.add(lblAjoutAuteur);
			pnlAjouter.add(tfAjoutAuteur);
			pnlAjouter.add(lblAjoutEspaceur2);
			pnlAjouter.add(lblAjoutNbrPage);
			pnlAjouter.add(tfAjoutNbrPage);
			pnlAjouter.add(btnEnvoiAjout);
		}
		else if(type == "Dictionnaire") {

			pnlAjouter.add(lblAjoutEditeur);
			pnlAjouter.add(tfAjoutEditeur);
			pnlAjouter.add(lblAjoutIsbn);
			pnlAjouter.add(tfAjoutIsbn);
			pnlAjouter.add(lblAjoutType);
			pnlAjouter.add(tfAjoutType);
			pnlAjouter.add(btnEnvoiAjout);
		}
		
		pnlAjouter.revalidate();
		pnlAjouter.repaint();
	}
	
	//***********************************************************************************************
	//Ajout l'item dans la base de donn�es
	//***********************************************************************************************
	private void ajouterItemBD() {
		boolean sansErreur = true;
		String messageErreur = "";
		
		String strTitre = tfAjoutTitre.getText();
		
		String strDate = tfAjoutDate.getText();
		
		GregorianCalendar date = new GregorianCalendar();
		try {
			validerMois(strDate);
			validerJour(strDate);
			date.set(GregorianCalendar.YEAR,Integer.parseInt(strDate.substring(0, 4)));
			date.set(GregorianCalendar.MONTH,Integer.parseInt(strDate.substring(5, 7)) - 1);
			date.set(GregorianCalendar.DAY_OF_MONTH,Integer.parseInt(strDate.substring(8, 10)));
		}
		catch(ErreurBiblio e1) {
			messageErreur += "\n" + e1.message();
			sansErreur = false;
		}
		catch(NumberFormatException nfe) {
			messageErreur += "\nEntrer une date au format AAAA-MM-JJ";
			sansErreur = false;
		}
		catch(StringIndexOutOfBoundsException siobe) {
			messageErreur += "\nEntrer une date au format AAAA-MM-JJ";
			sansErreur = false;
		}
		
		int iNbrCopie = 0;
		try {
			iNbrCopie = Integer.parseInt(tfAjoutNbrCopie.getText());
		}
		catch(NumberFormatException nfe) {
			messageErreur += "\nEntrer un nombre entier pour le nombre de copies";
			sansErreur = false;
		}
		
		String strCoteDewey = tfAjoutCoteDewey.getText();
		
		double dValeur = 0;
		try {
			dValeur = Double.parseDouble(tfAjoutValeur.getText());
		}
		catch(NumberFormatException nfe) {
			messageErreur += "\nEntrer un nombre r�el pour la valeur";
			sansErreur = false;
		}
		
		if(type == "Revue") {
			String strPeriodicite = tfAjoutPeriodicite.getText();
			String strNumPeriode = tfAjoutNumPeriode.getText();
			String strIssn = tfAjoutIssn.getText();
			String strSujet = tfAjoutSujet.getText();
			
			if(sansErreur) {
				try {
					Revue revue = new Revue(strTitre, date, iNbrCopie, strCoteDewey, dValeur, strPeriodicite,
							strNumPeriode, strIssn, strSujet);
					
					if(validerExisteDeja(revue)) {
						tfAjoutTitre.setText("");
						tfAjoutDate.setText("AAAA-MM-JJ");
						tfAjoutNbrCopie.setText("");
						tfAjoutCoteDewey.setText("");
						tfAjoutValeur.setText("");
						tfAjoutPeriodicite.setText("");
						tfAjoutNumPeriode.setText("");
						tfAjoutIssn.setText("");
						tfAjoutSujet.setText("");
						if(!modifierActif) {
							bdBiblio.ajouter(revue);
							JOptionPane.showMessageDialog(null, "Le document � �t� ajouter � la base de donn�es");
						}
						else {
							bdBiblio.supprimer(itemTemp);
							bdBiblio.ajouter(revue);
							JOptionPane.showMessageDialog(null, "Le document � �t� modifier dans la base de donn�es");
							supprimerDocument();
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "L'item est d�j� pr�sent dans la base de donn�es");
					}
					
					
				} catch (ErreurBiblio e1) {
					JOptionPane.showMessageDialog(null, "L'item n'a pas �t� ajouter � la base de donn�es\n " + e1.message());
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "L'item n'a pas �t� ajouter � la base de donn�e" + messageErreur);
			}
		}
		else if(type == "Journal") {
			String strPeriodicite = tfAjoutPeriodicite.getText();
			String strNumPeriode = tfAjoutNumPeriode.getText();
			String strIssn = tfAjoutIssn.getText();
			String strRegion = tfAjoutRegion.getText();
			
			if(sansErreur) {
				try {
					Journal journal = new Journal(strTitre, date, iNbrCopie, strCoteDewey, dValeur, strPeriodicite,
							strNumPeriode, strIssn, strRegion);
					if(validerExisteDeja(journal)) {
						tfAjoutTitre.setText("");
						tfAjoutDate.setText("AAAA-MM-JJ");
						tfAjoutNbrCopie.setText("");
						tfAjoutCoteDewey.setText("");
						tfAjoutValeur.setText("");
						tfAjoutPeriodicite.setText("");
						tfAjoutNumPeriode.setText("");
						tfAjoutIssn.setText("");
						tfAjoutRegion.setText("");
						if(!modifierActif) {
							bdBiblio.ajouter(journal);
							JOptionPane.showMessageDialog(null, "Le document � �t� ajouter � la base de donn�es");
						}
						else {
							bdBiblio.supprimer(itemTemp);
							bdBiblio.ajouter(journal);
							JOptionPane.showMessageDialog(null, "Le document � �t� modifier dans la base de donn�es");
							supprimerDocument();
						}
					}
					
				} catch (ErreurBiblio e1) {
					JOptionPane.showMessageDialog(null, "L'item n'a pas �t� ajouter � la base de donn�es\n " + e1.message());
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "L'item n'a pas �t� ajouter � la base de donn�e" + messageErreur);
			}
			
		}
		else if(type == "Dvd") {
			String strSujet = tfAjoutSujet.getText();
			String strIsbn = tfAjoutIsbn.getText();

			double dDuree = 0;
			try {
				dDuree = Double.parseDouble(tfAjoutDuree.getText());
			}
			catch(NumberFormatException nfe) {
				messageErreur += "\nEntrer un nombre r�el pour la dur�e";
				sansErreur = false;
			}

			String strCineaste = tfAjoutCineaste.getText();
			
			if(sansErreur) {
				try {
					Dvd dvd = new Dvd(strTitre, date, iNbrCopie, strCoteDewey, dValeur, strSujet,
							strIsbn, dDuree, strCineaste);
					if(validerExisteDeja(dvd)) {
						tfAjoutTitre.setText("");
						tfAjoutDate.setText("AAAA-MM-JJ");
						tfAjoutNbrCopie.setText("");
						tfAjoutCoteDewey.setText("");
						tfAjoutValeur.setText("");
						tfAjoutSujet.setText("");
						tfAjoutIsbn.setText("");
						tfAjoutDuree.setText("");
						tfAjoutCineaste.setText("");
						if(!modifierActif) {
							bdBiblio.ajouter(dvd);
							JOptionPane.showMessageDialog(null, "Le document � �t� ajouter � la base de donn�es");
						}
						else {
							bdBiblio.supprimer(itemTemp);
							bdBiblio.ajouter(dvd);
							JOptionPane.showMessageDialog(null, "Le document � �t� modifier dans la base de donn�es");
							supprimerDocument();
						}
					}
					
				} catch (ErreurBiblio e1) {
					JOptionPane.showMessageDialog(null, "L'item n'a pas �t� ajouter � la base de donn�es\n " + e1.message());
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "L'item n'a pas �t� ajouter � la base de donn�e" + messageErreur);
			}
			
		}
		else if(type == "Disque Compact") {
			String strSujet = tfAjoutSujet.getText();

			double dDuree = 0;
			try {
				dDuree = Double.parseDouble(tfAjoutDuree.getText());
			}
			catch(NumberFormatException nfe) {
				messageErreur += "\nEntrer un nombre r�el pour la dur�e";
				sansErreur = false;
			}

			String strIsbn = tfAjoutIsbn.getText();
			String strAuteur = tfAjoutAuteur.getText();
			String strGroupe = tfAjoutGroupe.getText();

			int iNbrPiste = 0;
			try {
				iNbrPiste = Integer.parseInt(tfAjoutNbrPiste.getText());
			}
			catch(NumberFormatException nfe) {
				messageErreur += "\nEntrer un nombre entier pour la nombre de pistes";
				sansErreur = false;
			}
			
			if(sansErreur) {
				try {
					DisqueCompact disqueCompact = new DisqueCompact(strTitre, date, iNbrCopie, strCoteDewey, dValeur, strSujet,
							strIsbn, dDuree, strAuteur, strGroupe, iNbrPiste);
					if(validerExisteDeja(disqueCompact)) {
						tfAjoutTitre.setText("");
						tfAjoutDate.setText("AAAA-MM-JJ");
						tfAjoutNbrCopie.setText("");
						tfAjoutCoteDewey.setText("");
						tfAjoutValeur.setText("");
						tfAjoutSujet.setText("");
						tfAjoutIsbn.setText("");
						tfAjoutDuree.setText("");
						tfAjoutAuteur.setText("");
						tfAjoutGroupe.setText("");
						tfAjoutNbrPiste.setText("");
						if(!modifierActif) {
							bdBiblio.ajouter(disqueCompact);
							JOptionPane.showMessageDialog(null, "Le document � �t� ajouter � la base de donn�es");
						}
						else {
							bdBiblio.supprimer(itemTemp);
							bdBiblio.ajouter(disqueCompact);
							JOptionPane.showMessageDialog(null, "Le document � �t� modifier dans la base de donn�es");
							supprimerDocument();
						}
					}
					
				} catch (ErreurBiblio e1) {
					JOptionPane.showMessageDialog(null, "L'item n'a pas �t� ajouter � la base de donn�es\n " + e1.message());
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "L'item n'a pas �t� ajouter � la base de donn�e" + messageErreur);
			}
		}
		else if(type == "Livre") {
			String strEditeur = tfAjoutEditeur.getText();
			String strIsbn = tfAjoutIsbn.getText();
			String strCategorie = tfAjoutCategorie.getText();
			String strCollection = tfAjoutCollection.getText();
			String strAuteur = tfAjoutAuteur.getText();
			
			int iNbrPage = 0;
			try {
				iNbrPage = Integer.parseInt(tfAjoutNbrPage.getText());
			}
			catch(NumberFormatException nfe) {
				messageErreur += "\nEntrer un nombre entier pour la nombre de page";
				sansErreur = false;
			}
			
			if(sansErreur) {
				try {
					Livre livre = new Livre(strTitre, date, iNbrCopie, strCoteDewey, dValeur, strEditeur,
							strIsbn, strCategorie, strCollection, strAuteur, iNbrPage);
					if(validerExisteDeja(livre)) {
						tfAjoutTitre.setText("");
						tfAjoutDate.setText("AAAA-MM-JJ");
						tfAjoutNbrCopie.setText("");
						tfAjoutCoteDewey.setText("");
						tfAjoutValeur.setText("");
						tfAjoutEditeur.setText("");
						tfAjoutIsbn.setText("");
						tfAjoutCategorie.setText("");
						tfAjoutCollection.setText("");
						tfAjoutAuteur.setText("");
						tfAjoutNbrPage.setText("");
						if(!modifierActif) {
							bdBiblio.ajouter(livre);
							JOptionPane.showMessageDialog(null, "Le document � �t� ajouter � la base de donn�es");
						}
						else {
							bdBiblio.supprimer(itemTemp);
							bdBiblio.ajouter(livre);
							JOptionPane.showMessageDialog(null, "Le document � �t� modifier dans la base de donn�es");
							supprimerDocument();
						}
					}
					
				} catch (ErreurBiblio e1) {
					JOptionPane.showMessageDialog(null, "L'item n'a pas �t� ajouter � la base de donn�es\n " + e1.message());
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "L'item n'a pas �t� ajouter � la base de donn�e" + messageErreur);
			}
			
		}
		else if(type == "Dictionnaire") {
			String strEditeur = tfAjoutEditeur.getText();
			String strIsbn = tfAjoutIsbn.getText();
			String strType = tfAjoutType.getText();
			
			if(sansErreur) {
				try {
					Dictionnaire dictionnaire = new Dictionnaire(strTitre, date, iNbrCopie, strCoteDewey, dValeur, strEditeur,
							strIsbn, strType);
					if(validerExisteDeja(dictionnaire)) {
						tfAjoutTitre.setText("");
						tfAjoutDate.setText("AAAA-MM-JJ");
						tfAjoutNbrCopie.setText("");
						tfAjoutCoteDewey.setText("");
						tfAjoutValeur.setText("");
						tfAjoutEditeur.setText("");
						tfAjoutIsbn.setText("");
						tfAjoutType.setText("");
						if(!modifierActif) {
							bdBiblio.ajouter(dictionnaire);
							JOptionPane.showMessageDialog(null, "Le document � �t� ajouter � la base de donn�es");
						}
						else {
							bdBiblio.supprimer(itemTemp);
							bdBiblio.ajouter(dictionnaire);
							JOptionPane.showMessageDialog(null, "Le document � �t� modifier dans la base de donn�es");
							supprimerDocument();
						}
					}
					
				} catch (ErreurBiblio e1) {
					JOptionPane.showMessageDialog(null, "L'item n'a pas �t� ajouter � la base de donn�es\n " + e1.message());
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "L'item n'a pas �t� ajouter � la base de donn�e" + messageErreur);
			}
			
		}
	}

	//***********************************************************************************************
	//Valide si l'item est dans la BD
	//***********************************************************************************************
	private boolean validerExisteDeja(Item unItem) {
		boolean itemExistePas = true;
		for(int i=0 ; i<bdBiblio.size() ; i++) {
			if(unItem.getClass().isInstance(bdBiblio.lire(i))) {
				if(bdBiblio.lire(i).equals(unItem)) {
					itemExistePas = false;
				}
			}
			
		}
		
		return itemExistePas;
	}
	
	//***********************************************************************************************
	//Cr�� la page Supprimer un document
	//***********************************************************************************************
	private void supprimerDocument() {
		resetFrame();
		
		pnlSupprimer = new JPanel();
		pnlSupprimer.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		
		JLabel lblSupprimerTitre;;
		if(modifierActif) {
			lblSupprimerTitre = new JLabel("Quel type d'item voulez-vous modifier?");
		}
		else {
			lblSupprimerTitre = new JLabel("Quel type d'item voulez-vous supprimer?");
		}
		
		lblSupprimerTitre.setFont(new Font("Arial", Font.BOLD, 24));
		pnlSupprimer.add(lblSupprimerTitre);
		
		
		JPanel pnlBouton = new JPanel();
		pnlBouton.setPreferredSize(new Dimension(1295, 40));
		pnlBouton.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton btnRevue = new JButton("Revue");
		btnRevue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String strType = e.getActionCommand();
				type = strType;
				pnlSupprimer.removeAll();
				pnlSupprimer.add(lblSupprimerTitre);
				pnlSupprimer.add(pnlBouton);
				
				supprimerItem(revueValid);
				
			}
			
		});
		pnlBouton.add(btnRevue);

		JButton btnJournal = new JButton("Journal");
		btnJournal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String strType = e.getActionCommand();
				type = strType;
				pnlSupprimer.removeAll();
				pnlSupprimer.add(lblSupprimerTitre);
				pnlSupprimer.add(pnlBouton);
				
				supprimerItem(journalValid);
				
			}
			
		});
		pnlBouton.add(btnJournal);
		
		JButton btnDvd = new JButton("Dvd");
		btnDvd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String strType = e.getActionCommand();
				type = strType;
				pnlSupprimer.removeAll();
				pnlSupprimer.add(lblSupprimerTitre);
				pnlSupprimer.add(pnlBouton);
				
				supprimerItem(dvdValid);
				
			}
			
		});
		pnlBouton.add(btnDvd);
		
		JButton btnCd = new JButton("Disque Compact");
		btnCd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String strType = e.getActionCommand();
				type = strType;
				pnlSupprimer.removeAll();
				pnlSupprimer.add(lblSupprimerTitre);
				pnlSupprimer.add(pnlBouton);
				
				supprimerItem(cdValid);
				
			}
			
		});
		pnlBouton.add(btnCd);
		
		JButton btnLivre = new JButton("Livre");
		btnLivre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String strType = e.getActionCommand();
				type = strType;
				pnlSupprimer.removeAll();
				pnlSupprimer.add(lblSupprimerTitre);
				pnlSupprimer.add(pnlBouton);
				
				supprimerItem(livreValid);
				
			}
			
		});
		pnlBouton.add(btnLivre);
		
		JButton btnDictionnaire = new JButton("Dictionnaire");
		btnDictionnaire.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String strType = e.getActionCommand();
				type = strType;
				pnlSupprimer.removeAll();
				pnlSupprimer.add(lblSupprimerTitre);
				pnlSupprimer.add(pnlBouton);
				
				supprimerItem(dictionnaireValid);
				
			}
			
		});
		pnlBouton.add(btnDictionnaire);
		
		pnlSupprimer.add(pnlBouton);
		
		frame.add(pnlSupprimer, BorderLayout.CENTER);
		frame.revalidate();
		frame.repaint();
		
	}
	
	//***********************************************************************************************
	//Supprime l'item dans la base de donn�es
	//***********************************************************************************************
	private void supprimerItem(Item unItem) {
		
		JPanel pnlSupprimerItem = new JPanel();
		pnlSupprimerItem.setPreferredSize(new Dimension(1355, 500));
		pnlSupprimerItem.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		JPanel pnlChoixItem = new JPanel();
		pnlChoixItem.setPreferredSize(new Dimension(1355, 40));
		pnlChoixItem.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JLabel lblChoixItem = new JLabel("Choisir un item :");
		lblChoixItem.setFont(new Font("Arial", Font.BOLD, 18));
		pnlChoixItem.add(lblChoixItem);
		pnlSupprimerItem.add(pnlChoixItem);
		
		for(int i = 0 ; i < bdBiblio.size() ; i++) {
			
			if(unItem.getClass().isInstance(bdBiblio.lire(i))) {
				
				JButton btnSupprimer = new JButton(bdBiblio.lire(i).getTitre() + " " + bdBiblio.lire(i).afficherDate());
				final int position = i;
				btnSupprimer.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int validation = 0;
						if(modifierActif) {
							validation = JOptionPane.showConfirmDialog  (	null, 
																			"Voulez-vous vraiment modifier " + bdBiblio.lire(position).getTitre() + " " 
																			+ bdBiblio.lire(position).afficherDate() + "?" ,  
																			"Biblio Manager Pro FR - Modifier" , 
																			JOptionPane.YES_NO_OPTION  ) ;
						}
						else {
							validation = JOptionPane.showConfirmDialog  (	null, 
																			"Voulez-vous vraiment supprimer " + bdBiblio.lire(position).getTitre() + " " 
																			+ bdBiblio.lire(position).afficherDate() + "?" ,  
																			"Biblio Manager Pro FR - Supprimer" , 
																			JOptionPane.YES_NO_OPTION  ) ;
						}
						

						if (validation  ==  0) {
							if(modifierActif) {
								resetFrame();
								itemTemp = bdBiblio.lire(position);
								
								pnlAjouter = new JPanel();
								pnlAjouter.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
								
								creerPanelAjouter();
								
								frame.add(pnlAjouter, BorderLayout.CENTER);
								frame.revalidate();
								frame.repaint();
							}
							else {
								bdBiblio.supprimer(position);
								supprimerDocument();
							}
						}
					}
					
				});
				pnlSupprimerItem.add(btnSupprimer);
			}
		}
		pnlSupprimer.add(pnlSupprimerItem);
		frame.revalidate();
		frame.repaint();
	}
	
	//***********************************************************************************************
	//Cr�� la page Rechercher
	//***********************************************************************************************
	private void rechercherDocument() {
		resetFrame();
		
		pnlRechercher = new JPanel();
		pnlRechercher.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

		JLabel lblRechercherTitre = new JLabel("Rechercher un document");
		lblRechercherTitre.setFont(new Font("Arial", Font.BOLD, 36));
		pnlRechercher.add(lblRechercherTitre);
		
		JLabel lblRechercherEspaceur1 = new JLabel(" ");
		lblRechercherEspaceur1.setPreferredSize(new Dimension(1500, 2));
		pnlRechercher.add(lblRechercherEspaceur1);
		
		JTextField tfRechercherItem = new JTextField();
		tfRechercherItem.setText(" Entrer un titre ou une cote Dewey");
		tfRechercherItem.setPreferredSize(new Dimension(250, 30));
		pnlRechercher.add(tfRechercherItem);
		
		JLabel lblRechercherEspaceur2 = new JLabel(" ");
		lblRechercherEspaceur2.setPreferredSize(new Dimension(1500, 2));
		pnlRechercher.add(lblRechercherEspaceur2);
		
		JButton btnRechercherTitre = new JButton("Rechercher par titre");
		btnRechercherTitre.setPreferredSize(new Dimension(250, 30));
		btnRechercherTitre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Collections.sort(bdBiblio.getListeItems());
				boolean resultatRechercher = false;
				String strItem = "";
				
				for(int i=0 ; i < bdBiblio.size() ; i++) {
					boolean comparaison = false;
					comparaison = tfRechercherItem.getText().equals(bdBiblio.lire(i).getTitre());
					if(comparaison) {
						resultatRechercher = true;
						strItem = bdBiblio.lire(i).toString();
					}
				}
				creerPanelRechercheFinal(resultatRechercher, strItem);
			}
		});	
		pnlRechercher.add(btnRechercherTitre);
		
		JButton btnRechercherDewey = new JButton("Rechercher par cote Dewey");
		btnRechercherDewey.setPreferredSize(new Dimension(250, 30));
		btnRechercherDewey.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Item compareDewey = new Revue();
				
				Collections.sort(bdBiblio.getListeItems(), compareDewey);
				boolean resultatRechercher = false;
				String strItem = "";
				try {
					for(int i=0 ; i < bdBiblio.size() ; i++) {
						boolean comparaison = false;
						comparaison = (tfRechercherItem.getText().equals(bdBiblio.lire(i).getCoteDewey()));
						if(comparaison) {
							resultatRechercher = true;
							strItem = bdBiblio.lire(i).toString();
						}
					}
					creerPanelRechercheFinal(resultatRechercher, strItem);
				}
				catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "Entrer un nombre r�el pour rechercher avec une cote Dewey");
				}
				
			}
		});	
		pnlRechercher.add(btnRechercherDewey);
		
		frame.add(pnlRechercher, BorderLayout.CENTER);
		frame.revalidate();
		frame.repaint();
	}
	
	//***********************************************************************************************
	//Affiche le r�sultat de la recherche
	//***********************************************************************************************
	private void creerPanelRechercheFinal(boolean resultatRechercher, String strItem) {
		pnlRechercher.removeAll();
		
		if(resultatRechercher) {
			JLabel lblRechercherTitre = new JLabel("Voici le document :");
			lblRechercherTitre.setFont(new Font("Arial", Font.BOLD, 24));
			pnlRechercher.add(lblRechercherTitre);
			
			JLabel lblRechercherEspaceur = new JLabel(" ");
			lblRechercherEspaceur.setPreferredSize(new Dimension(1500, 2));
			pnlRechercher.add(lblRechercherEspaceur);
			
			JPanel pnlItemTrouver = new JPanel();
			pnlItemTrouver.setPreferredSize(new Dimension(375, 180));
			pnlItemTrouver.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
			JTextArea taItemTrouver = new JTextArea(strItem);
			taItemTrouver.setFont(new Font("Arial", Font.PLAIN, 13));
			taItemTrouver.setPreferredSize(new Dimension(375, 180));
			taItemTrouver.setEditable(false);
			pnlItemTrouver.add(taItemTrouver);
			pnlRechercher.add(pnlItemTrouver);
		}
		else {
			JLabel lblRechercherTitre = new JLabel("Item non-existant");
			lblRechercherTitre.setFont(new Font("Arial", Font.BOLD, 24));
			pnlRechercher.add(lblRechercherTitre);
		}
		
		JLabel lblRechercherEspaceur = new JLabel(" ");
		lblRechercherEspaceur.setPreferredSize(new Dimension(1500, 2));
		pnlRechercher.add(lblRechercherEspaceur);
		
		JButton btnNouvelleRecherche = new JButton("Faire une autre recherche");
		btnNouvelleRecherche.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rechercherDocument();
			}
		});
		pnlRechercher.add(btnNouvelleRecherche);
		
		
		frame.add(pnlRechercher, BorderLayout.CENTER);
		frame.revalidate();
		frame.repaint();
	}
	
	//***********************************************************************************************
	//Cr�ation de la page afficher
	//***********************************************************************************************
	private void afficher() {
		resetFrame();
		
		Collections.sort(bdBiblio.getListeItems(), new CompareDate());
		
		pnlAfficherConsole = new JPanel();
		pnlAfficherConsole.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

		JLabel lblAfficherConsole = new JLabel("Voici les items de la biblioth�que");
		lblAfficherConsole.setFont(new Font("Arial", Font.BOLD, 36));
		pnlAfficherConsole.add(lblAfficherConsole);
		
		JPanel pnlAfficherBouton = new JPanel();
		pnlAfficherBouton.setPreferredSize(new Dimension(1295, 40));
		pnlAfficherBouton.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton btnPeriodique = new JButton("P�riodique");
		btnPeriodique.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pnlAfficherConsole.removeAll();
				pnlAfficherConsole.add(lblAfficherConsole);
				pnlAfficherConsole.add(pnlAfficherBouton);
				
				afficherItem(revueValid, journalValid);
				
			}
			
		});
		pnlAfficherBouton.add(btnPeriodique);
		
		JButton btnMultimedia = new JButton("Multim�dia");
		btnMultimedia.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pnlAfficherConsole.removeAll();
				pnlAfficherConsole.add(lblAfficherConsole);
				pnlAfficherConsole.add(pnlAfficherBouton);
				
				afficherItem(dvdValid, cdValid);
				
			}
			
		});
		pnlAfficherBouton.add(btnMultimedia);
		
		JButton btnVolume = new JButton("Volume");
		btnVolume.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pnlAfficherConsole.removeAll();
				pnlAfficherConsole.add(lblAfficherConsole);
				pnlAfficherConsole.add(pnlAfficherBouton);
				
				afficherItem(livreValid, dictionnaireValid);
				
			}
			
		});
		pnlAfficherBouton.add(btnVolume);
		pnlAfficherConsole.add(pnlAfficherBouton);
		
		
		frame.add(pnlAfficherConsole, BorderLayout.CENTER);
		frame.revalidate();
		frame.repaint();
		
	}
	
	//***********************************************************************************************
	//Cr�ation de l'affichage de chaque item selon leur classes
	//***********************************************************************************************
	private void afficherItem(Item unItem, Item deuxItem) {
		for(int i = 0 ; i < bdBiblio.size() ; i++) {
			if(unItem.getClass().isInstance(bdBiblio.lire(i)) || deuxItem.getClass().isInstance(bdBiblio.lire(i))) {
				JPanel pnlAfficher = new JPanel();
				pnlAfficher.setPreferredSize(new Dimension(375, 180));
				pnlAfficher.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0));
				JTextArea taAfficher = new JTextArea(bdBiblio.lire(i).toString());
				taAfficher.setFont(new Font("Arial", Font.PLAIN, 13));
				taAfficher.setPreferredSize(new Dimension(375, 180));
				taAfficher.setEditable(false);
				pnlAfficher.add(taAfficher);
				pnlAfficherConsole.add(pnlAfficher);
			}
		}
		frame.revalidate();
		frame.repaint();
	}
	
	private void archiverDocuments() {
		resetFrame();
		
		pnlArchiver = new JPanel();
		pnlArchiver.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		boolean periodeExiste, volumeExiste, multimediaExiste;
		
		File testPeriode = new File("periode.txt");
		File testVolume = new File("volume.txt");
		File testMultimedia = new File("multimedia.txt");
		
		periodeExiste = testPeriode.exists();
		volumeExiste = testVolume.exists();
		multimediaExiste = testMultimedia.exists();

		JLabel lblArchiver = new JLabel();
		
		if(periodeExiste || volumeExiste || multimediaExiste){
			int validation = JOptionPane.showConfirmDialog(	null, 
															"Des fichiers sont d�j� pr�sent dans l'archive, voulez-vous les �craser?",  
															"Biblio Manager Pro FR - Archiver" , 
															JOptionPane.YES_NO_OPTION  ) ;
			if (validation  ==  0) {
				creerFichierTexte();
				lblArchiver = new JLabel("Les fichiers dans l'archive ont �t� �cras�s");
			}
			else {
				lblArchiver = new JLabel("Les fichiers n'ont pas �t� archiv�s");
			}
		}
		else {
			creerFichierTexte();
			lblArchiver = new JLabel("Les fichiers ont �t� cr��s dans l'archive");
		}
		lblArchiver.setFont(new Font("Arial", Font.BOLD, 36));
		pnlArchiver.add(lblArchiver);

		frame.add(pnlArchiver, BorderLayout.CENTER);
		frame.revalidate();
		frame.repaint();
	}
	
	private void creerFichierTexte() {
		String fichierPeriode = "periode.txt";
		String fichierVolume = "volume.txt";
		String fichierMultimedia = "multimedia.txt";
		GregorianCalendar dateCreation = new GregorianCalendar();
		
		int annee = dateCreation.get(Calendar.YEAR);
		int mois = dateCreation.get(Calendar.MONTH) + 1;
		int jour = dateCreation.get(Calendar.DATE);
		int heure = dateCreation.get(Calendar.HOUR);
		int minute = dateCreation.get(Calendar.MINUTE);
		int seconde = dateCreation.get(Calendar.SECOND);
		DecimalFormat deuxChiffres = new DecimalFormat("00");
		String strDateCreation = annee + "-" + deuxChiffres.format(mois) + "-" + deuxChiffres.format(jour) + " � " + heure + ":" + minute + ":" + seconde;
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fichierPeriode));
			writer.write(fichierPeriode + " " + strDateCreation);
			
			for(Item unItem : bdBiblio.getListeItems()) {
				if(unItem instanceof Periodique) {
					writer.write("\n\n" + unItem.toString());
				}
			}
			writer.close();
		} catch (IOException e) {e.printStackTrace();}
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fichierVolume));
			writer.write(fichierVolume + " " + strDateCreation);
			
			for(Item unItem : bdBiblio.getListeItems()) {
				if(unItem instanceof Volume) {
					writer.write("\n\n" + unItem.toString());
				}
			}
			writer.close();
		} catch (IOException e) {e.printStackTrace();}
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fichierMultimedia));
			writer.write(fichierMultimedia + " " + strDateCreation);
			
			for(Item unItem : bdBiblio.getListeItems()) {
				if(unItem instanceof Multimedia) {
					writer.write("\n\n" + unItem.toString());
				}
			}
			writer.close();
		} catch (IOException e) {e.printStackTrace();}
	}
	
	//***********************************************************************************************
	//Enl�ve tous les panels du frame
	//***********************************************************************************************
	private void resetFrame() {
		try {frame.remove(pnlAccueil);} catch(NullPointerException npe) {}
		try {frame.remove(pnlAjouter);} catch(NullPointerException npe) {}
		try {frame.remove(pnlSupprimer);} catch(NullPointerException npe) {}
		try {frame.remove(pnlModifier);} catch(NullPointerException npe) {}
		try {frame.remove(pnlRechercher);} catch(NullPointerException npe) {}
		try {frame.remove(pnlAfficherConsole);} catch(NullPointerException npe) {}
		try {frame.remove(pnlArchiver);} catch(NullPointerException npe) {}
	}

	//***********************************************************************************************
	//Valide que le mois entr� est r�el
	//***********************************************************************************************
	private void validerMois(String date) throws ErreurBiblio, NumberFormatException {
		int mois = 0;		
		try {
			mois = Integer.parseInt(date.substring(5, 7));
		}
		catch(NumberFormatException nfe) {
			throw new NumberFormatException();
		}
		if(!(mois >= 1 && mois <= 12)) {
			throw new ErreurBiblio("mois");
		}
	}

	//***********************************************************************************************
	//Valide que le jour entr� est r�el
	//***********************************************************************************************
	private void validerJour(String date) throws ErreurBiblio {
		int annee = 0;
		int mois = 0;
		int jour = 0;
		
		
		try {
			annee = Integer.parseInt(date.substring(0, 4));
			mois = Integer.parseInt(date.substring(5, 7));
			jour = Integer.parseInt(date.substring(8, 10));
			final int ANNEE_BISEX_4 = 4;
			final int ANNEE_BISEX_100 = 100;
			final int ANNEE_BISEX_400 = 400;
			
			switch (mois)
			{
			case 1:	case 3:	case 5:	case 7:	case 8: case 10: case 12:	if(jour >= 1 && jour <= 31) {} else { throw new ErreurBiblio("jour"); } break;
			
			case 2:	if (annee % ANNEE_BISEX_4 == 0) {
						if (annee % ANNEE_BISEX_100 == 0)
							if (annee % ANNEE_BISEX_400 == 0)				
								if(jour >= 1 && jour <= 29) {} else { throw new ErreurBiblio("jour"); }
							else											
								if(jour >= 1 && jour <= 28) {} else { throw new ErreurBiblio("jour"); }
						else												
							if(jour >= 1 && jour <= 29) {} else { throw new ErreurBiblio("jour"); }
					}
					else													
						if(jour >= 1 && jour <= 28) {} else { throw new ErreurBiblio("jour"); } break;
			
			case 4:	case 6:	case 9:	case 11:	if(jour >= 1 && jour <= 30) {} else { throw new ErreurBiblio("jour"); } break;
			}
		}
		catch(NumberFormatException nfe) {
			throw new NumberFormatException();
		}
	}

	//***********************************************************************************************
	//Valide que l'application se ferme de la bonne fa�on
	//***********************************************************************************************
	private void quitterApp() {
		FileOutputStream fileOut;
		ObjectOutputStream out;
		try {
			fileOut = new FileOutputStream("Biblio.ser");
			out = new ObjectOutputStream(fileOut);
			out.writeObject(bdBiblio);
			out.close();
			fileOut.close();
			JOptionPane.showMessageDialog(null, "La base de don�es a �t� mise � jour, l'appilcation va se fermer");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		frame.dispose();
		System.exit(0);
	}

}
