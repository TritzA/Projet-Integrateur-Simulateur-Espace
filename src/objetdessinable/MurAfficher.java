package objetdessinable;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import interfaces.Dessinable;

/**
 * 
 * Classe qui permet d'afficher les murs dans simulaion lorsqu'un mur est activé
 * 
 * @author Charlyne Lefrancois
 *
 */
public class MurAfficher implements Dessinable {
	private Color c = Color.white;
	private double longueur = 1357;
	private final double HAUTEUR_MUR = 5;
	private double hauteur = 682;

	/*
	 * Constructeur du mur
	 */
	public MurAfficher() {
	}// fin du constructeur


	/**
	 * Permet de dessiner le mur du haut
	 */
	public void dessiner(Graphics2D g2d, AffineTransform mat) {
		// dessin de la barre de masse

		g2d.setColor(c);
		Rectangle2D.Double rectHaut = new Rectangle2D.Double(0, 0, longueur, HAUTEUR_MUR);
		g2d.fill(rectHaut);
	}// fin de la méthode

	/**
	 * Permet de dessiner le mur du bas
	 * @param g2d
	 * @param mat
	 */
	public void dessinerMurBas(Graphics2D g2d, AffineTransform mat) {
		g2d.setColor(c);
		Rectangle2D.Double rectBas = new Rectangle2D.Double(0, hauteur - HAUTEUR_MUR, longueur, HAUTEUR_MUR);
		g2d.fill(rectBas);
	}// fin de la méthode

	/**
	 * Permet de dessiner le mur de gauche
	 * @param g2d
	 * @param mat
	 */
	public void dessinerMurGauche(Graphics2D g2d, AffineTransform mat) {
		g2d.setColor(c);
		Rectangle2D.Double rectGauche = new Rectangle2D.Double(0, 0, HAUTEUR_MUR, hauteur);
		g2d.fill(rectGauche);
	}// fin de la méthode

	/**
	 * Permet de dessiner le mur de droite
	 * @param g2d
	 * @param mat
	 */
	public void dessinerMurDroite(Graphics2D g2d, AffineTransform mat) {
		g2d.setColor(c);
		Rectangle2D.Double rectDroite = new Rectangle2D.Double(longueur - HAUTEUR_MUR, 0, HAUTEUR_MUR, hauteur);
		g2d.fill(rectDroite);
	}// fin de la méthode
}// fin du constructeur
