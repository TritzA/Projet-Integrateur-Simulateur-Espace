package ecouteurs;

import java.util.EventListener;

/**
 * 
 * �couteur permettant d'�couter la varation des param�tres dans la
 * zoneAnimation
 * 
 * @author Charlyne Lefran�ois
 *
 */
public interface ZoneAnimationListener extends EventListener {

	/**
	 * Permet d'afficher les diff�rents param�tres de la simualtion
	 * 
	 * @param nbCorps      nbCorps dans la simulation
	 * @param energieT     �nergie totale du syst�me
	 * @param eCinetique   �nergie cin�tique totale du syst�me
	 * @param ePotentielle �nergie potentielle totale du syst�me
	 * @param quantiteMouv quantit� de mouvement totale du syst�me
	 * @param masse        masse totale du syst�me
	 * @param tempsR       temps r�el �coul�
	 * @param tempsS       temps de simulation �coul�
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
	 * Changement de l'�nergie totale du syst�me
	 * 
	 * @param energieT L'�nergie totale du syst�me
	 */
	public void energieTotaleUpdate(double energieT);

	/**
	 * Changement de l'�nergie cin�tique totale du syst�me
	 * 
	 * @param energieCinetique L'�nergie cin�tique totale du syst�me
	 */
	public void cin�tiqueTotaleUpdate(double energieCinetique);

	/**
	 * Changement de l'�nergie potentielle totale du syst�me
	 * 
	 * @param energiePotentielle L'�nergie potentielle totale du syst�me
	 */
	public void potentielleTotaleUpdate(double energiePotentielle);

	/**
	 * Changement de la quantit� de mouvement totale du syst�me
	 * 
	 * @param quantiteMouv La quantit� de mouvement totale du syst�me
	 */
	public void quantiteMouvTotaleUpdate(double quantiteMouv);

	/**
	 * Changement de la masse totale
	 * 
	 * @param masse Masse totale du syst�me
	 */
	public void masseTotaleUpdate(double masse);

	/**
	 * Changement du temps r�el �coul�
	 * 
	 * @param temps Temps r�el �coul� depuis le commencement de l'animation
	 */
	public void tempsReelUpdate(long tempsR);

	/**
	 * Changement du temps de simulation �coul�
	 * 
	 * @param tempsS Temps de simulation �coul� depuis le commencement de
	 *               l'animation
	 */
	public void tempsSimulationUpdate(double tempsS);
}// fin de la classe
