package ecouteurs;

import java.util.EventListener;

/**
 * 
 * �couteurs permettant d'�couter la varation des param�tres des diff�rents
 * corps
 * 
 * @author Charlyne Lefran�ois
 *
 */
public interface PlanetListener extends EventListener {

	/**
	 * ** Permet d'afficher les diff�rents param�tres d'un coprs qui changent
	 * 
	 * @param masse        masse du corps
	 * @param accel        acc�l�ration du corps
	 * @param rayon        rayon du corps
	 * @param vitesse      vitesse du corps
	 * @param click        permet de savoir si un corps est click�
	 * @param eCinetique   energie cin�tique du coprs
	 * @param ePotentielle energie potentielle du corps
	 */
	public void clickPlanet(double masse, double accel, double rayon, double vitesse, boolean click, double eCinetique,
			double ePotentielle);

	// /**
	// * Changement de la masse d'un corps
	// * @param masse
	// * @param typeCorps
	// * @param nom
	// */
	// public void massePlaneteUpdate (double masse, String typeCorps, String nom);
	//
	// /**
	// * Changement du rayon d'un corps
	// * @param rayon
	// * @param typeCorps
	// * @param nom
	// */
	// public void rayonPlaneteUpdate (double rayon, String typeCorps, String nom);
	//
	// /**
	// * Changement de l'�nergie cin�tique d'un corps
	// * @param energieCinetique
	// * @param typeCoprs
	// * @param nom
	// */
	// public void cinetiquePlaneteUpdate (double energieCinetique, String
	// typeCoprs, String nom);
	//
	// /**
	// * Changement de l'�nergie potentielle d'un corps
	// * @param energiePotentielle
	// * @param typeCoprs
	// * @param nom
	// */
	// public void potentiellePlaneteUpdate (double energiePotentielle, String
	// typeCoprs, String nom);
	//
	// /**
	// * Changement de l'acc�l�ration d'un corps
	// * @param acceleration
	// * @param typeCoprs
	// * @param nom
	// */
	// public void accelerationUpdate (double acceleration, String typeCoprs, String
	// nom);
	//
	// /**
	// * Changement de la vitesse d'un corps
	// * @param acceleration
	// * @param typeCorps
	// * @param nom
	// */
	// public void vitesseUpdate (double acceleration, String typeCorps, String
	// nom);
	// }
}// fin de la classe
