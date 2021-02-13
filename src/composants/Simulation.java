package composants;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import aapplication.Aapplication;
//import ecouteurs.BlobListener;
import ecouteurs.PlanetListener;
import ecouteurs.ZoneAnimationListener;
import objetdessinable.Corps;
import objetdessinable.MurAfficher;
import physique.MoteurPhysiqueCorps;
import structuredonnees.PasIteration;
import utilitaire.ActionSurCorps;

/**
 * Classe permettant l'animation des corps
 * 
 * @author Jeanne castonguay
 * @author Charlyne Lefrancois
 * @author Xavier Boucher
 *
 */

public class Simulation extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<ZoneAnimationListener> listeEcouteurs = new ArrayList<ZoneAnimationListener>();
	private ArrayList<PlanetListener> listeEcouteursPlanete = new ArrayList<PlanetListener>();
	protected ModeleAffichage modele;
	private AffineTransform mat;
	private ArrayList<Corps> ensembleDesCorps = new ArrayList<Corps>();
	private boolean estAnime = false;
	private double deltaT = 5; // valeur arbitraire, temps réel de la simulation
	private boolean trouverPlanete = false;
	public int nbCorps = 0;
	private Corps corps;
	private boolean clickPlanete;
	private Corps corpsClicker;
	private double masseTotale;
	private int grandeurListeAvant;
	private int grandeurListeApres;
	private Corps corpsFusion;
	boolean dessinerLigne = false;
	private double energiePotentielle;
	private double energieCT;
	private double quantiteMouv;
	private double energieT;
	private boolean murHautActive = false, murBasActive = false, murGaucheActive = false, murDroitActive = false;
	double largeurDuMonde = 100; // valeur arbitraire
	protected long temps = 10;
	private double tempsTotalEcoule = 0.0;
	java.util.Iterator<Corps> it = ensembleDesCorps.iterator();// attention bug avec iterateur quand on enleve ou ajoute
	// un objet (éviter l'utilisation)
	protected boolean premierefois = true;
	protected boolean modeJeu = false;
	private boolean collisionElastique = false;
	private ActionSurCorps actionSurCorps = new ActionSurCorps();
	private MoteurPhysiqueCorps moteurPhysiqueCorps = new MoteurPhysiqueCorps();
	private MurAfficher mur = new MurAfficher();
	private final double RATIO_INITIAL = 180000;
	private long tempsInitial;
	private long tempsFin;

	private AffineTransform matSansTransfo;

	private Image imgDecor = null;

	// Jeanne
	/**
	 * Crée le panel
	 */
	public Simulation() {

		setSize(1330, 650);
		ajouterGestionSouris();
		largeurDuMonde = getWidth() * RATIO_INITIAL;

		double facteur = 0.02;

		// Zoom et dezoom avec la molette
		addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {

				if (e.getPreciseWheelRotation() > 0) {

					largeurDuMonde += largeurDuMonde * facteur;

				} else if (e.getPreciseWheelRotation() < 0) {
					largeurDuMonde -= largeurDuMonde * facteur;

				}

				repaint();
			}
		});
		gestionnaireDePositionDeForceExterieurEtDEfface();

	}// fin du constructeur

	// Jeanne
	/**
	 * Méthode permettant de d'illustrer la simulation
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		matSansTransfo = g2d.getTransform();

		if (premierefois) {
			premierefois = false;

			URL fichDecor = getClass().getClassLoader().getResource("simulation.jpg");
			if (fichDecor == null) {
				System.out.println("Incapable de lire un fichier d'image");
				return;
			}
			try {
				imgDecor = ImageIO.read(fichDecor);
			} catch (IOException e) {
				System.out.println("Erreur de lecture d'images");
			}

		}
		g2d.drawImage(imgDecor, 0, 0, getWidth(), getHeight(), null);

		modele = new ModeleAffichage(getWidth(), getHeight(), largeurDuMonde);

		mat = modele.getMatMC();
		mat.translate(largeurDuMonde / 2, modele.getHautUnitesReelles() / 2);

		// échelle
		double nbm = 5000000.0;
		double dimin = 0.70;
		dessinerEchelle(g2d, mat, nbm, dimin);

		// collision
		if (murHautActive || murBasActive || murGaucheActive || murDroitActive) {
			gestionDeCollisionAvecMurs(modele.getLargUnitesReelles(), modele.getHautUnitesReelles());
		}

		try {

			for (Corps corps : ensembleDesCorps) {
				if (estAnime) {
					corps.dessinerTrajectoire(g2d, mat);

				}

				corps.dessiner(g2d, mat);
				if (Aapplication.estIsDessinVecteur()) {
					corps.dessinerVecteurRes(g2d, mat);
				}
				if (Aapplication.estIsDessinVecteurS()) {
					corpsClicker.dessinerVecteurRes(g2d, mat);
				}

			}

		} catch (ConcurrentModificationException e) {
		}

		if (murHautActive) {
			mur.dessiner(g2d, matSansTransfo);
		}
		if (murBasActive) {
			mur.dessinerMurBas(g2d, matSansTransfo);
		}
		if (murGaucheActive) {
			mur.dessinerMurGauche(g2d, matSansTransfo);
		}
		if (murDroitActive) {
			mur.dessinerMurDroite(g2d, matSansTransfo);
		}

	}// fin de la méthode

	// Jeanne
	/**
	 * Méthode de dessin simple permettant d'afficher uniquement les corps
	 * 
	 * @param g2d     Le contexte graphique
	 * @param mat     La matrice de transformation
	 * @param modele2
	 */
	public void dessiner(Graphics2D g2d, AffineTransform mat, double largUnitesReelles, double hautUnitesReelles) {

		try {

			for (Corps corps : ensembleDesCorps) {
				corps.dessiner(g2d, mat);
			}
		} catch (ConcurrentModificationException e) {
			// System.out.println("Coucou");
		}

		// collision
		if (murHautActive || murBasActive || murGaucheActive || murDroitActive) {
			gestionDeCollisionAvecMurs(largUnitesReelles, hautUnitesReelles);

		}
	}// fin de la méthode

	// jeanne
	/**
	 * Méthode récursive permettant de dessiner l'échelle sans qu'elle ne dépasse le
	 * cadre de la simulation
	 * 
	 * @param g2d   Le contexte graphique
	 * @param mat   La matrice de transformation
	 * @param nbkm2 Le nombre de metre à représenter sur l'échelle
	 * @param dimin Le facteur de diminution de l'échelle lorsqu'elle est trop
	 *              grande
	 */
	private void dessinerEchelle(Graphics2D g2d, AffineTransform mat2, double nbkm2, double dimin) {
		int margeX = 130;
		int margeY = 30;
		int moitie = 40;
		int marge = 20;
		int facteurDimin = 1000000;
		int facteurAugm = 2000000;
		g2d.setColor(Color.white);
		double nbkm = nbkm2;
		double diminu = dimin;

		double longueurEchelle = nbkm * modele.getPixelsParUniteX();
		double margeXmil = margeX - moitie; // milieu de la barre d'échelle

		if ((int) (longueurEchelle / 2) >= (margeXmil - margeY) && diminu > 0) {
			nbkm = (int) (nbkm - facteurDimin * dimin);
			diminu = diminu * diminu;
			dessinerEchelle(g2d, mat2, nbkm, dimin);

		} else if (longueurEchelle <= 30) {
			nbkm += facteurAugm;
			dessinerEchelle(g2d, mat2, nbkm, dimin);
		} else {

			g2d.drawString(nbkm / 1000.0 + " kilomètres", getWidth() - margeX, getHeight() - margeY);

			margeX -= moitie; // milieu de la barre d'échelle
			margeY += marge;

			int marge1x = (int) (margeXmil - longueurEchelle / 2);
			int marge2x = (int) (margeXmil + longueurEchelle / 2);
			g2d.drawLine(getWidth() - marge1x, getHeight() - margeY, getWidth() - marge2x, getHeight() - margeY);
		}
	}// fin de la méthode

	// Jeanne
	/**
	 * Méthode permettant de faire rebondir les corps sur les murs si il y a
	 * collision
	 * 
	 * @param largUnitesReelles La largeur de la simulation en unités réelles
	 * @param double            hautUnitesReelles La heuteur de la simulation en 
	 *                          unités réelles
	 * 
	 */
	private void gestionDeCollisionAvecMurs(double largUnitesReelles, double hautUnitesReelles) {
		for (Corps corps : ensembleDesCorps) {

			if (murHautActive) {
				if (corps.getPosition().getY() - corps.getRayon() <= -hautUnitesReelles / 2) {
					corps.rebondAvecMurHorizontal();
				}
			}

			if (murBasActive) {
				if (corps.getPosition().getY() + corps.getRayon() >= hautUnitesReelles / 2) {
					corps.rebondAvecMurHorizontal();
				}
			}

			if (murGaucheActive) {
				if (corps.getPosition().getX() - corps.getRayon() <= -largUnitesReelles / 2) {
					corps.rebondAvecMurVertical();
				}
			}

			if (murDroitActive) {
				if (corps.getPosition().getX() + corps.getRayon() >= largUnitesReelles / 2) {
					corps.rebondAvecMurVertical();
				}
			}

		}

	}// fin de la méthode

	// charlyne
	/**
	 * Permet de savoir si l'utilisateur clic sur un corps.
	 */
	private void ajouterGestionSouris() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// debut
				while (trouverPlanete == false && ensembleDesCorps.size() > nbCorps) {
					corps = ensembleDesCorps.get(nbCorps);

					if (corps.contient(e.getX(), e.getY(), mat)) {
						corpsClicker = corps;

						clickPlanete = true;
						trouverPlanete = true;
					} else {

						corps = null;
					}
					nbCorps++;
				}
				trouverPlanete = false;
				nbCorps = 0;
				// fin
			}// fin de la méthode
		});
	}// fin de la méthode

	// Jeanne
	/**
	 * Méthode permettant le mouvement dans la simulation
	 */
	public void run() {

		setTempsInitial(System.currentTimeMillis());
		while (estAnime) {

			setGrandeurListeAvant(ensembleDesCorps.size());

			try {
				corpsFusion = moteurPhysiqueCorps.unPasEuler(this, deltaT, collisionElastique);
			} catch (ConcurrentModificationException e) {

			}

			setGrandeurListeApres(ensembleDesCorps.size());
			if (corpsFusion != null) {
				if (corpsClicker == moteurPhysiqueCorps.getCorps1()
						|| corpsClicker == moteurPhysiqueCorps.getCorps2()) {
					corpsClicker = corpsFusion;
					;
				}
			} else {
				if (corpsClicker != null) {
					leverEvenPlanet(corpsClicker);
				}
			}
			recalculerLesForces();
			leverEvenEnergieCinetique();
			leverEvenTempsSimulation();
			leverEvenQuantiteMouv();
			leverEvenEnergiePotentielle();
			leverEvenEnergieTotale();
			leverEvenTempsReel();

			try {
				Thread.sleep(temps);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			tempsTotalEcoule += deltaT;

			setTempsFin(System.currentTimeMillis());
			repaint();
		}

	}// fin de la méthode

	// Jeanne
	/**
	 * Méthode permettant d'avancer la simulation pas à pas
	 */
	public void calculerUneIterationPhysique() {

		tempsTotalEcoule += deltaT;

		moteurPhysiqueCorps.unPasEuler(this, deltaT, collisionElastique);
		recalculerLesForces();
		repaint();
	}// fin de la méthode

	// Jeanne
	/**
	 * Méthode permettant de démarrer l'animation
	 */
	public void demarrer() {
		if (!estAnime) {
			Thread processusAnim = new Thread(this);
			processusAnim.start();
			estAnime = true;
		}
	}// fin de la méthode

	// Jeanne
	/**
	 * Méthode permettant d'arreter l'animation
	 */
	public void arreter() {
		estAnime = false;
	}// fin de la méthode

	// Jeanne
	// necessaire seulement avec le zoom qui bouge
	/*
	 * private void deplacerLeFocusSimulation(Vecteur v) { for (Corps c :
	 * ensembleDesCorps) { c.deplacer(v); }
	 * 
	 * }
	 */

	// Méthode entourant les corps
	// Jeanne
	public ArrayList<Corps> getEnsembleDesCorps() {
		return ensembleDesCorps;
	}// fin de la méthode

	// Jeanne
	/**
	 * Méthode permettant d'ajouter un corps à l'animation  
	 * 
	 * @param corps Le corps à ajouter à l'animation
	 */
	public void ajouterUnCorps(Corps corps) { // pas encore testé
		for (Corps c : ensembleDesCorps) {
			c.ajouterUneForce(c.attraction(corps));
			corps.ajouterUneForce(corps.attraction(c));
		}
		ensembleDesCorps.add(corps);
		// System.out.println("Nombre de corps : "+ensembleDesCorps.size());
		leverEvenZoneAnimation();
		leverEvenZoneMasse();
		repaint();
	}// fin de la méthode

	// Jeanne
	/**
	 * Méthode permettant de supprimer un corps particulier de l'animation
	 * 
	 * @param corps Le corps à supprimer de l'animation
	 */
	public void supprimerUnCorps(Corps corps) { // pas encore testé
		ensembleDesCorps.remove(corps);
		for (Corps c : ensembleDesCorps) {
			c.supprimerUneForce(c.attraction(corps));
		}
		leverEvenZoneAnimation();
		leverEvenZoneMasse();
		repaint();
		// recalculer les forces sur l'ensemble
	}// fin de la méthode

	// charlyne
	/**
	 * Permet d'ajouter les écouteurs de l'interface planetListener a la simulation
	 * 
	 * @param ecouteur ecouteur qu'on veut ajouter
	 */
	public void addPlanetListener(PlanetListener ecouteur) {
		listeEcouteursPlanete.add(ecouteur);
	}// fin de la méthode

	// charlyne
	/**
	 * Permet d'ajouter les écouteurs de l'interface zoneAnimationListener a la
	 * simulation
	 * 
	 * @param ecouteur
	 */
	public void addZoneAnimationListener(ZoneAnimationListener ecouteur) {
		listeEcouteurs.add(ecouteur);
	}// fin de la méthode

	// charlyne
	/**
	 * Permet de lever l'évenement des écouteurs dans l'interface
	 * zoneAnimationListener
	 */
	private void leverEvenZoneAnimation() {
		for (ZoneAnimationListener ecout : listeEcouteurs) {
			ecout.nombreCorpsUpdate(ensembleDesCorps.size());
		}
	}// fin de la méthode

	// charlyne
	/**
	 * Permet de lever l'évènement pour l'energie totale
	 */
	private void leverEvenEnergieTotale() {
		energieCT = 0;
		energiePotentielle = 0;
		for (Corps c : ensembleDesCorps) {
			energieCT = energieCT + c.getEnergieCinetique(c);
		}
		for (Corps c : ensembleDesCorps) {
			for (Corps a : ensembleDesCorps) {
				if (!(a.equals(c))) {
					energiePotentielle = energiePotentielle + c.getEnergiePotentielle(c, a);
				}
			}
		}
		energieT = energieCT + energiePotentielle;
		for (ZoneAnimationListener ecout : listeEcouteurs) {
			ecout.energieTotaleUpdate(energieT);
		}
	}// fin de la méthode

	// charlyne
	/**
	 * Permet de lever l'evenement des écouteurs pour afficher la masse totale du
	 * systeme
	 * 
	 */
	private void leverEvenZoneMasse() {
		nbCorps = 0;
		masseTotale = 0;
		while (nbCorps < ensembleDesCorps.size()) {
			masseTotale = masseTotale + ensembleDesCorps.get(nbCorps).getMasse();
			nbCorps++;
		}
		for (ZoneAnimationListener ecout : listeEcouteurs) {
			ecout.masseTotaleUpdate(masseTotale);
		}
	}// fin de la méthode

	// charlyne
	/**
	 * Permet d'afficher le temps de simulation
	 */
	private void leverEvenTempsSimulation() {
		for (ZoneAnimationListener ecout : listeEcouteurs) {
			ecout.tempsSimulationUpdate(getTempsTotalEcoule());
		}
	}// fin de la méthode

	// charlyne
	/**
	 * Permet d'afficher la quantité de mouvement totale lorsqu'elle change
	 */
	private void leverEvenQuantiteMouv() {
		quantiteMouv = 0;
		for (Corps c : ensembleDesCorps) {
			quantiteMouv = quantiteMouv + c.getQuantiteDeMouvement().getModule();
		}
		for (ZoneAnimationListener ecout : listeEcouteurs) {
			ecout.quantiteMouvTotaleUpdate(quantiteMouv); // a changer
		}
	}// fin de la méthode

	// charlyne
	/**
	 * Permet d'afficher l'énergie cinétique totale lorsqu'elle change
	 */
	private void leverEvenEnergieCinetique() {
		energieCT = 0;
		for (Corps c : ensembleDesCorps) {
			energieCT = energieCT + moteurPhysiqueCorps.energieCinetique(c);
		}
		for (ZoneAnimationListener ecout : listeEcouteurs) {
			ecout.cinétiqueTotaleUpdate(energieCT);
		}
	}// fin de la méthode

	// charlyne
	private void leverEvenEnergiePotentielle() {
		energiePotentielle = 0;
		for (Corps c : ensembleDesCorps) {
			for (Corps a : ensembleDesCorps) {
				if (!(c.equals(a))) {
					energiePotentielle = energiePotentielle + c.getEnergiePotentielle(c, a);
				}
			}
		}
		for (ZoneAnimationListener ecout : listeEcouteurs) {
			ecout.potentielleTotaleUpdate(energiePotentielle);
		}
	}// fin de la méthode

	// charlyne
	private void leverEvenTempsReel() {
		for (ZoneAnimationListener ecout : listeEcouteurs) {
			ecout.tempsReelUpdate(System.currentTimeMillis());
		}
	}

	// charlyne
	/**
	 * Permet de lever l'évenement des écouteurs dans l'interface planetListener
	 */
	private void leverEvenPlanet(Corps corps) {
		energiePotentielle = 0;
		for (Corps c : ensembleDesCorps) {
			if (!(c.equals(corps))) {
				energiePotentielle = energiePotentielle + corps.getEnergiePotentielle(corps, c);
			}
		}
		try {
			for (PlanetListener ecout : listeEcouteursPlanete) {
				ecout.clickPlanet(corps.getMasse(), corps.getAcceleration().getModule(), corps.getRayon(),
						corps.getVitesse().getModule(), trouverPlanete, corps.getEnergieCinetique(corps),
						energiePotentielle);
			}
		} catch (ConcurrentModificationException e) {
			System.out.println("Erreur : Parcours de la liste d'écouteurs");
		}

	}// fin de la méthode

	// Jeanne
	/**
	 * Méthode permettant de recalculer toutes les forces sur tous les corps
	 */
	public void recalculerLesForces() {
		supprimerToutesLesForcesDuSysteme();
		for (Corps c1 : ensembleDesCorps) {
			for (Corps c2 : ensembleDesCorps) {
				if (!c1.equals(c2)) {
					c1.ajouterUneForce(c1.attraction(c2));
				}
			}
		}

	}// fin de la méthode

	// Jeanne
	/**
	 * Méthode permettant de supprimer toutes les forces sur tous les corps
	 */
	public void supprimerToutesLesForcesDuSysteme() {
		for (Corps c1 : ensembleDesCorps) {
			c1.supprimerToutesLesForces();
		}
	}// fin de la méthode

	// Jeanne
	/**
	 * Méthode permettant de supprimer tous les corps de la simulation
	 */
	public void supprimerTousLesCorps() {
		ensembleDesCorps.clear();
		leverEvenZoneAnimation();
		leverEvenZoneMasse();
		repaint();
	}// fin de la méthode

	// Xavier
	/**
	 * Méthode permetant de gérer les fonctionnaliter de déplacement de position, de
	 * Force extérieur appliqué sur une planete et du boutton efface. Elle sert de
	 * filtre pour savoir quelle instruction suivre.
	 * 
	 */
	public void gestionnaireDePositionDeForceExterieurEtDEfface() {
		// debut Xavier
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actionSurCorps.exploser(Simulation.this, corps, e);

				while (trouverPlanete == false && ensembleDesCorps.size() > nbCorps) {
					corps = ensembleDesCorps.get(nbCorps);
					if (corps.contient(e.getX(), e.getY(), mat)) {
						clickPlanete = true;
						corps = ensembleDesCorps.get(nbCorps);
						trouverPlanete = true;

						if (Aapplication.estPretASupprimerValue()) {
							actionSurCorps.effacerCorps(Simulation.this, corps);
							repaint();
						}
					}
					nbCorps++;
				}
				trouverPlanete = false;
				nbCorps = 0;
			}// fin de la méthode

			public void mouseReleased(MouseEvent e) {
				corps = null;
				// JOptionPane.showMessageDialog(null,"Corps null tire");
			}// fin de la méthode

		});

		addMouseMotionListener(new MouseAdapter() {//
			public void mouseDragged(MouseEvent b) {
				ajouterGestionSouris();
				if (Simulation.this.estAnime) {
					actionSurCorps.tirer(Simulation.this, corps, b, modele);
				} else {
					actionSurCorps.positionDuCorps(Simulation.this, corps, b, modele);
				}
			}// fin de la méthode
		});

		// finXavier
	}// fin de la méthode

	// getter et setter

	// jeanne
	/**
	 * 
	 * @return
	 */
	public boolean isEstAnime() {
		return estAnime;
	}// fin de la méthode

	// jeanne
	/**
	 * 
	 * @param estAnime
	 */
	public void setEstAnime(boolean estAnime) {
		this.estAnime = estAnime;
	}// fin de la méthode

	// jeanne
	public double getTempsTotalEcoule() {
		return tempsTotalEcoule;
	}// fin de la méthode

	// jeanne
	public void setTempsTotalEcoule(double tempstotalEcoule) {
		this.tempsTotalEcoule = tempstotalEcoule;
	}// fin de la méthode

	// Charlyne
	public void setTempsAnim(PasIteration pas) {
		deltaT = pas.getPas();
		repaint();
	}// fin de la méthode

	// Charlyne
	public boolean getClickPlanete() {
		return clickPlanete;
	}// fin de la méthode

	// charlyne
	public double getMasseSysteme() {
		return masseTotale;
	}// fin de la méthode

	// Charlyne
	public AffineTransform getMat() {
		return mat;
	}// fin de la méthode

	// charlyne
	public Corps getcorpsClicker() {
		return corpsClicker;
	}// fin de la méthode

	// Jeanne
	public boolean isMurHautActive() {
		return murHautActive;
	}// fin de la méthode

	// Jeanne
	public void setMurHautActive(boolean murHautActive) {
		this.murHautActive = murHautActive;
	}// fin de la méthode

	// Jeanne
	public boolean isMurBasActive() {
		return murBasActive;
	}// fin de la méthode

	// Jeanne
	public void setMurBasActive(boolean murBasActive) {
		this.murBasActive = murBasActive;
	}// fin de la méthode

	// Jeanne
	public boolean isMurGaucheActive() {
		return murGaucheActive;
	}// fin de la méthode

	// Jeanne
	public void setMurGaucheActive(boolean murGaucheActive) {
		this.murGaucheActive = murGaucheActive;
	}// fin de la méthode

	// Jeanne
	public boolean isMurDroitActive() {
		return murDroitActive;
	}// fin de la méthode

	// Jeanne
	public void setMurDroitActive(boolean murDroitActive) {
		this.murDroitActive = murDroitActive;
	}// fin de la méthode

	// charlyne
	public void setCollisionElastique(boolean collision) {
		collisionElastique = collision;
	}// fin de la méthode

	public int getGrandeurListeAvant() {
		return grandeurListeAvant;
	}// fin de la méthode

	public void setGrandeurListeAvant(int grandeurListeAvant) {
		this.grandeurListeAvant = grandeurListeAvant;
	}// fin de la méthode

	public int getGrandeurListeApres() {
		return grandeurListeApres;
	}// fin de la méthode

	public void setGrandeurListeApres(int grandeurListeApres) {
		this.grandeurListeApres = grandeurListeApres;
	}// fin de la méthode

	public long getTempsInitial() {
		return tempsInitial;
	}// fin de la méthode

	public void setTempsInitial(long tempsInitial) {
		this.tempsInitial = tempsInitial;
	}// fin de la méthode

	public long getTempsFin() {
		return tempsFin;
	}// fin de la méthode

	public void setTempsFin(long tempsFin) {
		this.tempsFin = tempsFin;
	}// fin de la méthode

}// fin de la classe
