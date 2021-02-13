package objetdessinable;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import interfaces.Dessinable;
import physique.MoteurPhysiqueCorps;
import physique.Vecteur;

/**
 * Classe représentant les corps spaciaux
 * 
 * @author Jeanne Castonguay
 * @author Charlyne Lefrancois
 * @author Antonin Tritz
 */
public class Corps implements Dessinable {

	private Ellipse2D.Double cercle = null;
	private Vecteur position = new Vecteur(), acceleration = new Vecteur(), vitesse = new Vecteur(),
			forceResultante = new Vecteur();
	private double masse, rayon;
	private Color couleur = Color.PINK;
	private ArrayList<Vecteur> ensembleDesForces = new ArrayList<Vecteur>();
	private Trajectoire trajectoire = new Trajectoire();
	private DessinVecteur dessinVecteur;
	private boolean explosable = false;
	private MoteurPhysiqueCorps moteurPhysiqueCorps = new MoteurPhysiqueCorps();
	private String nomImage = "";
	Image imgLue = null;
	private boolean estImage = false;
	Image imgRedim = null;

	// Jeanne Castonguay
	/**
	 * Constructeur permettant de créer un corps pouvant être représenté dans la
	 * simulation
	 * 
	 * @param position Le vecteur position en mètre
	 * @param masse    La masse du corps en kilogramme
	 * @param rayon    Le rayon du corps en mètre
	 */
	public Corps(Vecteur position, double masse, double rayon) {
		this.position = position;
		this.masse = masse;
		this.rayon = rayon;
	}// fin du constructeur

	// Jeanne Castonguay - constructeur avec densité pour avoir un visuel
	// proportionnel a la masse
	/**
	 * Constructeur permettant de créer un corps pouvant être représenté dans la
	 * simulation et ayant une couleur précise
	 * 
	 * @param position Le vecteur position en mètre
	 * @param masse    La masse du corps en kilogramme
	 * @param couleur  La couleur du corps
	 * @param densite  La densité du corps
	 */
	public Corps(Vecteur position, double masse, Color couleur, double densite) {
		this.position = position;
		this.masse = masse;
		double b = 1.0 / 3.0;
		this.rayon = Math.pow(3.0 * (masse / densite) / (4.0 * Math.PI), b);
		// this.echelle = echelle;
		this.couleur = couleur;
	}// fin du constructeur

	// Antonin Triz
	/**
	 * Permet la construction d'un corps à partir de paramètres d'entrés
	 * 
	 * @param position   Position initial du corps construit
	 * @param masse      Masse initial du corps construit
	 * @param rayon      Rayon initial du corps construit
	 * @param couleur    Couleur initial du corps construit
	 * @param explosable Explosable initial du corps construit
	 */
	public Corps(Vecteur position, double masse, double rayon, Color couleur, boolean explosable) {
		this.position = position;
		this.masse = masse;
		this.rayon = rayon;
		this.couleur = couleur;
		this.explosable = explosable;
	}// fin du constructeur

	// Jeanne
	/**
	 * Permet la construction d'un corps représenté par une image à partir de
	 * paramètres d'entrés
	 * 
	 * @param position   Position initial du corps construit
	 * @param masse      Masse initial du corps construit
	 * @param rayon      Rayon initial du corps construit
	 * @param nomImage   Le nom de l'image représentant le corps construit
	 * @param explosable Explosable initial du corps construit
	 */
	public Corps(Vecteur position, double masse, double rayon, String nomImage, boolean explosable) {
		this.position = position;
		this.masse = masse;
		this.rayon = rayon;
		this.nomImage = nomImage;
		this.estImage = true;
		this.explosable = explosable;

		java.net.URL urlImage = getClass().getClassLoader().getResource(nomImage);
		if (urlImage == null) {
			JOptionPane.showMessageDialog(null, "Fichier introuvable");
		}
		try {
			imgLue = ImageIO.read(urlImage);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur pendant la lecture du fichier d'image");
		}

	}// fin du constructeur

