package physique;

import java.awt.Color;

import composants.Simulation;
import objetdessinable.Corps;

/**
 * 
 * @author Xavier Boucher
 * Classe pour mettre les planetes en orbite circulaire 
 */
public class MiseEnOrbite {

	final double DENOMINATEUR = 100.0;
	final double G = 6.673e-11;
	final int UNITEDETRANSLATION = 5000000;
	
	private Corps planete;
	private Corps satellite;
/**
 * Créer un objet de deux corps que serront stable ensemble
 * @param rayonTotal grandeur des deux rayon lorsque combiné
 * @param masseTotal masse total des deux corps
 * @param distance distance entre les deux centres
 */
	public MiseEnOrbite(double rayonTotal, double masseTotal, double distance) {

		double ratioRayon = 25.0 / DENOMINATEUR;
		double ratioMasse = 5.0 / DENOMINATEUR;
		double ratioRayoninverse = 1-ratioRayon / 1;
		double ratioMasseinverse = 1-ratioMasse / 1;		
		 
		double rayonPlanet = rayonTotal * ratioRayoninverse;
		double rayonSatellite = rayonTotal * ratioRayon;

		double massePlanet = masseTotal * ratioMasseinverse;
		double masseSatellite = masseTotal * ratioMasse;

		Vecteur vitesseDuSatellite = new Vecteur();

		vitesseDuSatellite.setY(Math.sqrt(G * massePlanet / distance));

		this.planete = new Corps(new Vecteur(0, 0), massePlanet, rayonPlanet);
		this.planete.setCouleur(Color.BLUE);
		this.satellite = new Corps(new Vecteur(distance, 0), masseSatellite, rayonSatellite);
		this.satellite.setCouleur(Color.LIGHT_GRAY);
		this.satellite.setVitesse(vitesseDuSatellite);

	}
	/**
	 * créer les corps dans une simulation
	 * @param simulation la simulation ou l'on veut mettre les corps
	 */
	public void miseEnOrbite(Simulation simulation) {// , MiseEnOrbite objetOrbite ) {
		simulation.ajouterUnCorps(planete);
		simulation.ajouterUnCorps(satellite);
	}
	/**
	 * replace les corps au bonne en droit en fonction des nouveaux parammettre
	 * @param ratioRayon ration de la taille entre les deux corps satellite/planete
	 * @param ratioMasse ratio de la masse entre les deux corps satellite/planete
	 * @param rayonTotal grandeur des deux rayon lorsque combiné
	 * @param masseTotal masse total des deux corps
	 * @param distance distance entre les deux centres
	 * @param sim la simulation concerner par les corps
	 */
	public void setMiseEnOrbite(double ratioRayon, double ratioMasse, double rayonTotal, double masseTotal, double distance, Simulation sim) {
		
		deleteOrbite(sim);
		
		ratioRayon = ratioRayon / DENOMINATEUR;
		ratioMasse = ratioMasse / DENOMINATEUR;
		double ratioRayoninverse = 1-ratioRayon / 1;
		double ratioMasseinverse = 1-ratioMasse / 1;
		 
		double rayonPlanet = rayonTotal * ratioRayoninverse;
		double rayonSatellite = rayonTotal * ratioRayon;

		double massePlanet = masseTotal * ratioMasseinverse;
		double masseSatellite = masseTotal * ratioMasse;

		Vecteur vitesseDuSatellite = new Vecteur();

		vitesseDuSatellite.setY(Math.sqrt(G * massePlanet / distance));

		this.planete.setMasse(massePlanet);
		this.planete.setRayon(rayonPlanet);
		this.planete.setCouleur(Color.BLUE);
		this.satellite.setPosition(new Vecteur(this.planete.getPosition().getX()+distance,this.planete.getPosition().getY()));
		this.satellite.setMasse(masseSatellite);
		this.satellite.setRayon(rayonSatellite);
		this.satellite.setCouleur(Color.LIGHT_GRAY);
		this.satellite.setVitesse(vitesseDuSatellite);
		
		miseEnOrbite(sim);
		
		sim.repaint();
	}
/**
 * supprime le dernier systeme apparus
 * @param sim la simulation concerner par les corps
 */
	public void deleteOrbite(Simulation sim) {
		sim.supprimerUnCorps(planete);
		sim.supprimerUnCorps(satellite);
	}
	
	/**
	 * Méthode pour déplacer le systeme par en haut
	 * @param sim simulation concerner
	 */
	public void MoveOrbiteUp(Simulation sim) {
		this.planete.setPosition(new Vecteur (this.planete.getPosition().getX(),this.planete.getPosition().getY()-UNITEDETRANSLATION));
		this.satellite.setPosition(new Vecteur (this.satellite.getPosition().getX(),this.satellite.getPosition().getY()-UNITEDETRANSLATION));
		sim.repaint();
	}
	
	/**
	 * Méthode pour déplacer le systeme vers la gauche
	 * @param sim simulation concerner
	 */
	public void MoveOrbiteLeft(Simulation sim) {
		this.planete.setPosition(new Vecteur (this.planete.getPosition().getX()-UNITEDETRANSLATION,this.planete.getPosition().getY()));
		this.satellite.setPosition(new Vecteur (this.satellite.getPosition().getX()-UNITEDETRANSLATION,this.satellite.getPosition().getY()));
		sim.repaint();
	}
	
	/**
	 * Méthode pour déplacer le systeme par en bas
	 * @param sim simulation concerner
	 */
	public void MoveOrbiteDown(Simulation sim) {
		this.planete.setPosition(new Vecteur (this.planete.getPosition().getX(),this.planete.getPosition().getY()+UNITEDETRANSLATION));
		this.satellite.setPosition(new Vecteur (this.satellite.getPosition().getX(),this.satellite.getPosition().getY()+UNITEDETRANSLATION));
		sim.repaint();
	}
	
	/**
	 * Méthode pour déplacer le systeme vers la droite
	 * @param sim simulation concerner
	 */
	public void MoveOrbiteRight(Simulation sim) {
		this.planete.setPosition(new Vecteur (this.planete.getPosition().getX()+UNITEDETRANSLATION,this.planete.getPosition().getY()));
		this.satellite.setPosition(new Vecteur (this.satellite.getPosition().getX()+UNITEDETRANSLATION,this.satellite.getPosition().getY()));
		sim.repaint();
	}
	
	public Corps getPlanet() {
		return planete;
	}

	public Corps getSatellite() {
		return satellite;
	}
	
}