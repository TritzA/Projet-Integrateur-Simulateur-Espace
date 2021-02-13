package structuredonnees;

/**
 * Classe repr�sentant un pas d'it�ration. Il poss�de un nom et une valeur
 * 
 * @author Charlyne Lefrancois
 *
 */
public class PasIteration {

	/** Le nom du pas d'it�ration */
	private String nom;
	/** La valeur du pas d'it�ration */
	private double pas;

	/**
	 * Constructeur pour un pas d'it�ration o� on initialise son nom et sa valeur
	 * 
	 * @param nom Le nom du pas d'it�ration
	 * @param pas La valeur du pas d'it�ration
	 */
	public PasIteration(String nom, double pas) {
		this.nom = nom;
		this.pas = pas;
	}// fin du constructeur

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}// fin de la m�thode

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}// fin de la m�thode

	/**
	 * @return the pas
	 */
	public double getPas() {
		return pas;
	}// fin de la m�thode

	/**
	 * @param pas the pas to set
	 */
	public void setPas(double pas) {
		this.pas = pas;
	}// fin de la m�thode

	@Override
	public String toString() {
		return nom + " - " + pas + "s";
	}// fin de la m�thode

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PasIteration other = (PasIteration) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (Double.doubleToLongBits(pas) != Double.doubleToLongBits(other.pas))
			return false;
		return true;
	}// fin de la m�thode

}// fin de la classe
