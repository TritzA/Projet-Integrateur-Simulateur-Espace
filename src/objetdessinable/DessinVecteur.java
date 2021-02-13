package objetdessinable;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import aapplication.Aapplication;
import composants.Simulation;
import interfaces.Dessinable;
import physique.Vecteur;

/**
 * 
 * @author Xavier Boucher Classe pour dessiner les vecteurs qui seront affichés
 *         à l'utilisateur
 */
public class DessinVecteur implements Dessinable {

	Simulation simulation = Aapplication.getSimulation();
	ArrayList<Corps> listeDesCorps = simulation.getEnsembleDesCorps();
	Corps corpsInfo;
	Vecteur vecteurPetiteLigne, vecteurADessiner;

	final int MULTIPLICATEURVECTEUR = 100000;
	final double RATIOPETITELIGNE = 0.2;
	final double ANGLE = 2.87979; // 165 deg en rad

	/**
	 * constructeur qui regarde sur quelle corp on va travailler par la suite
	 * @param corps corp sur lequel on va travailler par la suite
	 */
	public DessinVecteur(Corps corps) {
		this.corpsInfo = corps;
	}// fin de du constructeur

	/**
	 * Méthode qui dessin les vecteurs d'acceleration resultante
	 */
	public void dessiner(Graphics2D g2d, AffineTransform mat) {

		AffineTransform matIni = g2d.getTransform();
		g2d.setColor(Color.black);
		
		vecteurADessiner = new Vecteur(corpsInfo.getAcceleration().getX() * MULTIPLICATEURVECTEUR,
				corpsInfo.getAcceleration().getY() * MULTIPLICATEURVECTEUR);
		

		g2d.setTransform(mat);
		g2d.setStroke(new BasicStroke(600000));

		g2d.draw(new Line2D.Double(corpsInfo.getPosition().getX(), corpsInfo.getPosition().getY(),
				corpsInfo.getPosition().getX() + vecteurADessiner.getX(),
				corpsInfo.getPosition().getY() + vecteurADessiner.getY()));

		vecteurPetiteLigne = new Vecteur(
				(Math.cos(ANGLE) * vecteurADessiner.getX() - Math.sin(ANGLE) * vecteurADessiner.getY()),
				(Math.sin(ANGLE) * vecteurADessiner.getX() + Math.cos(ANGLE) * vecteurADessiner.getY()));
		vecteurPetiteLigne.setXY(vecteurPetiteLigne.getX() * RATIOPETITELIGNE,
				vecteurPetiteLigne.getY() * RATIOPETITELIGNE);

		g2d.draw(new Line2D.Double(corpsInfo.getPosition().getX() + vecteurADessiner.getX(),
				corpsInfo.getPosition().getY() + vecteurADessiner.getY(),
				corpsInfo.getPosition().getX() + vecteurADessiner.getX() + vecteurPetiteLigne.getX(),
				corpsInfo.getPosition().getY() + vecteurADessiner.getY() + vecteurPetiteLigne.getY()));

		vecteurPetiteLigne = new Vecteur(
				(Math.cos(-ANGLE) * vecteurADessiner.getX() - Math.sin(-ANGLE) * vecteurADessiner.getY()),
				(Math.sin(-ANGLE) * vecteurADessiner.getX() + Math.cos(-ANGLE) * vecteurADessiner.getY()));
		vecteurPetiteLigne.setXY(vecteurPetiteLigne.getX() * RATIOPETITELIGNE,
				vecteurPetiteLigne.getY() * RATIOPETITELIGNE);

		g2d.draw(new Line2D.Double(corpsInfo.getPosition().getX() + vecteurADessiner.getX(),
				corpsInfo.getPosition().getY() + vecteurADessiner.getY(),
				corpsInfo.getPosition().getX() + vecteurADessiner.getX() + vecteurPetiteLigne.getX(),
				corpsInfo.getPosition().getY() + vecteurADessiner.getY() + vecteurPetiteLigne.getY()));

		g2d.setTransform(matIni);
	}// fin de la méthode

}// fin de la classe
