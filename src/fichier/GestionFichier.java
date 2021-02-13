package fichier;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import aapplication.Aapplication;
import composants.Simulation;
import objetdessinable.Corps;
import physique.Vecteur;

/**
 * Classe offrant un ensemble de méthodes pour illustrer le fonctionnement des
 * fichiers texte et binaires (Description de Caroline). Classe inspiree de
 * Caroline.
 * 
 * 
 * @author Xavier Boucher 
 * @author Caroline
 *
 */
public class GestionFichier {

	String nomDossierSimulation = "Mes_sauvegardes_de_simulation";

	// fichiers texte
	String nomTextFileSimul = "infoSimulation.txt";
	String nomFichTexteConfigInit = "init.txt";// ?

	// fichiers binaires (objets)
	String nomBinaryFileSimul = "infoObjetSimul.dat";
	String nomFichBinConfigInit = "initObjet.dat";// ?
/**
 * méthode qui sauvegarde une simulation dans un fichier texte
 * 
 * @param nomDonne nom donnée au txt ou la simulation sera sauvegarder
 */
	public void saveFile(String nomDonne) {
		nomTextFileSimul = nomDonne;
		// chemin d'acces au dossier.
		File dossier = new File(System.getProperty("user.home"), "Desktop" + "\\" + nomDossierSimulation);

		// on cree le dossier s'il n'existe pas
		if (dossier.mkdir()) {
			JOptionPane.showMessageDialog(null,
					"Le dossier " + dossier.toString() + " a été créé car il n'existait pas...");
		}

		// chemin d'acces complet au fichier de travail
		File fichierDeTravail = new File(dossier + "\\" + nomTextFileSimul);

		PrintWriter fluxSortie = null;

		try {

			fluxSortie = new PrintWriter(new BufferedWriter(new FileWriter(fichierDeTravail)));

			Simulation simulation = Aapplication.getSimulation();

			ArrayList<Corps> listeDesCorps = simulation.getEnsembleDesCorps();

			Corps corpsEnCoursDeSauvegarde;

			fluxSortie.println("-Nombre de planet:");
			fluxSortie.println(listeDesCorps.size());

			for (int i = 0; i < listeDesCorps.size(); i++) { // (listeDesCorps.get(i))

				corpsEnCoursDeSauvegarde = listeDesCorps.get(i);

				fluxSortie.println("-Numéro de la planete:" + i);

				fluxSortie.println("-Position:");
				fluxSortie.println(corpsEnCoursDeSauvegarde.getPosition().getX());
				fluxSortie.println(corpsEnCoursDeSauvegarde.getPosition().getY());

				fluxSortie.println("-Masse:");
				fluxSortie.println(corpsEnCoursDeSauvegarde.getMasse());

				fluxSortie.println("-Rayon:");
				fluxSortie.println(corpsEnCoursDeSauvegarde.getRayon());

				fluxSortie.println("-Couleur:");
				fluxSortie.println(corpsEnCoursDeSauvegarde.getCouleur().getRed());
				fluxSortie.println(corpsEnCoursDeSauvegarde.getCouleur().getGreen());
				fluxSortie.println(corpsEnCoursDeSauvegarde.getCouleur().getBlue());

				fluxSortie.println("-Vitesse:");
				fluxSortie.println(corpsEnCoursDeSauvegarde.getVitesse().getX());
				fluxSortie.println(corpsEnCoursDeSauvegarde.getVitesse().getY());

				fluxSortie.println("-Accélération:");
				fluxSortie.println(corpsEnCoursDeSauvegarde.getAcceleration().getX());
				fluxSortie.println(corpsEnCoursDeSauvegarde.getAcceleration().getX());

				fluxSortie.println("-Explosable");
				fluxSortie.println(corpsEnCoursDeSauvegarde.estExplosable());

				fluxSortie.println("-NomImage");
				fluxSortie.println(corpsEnCoursDeSauvegarde.getNomImage());
				System.out.println("a: "+corpsEnCoursDeSauvegarde.getNomImage());


				// fluxSortie.println("Force Résultante:" +
				// (listeDesCorps.get(i)).getForceResultante() );
				// !!!!!!non nécéssaire ?

			}

			JOptionPane.showMessageDialog(null, "Les information de la simulation on été sauvegarder dans le fichier  "
					+ fichierDeTravail.toString());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur d'écriture!");
			e.printStackTrace();

		} finally {
			if (fluxSortie != null)
				fluxSortie.close();
		}

	}// fin du constructeur

