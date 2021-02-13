package objetdessinable;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import interfaces.Dessinable;
import physique.MoteurPhysiqueCorps;
import physique.Vecteur;

/**
 * Classe Blob, cet organisme qui mange les plan�tes dans le jeu
 * 
 * @author Jeanne Castonguay
 * @author Charlyne Lefrancois
 *
 */
public class Blob implements Dessinable {

	private Vecteur position = new Vecteur();
	private double masse = 10e22; // masse arbitraire de d�but 10e12 kg ?
	private double rayon; // le rayon sera proportionnel a la masse
	// va-t-on devoir utiliser la masse volumique ou la densit� ?
	private double volume = 0; // en m^3
	private final double DENSITE = 5000; // kg/m^3, c'est un tout petit peu moins que la densit� de la terre
	private Vecteur vitesse = new Vecteur();
	private final double PAS = 180000.0; // 1000 km par pas
	private MoteurPhysiqueCorps moteurPhysiqueCorps = new MoteurPhysiqueCorps();

	// jeanne
	/**
	 * Constructeur permettant d'instancier un blob
	 */
	public Blob() {
		calculerVolume();
		calculerRayon();
		position = new Vecteur();
	}// fin du constructeur

	// jeanne
	/**
	 * M�thode permettant de dessiner le blob
	 * 
	 * @param g2d Le contexte graphique
	 * @param mat La matrice de transformation
	 */
	public void dessiner(Graphics2D g2d, AffineTransform mat) {
		// dessin d'un cerlce jaune, pour l'instant
		Color colorIni = g2d.getColor();
		Color couleur = Color.YELLOW;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(couleur);

		Ellipse2D.Double cercle = new Ellipse2D.Double(position.getX() - rayon, position.getY() - rayon, 2 * rayon,
				2 * rayon);
		g2d.fill(mat.createTransformedShape(cercle));
		g2d.setColor(colorIni);

	}// fin de la m�thode

	// jeanne
	// vrm necessaire ? ou a modifier
	/**
	 * M�thode permettant au blob de se d�placer d'une certaine distance dans une
	 * direction
	 * 
	 * @param v Le vecteur d�placement que l'on veut effectuer
	 */
	public void deplacerDe(Vecteur v) {
		position = position.additionner(v);
	}// fin de la m�thode

	// jeanne
	/**
	 * M�thode permettant au blob de se d�placer vers une position � vitesse
	 * constante
	 * 
	 * @param v Le vecteur position vers lequel on veut que le blob se d�place
	 */
	public void deplacerVers(Vecteur v) {
		Vecteur difference = new Vecteur();

		difference = v.soustraire(position);

		double module = (double) difference.getModule();
		if (module > PAS) {
			// System.out.println(module);
			difference = difference.multiplier(PAS / module);
			vitesse = difference.multiplier(100.0); // pour mettre la vitesse en seconde puisque les pas sont 10
			// miliseconde
			position = position.additionner(difference);
			// System.out.println(difference);

		}
	}// fin de la m�thode

	// jeanne

	public Vecteur getPosition() {
		return position;
	}// fin de la m�thode

	// jeanne
	/**
	 * M�thode permettant d'ajouter une certaine masse au blob
	 * 
	 * @param masseAAjouter La masse � ajouter au blob
	 */
	public void ajouterMasse(double masseAAjouter) {
		masse += masseAAjouter;
		calculerVolume();
		calculerRayon();
	}// fin de la m�thode

	// jeanne
	/**
	 * M�thode permettant au blob de perdre une certaine masse
	 * 
	 * @param masseAPerdre La masse � perdre
	 */
	public void perdreMasse(double masseAPerdre) {
		masse -= masseAPerdre;
		calculerVolume();
		calculerRayon();
	}// fin de la m�thode

	// jeanne
	public double getMasse() {
		return masse;
	}// fin de la m�thode

	// jeanne
	public double getMasseInitiale() {
		return 10e22;
	}// fin de la m�thode

	// jeanne
	public double getRayon() {
		return rayon;
	}// fin de la m�thode

	// charlyne
	public double getEnergieCinetique(Blob blob) {
		return moteurPhysiqueCorps.energieCinetique(blob);
	}// fin de la m�thode

	// charlyne
	public double getEnergiePotentielle(Blob blob1, Corps corps) {
		return moteurPhysiqueCorps.energiePotentielleSur1(blob1, corps);
	}// fin de la m�thode

	// charlyne
	public Vecteur getQuantiteDeMouvement() {
		return vitesse.multiplier(masse);
	}// fin de la m�thode

	// jeanne
	/**
	 * M�thode permettant de calculer le volume en fonction de la masse
	 */
	private void calculerVolume() {
		volume = masse / DENSITE;
	}// fin de la m�thode
	

	// jeanne
	/**
	 * M�thode permettant de calculer le rayon en fonction du volume
	 */
	private void calculerRayon() {
		double b = 1.0 / 3.0;
		rayon = Math.pow(3.0 * volume / (4.0 * Math.PI), b);
	}// fin de la m�thode

	// jeanne
	public double getVolume() {
		return volume;
	}// fin de la m�thode

	// jeanne
	public Vecteur getVitesse() {
		return vitesse;
	}// fin de la m�thode

}// fin de la classe
