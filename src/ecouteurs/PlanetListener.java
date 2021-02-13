package ecouteurs;

import java.util.EventListener;

/**
 * 
 * Écouteurs permettant d'écouter la varation des paramètres des différents
 * corps
 * 
 * @author Charlyne Lefrançois
 *
 */
public interface PlanetListener extends EventListener {

	/**
	 * ** Permet d'afficher les différents paramètres d'un coprs qui changent
	 * 
	 * @param masse        masse du corps
	 * @param accel        accélération du corps
	 * @param rayon        rayon du corps
	 * @param vitesse      vitesse du corps
	 * @param click        permet de savoir si un corps est clické
	 * @param eCinetique   energie cinétique du coprs
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
	// * Changement de l'énergie cinétique d'un corps
	// * @param energieCinetique
	// * @param typeCoprs
	// * @param nom
	// */
	// public void cinetiquePlaneteUpdate (double energieCinetique, String
	// typeCoprs, String nom);
	//
	// /**
	// * Changement de l'énergie potentielle d'un corps
	// * @param energiePotentielle
	// * @param typeCoprs
	// * @param nom
	// */
	// public void potentiellePlaneteUpdate (double energiePotentielle, String
	// typeCoprs, String nom);
	//
	// /**
	// * Changement de l'accélération d'un corps
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
