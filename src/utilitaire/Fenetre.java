package utilitaire;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

import aapplication.Accueil;
import aapplication.Jeu;
import aapplication.SelectionNiveau;

/**
 * Classe utilitaire permettant les changements de fen�tre fluides et efficaces
 * 
 * @author Antonin Tritz
 */

public class Fenetre {

	public static boolean accesNiveau1 = true;
	public static boolean accesNiveau2 = false;
	public static boolean accesNiveau3 = false;
	private static long tempsPauseFinJeu = 5000;
	private static FenetreAideConceptsScientifiques fenetreAideConceptsScientifiques = new FenetreAideConceptsScientifiques();

	// Antonin Tritz
	/**
	 * Permet de changer de fen�tre
	 * 
	 * @param fenetreAjout Fen�tre que l'on veut ajouter
	 * @param fenetreSuppr Fen�tre que l'on veut supprimer
	 */
	public static void changer(JFrame fenetreAjout, JFrame fenetreSuppr) {
		fenetreAjout.setVisible(!fenetreAjout.isVisible());
		fenetreSuppr.setVisible(!fenetreSuppr.isVisible());
	}// fin de la m�thode

	// Antonin Tritz
	/**
	 * Permet d'afficher le m�me menu comportant les m�mes actions
	 * 
	 * @param fenetre Fen�tre � laquelle on veut ajouter un menu
	 */
	public static void ajoutMenu(JFrame fenetre) {
		JMenuBar menuBar = new JMenuBar();
		fenetre.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);

		JMenuItem mntmGuideUtilisation = new JMenuItem("Guide d'utilisation");
		mntmGuideUtilisation.addActionListener(new ActionListener() {

			// Antonin Tritz
			/**
			 * Permet d'effectuer une action lorsqu'on clique sur l'item
			 * mntmGuideUtilisation
			 * 
			 * @param arg0 Action lorsqu'on clique sur l'item guide utilisateur
			 */
			public void actionPerformed(ActionEvent arg0) {
				ouvrir(fenetreAideConceptsScientifiques);
			}// fin de la m�thode
		});
		mnNewMenu.add(mntmGuideUtilisation);

		JMenu menuModeCaroline = new JMenu("Mode Caroline");
		mnNewMenu.add(menuModeCaroline);

		JMenuItem mntmNiveau1 = new JMenuItem("Acc�s au niveau 1");
		mntmNiveau1.addActionListener(new ActionListener() {

			// Antonin Tritz
			/**
			 * Permet d'effectuer une action lorsqu'on clique sur l'item mntmNiveau1
			 * 
			 * @param arg0 Action lorsqu'on clique sur l'item retour
			 */
			public void actionPerformed(ActionEvent arg0) {
				changer(SelectionNiveau.getNiveau1(), fenetre);
			}// fin de la m�thode
		});
		menuModeCaroline.add(mntmNiveau1);

		JMenuItem mntmNiveau2 = new JMenuItem("Acc�s au niveau 2");
		mntmNiveau2.addActionListener(new ActionListener() {

			// Antonin Tritz
			/**
			 * Permet d'effectuer une action lorsqu'on clique sur l'item mntmNiveau2
			 * 
			 * @param arg0 Action lorsqu'on clique sur l'item retour
			 */
			public void actionPerformed(ActionEvent arg0) {

				changer(SelectionNiveau.getNiveau2(), fenetre);
			}// fin de la m�thode
		});
		menuModeCaroline.add(mntmNiveau2);

		JMenuItem mntmNiveau3 = new JMenuItem("Acc�s au niveau 3");
		mntmNiveau3.addActionListener(new ActionListener() {

			// Antonin Tritz
			/**
			 * Permet d'effectuer une action lorsqu'on clique sur l'item mntmNiveau3
			 * 
			 * @param arg0 Action lorsqu'on clique sur l'item retour
			 */
			public void actionPerformed(ActionEvent arg0) {
				changer(SelectionNiveau.getNiveau3(), fenetre);
			}// fin de la m�thode
		});
		menuModeCaroline.add(mntmNiveau3);

