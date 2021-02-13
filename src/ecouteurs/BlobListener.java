package ecouteurs;

import java.util.EventListener;

/**
 * Écouteurs permettant d'écouter la varation des paramètres du blob
 * 
 * @author Charlyne Lefrancois
 *
 */
public interface BlobListener extends EventListener {

	/**
	 * Permet d'afficher les différents paramètres d'un blob qui changent
	 * 
	 * @param masse        masse du blob
	 * @param vitesse      vitesse du blob
	 * @param rayon        rayon du blob
	 * @param eCinetique   énergie cinétique du blob
	 * @param ePotentielle énergie potentielle du blob
	 * @param acceleration acceleration du blob
	 */
	public void blobChange(double masse, double vitesse, double rayon, double eCinetique, double ePotentielle,
			double acceleration);
}// fin de la classe
