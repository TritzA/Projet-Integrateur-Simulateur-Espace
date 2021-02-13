package composants;

import aapplication.Jeu;
import utilitaire.Fenetre;

/**
 * Classe o� sont d�finis les actions de chacun des cinq boutons
 * 
 * @author Antonin Tritz
 */
public class Boutons {

	// Antonin Tritz
	/**
	 * Permet � l'utilisateur de d�marrer la simulation
	 * 
	 * @param simulation Lieu d'action du bouton demarrer
	 */
	public static void btnDemarrer(Simulation simulation) {
		simulation.demarrer();
	}// fin de la m�thode

	// Antonin Tritz
	/**
	 * Permet � l'utilisateur de d�marrer la simulation
	 * 
	 * @param espaceJeu Lieu d'action du bouton demarrer
	 */
	public static void btnDemarrer(EspaceJeu espaceJeu) {
		espaceJeu.demarrer();
	}// fin de la m�thode

	// Antonin Tritz
	/**
	 * Permet � l'utilisateur de mettre pause � la simulation et d'afficher la
	 * prochaine image de celle-ci
	 * 
	 * @param simulation Lieu d'action du bouton Prochaine Image
	 * @param app        JFrame o� sont pr�sent tous les boutons
	 */
	public static void btnProchaineImage(Simulation simulation) {
		simulation.arreter();
		simulation.calculerUneIterationPhysique();
	}// fin de la m�thode

	// Antonin Tritz
	/**
	 * Permet � l'utilisateur de mettre pause � la simulation et d'afficher la
	 * prochaine image de celle-ci
	 * 
	 * @param espaceJeu Lieu d'action du bouton Prochaine Image
	 */
	public static void btnProchaineImage(EspaceJeu espaceJeu) {
		espaceJeu.arreter();
		espaceJeu.calculerUneIterationPhysique();
	}// fin de la m�thode

	// Antonin Tritz
	/**
	 * Permet � l'utilisateur de recommencer une simulation en effa�ant tous les
	 * corps qui �taient dans simulation
	 * 
	 * @param simulation Lieu d'action du bouton Recommencer
	 */
	public static void btnRecommencer(Simulation simulation) {
		simulation.arreter();
		simulation.supprimerTousLesCorps();
		simulation.setTempsTotalEcoule(0.);
		simulation.repaint();

	}// fin de la m�thode

	// Antonin Tritz
	/**
	 * Permet � l'utilisateur de recommencer un niveau � partir de z�ro
	 * 
	 * @param simulation Lieu d'action du bouton Recommencer
	 * @param jeu        Lieu contenant le bouton
	 */
	public static void btnRecommencer(EspaceJeu EspaceJeu, Jeu jeu) {
		Fenetre.changer(new Jeu(jeu.getNiveau(), ""), jeu);
	}// fin de la m�thode

}// fin de la classe