	// Jeanne Castonguay
	/**
	 * Constructeur permettant de créer un corps pouvant être représenté dans la
	 * simulation et ayant une image
	 * 
	 * @param position Le vecteur position en mètre
	 * @param masse    La masse du corps en kilogramme
	 * @param couleur  La couleur du corps
	 * @param densite  La densité du corps
	 * @param image    Le nom de l'image associé au corps
	 */
	public Corps(Vecteur position, double masse, Color couleur, double densite, String image) {
		this.position = position;
		this.masse = masse;
		double b = 1.0 / 3.0;
		this.rayon = Math.pow(3.0 * (masse / densite) / (4.0 * Math.PI), b);
		this.couleur = couleur;

		this.nomImage = image;
		estImage = true;
		java.net.URL urlImage = getClass().getClassLoader().getResource(nomImage);
		if (urlImage == null) {
			JOptionPane.showMessageDialog(null, "Fichier introuvable");
		}
		try {
			imgLue = ImageIO.read(urlImage);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur pendant la lecture du fichier d'image");
		}

	}// fin du constructeur

	// Antonin Triz
	/**
	 * Permet la construction d'un corps à partir de paramètres d'entrés
	 * 
	 * @param position   Position initial du corps construit
	 * @param masse      Masse initial du corps construit
	 * @param rayon      Rayon initial du corps construit
	 * @param explosable Explosion initial du corps construit
	 */
	public Corps(Vecteur position, double masse, double rayon, boolean explosable) {
		this.position = position;
		this.masse = masse;
		this.rayon = rayon;
		this.explosable = explosable;
	}// fin du constructeur

	// Antonin Triz
	/**
	 * Permet la construction d'un corps à partir d'un autre
	 * 
	 * @param corpsExplosable Le corps servant à en construire un autre indentique
	 */
	public Corps(Corps corpsExplosable) {
		this.position = corpsExplosable.getPosition();
		this.masse = corpsExplosable.getMasse();
		this.rayon = corpsExplosable.getRayon();
		this.couleur = corpsExplosable.getCouleur();
		this.explosable = corpsExplosable.estExplosable();
	}// fin du constructeur

	// Jeanne
	/**
	 * Permet la construction d'un corps représenté par une image à partir de
	 * paramètres d'entrés
	 * 
	 * @param position   Position initial du corps construit
	 * @param masse      Masse initial du corps construit
	 * @param rayon      Rayon initial du corps construit
	 * @param nomImage   Le nom de l'image représentant le corps construit
	 * @param explosable Explosable initial du corps construit
	 */
	public Corps(Vecteur position, double masse, double rayon, Image image, boolean explosable) {
		this.position = position;
		this.masse = masse;
		this.rayon = rayon;
		this.imgLue = image;
		this.estImage = true;
		this.explosable = explosable;

	}// fin du constructeur

