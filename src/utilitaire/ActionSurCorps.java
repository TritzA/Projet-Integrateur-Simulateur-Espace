package utilitaire;

import java.awt.event.MouseEvent;

import aapplication.Aapplication;
import composants.ModeleAffichage;
import composants.Simulation;
import objetdessinable.Corps;
import physique.MoteurPhysiqueCorps;
import physique.MoteurPhysiqueExplosable;
import physique.Vecteur;

/**
 * Classe permettant la gestion des toutes les actions faites sur les corps dans
 * la simulation
 * 
 * @author Xavier Boucher
 * @author Antonin Tritz
 */

public class ActionSurCorps {

	private MoteurPhysiqueCorps moteurPhysiqueCorps = new MoteurPhysiqueCorps();
	private MoteurPhysiqueExplosable moteurPhysiqueExplosable = new MoteurPhysiqueExplosable();

	// Xavier
	/**
	 * Méthode permettant de changer la position d'un corps
	 * 
	 * 
	 * @param simulation est l'endrois où la simulation a lieu (La "ZoneDeDessin")
	 * @param corps      est le corps sur lequel on effectue le repositionnement
	 * @param event      est le MouseEvent de référence
	 * @param modele     est le ModeleAffichage permettant de changer les valeur en
	 *                   pixels en unité réel
	 */
	public void positionDuCorps(Simulation simulation, Corps corps, MouseEvent event, ModeleAffichage modele) {

		double px = ((event.getX() - (simulation.getWidth() / 2)) / modele.getPixelsParUniteX());
		double py = ((event.getY() - (simulation.getHeight() / 2)) / modele.getPixelsParUniteY());

		corps.setPosition(new Vecteur(px, py));
		simulation.repaint();
	}// fin de la méthode

	// Xavier
	/**
	 * Méthode permettant d'effacer un corps
	 * 
	 * @param simulation est l'endrois où la simulation a lieu (La "ZoneDeDessin")
	 * @param corps      est le corps que l'on veux effacer
	 */
	public void effacerCorps(Simulation simulation, Corps corps) {

		simulation.supprimerUnCorps(corps);
		Aapplication.setestPretASupprimer(false);
		simulation.repaint();

	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet de tirer sur un corps losqu'on drag le corps avec la souris. Agit
	 * selon les principes physique d'un ressort
	 * 
	 * @param simulation Lieu où la traction prend place
	 * @param corps      Objet que l'on veut tirer
	 * @param event      Evenement permettant la gestion du drag
	 * @param modele     Objet permettant les convesions du monde des pixels au
	 *                   monde réel
	 */
	public void tirer(Simulation simulation, Corps corps, MouseEvent event, ModeleAffichage modele) {
		if (corps != null) {
			double distanceX = ((event.getX() - (simulation.getWidth() / 2)) / modele.getPixelsParUniteX())
					- (corps.getPosition().getX());
			double distanceY = ((event.getY() - (simulation.getHeight() / 2)) / modele.getPixelsParUniteY())
					- (corps.getPosition().getY());

			moteurPhysiqueCorps.forceRessort(corps, distanceX, distanceY);
		}
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet de provoquer une explosion sur un corps lorsqu'on double-clic dessus
	 * 
	 * @param simulation Lieu où l'explosion prend place
	 * @param corps      Objet sur lequel on veut provoquer une explosion
	 * @param e          Evenement permettant la gestion du double0-clic
	 */
	public void exploser(Simulation simulation, Corps corps, MouseEvent event) {
		if (event.getClickCount() == 2 && corps != null) {
			moteurPhysiqueExplosable.explosionUnCorps(corps, simulation);
		}
	}// fin de la méthode

}// fin de la classe
