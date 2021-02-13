package objetdessinable;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import interfaces.Dessinable;

/**
 * Classe permettant le dessin et la gestion de trajectoires
 * 
 * @author Antonin Tritz
 */

public class Trajectoire implements Dessinable {

	private Color nouvCouleur = null;
	Ellipse2D.Double point;

	private final int TRANSPARENCE = 255 / 2;
	private final int FREQUENCE_APPARITION_TRAJECTOIRE = 10;
	private final double FRACTION_RAYON_POUR_DIAMETRE = 0.2;

	private int nbTraj = 0;

	private double derniereTrajPosX;
	private double derniereTrajPosY;

	private ArrayList<Trajectoire> ensembleDesPoints = new ArrayList<Trajectoire>();

	// Antonin Tritz
	/**
	 * Permet de construire un objet Trajectoire
	 * 
	 */
	public Trajectoire() {
	}// fin du constructeur

	// Antonin Tritz
	/**
	 * Permet de construire un objet Trajectoire dont une propriété à changé
	 * 
	 * @param x        Position en x du nouveau point ajouté
	 * @param y        Position en y du nouveau point ajouté
	 * @param diametre Diamètre du cercle de chaque point de la trajectoire
	 */
	public Trajectoire(double x, double y, double diametre) {
		point = new Ellipse2D.Double(x - diametre / 2., y - diametre / 2., diametre, diametre);/// ajout
	}// fin du constructeur

	// Antonin Tritz
	/**
	 * Permet de dessiner un des points de la trajectoire
	 * 
	 * @param g2d Le contexte graphique
	 * @param mat La matrice de transformation
	 */
	public void dessiner(Graphics2D g2d, AffineTransform mat) {

		AffineTransform matIni = g2d.getTransform();
		Color colorIni = g2d.getColor();

		g2d.setColor(nouvCouleur);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.fill(mat.createTransformedShape(point));

		g2d.setColor(colorIni);
		g2d.setTransform(matIni);

	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet d'ajouter un point à une trajectoire
	 * 
	 * @param g2d   Le contexte graphique
	 * @param mat   La matrice de transformation
	 * @param corps Le corps auquel appartient la trajectoire
	 */
	public void ajouterPoint(Graphics2D g2d, AffineTransform mat, Corps corps) {
		nbTraj++;
		nouvCouleur = new Color(corps.getCouleur().getRed(), corps.getCouleur().getGreen(),
				corps.getCouleur().getBlue(), TRANSPARENCE);
		g2d.setColor(nouvCouleur);

		if (nbTraj % FREQUENCE_APPARITION_TRAJECTOIRE == 0
				&& (derniereTrajPosX != corps.getPosition().getX() || derniereTrajPosY != corps.getPosition().getY())) {
			ensembleDesPoints.add(new Trajectoire(corps.getPosition().getX(), corps.getPosition().getY(),
					corps.getRayon() * FRACTION_RAYON_POUR_DIAMETRE));
		}

		derniereTrajPosX = corps.getPosition().getX();
		derniereTrajPosY = corps.getPosition().getY();

		for (Trajectoire trajectoire : ensembleDesPoints) {
			trajectoire.dessiner(g2d, mat);
		}
	}// fin de la méthode
}// fin de la classe
