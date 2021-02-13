package utilitaire;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import aapplication.Jeu;

/**
 * Cette classe permet de selectionnner un niveaux personnaliser dans une fenettre a part
 * 
 * @author Xavier Boucher
 *
 */
public class RechercheDeNiveau extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RechercheDeNiveau frame = new RechercheDeNiveau();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}// fin du main

	/**
	 * Creer la fenettre dans laquelle on va rechercher un niveau pour le jeu
	 */
	public RechercheDeNiveau() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 659, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JFileChooser chooser = new JFileChooser("Choisir un fichier");
		chooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = chooser.getSelectedFile().getName();
				System.out.println(fileName);
				if (fileName.endsWith(".txt")) {
					fileName = fileName.subSequence(0, fileName.length() - 4).toString();
				}

				Jeu fenJeu;

				fenJeu = new Jeu(4, fileName);
				fenJeu.setVisible(false);

				Fenetre.changer(fenJeu, RechercheDeNiveau.this);

			}
		});

		chooser.setBounds(10, 11, 623, 397);
		contentPane.add(chooser);
	}// fin du constructeur
}// fin de la classe
