package composants;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Classe permettant de visualiser les couleurs possible d'un corps lors de sa
 * création
 * 
 * @author Jeanne Castonguay
 *
 */
public class AppercuCorps extends JPanel {

	int i = 0, k = 0;
	private final Color COULEUR_ARRIERE = new Color(0, 0, 25);
	private int rayonEnPixels = 100;
	private static final long serialVersionUID = 1L;
	private Color[] ensembleCouleur = { Color.pink, new Color(250, 100, 100), new Color(201, 135, 157),
			new Color(202, 100, 253), new Color(100, 100, 250), new Color(124, 162, 242), new Color(64, 224, 208),
			new Color(100, 250, 100), new Color(167, 211, 134), new Color(254, 217, 6), new Color(255, 116, 64),
			new Color(200, 200, 200), new Color(244, 254, 254) };
	// rose, rouge, mauve, violet, bleu foncé, bleu pale, turquoise, vert petant,
	// vert terne, jaune, orange, gris, blanc lunaire

	private String[] ensembleImage = { "planete1.png", "planete2.png", "planete3.png", "planete4.png", "planete5.png",
			"planete6.png", "planete7.png", "planete8.png", "planete9.png", "planete10.png", "planete11.png",
			"asteroide.png" };
	private Color[] couleurImage = { new Color(202, 100, 253), new Color(255, 146, 94), new Color(250, 100, 100),
			new Color(255, 116, 64), new Color(254, 217, 6), new Color(200, 200, 200), new Color(200, 200, 200),
			new Color(244, 254, 254), new Color(64, 224, 208), new Color(100, 100, 250), new Color(124, 162, 242),
			new Color(200, 200, 200) };
	private boolean estCouleur = true;

	private Image[] image = new Image[ensembleImage.length];

	/**
	 * Constructeur permettant de créer un objet AppercuCorps qui permet de
	 * visualiser les couleurs et les images possibles d'un nouveau corps
	 */
	public AppercuCorps() {
		setSize(rayonEnPixels, rayonEnPixels);

		for (int i = 0; i < ensembleImage.length; i++) {
			java.net.URL urlImage = getClass().getClassLoader().getResource(ensembleImage[i]);
			if (urlImage == null) {
				JOptionPane.showMessageDialog(null, "Fichier " + "terre.png" + " introuvable");
			}
			try {
				image[i] = ImageIO.read(urlImage);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Erreur pendant la lecture du fichier d'image");
			}
		}

	}// fin du constructeur

	/**
	 * Méthode de dessin permettant de visualiser les différentes couleurs ou les
	 * images d'appercuCorps
	 * 
	 * @param g Le contexte graphique
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		setBackground(COULEUR_ARRIERE);

		if (estCouleur) {
			// dessin couleur
			g2d.setColor(ensembleCouleur[i]);
			Ellipse2D.Double cercle = new Ellipse2D.Double(0, 0, rayonEnPixels, rayonEnPixels);
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.fill(cercle);
		} else {
			// dessin image
			Image imgRedim = null;

			double c = (double) rayonEnPixels;

			imgRedim = image[k].getScaledInstance((int) (c), (int) (c), Image.SCALE_SMOOTH);

			g2d.drawImage(imgRedim, 0, 0, null);

		}

	}// fin de la méthode

	/**
	 * Méthode permettant de passer à la couleur suivante
	 */
	public void suivant() {

		if (estCouleur) {
			i++;
			if (i > ensembleCouleur.length - 1) {
				i = 0;
				estCouleur = !estCouleur;
			}
		} else {

			k++;
			if (k > ensembleImage.length - 1) {
				k = 0;
				estCouleur = !estCouleur;
			}
		}
		repaint();
	}// fin de la méthode

	/**
	 * Méthode permettant de passer à la couleur précédante
	 */
	public void precedant() {
		if (estCouleur) {
			i--;
			if (i < 0) {
				i = 0;
				k = ensembleImage.length - 1;
				estCouleur = !estCouleur;
			}
		} else {
			k--;
			if (k < 0) {
				k = 0;
				i = ensembleCouleur.length - 1;
				estCouleur = !estCouleur;
			}
		}
		repaint();
	}// fin de la méthode

	/**
	 * Méthode permettant d'accèder a la couleur actuelle visualisée grace à
	 * l'AppercuCorps
	 * 
	 * @return La couleur actuelle visualisée
	 */
	public Color getCouleurActuelle() {
		if (estCouleur) {
			return ensembleCouleur[i];
		} else {
			return couleurImage[k];
		}
	}// fin de la méthode

	/**
	 * Méthode permettant de retourner à la première couleur, donc de réinitialiser
	 * l'AppercuCorps
	 */
	public void reset() {
		i = 0;
		estCouleur = true;
	}// fin de la méthode

	public boolean estCouleur() {
		return estCouleur;
	}// fin de la méthode

	/**
	 * Méthode permettant d'obtenir le nom de l'image visualisée, si l'appercuCorps
	 * présente une image
	 * 
	 * @return Le nom de l'image
	 */
	public String getNomImage() {
		if (estCouleur) {
			System.err.println("Ce corps n'a pas d'image, donc getNomImage obselète");
			return null;
		} else {
			return ensembleImage[k];
		}
	}// fin de la méthode

	/**
	 * Méthode permettant d'obtenirl'image visualisée, si l'appercuCorps présente
	 * une image
	 * 
	 * @return l'image
	 */
	public Image getImage() {
		if (estCouleur) {
			System.err.println("Ce corps n'a pas d'image, donc getImage obselète");
			return null;
		} else {
			return image[k];

		}
	}// fin de la méthode

	/*
	 * public Image imageAleatoire() { Image imgLue=null;
	 * 
	 * int k = (int) (Math.random()*(12));
	 * 
	 * java.net.URL urlImage =
	 * getClass().getClassLoader().getResource(ensembleImage[k]); if (urlImage ==
	 * null) { JOptionPane.showMessageDialog(null , "Fichier " + "terre.png" +
	 * " introuvable"); } try { imgLue = ImageIO.read(urlImage); } catch
	 * (IOException e) { JOptionPane.showMessageDialog(null ,
	 * "Erreur pendant la lecture du fichier d'image"); }
	 * 
	 * return imgLue;
	 * 
	 * }
	 */

	/**
	 * Méthode permettant d'obtenir une image aléatoire
	 * 
	 * @return Une image aléatoire
	 */
	public Image imageAleatoire() {
		int k = (int) (Math.random() * (12));
		return image[k];
	}// fin de la méthode

}// fin de la classe