	// Jeanne
	/**
	 * Méthode permettant de dessiner le corps
	 * 
	 * @param g2d Le contexte graphique
	 * @param mat La matrice de transformation
	 */
	public void dessiner(Graphics2D g2d, AffineTransform mat) {
		AffineTransform matIni = g2d.getTransform();

		Color colorIni = g2d.getColor();

		cercle = new Ellipse2D.Double(position.getX() - rayon, position.getY() - rayon, 2 * rayon, 2 * rayon);

		if (!estImage) {
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setColor(couleur);

			g2d.fill(mat.createTransformedShape(cercle));
		} else {
			// dessin image

			double c = (double) rayon * 2 * mat.getScaleX();

			imgRedim = imgLue.getScaledInstance((int) (c), (int) (c), Image.SCALE_SMOOTH);

			g2d.translate(mat.getTranslateX(), mat.getTranslateY());

			g2d.drawImage(imgRedim, (int) ((position.getX() - rayon) * mat.getScaleX()),
					(int) ((position.getY() - rayon) * mat.getScaleY()), null);

		}
		g2d.setColor(colorIni);
		g2d.setTransform(matIni);

	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet d'ajouter une point de plus à la treajectoire
	 * 
	 * @param g2d Le contexte graphique
	 * @param mat La matrice de transformation
	 */
	public void dessinerTrajectoire(Graphics2D g2d, AffineTransform mat) {
		trajectoire.ajouterPoint(g2d, mat, this);
	}// fin de la méthode

	// Xavier Boucher
	/**
	 * parmet de dessiner les vecteurs de forces résultantes
	 * 
	 * @param g2d Le contexte graphique
	 * @param mat la matrice de transformation
	 */
	public void dessinerVecteurRes(Graphics2D g2d, AffineTransform mat) {
		dessinVecteur = new DessinVecteur(this);
		dessinVecteur.dessiner(g2d, mat);
	}// fin de la méthode

	// Charlyne Lefrancois
	/**
	 * Méthode qui permet de savoir si on clic sur un objet en particulier
	 * 
	 * @param xPixel la coordonnée de x en pixel
	 * @param yPixel la coordonnée de y en pixel
	 * @param modele Matrice de transformation en mode réel
	 * @return une valeur true si on clic sur l'objet et false pour le contraire
	 */
	public boolean contient(int xPixel, int yPixel, AffineTransform mat) {
		// System.out .println(mat.toString());
		if (cercle != null) {
			Area aireEquiv = aireEnPixels(mat);
			return (aireEquiv.contains(xPixel, yPixel));
		} else {
			return false;
		}

	}// fin de la méthode

	// Charlyne Lefrancois
	/**
	 * Generer une aire unique qui represente l'ensemble de la geometrie incluant
	 * ses transformations
	 * 
	 * @param modele Matrice de transformation en mode réel
	 * @return L'aire qui represente l'ensemble de la géométrie
	 */
	public Area aireEnPixels(AffineTransform mat) {
		AffineTransform matLocale = new AffineTransform(mat);

		Area aireTotale = new Area(matLocale.createTransformedShape(cercle));

		matLocale.translate(rayon, 0); // appliquer la transfo sur la copie locale
		aireTotale.add(new Area(matLocale.createTransformedShape(cercle)));

		return aireTotale;
	}// fin de la méthode

	// Méthodes concernant les FORCES

	// Jeanne Castonguay
	/**
	 * Méthode permettant de calculer la force résultante exercée sur un corps
	 */
	private void calculerForceRes() {
		forceResultante = new Vecteur();
		for (Vecteur vecteur : ensembleDesForces) {
			forceResultante = forceResultante.additionner(vecteur);
		}
	}// fin de la méthode

	// Jeanne Castonguay
	/**
	 * Méthode permettant d'ajouter une nouvelle force à appliquer sur un corps
	 * 
	 * @param vec La force à ajouter
	 */
	public void ajouterUneForce(Vecteur vec) {
		ensembleDesForces.add(vec);
		calculerForceRes();
	}// fin de la méthode

	// Jeanne Castonguay
	/**
	 * Méthode permettant de supprimer une force appliquée sur un corps
	 * 
	 * @param vec La force à ajouter
	 */
	public void supprimerUneForce(Vecteur vec) {
		ensembleDesForces.remove(vec);
		calculerForceRes();
	}// fin de la méthode

	// Jeanne Castonguay
	/**
	 * Méthode permettant de supprimer toutes les forces appliquées sur un corps
	 */
	public void supprimerToutesLesForces() {
		ensembleDesForces.clear();
		calculerForceRes(); // pourra etre changé pour forceRes = vecteur() (donc un vecteur null)
	}// fin de la méthode

	// Méthodes concernant les INTERACTIONS

	// Jeanne Castonguay
	/**
	 * Méthode permettant d'obtenir le vecteur correspondant a la force d,attraction
	 * du présent corps sur un autre
	 * 
	 * @param corps L'autre corps
	 * @return La force d'attraction d'un corps sur un autre
	 */
	public Vecteur attraction(Corps corps) {
		return moteurPhysiqueCorps.forceAttractionSurCorps1(this, corps);
	}// fin de la méthode

	// Jeanne
	/**
	 * Méthode permettant à un corps de rebondir sur un mur horizontal
	 */
	public void rebondAvecMurHorizontal() {
		vitesse = new Vecteur(vitesse.getX(), -vitesse.getY());
	}// fin de la méthode

	// Jeanne
	/**
	 * Méthode permettant à un corps de rebondir sur un mur horizontal
	 */
	public void rebondAvecMurVertical() {
		vitesse = new Vecteur(-vitesse.getX(), vitesse.getY());
	}// fin de la méthode

	// Jeanne
	/**
	 * Méthode permettant de déplacer un corps d'une certaine distance dans une
	 * certaine position
	 * 
	 * @param v Le vecteur de déplacement du corps
	 */
	public void deplacer(Vecteur v) {
		position = position.additionner(v);
	}// fin de la méthode

	// charlyne
	public double getEnergieCinetique(Corps corps) {
		return moteurPhysiqueCorps.energieCinetique(corps);
	}// fin de la méthode

	// charlyne
	public double getEnergiePotentielle(Corps corps1, Corps corps2) {
		return moteurPhysiqueCorps.energiePotentielleSur1(corps1, corps2);
	}// fin de la méthode

	// getter et setter par Jeanne Castonguay
	/**
	 * 
	 * @return
	 */
	public Vecteur getForceResultante() {
		calculerForceRes();
		return forceResultante;
	}// fin de la méthode

	/**
	 * 
	 * @return
	 */
	public Vecteur getPosition() {
		return position;
	}// fin de la méthode

	/**
	 * 
	 * @param position
	 */
	public void setPosition(Vecteur position) {
		this.position = position;
	}// fin de la méthode

	/**
	 * 
	 * @return
	 */
	public Vecteur getAcceleration() {
		return acceleration;
	}// fin de la méthode

	/**
	 * 
	 * @param acceleration
	 */
	public void setAcceleration(Vecteur acceleration) {
		this.acceleration = acceleration;
	}// fin de la méthode

	/**
	 * 
	 * @return
	 */
	public Vecteur getVitesse() {
		return vitesse;
	}// fin de la méthode

	/**
	 * 
	 * @param vitesse
	 */
	public void setVitesse(Vecteur vitesse) {
		this.vitesse = vitesse;
	}// fin de la méthode

	/**
	 * 
	 * @return
	 */
	public double getMasse() {
		return masse;
	}// fin de la méthode

	/**
	 * 
	 * @param masse
	 */
	public void setMasse(double masse) {
		this.masse = masse;

	}// fin de la méthode

	// ou bien

	public void ajouterMasse(double masse) {
		this.masse += masse;

	}// fin de la méthode

	/**
	 * 
	 * @return
	 */
	public double getRayon() {
		return rayon;
	}// fin de la méthode

	/**
	 * 
	 * @return
	 */
	public Color getCouleur() {
		return couleur;
	}// fin de la méthode

	// Jeanne
	/**
	 * Méthode retournant la quantité de mouvement du corps résultant de la
	 * multiplication de la masse avec la vitesse
	 * 
	 * @return Le vecteur quantité de mouvement
	 */
	public Vecteur getQuantiteDeMouvement() {
		return vitesse.multiplier(masse);

	}// fin de la méthode

	/**
	 * 
	 * @param couleur
	 */
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}// fin de la méthode

