package objetdessinable;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import interfaces.Dessinable;
import physique.Vecteur;

/**
 * Classe permettant d'avoir une bar de masse qui permet de montrer ou que le
 * joueur est rendu dans le jeu
 * 
 * @author Charlyne Lefrancois
 * @author Antonin Tritz
 *
 */
public class BarreDeMasse implements Dessinable {

	private Vecteur position = new Vecteur(40, 40);
	private double longueurExt = 200;
	private double hauteurExt = 35;
	private double longueurInt;
	private final double LONGUEUR_MAX_DOUBLE = 180.0;
	private double hauteurInt = 17.5;
	private double masseMax = 1;
	private double masse = 1;
	private final double ESPACE_ENTRE_2_RECTANGLE = 10;
	private Font f1 = new Font("Helvetica", 1, 60);
	private Font f2 = new Font("Helvetica", 1, 30);
	private Color noir = new Color(0, 0, 0, 100);
	private Color rouge = new Color(255, 0, 0, 150);

	// Charlyne
	/**
	 * Constructeur de la barre de masse
	 * 
	 * @param vec          position de la barre
	 * @param masseBlob    masse du blob
	 * @param masseMaxBlob masse maximum du blob
	 */
	public BarreDeMasse(Vecteur vec, double masseBlob, double masseMaxBlob) {
		this.masse = masseBlob;
		this.position = vec;
		this.masseMax = masseMaxBlob;
	}// fin du constructeur

	// Charlyne
	/**
	 * Permet de dessiner la barre de masse
	 */
	public void dessiner(Graphics2D g2d, AffineTransform mat) {
		// dessin de la barre de masse
		Color couleurRectangleExterieur = noir;
		Color couleurMasse = rouge;
		g2d.setColor(couleurRectangleExterieur);
		Rectangle2D.Double rectExt = new Rectangle2D.Double(position.getX(), position.getY(), longueurExt, hauteurExt);
		g2d.fill(rectExt);
		g2d.setColor(couleurMasse);
		longueurInt = ((masse * LONGUEUR_MAX_DOUBLE) / masseMax);
		Rectangle2D.Double rectInt = new Rectangle2D.Double(position.getX() + ESPACE_ENTRE_2_RECTANGLE,
				position.getY() + ESPACE_ENTRE_2_RECTANGLE, longueurInt, hauteurInt);
		g2d.fill(rectInt);
	}// fin de la méthode

	// Charlyne
	/**
	 * Permet d'afficher un message pour l'utillisateur si il gagne
	 * 
	 * @param g2d
	 * @param mat
	 */
	public void dessinerVictoire(Graphics2D g2d, AffineTransform mat) {
		g2d.setFont(f1);
		g2d.setColor(Color.WHITE);
		g2d.drawString("VICTOIRE", 400, 285);
		g2d.setFont(f2);
		g2d.drawString("   Félicitations, vous avez gagné! Vous avez", 250, 325);
		g2d.drawString("                accès au niveau suivant :)", 250, 365);
		g2d.drawString("Vous serez redirigé au menu dans 5 secondes.", 250, 405);
	}// fin de la méthode

	// Charlyne
	/**
	 * Permet d'afficher un message pour l'utilisateur si il perd
	 * 
	 * @param g2d
	 * @param mat
	 */
	public void dessinerPerdu(Graphics2D g2d, AffineTransform mat) {
		g2d.setFont(f1);
		g2d.setColor(Color.WHITE);
		g2d.drawString("GAME OVER", 395, 285);
		g2d.setFont(f2);
		g2d.drawString("Dommage, vous avez perdu! Recommencé", 280, 325);
		g2d.drawString("    pour avoir accès au niveau suivant.", 280, 365);
		g2d.drawString("Vous serez redirigé au menu dans 5 secondes.", 260, 405);
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Méthode qui dessine le message de fin de jeu lors de la dernière victoire
	 * 
	 * @param g2d Le contexte graphique
	 * @param mat La matrice de transformation
	 */
	public void dessinerFinJeu(Graphics2D g2d, AffineTransform matSansTransfo) {
		g2d.setFont(f1);
		g2d.setColor(Color.WHITE);
		g2d.drawString("JEU TERMINÉ", 400, 285);
		g2d.setFont(f2);
		g2d.drawString("   Félicitations !!! Vous venez de terminer ", 300, 325);
		g2d.drawString("                 le dernier niveau du jeu", 300, 365);
		g2d.drawString("Vous serez redirigé au menu dans 5 secondes.", 290, 405);

	}// fin de la méthode

	// Charlyne
	public void setMasseBlob(double masseB) {
		masse = masseB;
	}// fin de la méthode

}// fin de la classe