		JMenuItem mntmSelectionNiv = new JMenuItem("Aller � la selection des niveaux");
		mntmSelectionNiv.addActionListener(new ActionListener() {

			// Antonin Tritz
			/**
			 * Permet d'effectuer une action lorsqu'on clique sur l'item de selection des
			 * niveaux
			 * 
			 * @param arg0 Action lorsqu'on clique sur l'item retour
			 */
			public void actionPerformed(ActionEvent arg0) {
				changer(new SelectionNiveau(), fenetre);
			}// fin de la m�thode
		});
		mnNewMenu.add(mntmSelectionNiv);

		JMenuItem mntmRetour = new JMenuItem("Retour � l'accueil");
		mntmRetour.addActionListener(new ActionListener() {

			// Antonin Tritz
			/**
			 * Permet d'effectuer une action lorsqu'on clique sur l'item retour
			 * 
			 * @param arg0 Action lorsqu'on clique sur l'item retour
			 */
			public void actionPerformed(ActionEvent arg0) {
				try {
					retour(fenetre);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}// fin de la m�thode
		});
		mnNewMenu.add(mntmRetour);

	}// fin de la m�thode

	// Antonin Tritz
	/**
	 * Permet d'afficher un message qui indique que l'utilisateur � gagn� et lui
	 * propose de retourner � la s�lection des niveaux
	 * 
	 * @param fenetre Fen�tre � partir de laquelle on veut retourner � la section
	 *                des niveaux
	 */
	public static void retourSiVictoire(JFrame fenetre) {
		try {
			Thread.sleep(tempsPauseFinJeu);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Jeu jeu = (Jeu) fenetre;
		if (jeu.getNiveau() < 4) {
			if (accesNiveau2 == false) {
				accesNiveau2 = true;
			} else {
				accesNiveau3 = true;
			}
		}
		changer(new SelectionNiveau(), fenetre);
	}// fin de la m�thode

	// Antonin Tritz
	/**
	 * Permet d'afficher un message qui indique que l'utilisateur � perdu et lui
	 * propose de retourner � la s�lection des niveaux
	 * 
	 * @param fenetre Fen�tre � partir de laquelle on veut retourner � la section
	 *                des niveaux
	 */
	public static void retourSiDefaite(JFrame fenetre) {
		try {
			Thread.sleep(tempsPauseFinJeu);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		changer(new SelectionNiveau(), fenetre);
	}// fin de la m�thode

	// Antonin Tritz
	/**
	 * Permet de retourner � la fen�tre d'accueil
	 * 
	 * @param fenetre Fen�tre � partir de laquelle on veut retourner � l'accueil
	 */
	private static void retour(JFrame fenetre) throws BadLocationException {
		changer(new Accueil(), fenetre);
	}// fin de la m�thode

	// Antonin Tritz
	/**
	 * Permet d'ouvrir une nouvelle fen�tre
	 * 
	 * @param fenetre Fen�tre � partir de laquelle on ouvrir la nouvelle fen�tre
	 */
	private static void ouvrir(JFrame fenetreAjout) {
		fenetreAjout.setVisible(true);
	}// fin de la m�thode

	/**
	 * Permet d'associer un bouton � une image
	 * 
	 * @author Caroline
	 * @author Antonin Tritz (Importation)
	 * 
	 * @param leBouton     Le bouton auquel on veut associer une image
	 * @param fichierImage L'image qui sera associ�e au bouton
	 * @param classe       La classe o� on associe les deux �l�ments
	 *
	 */
	public static void associerBoutonAvecImage(JButton leBouton, String fichierImage, Object classe) {
		Image imgLue = null;
		java.net.URL urlImage = classe.getClass().getClassLoader().getResource(fichierImage);
		if (urlImage == null) {
			JOptionPane.showMessageDialog(null, "Fichier " + fichierImage + " introuvable");
		}
		try {
			imgLue = ImageIO.read(urlImage);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur pendant la lecture du fichier d'image");
		}

		// redimensionner l'image de la m�me grandeur que le bouton
		Image imgRedim = imgLue.getScaledInstance(leBouton.getWidth(), leBouton.getHeight(), Image.SCALE_SMOOTH);

		// au cas o� le fond de l�image serait transparent
		leBouton.setOpaque(false);
		leBouton.setContentAreaFilled(false);
		leBouton.setBorderPainted(false);

		// associer l'image au bouton
		leBouton.setText("");
		leBouton.setIcon(new ImageIcon(imgRedim));

		// on se d�barrasse des images interm�diaires
		imgLue.flush();
		imgRedim.flush();
	}// fin m�thode

}// fin de la classe
