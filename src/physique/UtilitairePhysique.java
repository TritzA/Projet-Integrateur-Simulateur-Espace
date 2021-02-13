package physique;

import java.awt.Color;

import composants.Simulation;
import objetdessinable.Blob;
import objetdessinable.Corps;

/**
 * Classe utilitaire contenant les méthodes secondaires permettant les
 * mouvements physiques de la simulation
 * 
 * @author Antonin Tritz
 */
public class UtilitairePhysique {

	// Antonin Tritz
	/**
	 * Permet de calculer la couleur du nouveau corps généré suite à une collision
	 * 
	 * @param corps1 N'importe quel premier corps entré en collision
	 * @param corps2 N'importe quel deuxième corps entré en collision différent du
	 *               premier
	 * 
	 * @return Couleur résultante du nouveau corps
	 */
	Color fusionCouleurs(Corps corps1, Corps corps2) {
		testMemeCorps(corps1, corps2);

		double aire1 = Math.PI * (Math.pow(corps1.getRayon(), 2));
		double aire2 = Math.PI * (Math.pow(corps2.getRayon(), 2));

		int moyPondeCoulRouge = (int) moyennePonderee(aire1, corps1.getCouleur().getRed(), aire2,
				corps2.getCouleur().getRed());
		int moyPondeCoulVert = (int) moyennePonderee(aire1, corps1.getCouleur().getGreen(), aire2,
				corps2.getCouleur().getGreen());
		int moyPondeCoulBleu = (int) moyennePonderee(aire1, corps1.getCouleur().getBlue(), aire2,
				corps2.getCouleur().getBlue());
		Color moyCouleur = new Color(moyPondeCoulRouge, moyPondeCoulVert, moyPondeCoulBleu);
		return moyCouleur;
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet de calculer la masse du nouveau corps généré suite à une collision
	 * 
	 * @param corps1 N'importe quel premier corps entré en collision
	 * @param corps2 N'importe quel deuxième corps entré en collision différent du
	 *               premier
	 * 
	 * @return Masse résultante du nouveau corps
	 */
	double fusionMasses(Corps corps1, Corps corps2) {
		testMemeCorps(corps1, corps2);

		double masse1 = corps1.getMasse();
		double masse2 = corps2.getMasse();
		double sommeMasses = masse1 + masse2;
		return sommeMasses;
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet de calculer le rayon du nouveau corps généré suite à une collision
	 * 
	 * @param corps1 N'importe quel premier corps entré en collision
	 * @param corps2 N'importe quel deuxième corps entré en collision différent du
	 *               premier
	 * 
	 * @return Rayon résultant du nouveau corps
	 */
	double fusionRayons(Corps corps1, Corps corps2) {
		testMemeCorps(corps1, corps2);

		double aire1 = aire(corps1);
		double aire2 = aire(corps2);
		double sommeAire = aire1 + aire2;
		double aireNouv = Math.sqrt(sommeAire / Math.PI);
		return aireNouv;
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet de calculer la quantité de mouvement du nouveau corps généré suite à
	 * une collision
	 * 
	 * @param corps1 N'importe quel premier corps entré en collision
	 * @param corps2 N'importe quel deuxième corps entré en collision différent du
	 *               premier
	 * 
	 * @return Quantité de mouvement résultante du nouveau corps
	 */
	private Vecteur fusionQuantiteDeMouvement(Corps corps1, Corps corps2) {
		testMemeCorps(corps1, corps2);

		Vecteur quantMouv1 = corps1.getQuantiteDeMouvement();
		Vecteur quantMouv2 = corps2.getQuantiteDeMouvement();
		Vecteur quantMouvNouv = quantMouv1.additionner(quantMouv2);
		return quantMouvNouv;
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet de calculer la vitesse du nouveau corps généré suite à une collision
	 * 
	 * @param corps1    N'importe quel premier corps entré en collision
	 * @param corps2    N'importe quel deuxième corps entré en collision différent
	 *                  du premier
	 * @param corpsNouv Corps formé de la fusion des deux premiers corps
	 * 
	 * @return Vitesse résultante du nouveau corps
	 */
	Vecteur fusionVitesses(Corps corps1, Corps corps2, Corps corpsNouv) {
		testMemeCorps(corps1, corps2);

		Vecteur vitesseNouv = fusionQuantiteDeMouvement(corps1, corps2).multiplier(1. / corpsNouv.getMasse());
		return vitesseNouv;
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet de calculer la position du nouveau corps généré suite à une collision
	 * 
	 * @param corps1 N'importe quel premier corps entré en collision
	 * @param corps2 N'importe quel deuxième corps entré en collision différent du
	 *               premier
	 * 
	 * @return Position résultante du nouveau corps
	 */
	Vecteur fusionPositions(Corps corps1, Corps corps2) {
		testMemeCorps(corps1, corps2);

		double aire1 = aire(corps1);
		double aire2 = aire(corps2);

		double posNouvX = moyennePonderee(aire1, corps1.getPosition().getX(), aire2, corps2.getPosition().getX());
		double posNouvY = moyennePonderee(aire1, corps1.getPosition().getY(), aire2, corps2.getPosition().getY());
		Vecteur posNouv = new Vecteur(posNouvX, posNouvY);
		return posNouv;
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet de supprimer de simulation un anciens corps entré en collision
	 * 
	 * @param corps2     N'importe quel deuxième corps entré en collision
	 * @param simulation Lieu des collisions
	 */
	void gestionCorps(Corps corps2, Simulation simulation) {
		simulation.supprimerUnCorps(corps2);
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet de modifier l'accélération d'un corps selon les équations du MUA
	 * 
	 * @param corps Corps dont on veut modifier l'accélération
	 */
	void modifierAcceleration(Corps corps) {
		// a = F/m
		double masse = corps.getMasse();
		Vecteur forceResu = corps.getForceResultante();

		Vecteur acceleration = forceResu.multiplier(1. / masse);
		corps.setAcceleration(acceleration);
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet de modifier la position d'un corps selon les équations du MUA
	 * 
	 * @param corps  Corps dont on veut modifier la position
	 * @param deltaT Temps réel de la simulation
	 */
	void modifierPosition(Corps corps, double deltaT) {
		// deltax = (vi*t) + ((a*t^2)/2)
		Vecteur vitesseInitiale = corps.getVitesse();
		Vecteur acceleration = corps.getAcceleration();

		Vecteur position = vitesseInitiale.multiplier(deltaT)
				.additionner(acceleration.multiplier(Math.pow(deltaT, 2)).multiplier(1. / 2.));
		corps.setPosition(position.additionner(corps.getPosition()));
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet de modifier la vitesse d'un corps selon les équations du MUA
	 * 
	 * @param corps  Corps dont on veut modifier la vitesse
	 * @param deltaT Temps réel de la simulation
	 */
	void modifierVitesse(Corps corps, double deltaT) {
		// Vf = vi + a*t
		Vecteur vitesseInitiale = corps.getVitesse();
		Vecteur acceleration = corps.getAcceleration();

		Vecteur vitesseFinale = vitesseInitiale.additionner(acceleration.multiplier(deltaT));
		corps.setVitesse(vitesseFinale);

	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet le calcul de l'aire d'un corps
	 * 
	 * @param corps Corps dont on connaitre l'aire
	 * 
	 * @return Aire du corps
	 */
	private double aire(Corps corps) {
		// aireCercle = pi * r^2
		return Math.PI * (Math.pow(corps.getRayon(), 2));
	}

	// Antonin Tritz
	/**
	 * Permet le calcul d'une moyenne pondérée
	 * 
	 * @param p1 Poid de la première valeur
	 * @param x1 Première valeur de la pondération
	 * @param p2 Poid de la deuxième valeur
	 * @param x2 Deuxième valeur de la pondération
	 * 
	 * @return Valeur résultante de la moyenne pondérée
	 */
	private double moyennePonderee(double p1, double x1, double p2, double x2) {// a coder
		return (p1 * x1 + p2 * x2) / (p1 + p2);
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet de tester si deux corps passés en paramètre sont finalement le même
	 * 
	 * @param corps1 N'importe quel premier corps
	 * @param corps2 N'importe quel deuxième corps normalement différent du premier
	 */
	void testMemeCorps(Corps corps1, Corps corps2) {
		if (corps1.equals(corps2)) {
			System.out.println("Erreur : On teste la distance entre un corps et lui-même.");
		}
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet de connaitre la distance entre le centre de deux corps
	 * 
	 * @param corps1 N'importe quel premier corps
	 * @param corps2 N'importe quel deuxième corps différent du premier
	 * 
	 * @return La distance entre les centres des deux corps
	 */
	double distance(Corps corps1, Corps corps2) {
		testMemeCorps(corps1, corps2);

		double distanceX = corps1.getPosition().getX() - corps2.getPosition().getX();
		double distanceY = corps1.getPosition().getY() - corps2.getPosition().getY();

		return Math.sqrt(Math.pow((distanceX), 2) + Math.pow((distanceY), 2));
	}// fin de la méthode

	// meme methode mais pour blob
	static double distance(Blob blob1, Corps corps) {

		double distanceX = blob1.getPosition().getX() - corps.getPosition().getX();
		double distanceY = blob1.getPosition().getY() - corps.getPosition().getY();

		return Math.sqrt(Math.pow((distanceX), 2) + Math.pow((distanceY), 2));
	}// fin de la méthode

}// fin de la classe