	/**
	 * méthode permettant de charger un fichier texte et de l'importter dans une simulation
	 * 
	 * @param fichierACharger nom du fichier a charger
	 * @param simAChanger la simulation ou l'on veux charger le fichier
	 */
	public void loadFile(String fichierACharger, Simulation simAChanger) {// commentaire

		nomTextFileSimul = fichierACharger;

		BufferedReader fluxEntree = null;
		//String nom = null;
		//String infoEnCours;
		//Color couleur;
		//Vecteur position, vitesse, acceleration;
		double posX, posY, vitX, vitY, accX, accY, masse, rayon;

		int /*index,*/ red, green, blue, nbrPlanet;

		boolean explosable=false;
		String nomImage="";
		Simulation simulation = simAChanger;

		//ArrayList<Corps> listeDesCorps = simulation.getEnsembleDesCorps();

		// chemin d'acces au fichier de travail, qui sera sur le Bureau
		File fichierDeTravail = new File(System.getProperty("user.home"),
				"Desktop" + "\\" + nomDossierSimulation + "\\" + nomTextFileSimul);

		// on test si le fichier à lire existe
		if (!fichierDeTravail.exists()) {
			JOptionPane.showMessageDialog(null,
					"Problème! Le fichier " + fichierDeTravail.toString() + " n'existe pas...");
		}

		try {
			fluxEntree = new BufferedReader(new FileReader(fichierDeTravail));

			simulation.supprimerTousLesCorps();

			fluxEntree.readLine();// nbrplanet
			nbrPlanet = Integer.parseInt(fluxEntree.readLine());

			for (int i = 0; i < nbrPlanet; i++) { // pour chaque planete

				fluxEntree.readLine();// nom planet
				fluxEntree.readLine();// position
				posX = Double.parseDouble(fluxEntree.readLine());
				posY = Double.parseDouble(fluxEntree.readLine());

				fluxEntree.readLine(); // masse
				masse = Double.parseDouble(fluxEntree.readLine());

				fluxEntree.readLine(); // rayon
				rayon = Double.parseDouble(fluxEntree.readLine());

				fluxEntree.readLine(); // Couleur
				red = Integer.parseInt(fluxEntree.readLine());
				green = Integer.parseInt(fluxEntree.readLine());
				blue = Integer.parseInt(fluxEntree.readLine());

				fluxEntree.readLine();// vitesse
				vitX = Double.parseDouble(fluxEntree.readLine());
				vitY = Double.parseDouble(fluxEntree.readLine());

				fluxEntree.readLine();// acceleration
				accX = Double.parseDouble(fluxEntree.readLine());
				accY = Double.parseDouble(fluxEntree.readLine());

				fluxEntree.readLine();// explosable
				String s = fluxEntree.readLine();
				if(s.equalsIgnoreCase("true")) {
					explosable=true;
				}else {
					explosable=false;
				}

				fluxEntree.readLine();// nomImage
				nomImage=fluxEntree.readLine();
				System.out.println("b: "+nomImage);
				
				// créer et ajouter le corps
				if(nomImage.equalsIgnoreCase("")) {
					Corps corps = new Corps(new Vecteur(posX, posY), masse, rayon, new Color(red, green, blue),explosable);
					corps.setVitesse(new Vecteur(vitX, vitY));
					corps.setAcceleration(new Vecteur(accX, accY));
					simulation.ajouterUnCorps(corps);
				}else {
					Corps corps = new Corps(new Vecteur(posX, posY), masse, rayon, nomImage, explosable);
					corps.setVitesse(new Vecteur(vitX, vitY));
					corps.setAcceleration(new Vecteur(accX, accY));
					simulation.ajouterUnCorps(corps);
				}

			}

		} // fin try

		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Incapable de lire le fichier  " + fichierDeTravail.toString());
			// System.exit(0);
		}

		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur rencontree lors de la lecture");
			e.printStackTrace();
			System.exit(0);
		}

		finally {
			// on exécutera toujours ceci, erreur ou pas
			try {
				fluxEntree.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Erreur rencontrée lors de la fermeture!");
			}
		} // fin finally

	}// fin de la méthode



}// fin de la classe