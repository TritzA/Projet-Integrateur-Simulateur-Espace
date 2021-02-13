package utilitaire;

/**
 * Classe permettant de faire des op�rations sur certaines valeurs
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
	 * @param nb      le nombre � arrondir
	 * @param decimal le nombre de nombre apres la virgule
	 * @return le nombre arrondi pour afficher dans l'�tiquette
	 */
	public static double arondir(double nb, double decimal) {
		return Math.round(nb * decimal) / decimal;
	}// fin de la m�thode

	// Charlyne Lefrancois
	/**
	 * Permet d'avoir l'exposant d'une nombre pour bien faire apparaitre les donn�es
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
	}// fin de la m�thode

	// Charlyne Lefrancois
	/**
	 * Permet d'avoir le d�but du nombre pour bien faire apparaitre les donn�es dans
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

	}// fin de la m�thode

	// Antonin Tritz
	/**
	 * Permet d'optenir un nombre entier al�atoire entre un minimum et un maximum
	 * 
	 * @param min N'importe quel premier nombre entier qui servira de borne
	 *            inf�rieur
	 * @param max N'importe quel deuxi�me nombre entier plus grand que le premier
	 *            qui servira de borne sup�rieur
	 * 
	 * @return Nombre al�atoire entre min et max inclus
	 */
	public static int aleatoire(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}// fin de la m�thode

	// Antonin Tritz
	/**
	 * Permet d'optenir un nombre entier al�atoire entre un minimum et un maximum
	 * 
	 * @param min N'importe quel premier nombre entier qui servira de borne
	 *            inf�rieur
	 * @param max N'importe quel deuxi�me nombre entier plus grand que le premier
	 *            qui servira de borne sup�rieur
	 * 
	 * @return Nombre al�atoire rationnel min et max inclus
	 */
	public static double aleatoire(double min, double max) {
		return (min + (Math.random() * ((max - min) + 1)));
	}// fin de la m�thode

}// fin de la classe
