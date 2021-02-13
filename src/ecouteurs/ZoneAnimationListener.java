package ecouteurs;

import java.util.EventListener;

/**
 * 
 * Écouteur permettant d'écouter la varation des paramètres dans la
 * zoneAnimation
 * 
 * @author Charlyne Lefrançois
 *
 */
public interface ZoneAnimationListener extends EventListener {

	/**
	 * Permet d'afficher les différents paramètres de la simualtion
	 * 
	 * @param nbCorps      nbCorps dans la simulation
	 * @param energieT     énergie totale du système
	 * @param eCinetique   énergie cinétique totale du système
	 * @param ePotentielle énergie potentielle totale du système
	 * @param quantiteMouv quantité de mouvement totale du système
	 * @param masse        masse totale du système
	 * @param tempsR       temps réel écoulé
	 * @param tempsS       temps de simulation écoulé
	 */
	public void zoneAnimationUpdate(int nbCorps, double energieT, double eCinetique, double ePotentielle,
			double quantiteMouv, double masse, double tempsR, double tempsS);

	/**
	 * Changement du nombre de corps
	 * 
	 * @param nbCorps Le nombre de corps dans la simulation
	 */
	public void nombreCorpsUpdate(int nbCorps);

	/**
	 * Changement de l'énergie totale du système
	 * 
	 * @param energieT L'énergie totale du système
	 */
	public void energieTotaleUpdate(double energieT);

	/**
	 * Changement de l'énergie cinétique totale du système
	 * 
	 * @param energieCinetique L'énergie cinétique totale du système
	 */
	public void cinétiqueTotaleUpdate(double energieCinetique);

	/**
	 * Changement de l'énergie potentielle totale du système
	 * 
	 * @param energiePotentielle L'énergie potentielle totale du système
	 */
	public void potentielleTotaleUpdate(double energiePotentielle);

	/**
	 * Changement de la quantité de mouvement totale du système
	 * 
	 * @param quantiteMouv La quantité de mouvement totale du système
	 */
	public void quantiteMouvTotaleUpdate(double quantiteMouv);

	/**
	 * Changement de la masse totale
	 * 
	 * @param masse Masse totale du système
	 */
	public void masseTotaleUpdate(double masse);

	/**
	 * Changement du temps réel écoulé
	 * 
	 * @param temps Temps réel écoulé depuis le commencement de l'animation
	 */
	public void tempsReelUpdate(long tempsR);

	/**
	 * Changement du temps de simulation écoulé
	 * 
	 * @param tempsS Temps de simulation écoulé depuis le commencement de
	 *               l'animation
	 */
	public void tempsSimulationUpdate(double tempsS);
}// fin de la classe
