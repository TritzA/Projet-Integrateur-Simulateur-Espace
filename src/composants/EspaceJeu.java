package composants;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import aapplication.Jeu;
import ecouteurs.BlobListener;
import ecouteurs.ZoneAnimationListener;
import fichier.GestionFichier;
import lecture.Son;
import objetdessinable.BarreDeMasse;
import objetdessinable.Blob;
import objetdessinable.Corps;
import physique.MoteurPhysiqueCorps;
import physique.Vecteur;
import utilitaire.Fenetre;

/**
 * Classe permettant l'animation du jeu
 * 
 * @author Jeanne Castonguay 
 * @author Charlyne Lefrancois
 *
 *
 */
public class EspaceJeu extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private Simulation simulation;
	private boolean estAnime = false;
	private boolean premierefois = true;
	private int largeurDuMonde;
	private ModeleAffichage modele;
	private AffineTransform mat;
	private double tempsEcoulé;
	private double quantiteMouv;
	private double eCinetiqueBlob;
	private double eCinetiqueT;
	private double eTotale;
	private int nb;
	private Blob blob = new Blob();
	private BarreDeMasse barre = new BarreDeMasse(new Vecteur(50, 50), 1, 2.01E10); // a
	private Vecteur posSouri = new Vecteur();
	private boolean blobImobile = false;
	private double echelle = 10;
	private double deltaT = 5;
	private long temps = 10;
	private ArrayList<BlobListener> listeEcouteurs = new ArrayList<BlobListener>();
	private ArrayList<ZoneAnimationListener> listeEcouteursZone = new ArrayList<ZoneAnimationListener>();
	private MoteurPhysiqueCorps moteurPhysiqueCorps = new MoteurPhysiqueCorps();
	private boolean perdu;
	private double energiePotentielle;
	private Jeu jeu;
	private final double RATIO_INITIAL = 180000;
	private boolean victoire = false;
	private int compteurFinJeu = 0;
	private final int finComteur = 2;
	private Image imgDecor = null;

	// Jeanne
	/**
	 * Constructeur permettant d'instancier un espace de jeu avec une simulation en
	 * paramètre, donc un ensemble de corps
	 * 
	 * @param simul La simulation en paramètre
	 * @param simul La simulation en paramètre
	 */
	public EspaceJeu(Simulation simul, Jeu jeu) {
		this.jeu = jeu;
		setSize(1330, 650);
		setBackground(Color.DARK_GRAY);
		simulation = simul;
		double masseTotale = simul.getMasseSysteme();
		barre = new BarreDeMasse(new Vecteur(50, 50), 1 // n'est pas la vrai masse du blob car je crois qu'on doit
		// négliger le blob
				, masseTotale);
		simul.getEnsembleDesCorps().size();

		simulation.setMurGaucheActive(true);
		simulation.setMurBasActive(true);
		simulation.setMurDroitActive(true);
		simulation.setMurHautActive(true);
		
	}

	// Jeanne
	/**
	 * Constructeur permettant d'instancier un espace de jeu avec un fichier
	 * contenant une simulation en paramètre, donc un ensemble de corps
	 * 
	 * @param gf Une fichier contenant une simulation
	 */
	public EspaceJeu(GestionFichier gf) {
		setSize(1330, 650);
		setBackground(Color.DARK_GRAY);

		// gf.loadFile();--------------------------------------------------------

		// je sais pas quoi faire après
		// barre = new BarreDeMasse(new Vecteur(50, 50), blob.getMasse(),

		// À FINIR !
	}

	// Jeanne
	/**
	 * Méthode permettant de d'illustrer le jeu
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// dessiner bar de masse
		AffineTransform matSansTransfo = g2d.getTransform();

		if (premierefois) {
			largeurDuMonde = (int) (getWidth() * RATIO_INITIAL);
			premierefois = false;

			// Antonin Tritz
			URL fichDecor = getClass().getClassLoader().getResource("jeu.jpg");
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

		blob.dessiner(g2d, mat);
		simulation.dessiner(g2d, mat, modele.getLargUnitesReelles(), modele.getHautUnitesReelles());

		barre.dessiner(g2d, matSansTransfo);

		if (getNbCorps() == 0) {
				compteurFinJeu++;
			
			if (jeu.getNiveau() == 3) {
				barre.dessinerFinJeu(g2d, matSansTransfo);
			} else {
				barre.dessinerVictoire(g2d, matSansTransfo);
			}
			Son.soundVictoire();
			estAnime = false;
			victoire = true;
		} else if (perduOuPas() == true) {
			barre.dessinerPerdu(g2d, matSansTransfo);
			Son.soundGameOver();
			estAnime = false;
		}

		addMouseMotionListener(new MouseMotionAdapter() { // permet au blob de suivre la souri
			public void mouseMoved(MouseEvent e) {

				if (echelle != 1.0 / modele.getPixelsParUniteX()) {
					echelle = 1.0 / modele.getPixelsParUniteX();
				}

				double x = (e.getX() - getWidth() / 2) * echelle;
				double y = (e.getY() - getHeight() / 2) * echelle;
				posSouri = new Vecteur(x, y);

			}
		});

		if (victoire == true && (compteurFinJeu == finComteur)) {
			Fenetre.retourSiVictoire(jeu);
		}

	}

	// Jeanne
	/**
	 * Méthode permettant l'animation du jeu
	 */
	public void run() {
		while (estAnime) {
			try {
				moteurPhysiqueCorps.unPasEuler(simulation, deltaT, false);
			} catch (ConcurrentModificationException e) {
			}

			try {
				simulation.recalculerLesForces();
			} catch (ConcurrentModificationException e) {

			}

			deplacerBlobVersSouris();

			leverEvenBlobUpdate();
			leverEvenZoneAnimationUpdate();

			try {
				Thread.sleep(temps);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			tempsEcoulé += deltaT;
			repaint();
		}

	}

	// Jeanne
	/**
	 * Méthode permettant d'avancer la simulation pas à pas
	 */

	public void calculerUneIterationPhysique() {
		demarrer();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		arreter();
		repaint();

	}

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
	}

	// Jeanne
	/**
	 * Méthode permettant d'arreter l'animation
	 */
	public void arreter() {
		estAnime = false;
	}

	// jeanne
	/**
	 * Méthode permettant d'analyser si le blob est en collision avec un corps et
	 * ajuster sa masse en conséquence
	 */
	private void gestionCollisionBlobCorps() {
		int k = simulation.getEnsembleDesCorps().size();
		for (int i = 0; i < k; i++) {
			Corps corps = simulation.getEnsembleDesCorps().get(i);
			if (distanceBlobAvec(corps) < blob.getRayon() + corps.getRayon()) { // il y a collision
				if (blob.getVolume() > corps.getVolume()) { // le blob est plus volumique que le corps
					// System.out.println("MANGER CORPS");
					mangerCorps(corps);
					// System.out.println("masse du blob apres" + blob.getMasse());
					barre.setMasseBlob(blob.getMasse() - blob.getMasseInitiale());
					k--;
				} else { // le blob est moins volumique que le corps
					donnerMasseA(corps);
				}
			}
		}
	}

	// jeanne
	/**
	 * Méthode permettant au blob de perdre de la masse lorsqu'il colle un gros
	 * corps
	 * 
	 * @param corps Le gros corps avec lequel le blob est en contact
	 */
	private void donnerMasseA(Corps corps) {
		// admettons le blob perd 10% de sa masse par run collé a un gros corps
		double masse = blob.getMasse() * 0.04;
		if (blob.getRayon() < 1 * echelle) { // condition pour voir si le blob fait moins qu'un pixel de rayon
			// perdu = true;
		} else {
			corps.ajouterMasse(masse);
			blob.perdreMasse(masse);
			barre.setMasseBlob(blob.getMasse() - blob.getMasseInitiale());
			Son.soundMouth();
			blobImobile = true;
		}
	}

	// jeanne
	/**
	 * Méthode permettant au blob d'absorber un corps plus petit que lui lorsqu'ils
	 * entrent en contact
	 * 
	 * @param c Le petit corps à absorber
	 */
	private void mangerCorps(Corps c) {
		// System.out.println("masse du blob avant" + blob.getMasse());
		blob.ajouterMasse(c.getMasse());
		// System.out.println("masse du blob apres" + blob.getMasse());
		barre.setMasseBlob(blob.getMasse() - blob.getMasseInitiale());
		Son.soundSlurp();
		simulation.supprimerUnCorps(c);

	}

	// jeanne
	/**
	 * Méthode donnant la distance entre le blob et un corps
	 * 
	 * @param c Un corps
	 * @return La distance entre le blob et un corps
	 */
	private double distanceBlobAvec(Corps c) {

		return (blob.getPosition().additionner(new Vecteur()).soustraire(c.getPosition()))
				.getModule();
	}

	// jeanne
	/**
	 * Méthode permettant au blob de suivre la souris
	 */
	private void deplacerBlobVersSouris() {
		gestionCollisionBlobCorps();
		if (!blobImobile) {
			blob.deplacerVers(posSouri);
		} else {
			blobImobile = false;
		}
	}

	// charlyne
	public Blob getBlob() {
		return blob;
	}

	// charlyne
	/**
	 * Méthode qui permet de savoir si le joueur a perdu
	 * 
	 * @return un boolean qui dit si le joueur a perdu ou pas
	 */
	public boolean perduOuPas() {
		nb = 0;
		perdu = false;
		System.out.println("NOMBREFWDWWF  "+simulation.getEnsembleDesCorps().size() +"    "+ simulation.getEnsembleDesCorps().get(0).toString());
		
		for (int i = 0 ; i< simulation.getEnsembleDesCorps().size();i++) {
			if (simulation.getEnsembleDesCorps().get(i).getRayon() > blob.getRayon()) {
				nb++;
			}
		}
		if (nb == simulation.getEnsembleDesCorps().size()) {
			perdu = true;
		}
		return perdu;
	}

	// charlyne
	public int getNbCorps() {
		return simulation.getEnsembleDesCorps().size();
	}

	// charlyne
	/**
	 * Permet d'ajouter les écouteurs de l'interface blobListener a l'espace de jeu
	 * 
	 * @param ecouteur ecouteur qu'on veut ajouter
	 */
	public void addBlobListener(BlobListener ecouteur) {
		listeEcouteurs.add(ecouteur);
	}

	// charlyne
	/**
	 * Permet d'ajouter les écouteurs de l'interface ZoneAnimationlistener a
	 * l'espace de jeu
	 * 
	 * @param ecouteur ecouteur qu'on veut ajouter
	 */
	public void addZoneAnimationListener(ZoneAnimationListener ecouteur) {
		listeEcouteursZone.add(ecouteur);
	}

	// charlyne
	/**
	 * Permet de lever l'evenement des écouteurs pour afficher la masse totale du
	 * systeme
	 * 
	 * 
	 */
	private void leverEvenBlobUpdate() {
		energiePotentielle = 0;
		for (Corps c : simulation.getEnsembleDesCorps()) {
			energiePotentielle = energiePotentielle + blob.getEnergiePotentielle(blob, c);
		}
		for (BlobListener ecout : listeEcouteurs) {
			ecout.blobChange(blob.getMasse(), blob.getVitesse().getModule(), blob.getRayon(),
					blob.getEnergieCinetique(blob), energiePotentielle, 0);// ici il manque energie cinetique,
			// potentielle et
			// acceleration
		}
	}

	// charlyne
	/**
	 * Permet d'afficher les différents paramètres des éléments qui changent dans le
	 * jeu
	 */
	private void leverEvenZoneAnimationUpdate() {
		eCinetiqueT = 0;
		eCinetiqueBlob = blob.getEnergieCinetique(blob);
		energiePotentielle = 0;
		for (Corps c : simulation.getEnsembleDesCorps()) {
			eCinetiqueT = eCinetiqueT + moteurPhysiqueCorps.energieCinetique(c);
		}

		eCinetiqueT = eCinetiqueT + eCinetiqueBlob;

		quantiteMouv = 0;
		for (Corps c : simulation.getEnsembleDesCorps()) {
			quantiteMouv = quantiteMouv + c.getQuantiteDeMouvement().getModule();
		}
		quantiteMouv = quantiteMouv + blob.getQuantiteDeMouvement().getModule();
		energiePotentielle = 0;
		for (Corps c : simulation.getEnsembleDesCorps()) {
			for (Corps a : simulation.getEnsembleDesCorps()) {
				if (!(c.equals(a))) {
					energiePotentielle = energiePotentielle + c.getEnergiePotentielle(c, a);
				}
			}
			energiePotentielle = energiePotentielle + blob.getEnergiePotentielle(blob, c);
		}
		eTotale = energiePotentielle + eCinetiqueT;

		for (ZoneAnimationListener ecout : listeEcouteursZone) {
			ecout.zoneAnimationUpdate(simulation.getEnsembleDesCorps().size() + 1, eTotale, eCinetiqueT,
					energiePotentielle, quantiteMouv, simulation.getMasseSysteme() / (5.972 * 10e24),
					System.currentTimeMillis(), tempsEcoulé);
		}
	}
}
