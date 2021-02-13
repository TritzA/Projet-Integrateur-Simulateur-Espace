package ecouteurs;

import java.util.EventListener;

/**
 * �couteurs permettant d'�couter la varation des param�tres du blob
 * 
 * @author Charlyne Lefrancois
 *
 */
public interface BlobListener extends EventListener {

	/**
	 * Permet d'afficher les diff�rents param�tres d'un blob qui changent
	 * 
	 * @param masse        masse du blob
	 * @param vitesse      vitesse du blob
	 * @param rayon        rayon du blob
	 * @param eCinetique   �nergie cin�tique du blob
	 * @param ePotentielle �nergie potentielle du blob
	 * @param acceleration acceleration du blob
	 */
	public void blobChange(double masse, double vitesse, double rayon, double eCinetique, double ePotentielle,
			double acceleration);
}// fin de la classe
