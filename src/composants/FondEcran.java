package composants;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Classe permettant d'ajouter un fond d'écran sur un JFrame ou sur un JPanel
 * 
 * @author Antonin Tritz
 */

public class FondEcran extends JPanel {

	private static final long serialVersionUID = 6851277940062287249L;
	private Image imgDecor = null;

	// Antonin Tritz
	/**
	 * Méthode permettant de lire une image
	 * 
	 * @param nomFichier Le nom du fichier à lire
	 */
	public FondEcran(String nomFichier) {
		URL fichDecor = getClass().getClassLoader().getResource(nomFichier + ".jpg");

		if (fichDecor == null) {
			System.out.println("Incapable de lire un fichier d'image");
			return;
		}
		try {
			imgDecor = ImageIO.read(fichDecor);
		} catch (IOException e) {
			System.out.println("Erreur de lecture d'images");
		}

	}// fin du constructeur

	// Antonin Tritz
	/**
	 * Méthode permettant de dessiner cette image
	 * 
	 * @param g Le contexte graphique
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(imgDecor, 0, 0, getWidth(), getHeight(), null);
	}// fin de la méthode

}// fin de la classe