	/**
	 * 
	 * @param rayon
	 */
	public void setRayon(double rayon) {
		this.rayon = rayon;
	}// fin de la méthode

	// Jeanne Castonguay
	/**
	 * Méthode permettant d'afficher dans la console toutes les forces agissant sur
	 * un corps
	 */
	public void afficherVecteurs() {
		int i = 1;
		for (Vecteur vecteur : ensembleDesForces) {
			System.out.println(i + "eme vecteur : " + vecteur.toString());
			i++;

		}
	}// fin de la méthode

	// Jeanne
	public double getVolume() {
		return 4 * Math.PI * Math.pow(rayon, 3.0) / 3.0;
	}// fin de la méthode

	// Antonin Triz
	/**
	 * Retourne si l'objet est explosable ou non
	 * 
	 * @return Si l'objet est explosable ou non
	 */
	public boolean estExplosable() {
		return explosable;
	}// fin de la méthode

	// Antonin Triz
	/**
	 * Permet de modifier si un objet est explosable ou non
	 * 
	 * @param explosable Si l'objet est explosable ou non
	 */
	public void setExplosable(boolean explosable) {
		this.explosable = explosable;
	}// fin de la méthode

	// Antonin Triz
	/**
	 * Permet de modifier la trajectoire de l'objet
	 * 
	 * @param trajectoire Valeur de la trajectoire de l'objet
	 */
	public void setTrajectoire(Trajectoire trajectoire) {
		this.trajectoire = trajectoire;
	}// fin de la méthode

	public String getNomImage() {
		return nomImage;
	}// fin de la méthode

	public Image getImage() {

		return imgLue;
	}// fin de la méthode

	public void setImage(Image image) {
		if (!estImage) {
			estImage = true;
		}
		this.imgLue = image;
	}// fin de la méthode

	// jeanne

	public void setNomImage(String nom) {
		this.nomImage = nom;

	}// fin de la méthode

	public boolean isEstImage() {
		return estImage;
	}// fin de la méthode

	public void setEstImage(boolean estImage) {
		this.estImage = estImage;
	}// fin de la méthode

	public String toString() {
		return "rayon : " + rayon + "\nmasse: " + masse + "\ncouleur: " + couleur;
	}// fin de la méthode

}// fin de la classe
