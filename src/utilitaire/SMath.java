package utilitaire;

/**
 * Classe permettant de faire des opérations sur certaines valeurs
 * 
 * @author Charlyne Lefrancois
 * @author Antonin Tritz
 *
 */
public class SMath {

	// Charlyne Lefrancois
	/**
	 * Permet d'arrondir un nombre
	 * 
	 * @param nb      le nombre à arrondir
	 * @param decimal le nombre de nombre apres la virgule
	 * @return le nombre arrondi pour afficher dans l'étiquette
	 */
	public static double arondir(double nb, double decimal) {
		return Math.round(nb * decimal) / decimal;
	}// fin de la méthode

	// Charlyne Lefrancois
	/**
	 * Permet d'avoir l'exposant d'une nombre pour bien faire apparaitre les données
	 * dans l'interface
	 * 
	 * @param number nombre pour lequel on veut l'exposant
	 * @return l'exposant du nombre
	 */
	public static int getExponentForNumber(double number) {
		String numberAsString = number + "";
		String exposant;
		int fin = 0;

		if (number != 0) {
			int i = 0;
			while (i < numberAsString.length()) {
				if (numberAsString.charAt(i) == 'E') {
					exposant = numberAsString.substring(i + 1);

					fin = Integer.parseInt(exposant);
				}
				i++;
			}
		}

		return fin;
	}// fin de la méthode

	// Charlyne Lefrancois
	/**
	 * Permet d'avoir le début du nombre pour bien faire apparaitre les données dans
	 * l'interface
	 * 
	 * @param number nombre pour lequel on veut le nombre sans l'exposant
	 * @return le debut du nombre
	 */
	public static double getDebutNombre(double number) {
		String numberAsString = number + "";
		String debut;
		double deb = 0;

		if (number != 0) {
			int i = 0;
			while (i < numberAsString.length()) {
				if (numberAsString.charAt(i) == 'E') {
					debut = numberAsString.substring(0, i - 1);

					deb = Double.parseDouble(debut);
				}
				i++;
			}
		}

		return deb;

	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet d'optenir un nombre entier aléatoire entre un minimum et un maximum
	 * 
	 * @param min N'importe quel premier nombre entier qui servira de borne
	 *            inférieur
	 * @param max N'importe quel deuxième nombre entier plus grand que le premier
	 *            qui servira de borne supérieur
	 * 
	 * @return Nombre aléatoire entre min et max inclus
	 */
	public static int aleatoire(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet d'optenir un nombre entier aléatoire entre un minimum et un maximum
	 * 
	 * @param min N'importe quel premier nombre entier qui servira de borne
	 *            inférieur
	 * @param max N'importe quel deuxième nombre entier plus grand que le premier
	 *            qui servira de borne supérieur
	 * 
	 * @return Nombre aléatoire rationnel min et max inclus
	 */
	public static double aleatoire(double min, double max) {
		return (min + (Math.random() * ((max - min) + 1)));
	}// fin de la méthode

}// fin de la classe
