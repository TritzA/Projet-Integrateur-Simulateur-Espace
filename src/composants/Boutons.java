package composants;

import aapplication.Jeu;
import utilitaire.Fenetre;

/**
 * Classe où sont définis les actions de chacun des cinq boutons
 * 
 * @author Antonin Tritz
 */
public class Boutons {

	// Antonin Tritz
	/**
	 * Permet à l'utilisateur de démarrer la simulation
	 * 
	 * @param simulation Lieu d'action du bouton demarrer
	 */
	public static void btnDemarrer(Simulation simulation) {
		simulation.demarrer();
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet à l'utilisateur de démarrer la simulation
	 * 
	 * @param espaceJeu Lieu d'action du bouton demarrer
	 */
	public static void btnDemarrer(EspaceJeu espaceJeu) {
		espaceJeu.demarrer();
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet à l'utilisateur de mettre pause à la simulation et d'afficher la
	 * prochaine image de celle-ci
	 * 
	 * @param simulation Lieu d'action du bouton Prochaine Image
	 * @param app        JFrame où sont présent tous les boutons
	 */
	public static void btnProchaineImage(Simulation simulation) {
		simulation.arreter();
		simulation.calculerUneIterationPhysique();
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet à l'utilisateur de mettre pause à la simulation et d'afficher la
	 * prochaine image de celle-ci
	 * 
	 * @param espaceJeu Lieu d'action du bouton Prochaine Image
	 */
	public static void btnProchaineImage(EspaceJeu espaceJeu) {
		espaceJeu.arreter();
		espaceJeu.calculerUneIterationPhysique();
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet à l'utilisateur de recommencer une simulation en effaçant tous les
	 * corps qui étaient dans simulation
	 * 
	 * @param simulation Lieu d'action du bouton Recommencer
	 */
	public static void btnRecommencer(Simulation simulation) {
		simulation.arreter();
		simulation.supprimerTousLesCorps();
		simulation.setTempsTotalEcoule(0.);
		simulation.repaint();

	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet à l'utilisateur de recommencer un niveau à partir de zéro
	 * 
	 * @param simulation Lieu d'action du bouton Recommencer
	 * @param jeu        Lieu contenant le bouton
	 */
	public static void btnRecommencer(EspaceJeu EspaceJeu, Jeu jeu) {
		Fenetre.changer(new Jeu(jeu.getNiveau(), ""), jeu);
	}// fin de la méthode

}// fin de la classe
