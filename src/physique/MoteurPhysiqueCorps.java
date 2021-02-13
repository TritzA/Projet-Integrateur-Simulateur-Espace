package physique;

import composants.Simulation;
import lecture.Son;
import objetdessinable.Blob;
import objetdessinable.Corps;
import objetdessinable.Trajectoire;

/**
 * Classe contenant les méthodes principales permettant les mouvements physiques
 * de la simulation
 * 
 * @author Antonin Tritz
 * @author Charlyne Lefrancois
 */

public class MoteurPhysiqueCorps {

	private final double G = 0.00000000006674;
	private final double K = 1E-5;
	private final double FACTEUR_CONVERSION_KJ = 1000.;
	private UtilitairePhysique utilitairePhysique = new UtilitairePhysique();
	private MoteurPhysiqueExplosable moteurPhysiqueExplosable = new MoteurPhysiqueExplosable();
	private Corps corps1;
	private Corps corps2;

	// Antonin Tritz
	/**
	 * Permet d'obtenir la force d'attraction gravitationnelle d'un corps sur un
	 * autre
	 * 
	 * @param corps1 N'importe quel premier corps dont on veut connaitre
	 *               l'attraction que corps2 excerce sur lui
	 * @param corps2 N'importe quel deuxième corps différent du premier qui exerce
	 *               une attraction sur corps1
	 * 
	 * @return Force gravitationnelle d'attraction du corps2 sur le corps 1
	 */
	public Vecteur forceAttractionSurCorps1(Corps corps1, Corps corps2) {
		utilitairePhysique.testMemeCorps(corps1, corps2);

		double position1x = corps1.getPosition().getX();
		double position2x = corps2.getPosition().getX();
		double distanceX = position2x - position1x;

		double position1y = corps1.getPosition().getY();
		double position2y = corps2.getPosition().getY();
		double distanceY = position2y - position1y;

		double distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));

		double masseA = corps1.getMasse();
		double masseB = corps2.getMasse();

		Vecteur unitaire = new Vecteur(distanceX, distanceY);
		unitaire = new Vecteur(distanceX / unitaire.getModule(), distanceY / unitaire.getModule());

		Vecteur vecteurForceGravite = new Vecteur();

		if (distance != 0) {
			vecteurForceGravite = unitaire.multiplier((G * masseA * masseB) / Math.pow(distance, 2));
		} else {
			vecteurForceGravite = new Vecteur();
		}

		return vecteurForceGravite;

	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet de simuler une iteration physique d'une certaine durée de temps
	 * 
	 * @param simulation         Lieu des attractions et des collisions
	 * @param deltaT             Temps réel de la simulation
	 * @param collisionElastique Type de collision entre les corps
	 */
	public Corps unPasEuler(Simulation simulation, double deltaT, boolean collisionElastique) {

		Corps corpsPourEcouteur = null;

		int k = simulation.getEnsembleDesCorps().size();

		for (int i = 0; i < k; i++) {
			Corps corps = simulation.getEnsembleDesCorps().get(i);
			utilitairePhysique.modifierAcceleration(corps);
			Corps collision = estCollision(corps, simulation);
			if (collision != null && !collision.equals(corps)) {

				if (collision.estExplosable() && !corps.estExplosable()) {
					moteurPhysiqueExplosable.explosionUnCorps(collision, simulation);
					k = simulation.getEnsembleDesCorps().size();

				} else if (corps.estExplosable() && !collision.estExplosable()) {
					moteurPhysiqueExplosable.explosionUnCorps(corps, simulation);
					k = simulation.getEnsembleDesCorps().size();

				} else if (collision.estExplosable() && corps.estExplosable()) {
					moteurPhysiqueExplosable.explosionDeuxCorps(corps, collision, simulation);
					k = simulation.getEnsembleDesCorps().size();

				} else {

					if (collisionElastique) {
						collisionsElastiques(corps, collision, simulation);
					} else {
						corpsPourEcouteur = fusion(corps, collision, simulation);
						corps1 = corps;
						corps2 = collision;
						k = simulation.getEnsembleDesCorps().size();

					}

				}

			}
			k = simulation.getEnsembleDesCorps().size();
			utilitairePhysique.modifierPosition(corps, deltaT);
			utilitairePhysique.modifierVitesse(corps, deltaT);
		}
		return corpsPourEcouteur;
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet de savoir si un certain corps est en collision avec un autre et, si
	 * oui, avec lequel
	 * 
	 * @param corps      Corps dont on veut tester s'il est en collision
	 * @param simulation Lieu des collisions
	 * 
	 * @return Corps qui est en collision avec le corps en paramètre, retourne null
	 *         si le corps n'est pas en collision avec un autre
	 */
	private Corps estCollision(Corps corps, Simulation simulation) {// bug si 2 collision en mm temps je crois
		Corps collision = null;
		for (Corps corps2 : simulation.getEnsembleDesCorps()) {
			if (!corps2.equals(corps)) {
				if (utilitairePhysique.distance(corps, corps2) <= (corps.getRayon() + corps2.getRayon())) {// condition
					// un dans
					// l<autre,
					// pas
					collision = corps2;
				}
			}
		}
		return collision;
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet de régler tous les paramètres nécessaires à la création d'un nouveau
	 * corps lors d'un collision. Supprime aussi les anciens corps collisionnés de
	 * la simulation
	 * 
	 * @param corps1     N'importe quel premier corps qui est en collision
	 * @param corps2     N'importe quel deuxième corps différent du premier qui est
	 *                   en collision
	 * @param simulation Lieu des collisions
	 */
	private Corps fusion(Corps corps1, Corps corps2, Simulation simulation) {
		utilitairePhysique.testMemeCorps(corps1, corps2);

		Corps corps1Tempo = new Corps(corps1);

		corps1.setCouleur(utilitairePhysique.fusionCouleurs(corps1Tempo, corps2));

		corps1.setRayon(utilitairePhysique.fusionRayons(corps1Tempo, corps2));

		corps1.setMasse(utilitairePhysique.fusionMasses(corps1Tempo, corps2));

		corps1.setVitesse(utilitairePhysique.fusionVitesses(corps1Tempo, corps2, corps1));

		corps1.setPosition(utilitairePhysique.fusionPositions(corps1Tempo, corps2));

		corps1.setTrajectoire(new Trajectoire());

		utilitairePhysique.gestionCorps(corps2, simulation);// gere les forces

		utilitairePhysique.modifierAcceleration(corps1);

		Son.soundFusion();

		return corps1;
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Perrmet de calculer et ajoute au corps la force du ressort
	 * 
	 * @param corps     Corps sur lequel on veut ajouter une force de ressort
	 * @param distanceX Distance en X entre le point de traction du corps et le
	 *                  corps
	 * @param distanceY Distance en Y entre le point de traction du corps et le
	 *                  corps
	 */
	public void forceRessort(Corps corps, double distanceX, double distanceY) {
		// F = k*deltaL
		Vecteur distance = new Vecteur(distanceX, distanceY);
		Vecteur forceRessort = distance.multiplier(K * corps.getMasse());// on multiplie par la masse pour avoir une
		// force addaptee
		corps.ajouterUneForce(forceRessort);
		// recalculerLesForces()??
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet de calculer la valeur de l'énergie cinétique d'un corps donné
	 * 
	 * @param corps N'importe quel corps dont on veut connaitre l'énergie cinétique
	 * 
	 * @return Énergie cinétique du corps donné
	 */
	public double energieCinetique(Corps corps) {
		// ek = (1/2) * m * v^2
		double masse = corps.getMasse();
		double vitesse = corps.getVitesse().getModule();
		return (0.5 * masse * Math.pow(vitesse, 2)) / FACTEUR_CONVERSION_KJ;
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet de calculer la valeur de l'énergie potentielle d'un corps donné par
	 * rapport à un autre
	 * 
	 * @param corps1 N'importe quel corps dont on veut connaitre l'énergie
	 *               potentielle créé sur ce dernier par corps2
	 * @param corps2 N'importe quel autre corps qui produit une énergie potentille
	 *               sur le corps1
	 * 
	 * @return Énergie potentielle généré par le corps2 sur le corps1
	 */
	public double energiePotentielleSur1(Corps corps1, Corps corps2) {
		// ep = (-GMm)/r
		utilitairePhysique.testMemeCorps(corps1, corps2);

		double masse1 = corps1.getMasse();
		double masse2 = corps2.getMasse();
		double distance = utilitairePhysique.distance(corps1, corps2);

		return ((-G * masse1 * masse2) / distance) / FACTEUR_CONVERSION_KJ;
	}// fin de la méthode

	// charlyne
	/**
	 * Permet une collision élastique entre deux corps
	 * 
	 * @param corps1     un des deux corps qui est en collision
	 * @param corps2     l'autre corps qui est en collision
	 * @param simulation simulation dans laquelle les collision se font
	 */
	public void collisionsElastiques(Corps corps1, Corps corps2, Simulation simulation) {
		utilitairePhysique.testMemeCorps(corps1, corps2);
		Vecteur normale = corps1.getPosition().soustraire(corps2.getPosition())
				.multiplier(1 / (corps1.getPosition().getModule() - corps2.getPosition().getModule()));
		double impulsion = (corps1.getVitesse().soustraire(corps2.getVitesse()).point(normale))
				* (((-2) / ((1 / corps1.getMasse()) + ((1 / corps2.getMasse())))));

		Vecteur vitesseF1 = (corps1.getVitesse()).additionner(normale.multiplier(impulsion / corps1.getMasse()));
		Vecteur vitesseF2 = (corps2.getVitesse()).soustraire(normale.multiplier(impulsion / corps2.getMasse()));

		corps1.setVitesse(vitesseF1);
		corps2.setVitesse(vitesseF2);

		Son.soundBump();
	}// fin de la méthode

	// charlyne
	// methode antonin mais pour blo
	/**
	 * Méthode qui permet de calculer l'énergie cinétique d'un blob
	 * 
	 * @param blob blob sur lequel on veut calculer l'energie cinétique
	 * @return l'énergie cinétique du blob
	 */
	public double energieCinetique(Blob blob) {
		double masse = blob.getMasse();
		double vitesse = blob.getVitesse().getModule();
		return 0.5 * masse * Math.pow(vitesse, 2) / FACTEUR_CONVERSION_KJ;
	}// fin de la méthode

	// charlyne
	// methode antonin mais pour blob
	/**
	 * Méthode qui permet de calculer l'energie potentielle sur un blob
	 * 
	 * @param blob1 blob sur lequel on veut calculer l'energie potentielle
	 * @param corps corps avec lequel on va calculer l'energie potentielle du blob
	 * @return l'energie potentielle totale effectué sur le blob
	 */
	public double energiePotentielleSur1(Blob blob1, Corps corps) {

		double masse1 = blob1.getMasse();
		double masse2 = corps.getMasse();
		double distance = UtilitairePhysique.distance(blob1, corps);

		return ((-G * masse1 * masse2) / distance) / FACTEUR_CONVERSION_KJ;
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Méthode qui retourne le premier corps qui fusionnne
	 * 
	 * @return Le premier corps qui fusionnne
	 */
	public Corps getCorps1() {
		return corps1;
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Méthode qui retourne le deuxième corps qui fusionnne
	 * 
	 * @return Le deuxième corps qui fusionnne
	 */
	public Corps getCorps2() {
		return corps2;
	}// fin de la méthode

}// fin de la classe
