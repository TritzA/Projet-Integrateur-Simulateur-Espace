package physique;

/**
 * @author Jeanne Castonguay
 *
 */
public class Vecteur {

	/**
	 * x La coordonnée en x en km y La coordonnée en y en km EPSILON Un nombre très
	 * petit
	 *
	 */
	private static final double EPSILON = 1e-10; // tolerance utilisee dans les comparaisons reeles avec zero
	private double x, y;

	// constructeurs

	/**
	 * Constructeur permettant de créer un vecteur null
	 */
	public Vecteur() {
		x = 0;
		y = 0;
	}// fin du constructeur

	/**
	 * Constructeur permettant de créer un vecteur ayant une valeur x et une valeur
	 * y
	 * 
	 * @param x La coordonnée x du vecteur en km
	 * @param y La coordonnée y du vecteur en km
	 */
	public Vecteur(double x, double y) {
		this.x = x;
		this.y = y;
	}// fin du constructeur

	// méthodes utiles

	/**
	 * Méthode permettant d'additionner un vecteur avec un autre et qui retourne le
	 * vecteur résultant
	 * 
	 * @param v Le vecteur à additionner
	 * @return Le vecteur résultant de l'addition
	 */
	public Vecteur additionner(Vecteur v) {
		return new Vecteur(x + v.getX(), y + v.getY());
	}// fin de la méthode

	/**
	 * Méthode permettant de soustraire un vecteur à un autre et qui retourne le
	 * vecteur résultant
	 * 
	 * @param v Le vecteur à soustraire
	 * @return Le vecteur résultant de la soustraction
	 */
	public Vecteur soustraire(Vecteur v) {
		return new Vecteur(x - v.getX(), y - v.getY());
	}// fin de la méthode

	/**
	 * Méthode permettant de faire un produit scalaire entre deux vecteur et qui
	 * retourne le scalaire résultant
	 * 
	 * @param v Le vecteur avec lequel on veut effectuer le produit scalaire
	 * @return La valeur, en double, du scalaire résultant
	 */
	public double point(Vecteur v) {
		return x * v.getX() + y * v.getY();
	}// fin de la méthode

	/**
	 * Méthode permettant de mutliplier un vecteur avec un scalaire, donc de grandir
	 * un vecteur
	 * 
	 * @param a La valeur double du scalaire
	 * @return Le vecteur résultant
	 */
	public Vecteur multiplier(double a) {
		return new Vecteur(x * a, y * a);
	}// fin de la méthode

	/**
	 * Méthode permettant de diviser un vecteur avec un scalaire, donc de grandir un
	 * vecteur
	 * 
	 * @param a La valeur double du scalaire
	 * @return Le vecteur résultant
	 */
	public Vecteur diviser(double a) {
		return new Vecteur(x / a, y / a);
	}// fin de la méthode

	// vraiment necessaire ?
	/**
	 * À EVITER : Méthode permettant d'obtenir le vecteur dans un tableau de double
	 * NE PAS UTILISER POUR LES CALCULS, IL EXISTE DES MÉTHODES FACILITANT CELA
	 * 
	 * @return vec Un tableau avec les valeurs du vecteur en km
	 */
	public double[] getVecteur() {
		double[] vec = { x, y };
		return vec;
	}// fin de la méthode

	// produit vectoriel ?

	/**
	 * Méthode permettant de calculer le module d'un vecteur
	 * 
	 * @return Le module du vecteur en km
	 */
	public double getModule() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}// fin de la méthode

	// setter et getter
	public void setXY(double x, double y) {
		this.setX(x);
		this.setY(y);
	}// fin de la méthode

	public double getX() {
		return x;
	}// fin de la méthode

	public void setX(double x) {
		this.x = x;
	}// fin de la méthode

	public double getY() {
		return y;
	}// fin de la méthode

	public void setY(double y) {
		this.y = y;
	}// fin de la méthode

	// toString
	/**
	 * Méthode permettant d'accèder au vecteur en chaine de caractères
	 * 
	 * @return Une chaine de caractère équivalente au vecteur
	 */
	public String toString() {
		return "[ x = " + x + ", y = " + y + "]";
	}// fin de la méthode

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
	}// fin de la méthode

}// fin de la classe
