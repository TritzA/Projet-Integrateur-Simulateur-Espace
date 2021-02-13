package physique;

/**
 * @author Jeanne Castonguay
 *
 */
public class Vecteur {

	/**
	 * x La coordonn�e en x en km y La coordonn�e en y en km EPSILON Un nombre tr�s
	 * petit
	 *
	 */
	private static final double EPSILON = 1e-10; // tolerance utilisee dans les comparaisons reeles avec zero
	private double x, y;

	// constructeurs

	/**
	 * Constructeur permettant de cr�er un vecteur null
	 */
	public Vecteur() {
		x = 0;
		y = 0;
	}// fin du constructeur

	/**
	 * Constructeur permettant de cr�er un vecteur ayant une valeur x et une valeur
	 * y
	 * 
	 * @param x La coordonn�e x du vecteur en km
	 * @param y La coordonn�e y du vecteur en km
	 */
	public Vecteur(double x, double y) {
		this.x = x;
		this.y = y;
	}// fin du constructeur

	// m�thodes utiles

	/**
	 * M�thode permettant d'additionner un vecteur avec un autre et qui retourne le
	 * vecteur r�sultant
	 * 
	 * @param v Le vecteur � additionner
	 * @return Le vecteur r�sultant de l'addition
	 */
	public Vecteur additionner(Vecteur v) {
		return new Vecteur(x + v.getX(), y + v.getY());
	}// fin de la m�thode

	/**
	 * M�thode permettant de soustraire un vecteur � un autre et qui retourne le
	 * vecteur r�sultant
	 * 
	 * @param v Le vecteur � soustraire
	 * @return Le vecteur r�sultant de la soustraction
	 */
	public Vecteur soustraire(Vecteur v) {
		return new Vecteur(x - v.getX(), y - v.getY());
	}// fin de la m�thode

	/**
	 * M�thode permettant de faire un produit scalaire entre deux vecteur et qui
	 * retourne le scalaire r�sultant
	 * 
	 * @param v Le vecteur avec lequel on veut effectuer le produit scalaire
	 * @return La valeur, en double, du scalaire r�sultant
	 */
	public double point(Vecteur v) {
		return x * v.getX() + y * v.getY();
	}// fin de la m�thode

	/**
	 * M�thode permettant de mutliplier un vecteur avec un scalaire, donc de grandir
	 * un vecteur
	 * 
	 * @param a La valeur double du scalaire
	 * @return Le vecteur r�sultant
	 */
	public Vecteur multiplier(double a) {
		return new Vecteur(x * a, y * a);
	}// fin de la m�thode

	/**
	 * M�thode permettant de diviser un vecteur avec un scalaire, donc de grandir un
	 * vecteur
	 * 
	 * @param a La valeur double du scalaire
	 * @return Le vecteur r�sultant
	 */
	public Vecteur diviser(double a) {
		return new Vecteur(x / a, y / a);
	}// fin de la m�thode

	// vraiment necessaire ?
	/**
	 * � EVITER : M�thode permettant d'obtenir le vecteur dans un tableau de double
	 * NE PAS UTILISER POUR LES CALCULS, IL EXISTE DES M�THODES FACILITANT CELA
	 * 
	 * @return vec Un tableau avec les valeurs du vecteur en km
	 */
	public double[] getVecteur() {
		double[] vec = { x, y };
		return vec;
	}// fin de la m�thode

	// produit vectoriel ?

	/**
	 * M�thode permettant de calculer le module d'un vecteur
	 * 
	 * @return Le module du vecteur en km
	 */
	public double getModule() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}// fin de la m�thode

	// setter et getter
	public void setXY(double x, double y) {
		this.setX(x);
		this.setY(y);
	}// fin de la m�thode

	public double getX() {
		return x;
	}// fin de la m�thode

	public void setX(double x) {
		this.x = x;
	}// fin de la m�thode

	public double getY() {
		return y;
	}// fin de la m�thode

	public void setY(double y) {
		this.y = y;
	}// fin de la m�thode

	// toString
	/**
	 * M�thode permettant d'acc�der au vecteur en chaine de caract�res
	 * 
	 * @return Une chaine de caract�re �quivalente au vecteur
	 */
	public String toString() {
		return "[ x = " + x + ", y = " + y + "]";
	}// fin de la m�thode

	// equals pris de la classe de caroline
	/**
	 * Determine si le vecteur courant est egal ou non a un autre vecteur, a EPSILON
	 * pres
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (!(obj instanceof Vecteur))
			return false;

		Vecteur other = (Vecteur) obj;

		// Comparaison des valeurs x,y et z en utilisant la precision de EPSILON modulee
		// par la valeur a comparer
		if (Math.abs(x - other.getX()) > Math.abs(x) * EPSILON)
			return false;

		if (Math.abs(y - other.getY()) > Math.abs(y) * EPSILON)
			return false;

		return true;
	}// fin de la m�thode

}// fin de la classe
