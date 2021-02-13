package aapplication;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import composants.FondEcran;
import utilitaire.Fenetre;
import utilitaire.RechercheDeNiveau;

/**
 * Application permettant de selectionner un niveau pour le jeu 
 * 
 * @author Jeanne Castonguay
 * @author Antonin Tritz
 * @author Xavier Boucher
 */

public class SelectionNiveau extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FondEcran img;
	private static Jeu niveau1;
	private static Jeu niveau2;
	private static Jeu niveau3;
	private Font f = new Font("Helvetica", 1, 35);

	private JPanel contentPane;

	// Jeanne Castonguay
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Erreur Ne trouve pas le look and feel du système : " + e);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectionNiveau frame = new SelectionNiveau();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}// fin du main

	// Jeanne Castonguay
	/**
	 * Constructeur permettant d'instancier l'application selection du niveau
	 */
	public SelectionNiveau() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(300, 150, 920, 600);
		setTitle("SelectionNiveau");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		Fenetre.accesNiveau1 = true;
		Fenetre.ajoutMenu(SelectionNiveau.this);

		niveau1 = new Jeu(1, "");
		niveau2 = new Jeu(2, "");
		niveau3 = new Jeu(3, "");

		JButton btnNiv1 = new JButton("1");
		btnNiv1.addActionListener(new ActionListener() {

			// Jeanne Castonguay
			public void actionPerformed(ActionEvent e) {
				Fenetre.changer(niveau1, SelectionNiveau.this);
			}// fin de la méthode
		});
		btnNiv1.setBounds(145, 190, 181, 174);
		contentPane.add(btnNiv1);
		Fenetre.associerBoutonAvecImage(btnNiv1, "niv1.png", SelectionNiveau.this);
		btnNiv1.setToolTipText("Cliquer ici pour accéder au niveau 1 !");

		JButton btnNiv2 = new JButton("2");
		btnNiv2.addActionListener(new ActionListener() {

			// Jeanne Castonguay
			public void actionPerformed(ActionEvent e) {
				Fenetre.changer(niveau2, SelectionNiveau.this);
			}// fin de la méthode
		});
		btnNiv2.setBounds(361, 190, 181, 174);
		contentPane.add(btnNiv2);
		Fenetre.associerBoutonAvecImage(btnNiv2, "niv2.png", SelectionNiveau.this);
		if (Fenetre.accesNiveau2) {
			btnNiv2.setToolTipText("Cliquer ici pour accéder au niveau 2 !");
			btnNiv2.setEnabled(true);
		} else {
			btnNiv2.setToolTipText("Vous devez d'abord réussir le niveau 1 pour accéder à ce niveau.");
			btnNiv2.setEnabled(false);
		}

		JButton btnNiv3 = new JButton("3");
		btnNiv3.addActionListener(new ActionListener() {

			// Jeanne Castonguay
			public void actionPerformed(ActionEvent e) {
				Fenetre.changer(niveau3, SelectionNiveau.this);
			}// fin de la méthode
		});
		btnNiv3.setBounds(578, 190, 181, 174);
		contentPane.add(btnNiv3);
		Fenetre.associerBoutonAvecImage(btnNiv3, "niv3.png", SelectionNiveau.this);
		if (Fenetre.accesNiveau3) {
			btnNiv3.setToolTipText("Cliquer ici pour accéder au niveau 3 !");
			btnNiv3.setEnabled(true);
		} else {
			btnNiv3.setToolTipText("Vous devez d'abord réussir le niveau 2 pour accéder à ce niveau.");
			btnNiv3.setEnabled(false);
		}

		
		partiePerso();
		

		JLabel lblSlectionnezUnNiveau = new JLabel("Sélectionner un niveau");
		lblSlectionnezUnNiveau.setFont(f);
		lblSlectionnezUnNiveau.setForeground(Color.BLACK);
		lblSlectionnezUnNiveau.setBounds(getWidth() / 2 - 415 / 2, 62, 415, 66);
		contentPane.add(lblSlectionnezUnNiveau);

		img = new FondEcran("selectionNiveau");
		img.setBounds(0, 0, getWidth(), getHeight());
		contentPane.add(img);
		img.setLayout(null);
	}// fin de la méthode

	// Antonin Triz
	/**
	 * Retourne le premier niveau
	 * 
	 * @return
	 */
	public static Jeu getNiveau1() {
		return niveau1;
	}// fin de la méthode

	// Antonin Triz
	/**
	 * Retourne le deuxième niveau
	 * 
	 * @return
	 */
	public static Jeu getNiveau2() {
		return niveau2;
	}// fin de la méthode

	// Antonin Triz
	/**
	 * Retourne le troisième niveau
	 * 
	 * @return
	 */
	public static Jeu getNiveau3() {
		return niveau3;
	}// fin de la méthode
	
	//Xavier Boucher
	/**
	 * Méthode pour la selection du bouton partiPerso
	 */
	public void partiePerso() {
		
		RechercheDeNiveau fenRechercheDeNiveau;

		fenRechercheDeNiveau = new RechercheDeNiveau();
		fenRechercheDeNiveau.setVisible(false);
		
		JButton btnPartiePersonnalise = new JButton("Partie personnalisée");
		btnPartiePersonnalise.setFont(new Font("SansSerif", Font.BOLD, 28));
		btnPartiePersonnalise.setBackground(new Color(0, 0, 0));
		btnPartiePersonnalise.setForeground(Color.BLACK);
		btnPartiePersonnalise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Fenetre.changer(fenRechercheDeNiveau, SelectionNiveau.this);

			}
		});
		btnPartiePersonnalise.setBounds(145, 393, 614, 78);
		contentPane.add(btnPartiePersonnalise);
		btnPartiePersonnalise.setToolTipText("Charger un niveau personnalisé !");
	}
}// fin de la classe
