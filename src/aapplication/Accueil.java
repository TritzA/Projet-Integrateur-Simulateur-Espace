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
import javax.swing.text.BadLocationException;

import composants.FondEcran;
import utilitaire.Fenetre;

/**
 * Classe permettant l'affichage de la fenêtre d'accueil
 * 
 * @author Antonin Tritz
 */

public class Accueil extends JFrame {

	private static final long serialVersionUID = 1L;
	private FondEcran img;
	private JPanel contentPane;
	private JButton btnSimulation;
	private JButton btnJeu;
	private JLabel lblTextAccueilLigne1;
	private JLabel lblTextAccueilLigne2;
	private Font f = new Font("Helvetica", 1, 18);

	private Aapplication fenSimulation;
	private SelectionNiveau fenNiveau = new SelectionNiveau();

	// Antonin Tritz
	/**
	 * Lance la classe Accueil
	 * 
	 * @param args Permet de lancer la classe
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
					Accueil frame = new Accueil();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet la construction d'un objet Test 
	 */
	public Accueil() throws BadLocationException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(400, 150, 800, 500);
		setTitle("Accueil");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Fenetre.ajoutMenu(Accueil.this);

		lblTextAccueilLigne1 = new JLabel("      Deux choix s'offrent à vous, vous avez la possibilité d'utiliser le");
		lblTextAccueilLigne1.setBounds(getWidth() / 2 - 650 / 2, 30, 650, 80);
		contentPane.add(lblTextAccueilLigne1);
		lblTextAccueilLigne1.setForeground(Color.WHITE);
		lblTextAccueilLigne1.setFont(f);
		lblTextAccueilLigne1.setBackground(new Color(0, 0, 0, 1));

		lblTextAccueilLigne2 = new JLabel("simulateur de physique d'espace, ou bien de jouer à un jeu sur l'espace.");
		lblTextAccueilLigne2.setBounds(getWidth() / 2 - 650 / 2, 50, 650, 80);
		contentPane.add(lblTextAccueilLigne2);
		lblTextAccueilLigne2.setForeground(Color.WHITE);
		lblTextAccueilLigne2.setFont(f);
		lblTextAccueilLigne2.setBackground(new Color(0, 0, 0, 1));

		fenSimulation = new Aapplication();
		fenSimulation.setVisible(false);
		btnSimulation = new JButton("Mode simulation");

		btnSimulation.addActionListener(new ActionListener() {
			// Antonin Tritz
			/**
			 * Permet une action lorsqu'on clique sur le bouton Simulation
			 * 
			 * @param e Action lorsqu'on clique sur le bouton Simulation
			 */
			public void actionPerformed(ActionEvent e) {
				Fenetre.changer(fenSimulation, Accueil.this);

			}// fin de la méthode
		});
		btnSimulation.setBounds(getWidth() / 3 - 200 / 2, 150, 200, 200);
		contentPane.add(btnSimulation);
		Fenetre.associerBoutonAvecImage(btnSimulation, "btnSimulation.png", Accueil.this);
		btnSimulation.setToolTipText("Cliquer ici pour accéder à la simulation."); 

		btnJeu = new JButton("Mode jeu");

		btnJeu.addActionListener(new ActionListener() {
			// Antonin Tritz
			/**
			 * Permet une action lorsqu'on clique sur le bouton Jeu
			 * 
			 * @param e Action lorsqu'on clique sur le bouton Jeu
			 */
			public void actionPerformed(ActionEvent e) {
				Fenetre.changer(fenNiveau, Accueil.this);
			}// fin de la méthode
		});
		btnJeu.setBounds(2 * getWidth() / 3 - 200 / 2, 180, 205, 155);
		contentPane.add(btnJeu);
		Fenetre.associerBoutonAvecImage(btnJeu, "btnJeu.png", Accueil.this);
		btnJeu.setToolTipText("Cliquer ici pour accéder au jeu.");

		img = new FondEcran("accueil");
		img.setBounds(0, 0, getWidth(), getHeight());
		contentPane.add(img);
		img.setLayout(null);

	}// fin de la méthode

}// fin de la classe
