package composants;

import java.awt.geom.AffineTransform;

/**
 * Cette classe permet de gérer l'affichage d'objets selon des mesures réelles
 * 
 * @author Charlyne Lefrancois code du tp2
 *
 */
public class ModeleAffichage {
	private double hautUnitesReelles = -1;
	private double largUnitesReelles;
	private double largPixels;
	private double hautPixels;
	private double pixelsParUniteX;
	private double pixelsParUniteY;
	private AffineTransform matMC;

	/**
	 * Permet de créer un objet ModeleAffichage, pouvant mémoriser la matrice (et
	 * autres valeurs) de transformation pour passer du monde vers le composant. La
	 * largeur du monde réel est passée en paramètre. La hauteur sera calculée de
	 * façon à n'introduire aucune distortion.
	 * 
	 * @param largPixels     La largeur du composant, en pixels
	 * @param hautPixels     La hauteur du composant, en pixels
	 * @param largeurDuMonde La largeur du monde, en unitées réelles
	 */
	public ModeleAffichage(double largPixels, double hautPixels, double largeurDuMonde) {

		this.largPixels = largPixels;
		this.hautPixels = hautPixels;
		this.largUnitesReelles = largeurDuMonde;

		// on calcule la hauteur correspondante pour éviter toute distortion
		this.hautUnitesReelles = largUnitesReelles * hautPixels / largPixels;

		calculerRatios();
		creerMatrice();
	}// fin constructeur

	/**
	 * Permet de créer un objet ModeleAffichage, pouvant mémoriser la matrice (et
	 * autres valeurs) de transformation pour passer du monde vers le composant. La
	 * largeur du monde réel est passée en paramètre.
	 * 
	 * @param largPixels     La largeur du composant, en pixels
	 * @param hautPixels     La hauteur du composant, en pixels
	 * @param largeurDuMonde La largeur du monde, en unitées réelles
	 * @param hauteurMonde   La hauteur du monde, en unités réelles
	 */
	public ModeleAffichage(double largPixels, double hautPixels, double largeurDuMonde, double hauteurMonde) {

		this.largPixels = largPixels;
		this.hautPixels = hautPixels;
		this.largUnitesReelles = largeurDuMonde;
		this.hautUnitesReelles = hauteurMonde;

		calculerRatios();
		creerMatrice();
	}// fin constructeur

	private void calculerRatios() {
		// calcul du nombre de pixels qu'on aura par unité réelle
		this.pixelsParUniteX = largPixels / largUnitesReelles;
		this.pixelsParUniteY = hautPixels / hautUnitesReelles;
	}// fin de la méthode

	private void creerMatrice() {
		// formation de la matrice monde-vers-composant
		AffineTransform mat = new AffineTransform(); // on part d'une matrice identite
		mat.scale(pixelsParUniteX, pixelsParUniteY);

		// on mémorise la matrice (qui pourra être retournée via le getter associé)
		this.matMC = mat;
	}// fin de la méthode

	/**
	 * Retourne une copie de la matrice monde-vers-composant qui a été calculée dans
	 * le constructeur
	 * 
	 * @return La matrice monde-vers-composant
	 */
	public AffineTransform getMatMC() {
		// on décide de retourner une copie de celle qui est mémorisée, pour éviter
		// qu'elle soit modifiée
		return new AffineTransform(matMC);
	}// fin de la méthode

	/**
	 * Retourne la hauteur du monde, en unités réelles
	 * 
	 * @return La hauteur du monde, en unités réelles
	 */
	public double getHautUnitesReelles() {
		return hautUnitesReelles;
	}// fin de la méthode

	/**
	 * Change la valeur de la hauteur réelle
	 * 
	 * @param hauteur La nouvelle hauteur réelle
	 */
	public void setHautUnitReelles(double hauteur) {
		this.hautUnitesReelles = hauteur;
		calculerRatios();
		creerMatrice();
	}// fin de la méthode

	/**
	 * Retourne la largeur du monde, en unités réelles
	 * 
	 * @return La largeur du monde, en unités réelles
	 */
	public double getLargUnitesReelles() {
		return largUnitesReelles;
	}// fin de la méthode

	/**
	 * Retourne le nombre de pixels contenus dans une unité du monde réel, en x
	 * 
	 * @return Le nombre de pixels contenus dans une unité du monde réel, en x
	 */
	public double getPixelsParUniteX() {
		return pixelsParUniteX;
	}// fin de la méthode

	/**
	 * Retourne le nombre de pixels contenus dans une unité du monde réel, en y
	 * 
	 * @return Le nombre de pixels contenus dans une unité du monde réel, en y
	 */
	public double getPixelsParUniteY() {
		return pixelsParUniteY;
	}// fin de la méthode

	/**
	 * Retourne la largeur en pixels du composant auquel s'appliquera la
	 * transformation
	 * 
	 * @return La largeur en pixels
	 */
	public double getLargPixels() {
		return largPixels;
	}// fin de la méthode

	/**
	 * Retourne la hauteur en pixels du composant auquel s'appliquera la
	 * transformation
	 * 
	 * @return La hauteur en pixels
	 */
	public double getHautPixels() {
		return hautPixels;
	}// fin de la méthode

}// fin de la classe
