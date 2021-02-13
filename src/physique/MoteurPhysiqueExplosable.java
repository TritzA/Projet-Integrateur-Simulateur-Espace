package physique;

import java.util.ArrayList;
import composants.Simulation;
import lecture.Son;
import objetdessinable.Corps;
import objetdessinable.Trajectoire;

/**
 * Classe contenant les méthodes principales permettant les mouvements physiques
 * de la simulation lorsqu'il y a une explosion
 * 
 * @author Antonin Tritz
 */

public class MoteurPhysiqueExplosable {

	private final double nombreDeCorpsCree = 8.;
	private double distanceBaseDebris = 4.5;
	private double unTourEnRad = 2. * Math.PI;
	private UtilitairePhysique utilitairePhysique = new UtilitairePhysique();

	// Antonin Tritz
	/**
	 * Permet la collision entre un corps explosable et un corps non explosable
	 * 
	 * @param corpsExplosable Corps explosable qui explosera
	 * @param simulation      Lieu de l'explosion
	 */
	public void explosionUnCorps(Corps corpsExplosable, Simulation simulation) {

		ArrayList<Corps> ensembleDesNouvCorps = new ArrayList<Corps>();

		Corps corps0 = new Corps(null, 0, 0);
		Corps corps1 = new Corps(null, 0, 0);
		Corps corps2 = new Corps(null, 0, 0);
		Corps corps3 = new Corps(null, 0, 0);
		Corps corps4 = new Corps(null, 0, 0);
		Corps corps5 = new Corps(null, 0, 0);
		Corps corps6 = new Corps(null, 0, 0);

		ensembleDesNouvCorps.add(corps0);
		ensembleDesNouvCorps.add(corps1);
		ensembleDesNouvCorps.add(corps2);
		ensembleDesNouvCorps.add(corps3);
		ensembleDesNouvCorps.add(corps4);
		ensembleDesNouvCorps.add(corps5);
		ensembleDesNouvCorps.add(corps6);

		Vecteur posInitiale = corpsExplosable.getPosition();
		double teta = 0;

		Corps ancien = new Corps(corpsExplosable);

		corpsExplosable.setCouleur(ancien.getCouleur());
		corpsExplosable.setRayon(nouvRayon(ancien));
		corpsExplosable.setMasse(nouvMasse(ancien));

		Vecteur posFinale = nouvPosition(ancien, teta, distanceBaseDebris);

		corpsExplosable.setPosition(posFinale);
		corpsExplosable.setVitesse(nouvVitesse(ancien, posInitiale, posFinale));
		corpsExplosable.setTrajectoire(new Trajectoire());
		corpsExplosable.setExplosable(false);
		utilitairePhysique.modifierAcceleration(ancien);

		teta += unTourEnRad / nombreDeCorpsCree;

		for (Corps unCorps : ensembleDesNouvCorps) {

			unCorps.setCouleur(ancien.getCouleur());
			if (corpsExplosable.isEstImage()) {
				unCorps.setImage(corpsExplosable.getImage());
			}
			unCorps.setRayon(nouvRayon(ancien));
			unCorps.setMasse(nouvMasse(ancien));

			posFinale = nouvPosition(ancien, teta, distanceBaseDebris);
			unCorps.setPosition(posFinale);
			unCorps.setVitesse(nouvVitesse(ancien, posInitiale, posFinale));

			simulation.ajouterUnCorps(unCorps);

			utilitairePhysique.modifierAcceleration(unCorps);

			teta += unTourEnRad / nombreDeCorpsCree;

		}
		Son.soundExplosion();

	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet la collision entre deux corps exlposables
	 * 
	 * @param corpsExplosable1 Premier corps explosable explosera
	 * @param corpsExplosable2 Deuxième corps explosable explosera
	 * @param simulation       Lieu de l'explosion
	 */
	public void explosionDeuxCorps(Corps corpsExplosable1, Corps corpsExplosable2, Simulation simulation) {

		ArrayList<Corps> ensembleDesNouvCorps1 = new ArrayList<Corps>();
		ArrayList<Corps> ensembleDesNouvCorps2 = new ArrayList<Corps>();

		Corps corps0 = new Corps(null, 0, 0);
		Corps corps1 = new Corps(null, 0, 0);
		Corps corps2 = new Corps(null, 0, 0);
		Corps corps3 = new Corps(null, 0, 0);
		Corps corps4 = new Corps(null, 0, 0);
		Corps corps5 = new Corps(null, 0, 0);
		Corps corps6 = new Corps(null, 0, 0);

		Corps corpsA = new Corps(null, 0, 0);
		Corps corpsB = new Corps(null, 0, 0);
		Corps corpsC = new Corps(null, 0, 0);
		Corps corpsD = new Corps(null, 0, 0);
		Corps corpsE = new Corps(null, 0, 0);
		Corps corpsF = new Corps(null, 0, 0);
		Corps corpsG = new Corps(null, 0, 0);

		ensembleDesNouvCorps1.add(corps0);
		ensembleDesNouvCorps1.add(corps1);
		ensembleDesNouvCorps1.add(corps2);
		ensembleDesNouvCorps1.add(corps3);
		ensembleDesNouvCorps1.add(corps4);
		ensembleDesNouvCorps1.add(corps5);
		ensembleDesNouvCorps1.add(corps6);

		ensembleDesNouvCorps2.add(corpsA);
		ensembleDesNouvCorps2.add(corpsB);
		ensembleDesNouvCorps2.add(corpsC);
		ensembleDesNouvCorps2.add(corpsD);
		ensembleDesNouvCorps2.add(corpsE);
		ensembleDesNouvCorps2.add(corpsF);
		ensembleDesNouvCorps2.add(corpsG);

		Vecteur posInitiale1 = corpsExplosable1.getPosition();
		double teta1 = 0;

		Corps ancien1 = new Corps(corpsExplosable1);

		corpsExplosable1.setCouleur(ancien1.getCouleur());
		corpsExplosable1.setRayon(nouvRayon(ancien1));
		corpsExplosable1.setMasse(nouvMasse(ancien1));

		Vecteur posFinale1 = nouvPosition(ancien1, teta1, distanceBaseDebris * 1.5);

		corpsExplosable1.setPosition(posFinale1);
		corpsExplosable1.setVitesse(nouvVitesse(ancien1, posInitiale1, posFinale1));
		corpsExplosable1.setTrajectoire(new Trajectoire());
		corpsExplosable1.setExplosable(false);
		utilitairePhysique.modifierAcceleration(ancien1);

		teta1 += unTourEnRad / nombreDeCorpsCree;

		for (Corps unCorps : ensembleDesNouvCorps1) {

			unCorps.setCouleur(ancien1.getCouleur());
			if (corpsExplosable1.isEstImage()) {
				unCorps.setImage(corpsExplosable1.getImage());
			}
			unCorps.setRayon(nouvRayon(ancien1));
			unCorps.setMasse(nouvMasse(ancien1));

			posFinale1 = nouvPosition(ancien1, teta1, distanceBaseDebris);
			unCorps.setPosition(posFinale1);
			unCorps.setVitesse(nouvVitesse(ancien1, posInitiale1, posFinale1));

			simulation.ajouterUnCorps(unCorps);

			utilitairePhysique.modifierAcceleration(unCorps);

			teta1 += unTourEnRad / nombreDeCorpsCree;
		}

		Vecteur posInitiale2 = corpsExplosable2.getPosition();
		double teta2 = 0;

		Corps ancien2 = new Corps(corpsExplosable2);

		corpsExplosable2.setCouleur(ancien2.getCouleur());
		if (ancien2.isEstImage()) {
			corpsExplosable2.setImage(ancien2.getImage());
		}
		corpsExplosable2.setRayon(nouvRayon(ancien2));
		corpsExplosable2.setMasse(nouvMasse(ancien2));

		Vecteur posFinale2 = nouvPosition(ancien2, teta2, distanceBaseDebris * 1.5);

		corpsExplosable2.setPosition(posFinale2);
		corpsExplosable2.setVitesse(nouvVitesse(ancien2, posInitiale2, posFinale2));
		corpsExplosable2.setTrajectoire(new Trajectoire());
		corpsExplosable2.setExplosable(false);
		utilitairePhysique.modifierAcceleration(ancien2);

		teta2 += unTourEnRad / nombreDeCorpsCree;
		for (Corps unCorps : ensembleDesNouvCorps2) {

			unCorps.setCouleur(ancien2.getCouleur());

			if (corpsExplosable2.isEstImage()) {
				unCorps.setImage(corpsExplosable2.getImage());
			}
			unCorps.setRayon(nouvRayon(ancien2));
			unCorps.setMasse(nouvMasse(ancien2));

			posFinale2 = nouvPosition(ancien2, teta2, distanceBaseDebris);
			unCorps.setPosition(posFinale2);
			unCorps.setVitesse(nouvVitesse(ancien2, posInitiale2, posFinale2));

			simulation.ajouterUnCorps(unCorps);

			utilitairePhysique.modifierAcceleration(unCorps);

			teta2 += unTourEnRad / nombreDeCorpsCree;

		}
		Son.soundExplosion();

	}// fin de la méthode

	// Antonin Triz
	/**
	 * Permet de caculer le rayon du nouveau corps émergeant de l'explosion
	 * 
	 * 
	 * @param corpsExplosable Le corps explosable dont on va calculer le rayon
	 * 
	 * @return Rayon calculé du nouveau corps
	 */
	private double nouvRayon(Corps corpsExplosable) {
		double rayoncorpsExplosable = corpsExplosable.getRayon();
		double aireNouv = rayoncorpsExplosable / Math.sqrt(nombreDeCorpsCree);
		return aireNouv;
	}// fin de la méthode

	// Antonin Triz
	/**
	 * Permet de caculer la masse du nouveau corps émergeant de l'explosion
	 * 
	 * @param corpsExplosable Le corps explosable dont on va calculer la masse
	 * 
	 * @return Masse calculé du nouveau corps
	 */
	private double nouvMasse(Corps corpsExplosable) {
		double massecorpsExplosable = corpsExplosable.getMasse();
		double masseNouv = massecorpsExplosable / nombreDeCorpsCree;
		return masseNouv;
	}// fin de la méthode

	// Antonin Triz
	/**
	 * Permet de caculer la position du nouveau corps émergeant de l'explosion
	 * 
	 * @param corpsExplosable    Le corps explosable dont on va calculer la position
	 * @param teta               Angle permettant de calculer la position du nouveau
	 *                           corps
	 * @param distanceBaseDebris Distance entre le centre du corps explosable et la
	 *                           position initial du nouveau coprs
	 * 
	 * @return Position calculé du nouveau corps
	 */
	private Vecteur nouvPosition(Corps corpsExplosable, double teta, double distanceBaseDebris) {
		double positionX = corpsExplosable.getPosition().getX()
				+ ((corpsExplosable.getRayon() + distanceBaseDebris * nouvRayon(corpsExplosable)) * Math.cos(teta));
		double positionY = corpsExplosable.getPosition().getY()
				+ ((corpsExplosable.getRayon() + distanceBaseDebris * nouvRayon(corpsExplosable)) * Math.sin(teta));

		return (new Vecteur(positionX, positionY));
	}// fin de la méthode

	// Antonin Triz
	/**
	 * Permet de caculer la vitesse du nouveau corps émergeant de l'explosion
	 * 
	 * @param corpsExplosable Le corps explosable dont on va calculer la vitesse
	 * @param posInitial      Position initiale du corps explosable
	 * @param posFinal        Position finale du nouveau corps
	 * 
	 * @return Vitese calculé du nouveau corps
	 */
	private Vecteur nouvVitesse(Corps corpsExplosable, Vecteur posInitiale, Vecteur posFinale) {
		Vecteur direction = posFinale.soustraire(posInitiale);

		Vecteur directionUnitaire = direction.diviser(direction.getModule());

		Vecteur vecVitesseInitiale = corpsExplosable.getVitesse();
		double moduleVitesseInitiale = vecVitesseInitiale.getModule();

		Vecteur directionUnitaireBonneNorme = directionUnitaire.multiplier(moduleVitesseInitiale);
		Vecteur vecteurFinal = directionUnitaireBonneNorme.additionner(vecVitesseInitiale);

		return vecteurFinal;
	}// fin de la méthode
}// fin de la classe