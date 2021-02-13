package composants;

import java.awt.geom.AffineTransform;

/**
 * Cette classe permet de g�rer l'affichage d'objets selon des mesures r�elles
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
	 * Permet de cr�er un objet ModeleAffichage, pouvant m�moriser la matrice (et
	 * autres valeurs) de transformation pour passer du monde vers le composant. La
	 * largeur du monde r�el est pass�e en param�tre. La hauteur sera calcul�e de
	 * fa�on � n'introduire aucune distortion.
	 * 
	 * @param largPixels     La largeur du composant, en pixels
	 * @param hautPixels     La hauteur du composant, en pixels
	 * @param largeurDuMonde La largeur du monde, en unit�es r�elles
	 */
	public ModeleAffichage(double largPixels, double hautPixels, double largeurDuMonde) {

		this.largPixels = largPixels;
		this.hautPixels = hautPixels;
		this.largUnitesReelles = largeurDuMonde;

		// on calcule la hauteur correspondante pour �viter toute distortion
		this.hautUnitesReelles = largUnitesReelles * hautPixels / largPixels;

		calculerRatios();
		creerMatrice();
	}// fin constructeur

	/**
	 * Permet de cr�er un objet ModeleAffichage, pouvant m�moriser la matrice (et
	 * autres valeurs) de transformation pour passer du monde vers le composant. La
	 * largeur du monde r�el est pass�e en param�tre.
	 * 
	 * @param largPixels     La largeur du composant, en pixels
	 * @param hautPixels     La hauteur du composant, en pixels
	 * @param largeurDuMonde La largeur du monde, en unit�es r�elles
	 * @param hauteurMonde   La hauteur du monde, en unit�s r�elles
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
		// calcul du nombre de pixels qu'on aura par unit� r�elle
		this.pixelsParUniteX = largPixels / largUnitesReelles;
		this.pixelsParUniteY = hautPixels / hautUnitesReelles;
	}// fin de la m�thode

	private void creerMatrice() {
		// formation de la matrice monde-vers-composant
		AffineTransform mat = new AffineTransform(); // on part d'une matrice identite
		mat.scale(pixelsParUniteX, pixelsParUniteY);

		// on m�morise la matrice (qui pourra �tre retourn�e via le getter associ�)
		this.matMC = mat;
	}// fin de la m�thode

	/**
	 * Retourne une copie de la matrice monde-vers-composant qui a �t� calcul�e dans
	 * le constructeur
	 * 
	 * @return La matrice monde-vers-composant
	 */
	public AffineTransform getMatMC() {
		// on d�cide de retourner une copie de celle qui est m�moris�e, pour �viter
		// qu'elle soit modifi�e
		return new AffineTransform(matMC);
	}// fin de la m�thode

	/**
	 * Retourne la hauteur du monde, en unit�s r�elles
	 * 
	 * @return La hauteur du monde, en unit�s r�elles
	 */
	public double getHautUnitesReelles() {
		return hautUnitesReelles;
	}// fin de la m�thode

	/**
	 * Change la valeur de la hauteur r�elle
	 * 
	 * @param hauteur La nouvelle hauteur r�elle
	 */
	public void setHautUnitReelles(double hauteur) {
		this.hautUnitesReelles = hauteur;
		calculerRatios();
		creerMatrice();
	}// fin de la m�thode

	/**
	 * Retourne la largeur du monde, en unit�s r�elles
	 * 
	 * @return La largeur du monde, en unit�s r�elles
	 */
	public double getLargUnitesReelles() {
		return largUnitesReelles;
	}// fin de la m�thode

	/**
	 * Retourne le nombre de pixels contenus dans une unit� du monde r�el, en x
	 * 
	 * @return Le nombre de pixels contenus dans une unit� du monde r�el, en x
	 */
	public double getPixelsParUniteX() {
		return pixelsParUniteX;
	}// fin de la m�thode

	/**
	 * Retourne le nombre de pixels contenus dans une unit� du monde r�el, en y
	 * 
	 * @return Le nombre de pixels contenus dans une unit� du monde r�el, en y
	 */
	public double getPixelsParUniteY() {
		return pixelsParUniteY;
	}// fin de la m�thode

	/**
	 * Retourne la largeur en pixels du composant auquel s'appliquera la
	 * transformation
	 * 
	 * @return La largeur en pixels
	 */
	public double getLargPixels() {
		return largPixels;
	}// fin de la m�thode

	/**
	 * Retourne la hauteur en pixels du composant auquel s'appliquera la
	 * transformation
	 * 
	 * @return La hauteur en pixels
	 */
	public double getHautPixels() {
		return hautPixels;
	}// fin de la m�thode

}// fin de la classe
