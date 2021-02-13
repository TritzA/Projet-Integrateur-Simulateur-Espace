package aapplication;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import composants.AppercuCorps;
import composants.Boutons;
import composants.Simulation;
import ecouteurs.PlanetListener;
import ecouteurs.ZoneAnimationListener;
import fichier.GestionFichier;
import objetdessinable.Corps;
import physique.MiseEnOrbite;
import physique.Vecteur;
import structuredonnees.PasIteration;
import utilitaire.Fenetre;
import utilitaire.SMath;

/**
 * Classe qui permet la création de l'interface
 * 
 * @author Charlyne Lefrancois
 * @author Xavier Boucher
 * @author Antonin Tritz
 *
 */
public class Aapplication extends JFrame {

	// lorsque fusion info de la planete de change pas
	// lors de la creation d'une planete le spinner ne change rien a la masse total
	// du systeme
	/**
	 * 
	 * 
	 */
	private DecimalFormat de = new DecimalFormat("#0.00001");

	private static final long serialVersionUID = 1L;
	private final Color COULEUR_ARRIERE = new Color(0, 0, 25);
	private static final PasIteration LONG = new PasIteration("Long", 10);
	private static final PasIteration MOYEN = new PasIteration("Moyen", 5);// commentaire
	private static final PasIteration COURT = new PasIteration("Court", 2);
	private JPanel contentPane;
	private JLabel lblVitesseDeSimulation;
	private JRadioButton rdbtnInlastique;
	private JRadioButton rdbtnElastique;
	private JLabel lblModeDeCollision;
	private JCheckBox chckbxAfficherVec;
	private JPanel pnlEntreesGenerales;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxPasIteration;
	private JCheckBox chckbxMurHaut;
	private JCheckBox chckbxMurDroite;
	private JLabel lblCollisionMur;
	private JCheckBox chckbxMurBas;
	private JCheckBox chckbxMurGauche;
	private JPanel pnlSortiesGenerales;
	private JLabel lblEnergieTotale;
	private JLabel lblEnergieT;
	private JLabel lblJ;
	private JLabel lblEnergieCintiqueTotale;
	private JLabel lblEnergieCT;
	private JLabel lblKJ;
	private JLabel lblEnergiePotentielle;
	private JLabel lblEnergiePT;
	private JLabel lblKJ2;
	private JLabel lblQuantiteMouv;
	private JLabel lblQuantiteMT;
	private JLabel lblKG;
	private JLabel lblMasseTotale;
	private JLabel lblMasseT;
	private JLabel lblKg;
	private JLabel lblNombreCorps;
	private JLabel lblNombreC;
	private JLabel lblCorps;
	private JPanel pnlOutils;
	private JPanel panelCreation;
	private JPanel pnlInfo;
	private JPanel pnlFichier;
	private JButton btnDemarrer;
	private JButton btnRecommencer;
	private JButton btnPauseProchaineImage;
	private JTabbedPane tabbedPane; 
	private JLabel lblSatellite;
	private JLabel lblInfoTitre;
	private JLabel lblInfoMasse;
	private JLabel lblInfoMasseNombre;
	private JLabel lblInfoMasseUnite;
	private JLabel lblInfoRayon;
	private JLabel lblInfoRayonNombre;
	private JLabel lblInfoRayonUnite;
	private JLabel lblInfoCinetique;
	private JLabel lblInfoCinetiqueNombre;
	private JLabel lblInfoCinetiqueUnite;
	private JLabel lblInfoPotentiel;
	private JLabel lblInfoPotentielNombre;
	private JLabel lblInfoPotentielUnite;
	private JLabel lblInfoAccel;
	private JLabel lblInfoAccelNombre;
	private JLabel lblInfoAccelUnite;
	private JLabel lblInfoVResultante;
	private JLabel lblInfoVresultanteNombre;
	private JLabel lblInfoVResultanteUnite;
	private JSpinner spnnrRayon;
	private JSpinner spnnrMasse;
	private JTextField txtFldFichierSauvegarde;
	private JTextField txtFldFichierCharger;
	private JLabel lblSauvegarder;
	private JLabel lblCharger;
	private JLabel lblMasseTerre;
	private Corps corpsCree;
	private MiseEnOrbite orbite;
	private static Simulation simulation;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnSelectPlanete;
	private JButton btnRetour;
	private JButton btnCorpsDeffinitif;
	private boolean murHautActive;
	private boolean murDroitActive;
	private boolean murBasActive;
	private boolean murGaucheActive;
	private static boolean estPretASupprimer = false;
	private JLabel lblTempsSimulation;
	private JLabel lblTempsRelcoul;
	private JLabel labelTempsS;
	private JLabel labelTempsR;
	private JLabel lblSecondes;
	private JLabel lblSecondes2;
	private double masseTotale;
	private double ecart;
	private JCheckBox chckbxTousLesMurs;
	private JLabel lblExposantPotentielle;
	private JLabel lblExposantP;
	private JLabel lblExposantAccel;
	private JLabel lblExposantCinetique;
	private JLabel lblExposantCT;
	private JLabel lblExposantET;
	private JLabel lblExposantQM;
	private static long tempsDepart;
	private static long tempsPause;
	private static long tempsDepartPause;
	private static long tempsFinPause;
	private JButton btnSelectSatellite;
	private JLabel lblCouleur;
	private JLabel lblRayon;
	private JLabel lblR;
	private JLabel lblKm;
	private JLabel lblMasse;
	private JLabel lblM;
	private JLabel lblKG2;
	private JButton btnChangeCouleurGauche;
	private JButton btnChangeCouleurDroite;
	private AppercuCorps appercuPlanete;
	private JLabel lblPlanete;
	private static boolean isDessinVecteur = false;
	private static boolean isDessinVecteurS = false;
	private JCheckBox chckbxExplosable;
	private JButton btnORetour;
	private JButton btnOCreer;
	private JButton btnOUp;
	private JButton btnORight;
	private JButton btnOLeft;
	private JButton btnODown;
	private JLabel lblRatioRayon = new JLabel("Ratio du rayon Satellite/Plan\u00E8te");
	private JLabel lblRatioRayon2 = new JLabel("/100");
	private JLabel lblRatioMasse = new JLabel("Ratio de la masse Satellite/Plan\u00E8te");
	private JLabel lblRatioMasse2 = new JLabel("/100");
	private JLabel lblRayonTotal = new JLabel("Rayon total du syst\u00E8me en Km");
	private JLabel lblMasseTotal = new JLabel("Masse total du syst\u00E8eme en masse terreste");
	private JLabel lblDistance = new JLabel("Distance en les centes du syst\u00E8me");
	private JSpinner spnnrORatioRayon = new JSpinner();
	private JSpinner spnnrORatioMasse = new JSpinner();
	private JSpinner spnnrORayonTotal = new JSpinner();
	private JSpinner spnnrOMasseTotal = new JSpinner();
	private JSpinner spnnrODistance = new JSpinner();

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
					Accueil frame = new Accueil();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Aapplication() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(0, 0, 1920, 1080);
		setTitle("Simulation");

		Fenetre.ajoutMenu(Aapplication.this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // enlever le com.
		contentPane.setLayout(null);
		setContentPane(contentPane);
		contentPane.setBackground(COULEUR_ARRIERE);

		pnlEntreesGenerales = new JPanel();
		pnlEntreesGenerales.setForeground(Color.WHITE);
		pnlEntreesGenerales.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Entr\u00E9es g\u00E9n\u00E9rales", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnlEntreesGenerales.setBounds(10, 11, 797, 302);
		contentPane.add(pnlEntreesGenerales);
		pnlEntreesGenerales.setLayout(null);
		pnlEntreesGenerales.setBackground(COULEUR_ARRIERE);

		chckbxAfficherVec = new JCheckBox("Afficher tous les vecteurs forces ");
		chckbxAfficherVec.setBackground(COULEUR_ARRIERE);
		chckbxAfficherVec.setForeground(Color.WHITE);
		chckbxAfficherVec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxAfficherVec.setBounds(23, 37, 247, 23);
		pnlEntreesGenerales.add(chckbxAfficherVec);
		chckbxAfficherVec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxAfficherVec.isSelected()) {
					isDessinVecteur = true;
				} else {
					isDessinVecteur = false;
				}
			}
		});

		lblModeDeCollision = new JLabel("Mode de collision :");
		lblModeDeCollision.setForeground(Color.WHITE);
		lblModeDeCollision.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblModeDeCollision.setBounds(23, 87, 188, 23);
		pnlEntreesGenerales.add(lblModeDeCollision);

		boolean collisionE = true;
		boolean collisionIne = false;
		rdbtnElastique = new JRadioButton("\u00C9lastique");
		rdbtnElastique.setBackground(COULEUR_ARRIERE);
		rdbtnElastique.setForeground(Color.WHITE);
		rdbtnElastique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnElastique.isSelected()) {
					simulation.setCollisionElastique(collisionE);
				} else {
					simulation.setCollisionElastique(collisionIne);
				}
			}
		});
		buttonGroup.add(rdbtnElastique);
		rdbtnElastique.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnElastique.setBounds(23, 119, 109, 23);
		pnlEntreesGenerales.add(rdbtnElastique);

		rdbtnInlastique = new JRadioButton("In\u00E9lastique");
		rdbtnInlastique.setBackground(COULEUR_ARRIERE);
		rdbtnInlastique.setForeground(Color.WHITE);
		rdbtnInlastique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnInlastique.isSelected()) {
					simulation.setCollisionElastique(collisionIne);
				} else {
					simulation.setCollisionElastique(collisionE);
				}
			}
		});
		buttonGroup.add(rdbtnInlastique);
		rdbtnInlastique.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnInlastique.setBounds(23, 145, 109, 23);
		pnlEntreesGenerales.add(rdbtnInlastique);
		rdbtnInlastique.setSelected(true);

		lblVitesseDeSimulation = new JLabel("Vitesse de simulation :");
		lblVitesseDeSimulation.setForeground(Color.WHITE);
		lblVitesseDeSimulation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVitesseDeSimulation.setBounds(23, 209, 188, 23);
		pnlEntreesGenerales.add(lblVitesseDeSimulation);

		comboBoxPasIteration = new JComboBox();
		comboBoxPasIteration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PasIteration pas = (PasIteration) getItemInComboBox(comboBoxPasIteration);
				simulation.setTempsAnim(pas);
			}
		});
		comboBoxPasIteration.setBounds(27, 255, 222, 23);
		pnlEntreesGenerales.add(comboBoxPasIteration);
		comboBoxPasIteration.setModel(new DefaultComboBoxModel(new PasIteration[] { LONG, MOYEN, COURT }));

		chckbxMurHaut = new JCheckBox("Activer mur du haut");
		chckbxMurHaut.setBackground(COULEUR_ARRIERE);
		chckbxMurHaut.setForeground(Color.WHITE);
		chckbxMurHaut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxMurHaut.isSelected()) {
					murHautActive = true;
				} else {
					murHautActive = false;
				}

				if (murBasActive && murDroitActive && murGaucheActive && murHautActive) {
					chckbxTousLesMurs.setSelected(true);
				} else {
					chckbxTousLesMurs.setSelected(false);
				}
				simulation.setMurHautActive(murHautActive);
			}
		});
		chckbxMurHaut.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxMurHaut.setBounds(325, 69, 198, 23);
		pnlEntreesGenerales.add(chckbxMurHaut);

		chckbxMurDroite = new JCheckBox("Activer mur \u00E0 droite");
		chckbxMurDroite.setBackground(COULEUR_ARRIERE);
		chckbxMurDroite.setForeground(Color.WHITE);
		chckbxMurDroite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxMurDroite.isSelected()) {
					murDroitActive = true;
				} else {
					murDroitActive = false;
				}
				if (murBasActive && murDroitActive && murGaucheActive && murHautActive) {
					chckbxTousLesMurs.setSelected(true);
				} else {
					chckbxTousLesMurs.setSelected(false);
				}
				simulation.setMurDroitActive(murDroitActive);
			}
		});
		chckbxMurDroite.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxMurDroite.setBounds(325, 106, 173, 23);
		pnlEntreesGenerales.add(chckbxMurDroite);

		lblCollisionMur = new JLabel("Collision avec les murs : ");
		lblCollisionMur.setForeground(Color.WHITE);
		lblCollisionMur.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCollisionMur.setBounds(325, 34, 173, 28);
		pnlEntreesGenerales.add(lblCollisionMur);

		chckbxMurBas = new JCheckBox("Activer mur du bas");
		chckbxMurBas.setBackground(COULEUR_ARRIERE);
		chckbxMurBas.setForeground(Color.WHITE);
		chckbxMurBas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxMurBas.isSelected()) {
					murBasActive = true;
				} else {
					murBasActive = false;
				}
				if (murBasActive && murDroitActive && murGaucheActive && murHautActive) {
					chckbxTousLesMurs.setSelected(true);
				} else {
					chckbxTousLesMurs.setSelected(false);
				}
				simulation.setMurBasActive(murBasActive);
			}
		});
		chckbxMurBas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxMurBas.setBounds(325, 145, 138, 23);
		pnlEntreesGenerales.add(chckbxMurBas);

		chckbxMurGauche = new JCheckBox("Activer mur \u00E0 gauche");
		chckbxMurGauche.setBackground(COULEUR_ARRIERE);
		chckbxMurGauche.setForeground(Color.WHITE);
		chckbxMurGauche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxMurGauche.isSelected()) {
					murGaucheActive = true;
				} else {
					murGaucheActive = false;
				}
				if (murBasActive && murDroitActive && murGaucheActive && murHautActive) {
					chckbxTousLesMurs.setSelected(true);
				} else {
					chckbxTousLesMurs.setSelected(false);
				}
				simulation.setMurGaucheActive(murGaucheActive);
			}
		});
		chckbxMurGauche.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxMurGauche.setBounds(325, 185, 173, 23);
		pnlEntreesGenerales.add(chckbxMurGauche);

		chckbxTousLesMurs = new JCheckBox("Activer tous les murs");
		chckbxTousLesMurs.setBackground(COULEUR_ARRIERE);
		chckbxTousLesMurs.setForeground(Color.WHITE);
		chckbxTousLesMurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxTousLesMurs.isSelected()) {
					murBasActive = true;
					murDroitActive = true;
					murGaucheActive = true;
					murHautActive = true;
					chckbxMurBas.setSelected(true);
					chckbxMurDroite.setSelected(true);
					chckbxMurGauche.setSelected(true);
					chckbxMurHaut.setSelected(true);
				} else {
					murBasActive = false;
					murDroitActive = false;
					murGaucheActive = false;
					murHautActive = false;
					chckbxMurBas.setSelected(false);
					chckbxMurDroite.setSelected(false);
					chckbxMurGauche.setSelected(false);
					chckbxMurHaut.setSelected(false);
				}
				simulation.setMurBasActive(murBasActive);
				simulation.setMurDroitActive(murDroitActive);
				simulation.setMurGaucheActive(murGaucheActive);
				simulation.setMurHautActive(murHautActive);
			}
		});
		chckbxTousLesMurs.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxTousLesMurs.setBounds(325, 222, 173, 23);
		pnlEntreesGenerales.add(chckbxTousLesMurs);

		pnlSortiesGenerales = new JPanel();
		pnlSortiesGenerales.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Sorties g\u00E9n\u00E9rales", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnlSortiesGenerales.setBounds(817, 11, 550, 302);
		contentPane.add(pnlSortiesGenerales);
		pnlSortiesGenerales.setLayout(null);
		pnlSortiesGenerales.setBackground(COULEUR_ARRIERE);

		lblEnergieTotale = new JLabel("Energie totale : ");
		lblEnergieTotale.setForeground(Color.WHITE);
		lblEnergieTotale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnergieTotale.setBounds(10, 25, 108, 23);
		pnlSortiesGenerales.add(lblEnergieTotale);

		lblEnergieT = new JLabel("0");
		lblEnergieT.setForeground(Color.WHITE);
		lblEnergieT.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEnergieT.setBounds(215, 25, 66, 23);
		pnlSortiesGenerales.add(lblEnergieT);

		lblJ = new JLabel("KJ");
		lblJ.setForeground(Color.WHITE);
		lblJ.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblJ.setBounds(365, 25, 81, 23);
		pnlSortiesGenerales.add(lblJ);

		lblEnergieCintiqueTotale = new JLabel("Energie cin\u00E9tique totale : ");
		lblEnergieCintiqueTotale.setForeground(Color.WHITE);
		lblEnergieCintiqueTotale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnergieCintiqueTotale.setBounds(10, 62, 169, 23);
		pnlSortiesGenerales.add(lblEnergieCintiqueTotale);

		lblEnergieCT = new JLabel("0");
		lblEnergieCT.setForeground(Color.WHITE);
		lblEnergieCT.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEnergieCT.setBounds(215, 62, 66, 23);
		pnlSortiesGenerales.add(lblEnergieCT);

		lblKJ = new JLabel("KJ");
		lblKJ.setForeground(Color.WHITE);
		lblKJ.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKJ.setBounds(365, 62, 81, 23);
		pnlSortiesGenerales.add(lblKJ);

		lblEnergiePotentielle = new JLabel("Energie potentielle totale : ");
		lblEnergiePotentielle.setForeground(Color.WHITE);
		lblEnergiePotentielle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnergiePotentielle.setBounds(10, 99, 169, 23);
		pnlSortiesGenerales.add(lblEnergiePotentielle);

		lblEnergiePT = new JLabel("0");
		lblEnergiePT.setForeground(Color.WHITE);
		lblEnergiePT.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEnergiePT.setBounds(215, 96, 66, 26);
		pnlSortiesGenerales.add(lblEnergiePT);

		lblKJ2 = new JLabel("KJ");
		lblKJ2.setForeground(Color.WHITE);
		lblKJ2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKJ2.setBounds(365, 99, 81, 23);
		pnlSortiesGenerales.add(lblKJ2);

		lblQuantiteMouv = new JLabel("Quantit\u00E9 de mouvement totale : ");
		lblQuantiteMouv.setForeground(Color.WHITE);
		lblQuantiteMouv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuantiteMouv.setBounds(10, 133, 202, 23);
		pnlSortiesGenerales.add(lblQuantiteMouv);

		lblQuantiteMT = new JLabel("0");
		lblQuantiteMT.setForeground(Color.WHITE);
		lblQuantiteMT.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuantiteMT.setBounds(215, 133, 66, 23);
		pnlSortiesGenerales.add(lblQuantiteMT);

		lblKG = new JLabel("m*kg/s");
		lblKG.setForeground(Color.WHITE);
		lblKG.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKG.setBounds(365, 133, 81, 23);
		pnlSortiesGenerales.add(lblKG);

		lblMasseTotale = new JLabel("Masse totale : ");
		lblMasseTotale.setForeground(Color.WHITE);
		lblMasseTotale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMasseTotale.setBounds(10, 168, 169, 23);
		pnlSortiesGenerales.add(lblMasseTotale);

		lblMasseT = new JLabel("0");
		lblMasseT.setForeground(Color.WHITE);
		lblMasseT.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMasseT.setBounds(215, 164, 86, 23);
		pnlSortiesGenerales.add(lblMasseT);

		lblKg = new JLabel("M\u2295");
		lblKg.setForeground(Color.WHITE);
		lblKg.setFont(new Font("Cambria", Font.PLAIN, 12));
		lblKg.setBounds(365, 168, 81, 14);
		pnlSortiesGenerales.add(lblKg);

		lblNombreCorps = new JLabel("Nombre de corps totale : ");
		lblNombreCorps.setForeground(Color.WHITE);
		lblNombreCorps.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombreCorps.setBounds(10, 201, 159, 23);
		pnlSortiesGenerales.add(lblNombreCorps);

		lblNombreC = new JLabel("0");
		lblNombreC.setForeground(Color.WHITE);
		lblNombreC.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombreC.setBounds(215, 201, 122, 23);
		pnlSortiesGenerales.add(lblNombreC);

		lblTempsSimulation = new JLabel("Temps de simulation \u00E9coul\u00E9 : ");
		lblTempsSimulation.setForeground(Color.WHITE);
		lblTempsSimulation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTempsSimulation.setBounds(10, 235, 195, 23);
		pnlSortiesGenerales.add(lblTempsSimulation);

		lblCorps = new JLabel("Corps");
		lblCorps.setForeground(Color.WHITE);
		lblCorps.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCorps.setBounds(365, 201, 81, 14);
		pnlSortiesGenerales.add(lblCorps);

		lblTempsRelcoul = new JLabel("Temps r\u00E9el \u00E9coul\u00E9 : ");
		lblTempsRelcoul.setForeground(Color.WHITE);
		lblTempsRelcoul.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTempsRelcoul.setBounds(10, 269, 169, 23);
		pnlSortiesGenerales.add(lblTempsRelcoul);

		labelTempsS = new JLabel("0");
		labelTempsS.setForeground(Color.WHITE);
		labelTempsS.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelTempsS.setBounds(215, 235, 122, 23);
		pnlSortiesGenerales.add(labelTempsS);

		labelTempsR = new JLabel("0");
		labelTempsR.setForeground(Color.WHITE);
		labelTempsR.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelTempsR.setBounds(215, 271, 122, 21);
		pnlSortiesGenerales.add(labelTempsR);

		lblSecondes = new JLabel("secondes");
		lblSecondes.setForeground(Color.WHITE);
		lblSecondes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSecondes.setBounds(365, 235, 81, 23);
		pnlSortiesGenerales.add(lblSecondes);

		lblSecondes2 = new JLabel("secondes");
		lblSecondes2.setForeground(Color.WHITE);
		lblSecondes2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSecondes2.setBounds(365, 270, 81, 23);
		pnlSortiesGenerales.add(lblSecondes2);

		lblExposantPotentielle = new JLabel("");
		lblExposantPotentielle.setForeground(Color.WHITE);
		lblExposantPotentielle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblExposantPotentielle.setBounds(280, 99, 48, 23);
		pnlSortiesGenerales.add(lblExposantPotentielle);

		lblExposantCT = new JLabel("");
		lblExposantCT.setForeground(Color.WHITE);
		lblExposantCT.setBounds(280, 62, 57, 23);
		pnlSortiesGenerales.add(lblExposantCT);

		lblExposantET = new JLabel("");
		lblExposantET.setForeground(Color.WHITE);
		lblExposantET.setBounds(280, 25, 57, 23);
		pnlSortiesGenerales.add(lblExposantET);

		lblExposantQM = new JLabel("");
		lblExposantQM.setForeground(Color.WHITE);
		lblExposantQM.setBounds(280, 133, 57, 23);
		pnlSortiesGenerales.add(lblExposantQM);

		pnlOutils = new JPanel();
		pnlOutils.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Outils",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnlOutils.setBounds(1376, 11, 518, 302);
		contentPane.add(pnlOutils);
		pnlOutils.setLayout(null);
		pnlOutils.setBackground(COULEUR_ARRIERE);
		btnDemarrer = new JButton("Start");
		btnDemarrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tempsDepartPause > 0) {
					tempsFinPause = System.currentTimeMillis();
					tempsPause += (tempsFinPause - tempsDepartPause) / 1000;
				}
				if (tempsDepart == 0) {
					tempsDepart = System.currentTimeMillis();
				}
				tabbedPane.setEnabledAt(1, true);
				tabbedPane.setEnabledAt(0, false);
				tabbedPane.setSelectedComponent(pnlInfo);
				Boutons.btnDemarrer(simulation);
				btnDemarrer.setEnabled(false);
			}
		});
		btnDemarrer.setBounds(96, 31, 97, 97);
		pnlOutils.add(btnDemarrer);
		Fenetre.associerBoutonAvecImage(btnDemarrer, "demarrer.png", Aapplication.this);
		btnDemarrer.setToolTipText("Lancer la simulation.");

		btnRecommencer = new JButton("Recommencer");
		btnRecommencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				lblCouleur.setVisible(false);// enlever les composants
				lblRayon.setVisible(false);
				lblR.setVisible(false);
				lblKm.setVisible(false);
				lblMasse.setVisible(false);
				lblM.setVisible(false);
				lblKG2.setVisible(false);
				lblMasseTerre.setVisible(false);
				chckbxExplosable.setVisible(false);

				lblInfoAccelNombre.setText("0");
				lblInfoCinetiqueNombre.setText("0");
				lblInfoMasseNombre.setText("0");
				lblInfoPotentielNombre.setText("0");
				lblInfoRayonNombre.setText("0");
				lblInfoVresultanteNombre.setText("0");
				lblExposantCinetique.setText("");
				lblExposantP.setText("");
				lblExposantAccel.setText("");
				lblExposantCT.setText("");
				lblExposantET.setText("");
				lblExposantPotentielle.setText("");
				lblExposantQM.setText("");
				lblEnergieCT.setText("0");
				lblEnergiePT.setText("0");
				lblEnergieT.setText("0");
				labelTempsR.setText("0");
				labelTempsS.setText("0");
				lblQuantiteMT.setText("0");

				btnChangeCouleurGauche.setVisible(false);
				;
				btnChangeCouleurDroite.setVisible(false);
				appercuPlanete.setVisible(false);
				spnnrRayon.setVisible(false);
				spnnrMasse.setVisible(false);
				btnRetour.setVisible(false);
				btnCorpsDeffinitif.setVisible(false);

				spnnrRayon.setEnabled(false);
				spnnrMasse.setEnabled(false);
				btnChangeCouleurGauche.setEnabled(false);
				btnChangeCouleurDroite.setEnabled(false);
				appercuPlanete.setEnabled(false);
				btnRetour.setEnabled(false);
				btnCorpsDeffinitif.setEnabled(false);

				lblPlanete.setVisible(true); // mettre les composants
				lblSatellite.setVisible(true);

				btnSelectPlanete.setVisible(true);
				btnSelectSatellite.setVisible(true);

				btnSelectPlanete.setEnabled(true);
				btnSelectSatellite.setEnabled(true);

				tabbedPane.setEnabledAt(0, true);
				tabbedPane.setSelectedComponent(panelCreation);

				suppComposantsOrbite();

				tempsDepartPause = System.currentTimeMillis();
				Boutons.btnRecommencer(simulation);
				btnDemarrer.setEnabled(true);
			}
		});
		btnRecommencer.setBounds(289, 164, 97, 97);
		pnlOutils.add(btnRecommencer);
		Fenetre.associerBoutonAvecImage(btnRecommencer, "recommencer.png", Aapplication.this);
		btnRecommencer.setToolTipText("Restaurer les paramètres par défaut.");

		btnPauseProchaineImage = new JButton("Proch. Img.");
		btnPauseProchaineImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblCouleur.setVisible(false);// enlever les composants
				lblRayon.setVisible(false);
				lblR.setVisible(false);
				lblKm.setVisible(false);
				lblMasse.setVisible(false);
				lblM.setVisible(false);
				lblKG2.setVisible(false);
				lblMasseTerre.setVisible(false);
				chckbxExplosable.setVisible(false);

				btnChangeCouleurGauche.setVisible(false);
				btnChangeCouleurDroite.setVisible(false);
				appercuPlanete.setVisible(false);
				spnnrRayon.setVisible(false);
				spnnrMasse.setVisible(false);
				btnRetour.setVisible(false);
				btnCorpsDeffinitif.setVisible(false);

				spnnrRayon.setEnabled(false);
				spnnrMasse.setEnabled(false);
				btnChangeCouleurGauche.setEnabled(false);
				btnChangeCouleurDroite.setEnabled(false);
				appercuPlanete.setEnabled(false);
				btnRetour.setEnabled(false);
				btnCorpsDeffinitif.setEnabled(false);

				lblPlanete.setVisible(true); // mettre les composants
				lblSatellite.setVisible(true);

				btnSelectPlanete.setVisible(true);
				btnSelectSatellite.setVisible(true);

				btnSelectPlanete.setEnabled(true);
				btnSelectSatellite.setEnabled(true);

				tabbedPane.setEnabledAt(0, true);
				tabbedPane.setEnabledAt(1, false);
				tabbedPane.setSelectedComponent(panelCreation);
				btnDemarrer.setEnabled(true);
				Boutons.btnProchaineImage(simulation);
				suppComposantsOrbite();
			}
		});
		btnPauseProchaineImage.setBounds(289, 31, 97, 97);
		pnlOutils.add(btnPauseProchaineImage);
		Fenetre.associerBoutonAvecImage(btnPauseProchaineImage, "pauseProchaineImage.png", Aapplication.this);
		btnPauseProchaineImage.setToolTipText(
				"Arrêter la simulation si elle ne l'est pas déjà, puis afficher la prochaine image à chaque nouveau clic.");

		JButton btnEfface = new JButton("Efface");
		btnEfface.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Aapplication.setestPretASupprimer(true);
			}
		});
		btnEfface.setBounds(96, 164, 97, 97);
		pnlOutils.add(btnEfface);
		btnEfface.setVisible(true);
		Fenetre.associerBoutonAvecImage(btnEfface, "efface.png", Aapplication.this);
		btnEfface.setToolTipText("Après avoir cliquer sur ce bouton, la prochaine planète sélectionnée sera effacée.");

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		tabbedPane.setBounds(1376, 326, 520, 690);

		tabbedPane.setBounds(1376, 326, 518, 682);

		contentPane.add(tabbedPane);

		setUpOnglets(); // Méthode de Xavier pour Caroline ;)

		simulation = new Simulation();
		simulation.addPlanetListener(new PlanetListener() {
			public void clickPlanet(double masse, double accel, double rayon, double vitesse, boolean click,
					double eCinetique, double ePotentielle) {
				// System.out.print("ecouteur planete fonctionne");
				tabbedPane.setSelectedComponent(pnlInfo);
				for (Corps corps : simulation.getEnsembleDesCorps()) {
					if (simulation.getClickPlanete() && corps != null) {
						corps = simulation.getcorpsClicker();
						tabbedPane.setSelectedComponent(pnlInfo);
						lblInfoMasseNombre.setText("" + (int) (masse / (5.972 * 10e24)));
						lblInfoRayonNombre.setText("" + corps.getRayon());
						if (accel == 0) {
							lblInfoAccelNombre.setText("0");
						} else {
							lblInfoAccelNombre.setText("" + de.format(accel));
						}
						lblInfoVresultanteNombre.setText("" + de.format(corps.getVitesse().getModule()));
						if (ePotentielle == 0) {
							lblInfoPotentielNombre.setText("0");
							lblExposantP.setText("" + SMath.getExponentForNumber(ePotentielle));
						} else {
							lblInfoPotentielNombre.setText("" + de.format(SMath.getDebutNombre(ePotentielle)));
							lblExposantP.setText("E" + SMath.getExponentForNumber(ePotentielle));
						}
						lblInfoCinetiqueNombre.setText("" + de.format(SMath.getDebutNombre(eCinetique))); // a changer
						lblExposantCinetique.setText("E" + SMath.getExponentForNumber(eCinetique));
					}
				}
			}
		});
		simulation.addZoneAnimationListener(new ZoneAnimationListener() {
			public void cinétiqueTotaleUpdate(double energieCinetique) {
				if (energieCinetique == 0) {
					lblEnergieCT.setText("0");
				} else {
					lblEnergieCT.setText(de.format(SMath.getDebutNombre(energieCinetique)) + "");
					lblExposantCT.setText("E" + SMath.getExponentForNumber(energieCinetique));
				}
			}

			public void energieTotaleUpdate(double energieT) {
				if (energieT == 0) {
					lblEnergieT.setText("0");
				} else {
					lblEnergieT.setText("" + de.format(SMath.getDebutNombre(energieT)));
					lblExposantET.setText("E" + SMath.getExponentForNumber(energieT));
				}
			}

			public void masseTotaleUpdate(double masse) {
				masseTotale = masse;
				lblMasseT.setText((int) (masse / (5.972 * 10e24)) + "");
			}

			public void nombreCorpsUpdate(int nbCorps) {
				// System.out.print("ecouteur zone fonctionne" + nbCorps);
				lblNombreC.setText(nbCorps + "");
			}

			public void potentielleTotaleUpdate(double energiePotentielle) {
				if (energiePotentielle == 0) {
					lblEnergiePT.setText("0");
				} else {
					lblEnergiePT.setText("" + de.format(SMath.getDebutNombre(energiePotentielle)));
					lblExposantPotentielle.setText("E" + SMath.getExponentForNumber(energiePotentielle));
				}
			}

			public void quantiteMouvTotaleUpdate(double quantiteMouv) {
				if (quantiteMouv == 0) {
					lblQuantiteMT.setText("0");
				} else {

					lblQuantiteMT.setText("" + de.format(SMath.getDebutNombre(quantiteMouv)));
					lblExposantQM.setText("E" + SMath.getExponentForNumber(quantiteMouv));
				}
			}

			public void tempsReelUpdate(long tempsR) {
				labelTempsR.setText("" + de.format(((tempsR - tempsDepart) / 1000.0) - tempsPause));
			}

			public void tempsSimulationUpdate(double tempsS) {
				labelTempsS.setText(tempsS + "");
			}

			@Override
			public void zoneAnimationUpdate(int nbCorps, double energieT, double eCinetique, double ePotentielle,
					double quantiteMouv, double masse, double tempsR, double tempsS) {

			}
		});

		/*
		 * simulation.addPlanetListener(new PlanetListener() { public void
		 * clickPlanet(double masse, double acceleration, double rayon, double vitesse ,
		 * boolean click) { System.out.print("ecouteur planete fonctionne"); for (Corps
		 * corps : ensembleDesCorps) { corps = ensembleDesCorps.get(nbCorps); nbCorps++;
		 * if (simulation.getClickPlanete()) {
		 * tabbedPane.setSelectedComponent(panelInfo);
		 * lblInfoMasseNombre.setText(""+corps.getMasse());
		 * lblInfoRayonNombre.setText("" + corps.getRayon());
		 * lblInfoAccelNombre.setText("" + corps.getAcceleration());
		 * lblInfoVresultanteNombre.setText("" +corps.getVitesse()); repaint(); } } //}
		 * });
		 */
		simulation.setBounds(10, 326, 1357, 682);
		contentPane.add(simulation);

		// Debut Affichage test

		AppercuCorps appercu = new AppercuCorps();
		appercu.setBounds(10, 10, 100, 100);
	}

	// par Antonin Tritz
	public void setChckbxMur1(boolean b) {
		chckbxMurHaut.setSelected(b);
	}// fin de la méthode

	// par Antonin Tritz
	public void setChckbxMur2(boolean b) {
		chckbxMurDroite.setSelected(b);
	}// fin de la méthode

	// par Antonin Tritz
	public void setChckbxMur3(boolean b) {
		chckbxMurBas.setSelected(b);
	}// fin de la méthode

	// par Antonin Tritz
	public void setChckbxMur4(boolean b) {
		chckbxMurGauche.setSelected(b);
	}// fin de la méthode

	// par Antonin Tritz
	public void setRbtnElastique(boolean b) {
		rdbtnElastique.setSelected(b);
	}// fin de la méthode

	// par Antonin Tritz
	public void setRdbtnInlastique(boolean b) {
		rdbtnInlastique.setSelected(b);
	}// fin de la méthode

	// par Antonin Tritz
	public void setChckbxAfficherVec(boolean b) {
		chckbxAfficherVec.setSelected(b);
	}// fin de la méthode

	// par Antonin Tritz
	public void setComboBoxPasIteration(int index) {
		comboBoxPasIteration.setSelectedIndex(index);
	}// fin de la méthode

	// par Antonin Tritz
	public void setBtnRecommencer(boolean b) {
		btnRecommencer.setEnabled(b);
	}// fin de la méthode

	// par Antonin Tritz
	public void setBtnDemarrer(boolean b) {
		btnDemarrer.setEnabled(b);
	}// fin de la méthode

	// par Antonin Tritz
	public void setBtnProchaineImage(boolean b) {
		btnPauseProchaineImage.setEnabled(b);
	}// fin de la méthode

	// par Antonin Tritz
	public void setTabbedPane(int index) {
		tabbedPane.setSelectedIndex(index);
	}// fin de la méthode

	// par Antonin Tritz
	public void setSpnnrRayon(int valeur) {
		spnnrRayon.setValue(valeur);
	}// fin de la méthode

	// par Antonin Tritz
	public void setSpnnrMasse(int valeur) {
		spnnrMasse.setValue(valeur);
	}// fin de la méthode

	// charlyne
	// Code Emprunté du Tp2
	/**
	 * Retourne le {@link String} sélectionné dans le {@link JComboBox}
	 * 
	 * @param cb La liste déroulante à utiliser
	 * @return La chaîne sélectionnée, null si c'est impossible
	 */
	public static Object getItemInComboBox(JComboBox<String> cb) {
		try {
			return cb.getSelectedItem();
		} catch (Exception e) {
			System.err.println("Unable to get selected item in combo box");
			return null;
		}
	}

	// Xavier
	/**
	 * Sert a retourner la valeur boolean de estPretASupprimer
	 * 
	 * @author Xavier Boucher
	 * @return La valeur boolean de estPretASupprimer
	 */
	public static boolean estPretASupprimerValue() {
		return estPretASupprimer;
	}

	// Xavier
	/**
	 * Sert a changer la valeur du boolean estPretASupprimer a l'extérieur de
	 * l'application
	 * 
	 * @author Xavier Boucher
	 * @param b est la valeur que l'on veut donner au boolean estPretASupprimer
	 */
	public static void setestPretASupprimer(boolean b) {
		estPretASupprimer = b;
	}

	/**
	 * @author Xavier Boucher
	 * @return la valeur du boolean isDessinVecteur
	 */
	public static boolean estIsDessinVecteur() {
		return isDessinVecteur;
	}

	/**
	 * @author Xavier Boucher
	 * @return la valeur du boolean isDessinVecteurS
	 */
	public static boolean estIsDessinVecteurS() {
		return isDessinVecteurS;
	}

	public static Simulation getSimulation() {
		return simulation;
	}

	// Xavier
	/**
	 * Méthode servant a placer les composants des onglets et à gérer les
	 * paramettres de création de corps
	 * 
	 */

	private void setUpOnglets() {
		// debut Xavier

		panelCreation = new JPanel();
		tabbedPane.addTab("Creation", null, panelCreation, null);
		panelCreation.setLayout(null);
		panelCreation.setBackground(COULEUR_ARRIERE);

		lblSatellite = new JLabel("Satellite");
		lblSatellite.setForeground(Color.WHITE);
		lblSatellite.setBounds(232, 369, 60, 14);
		panelCreation.add(lblSatellite);

		lblCouleur = new JLabel("Couleur");
		lblCouleur.setVisible(false);
		lblCouleur.setBounds(232, 30, 48, 14);
		lblCouleur.setForeground(Color.WHITE);
		panelCreation.add(lblCouleur);

		lblRayon = new JLabel("Rayon :");
		lblRayon.setForeground(Color.WHITE);
		lblRayon.setVisible(false);
		lblRayon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRayon.setBounds(48, 262, 108, 23);
		panelCreation.add(lblRayon);

		lblR = new JLabel("5000");
		lblR.setVisible(false);
		lblR.setForeground(Color.WHITE);
		lblR.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblR.setBounds(112, 263, 96, 23);
		panelCreation.add(lblR);

		lblKm = new JLabel("Km");
		lblKm.setForeground(Color.WHITE);
		lblKm.setVisible(false);
		lblKm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKm.setBounds(205, 263, 48, 23);
		panelCreation.add(lblKm);

		lblMasse = new JLabel("Masse :");
		lblMasse.setForeground(Color.WHITE);
		lblMasse.setVisible(false);
		lblMasse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMasse.setBounds(48, 358, 108, 23);
		panelCreation.add(lblMasse);

		lblM = new JLabel("5.0");
		lblM.setVisible(false);
		lblM.setForeground(Color.WHITE);
		lblM.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblM.setBounds(112, 359, 96, 23);
		panelCreation.add(lblM);

		lblKG2 = new JLabel("M\u2295");
		lblKG2.setForeground(Color.WHITE);
		lblKG2.setVisible(false);
		lblKG2.setFont(new Font("Cambria", Font.PLAIN, 12));
		lblKG2.setBounds(205, 363, 34, 14);
		panelCreation.add(lblKG2);

		lblMasseTerre = new JLabel("(Masse de la Terre)");
		lblMasseTerre.setForeground(Color.WHITE);
		lblMasseTerre.setVisible(false);
		lblMasseTerre.setBounds(205, 377, 159, 14);
		panelCreation.add(lblMasseTerre);

		spnnrORatioMasse.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				orbite.setMiseEnOrbite((int) spnnrORatioRayon.getValue(), (int) spnnrORatioMasse.getValue(),
						(int) spnnrORayonTotal.getValue() * 1000,
						(double) spnnrOMasseTotal.getValue() * (5.972 * 10e24), (int) spnnrODistance.getValue() * 1000,
						simulation);
			}
		});

		spnnrORayonTotal.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				orbite.setMiseEnOrbite((int) spnnrORatioRayon.getValue(), (int) spnnrORatioMasse.getValue(),
						(int) spnnrORayonTotal.getValue() * 1000,
						(double) spnnrOMasseTotal.getValue() * (5.972 * 10e24), (int) spnnrODistance.getValue() * 1000,
						simulation);
			}
		});

		spnnrOMasseTotal.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				orbite.setMiseEnOrbite((int) spnnrORatioRayon.getValue(), (int) spnnrORatioMasse.getValue(),
						(int) spnnrORayonTotal.getValue() * 1000,
						(double) spnnrOMasseTotal.getValue() * (5.972 * 10e24), (int) spnnrODistance.getValue() * 1000,
						simulation);
			}
		});

		spnnrODistance.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				orbite.setMiseEnOrbite((int) spnnrORatioRayon.getValue(), (int) spnnrORatioMasse.getValue(),
						(int) spnnrORayonTotal.getValue() * 1000,
						(double) spnnrOMasseTotal.getValue() * (5.972 * 10e24), (int) spnnrODistance.getValue() * 1000,
						simulation);
			}
		});

		spnnrORatioRayon.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				orbite.setMiseEnOrbite((int) spnnrORatioRayon.getValue(), (int) spnnrORatioMasse.getValue(),
						(int) spnnrORayonTotal.getValue() * 1000,
						(double) spnnrOMasseTotal.getValue() * (5.972 * 10e24), (int) spnnrODistance.getValue() * 1000,
						simulation);
			}
		});
		spnnrORatioRayon.setModel(new SpinnerNumberModel(25, 1, 95, 1));
		spnnrORatioRayon.setBounds(46, 115, 83, 20);
		spnnrORatioRayon.setVisible(false);
		spnnrORatioRayon.setEnabled(false);
		panelCreation.add(spnnrORatioRayon);

		spnnrORatioMasse.setModel(new SpinnerNumberModel(10, 1, 70, 1));
		spnnrORatioMasse.setBounds(46, 169, 83, 20);
		spnnrORatioMasse.setVisible(false);
		spnnrORatioMasse.setEnabled(false);
		panelCreation.add(spnnrORatioMasse);

		spnnrORayonTotal.setModel(new SpinnerNumberModel(5000, 1000, 70000, 2500));
		spnnrORayonTotal.setBounds(46, 220, 83, 20);
		spnnrORayonTotal.setVisible(false);
		spnnrORayonTotal.setEnabled(false);
		panelCreation.add(spnnrORayonTotal);

		spnnrOMasseTotal.setModel(new SpinnerNumberModel(5.0, 1.0, 318.0, 1.0));
		spnnrOMasseTotal.setBounds(47, 272, 83, 20);
		spnnrOMasseTotal.setVisible(false);
		spnnrOMasseTotal.setEnabled(false);
		panelCreation.add(spnnrOMasseTotal);

		spnnrODistance.setModel(new SpinnerNumberModel(25000, 5000, 5000000, 5000));
		spnnrODistance.setBounds(46, 329, 83, 20);
		spnnrODistance.setVisible(false);
		spnnrODistance.setEnabled(false);
		panelCreation.add(spnnrODistance);

		btnORetour = new JButton("retour");
		btnORetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				suppComposantsOrbite();

				orbite.deleteOrbite(simulation);
			}
		});
		btnORetour.setEnabled(false);
		btnORetour.setVisible(false);
		btnORetour.setBounds(10, 26, 77, 23);
		panelCreation.add(btnORetour);

		// ----------------------------------------------------------------------------------------------------------------------------

		btnSelectSatellite = new JButton("Select Satellite");
		btnSelectSatellite.setBounds(206, 402, 100, 100);
		panelCreation.add(btnSelectSatellite);
		btnSelectSatellite.setToolTipText("Créer un système orbital.");
		Fenetre.associerBoutonAvecImage(btnSelectSatellite, "btnOrbite.png", Aapplication.this);
		btnSelectSatellite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				lblPlanete.setVisible(false); // enlever les composants
				lblSatellite.setVisible(false);

				btnSelectPlanete.setVisible(false);
				btnSelectSatellite.setVisible(false);

				btnSelectPlanete.setEnabled(false);
				btnSelectSatellite.setEnabled(false);

				spnnrORatioRayon.setVisible(true); // Mettre des composant
				spnnrORatioRayon.setEnabled(true);

				spnnrORatioMasse.setVisible(true);
				spnnrORatioMasse.setEnabled(true);

				spnnrOMasseTotal.setVisible(true);
				spnnrOMasseTotal.setEnabled(true);

				spnnrORayonTotal.setVisible(true);
				spnnrORayonTotal.setEnabled(true);

				spnnrODistance.setVisible(true);
				spnnrODistance.setEnabled(true);

				lblRatioRayon.setVisible(true);
				lblRatioRayon2.setVisible(true);
				lblRatioMasse.setVisible(true);
				lblRatioMasse2.setVisible(true);
				lblRayonTotal.setVisible(true);
				lblMasseTotal.setVisible(true);
				lblDistance.setVisible(true);

				btnOCreer.setEnabled(true);
				btnOCreer.setVisible(true);

				btnOLeft.setEnabled(true);
				btnOLeft.setVisible(true);
				btnOUp.setEnabled(true);
				btnOUp.setVisible(true);
				btnORight.setEnabled(true);
				btnORight.setVisible(true);
				btnODown.setEnabled(true);
				btnODown.setVisible(true);

				btnORetour.setEnabled(true);
				btnORetour.setVisible(true);

				orbite = new MiseEnOrbite(5000 * 1000, 5 * (5.972 * 10e24), 25000 * 1000);
				orbite.miseEnOrbite(simulation);

				spnnrORatioRayon.setValue(25);
				spnnrORatioMasse.setValue(10);
				spnnrORayonTotal.setValue(5000);
				spnnrOMasseTotal.setValue(5.0);
				spnnrODistance.setValue(25000);

				// System.out.println("Le bouton \"btnSelectSatellite\" a été actionner!");

			}
		});

		appercuPlanete = new AppercuCorps();

		btnChangeCouleurGauche = new JButton("Change couleur gauche");
		btnChangeCouleurGauche.setEnabled(false);
		btnChangeCouleurGauche.setVisible(false);
		btnChangeCouleurGauche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// System.out.println("Le bouton \"btnChangeCouleurGauche\" a été actionner!");

				appercuPlanete.precedant();
				gestionCouleurOuImage(appercuPlanete, corpsCree);
				simulation.repaint();
			}
		});
		btnChangeCouleurGauche.setBounds(88, 137, 50, 50);
		panelCreation.add(btnChangeCouleurGauche);
		Fenetre.associerBoutonAvecImage(btnChangeCouleurGauche, "defilerGauche.png", Aapplication.this);
		btnChangeCouleurGauche.setToolTipText("Passer à la prochaine image de gauche.");

		btnChangeCouleurDroite = new JButton("Change couleur droite");
		btnChangeCouleurDroite.setEnabled(false);
		btnChangeCouleurDroite.setVisible(false);
		btnChangeCouleurDroite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// System.out.println("Le bouton \"btnChangeCouleurDroite\" a été actionner!");

				appercuPlanete.suivant();
				gestionCouleurOuImage(appercuPlanete, corpsCree);
				simulation.repaint();
			}

		});

		btnChangeCouleurDroite.setBounds(375, 137, 50, 50);
		Fenetre.associerBoutonAvecImage(btnChangeCouleurDroite, "deflierDroite.png", Aapplication.this);
		btnChangeCouleurDroite.setToolTipText("Passer à la prochaine image de droite.");

		panelCreation.add(btnChangeCouleurDroite);
		appercuPlanete.setEnabled(false);
		appercuPlanete.setVisible(false);
		appercuPlanete.setBounds(206, 112, 100, 100);
		panelCreation.add(appercuPlanete);

		spnnrRayon = new JSpinner();
		spnnrRayon.setEnabled(false);
		spnnrRayon.setVisible(false);
		spnnrRayon.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {

				// System.out.println("Le spiner \"spnnrRayon\" a changer d'état");
				lblR.setText("" + (int) spnnrRayon.getValue());

				corpsCree.setRayon((int) spnnrRayon.getValue() * 1000);

				simulation.repaint(); // a enlever

			}
		});
		spnnrRayon.setModel(new SpinnerNumberModel(5000, 500, 70000, 500));
		spnnrRayon.setBounds(48, 297, 119, 33);
		panelCreation.add(spnnrRayon);

		spnnrMasse = new JSpinner();
		spnnrMasse.setEnabled(false);
		spnnrMasse.setVisible(false);
		spnnrMasse.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				// System.out.println("Le spiner \"spnnrMasse\" a changer d'état");
				lblM.setText("" + (double) spnnrMasse.getValue());
				corpsCree.setMasse((double) spnnrMasse.getValue() * (5.972 * 10e24)); // *5.972*10e24 ); ceci est la
				// masse de la terre,
				// techniquement il faudrait la multiplier ici, mais cela donne des problèmes
				// dans simulation
				// On ne voit pas bien l'animation, soit rien ne bouge, soit tout bouge trop
				// vite et on ne peut pas voir ce qui se passe
				simulation.repaint(); // a enlever

				ecart = (double) spnnrMasse.getValue() - 5.0;
				lblMasseT.setText((masseTotale / (5.972 * 10e24) + (ecart)) + "");
			}
		});
		spnnrMasse.setModel(new SpinnerNumberModel(5.0, 1.0, 318.0, 1.0));
		spnnrMasse.setBounds(48, 397, 119, 33);
		panelCreation.add(spnnrMasse);

		btnSelectPlanete = new JButton("Select Planete");
		btnSelectPlanete.setBounds(206, 151, 100, 100);
		panelCreation.add(btnSelectPlanete);
		btnSelectPlanete.setToolTipText("Créer une planète.");
		Fenetre.associerBoutonAvecImage(btnSelectPlanete, "btnPlanete.png", Aapplication.this);
		btnSelectPlanete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// System.out.println("Le bouton \"btnSelectPlanete\" a été actionner!");

				corpsCree = new Corps(new Vecteur(0, 0), 5 * (5.972 * 10e24), 5000000);

				simulation.ajouterUnCorps(corpsCree);

				lblPlanete.setVisible(false); // enlever les composants
				lblSatellite.setVisible(false);

				creeBoutonExplosable();

				chckbxExplosable.setVisible(true);
				chckbxExplosable.setEnabled(true);
				chckbxExplosable.setSelected(false);

				btnSelectPlanete.setVisible(false);
				btnSelectSatellite.setVisible(false);

				btnSelectPlanete.setEnabled(false);
				btnSelectSatellite.setEnabled(false);

				lblCouleur.setVisible(true);// mettre les composants
				lblRayon.setVisible(true);
				lblR.setVisible(true);
				lblKm.setVisible(true);
				lblMasse.setVisible(true);
				lblM.setVisible(true);
				lblKG2.setVisible(true);
				lblMasseTerre.setVisible(true);

				btnChangeCouleurGauche.setVisible(true);
				btnChangeCouleurDroite.setVisible(true);
				appercuPlanete.setVisible(true);
				spnnrRayon.setVisible(true);
				spnnrMasse.setVisible(true);
				btnRetour.setVisible(true);
				btnCorpsDeffinitif.setVisible(true);

				spnnrRayon.setEnabled(true);
				spnnrMasse.setEnabled(true);
				btnChangeCouleurGauche.setEnabled(true);
				btnChangeCouleurDroite.setEnabled(true);
				appercuPlanete.setEnabled(true);
				btnCorpsDeffinitif.setEnabled(true);
				btnRetour.setEnabled(true);

				appercuPlanete.reset();
				spnnrRayon.setValue(5000);
				spnnrMasse.setValue(5.0);
				lblM.setText("5.0");

			}
		});

		btnCorpsDeffinitif = new JButton("Cr\u00E9er le corps");
		btnCorpsDeffinitif.setEnabled(true);
		btnCorpsDeffinitif.setVisible(false);
		btnCorpsDeffinitif.setBounds(181, 565, 119, 50);
		panelCreation.add(btnCorpsDeffinitif);
		btnCorpsDeffinitif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblCouleur.setVisible(false);// enlever les composants
				lblRayon.setVisible(false);
				lblR.setVisible(false);
				lblKm.setVisible(false);
				lblMasse.setVisible(false);
				lblM.setVisible(false);
				lblKG2.setVisible(false);
				lblMasseTerre.setVisible(false);

				btnChangeCouleurGauche.setVisible(false);
				btnChangeCouleurDroite.setVisible(false);
				appercuPlanete.setVisible(false);
				spnnrRayon.setVisible(false);
				spnnrMasse.setVisible(false);
				btnRetour.setVisible(false);
				btnCorpsDeffinitif.setVisible(false);
				chckbxExplosable.setVisible(false);

				spnnrRayon.setEnabled(false);
				spnnrMasse.setEnabled(false);
				btnChangeCouleurGauche.setEnabled(false);
				btnChangeCouleurDroite.setEnabled(false);
				appercuPlanete.setEnabled(false);
				btnRetour.setEnabled(false);
				btnCorpsDeffinitif.setEnabled(false);

				lblPlanete.setVisible(true); // mettre les composants
				lblSatellite.setVisible(true);

				btnSelectPlanete.setVisible(true);
				btnSelectSatellite.setVisible(true);

				btnSelectPlanete.setEnabled(true);
				btnSelectSatellite.setEnabled(true);

				corpsCree = new Corps(new Vecteur(0, 0), 0.0, 0.0);
				btnCorpsDeffinitif.setEnabled(false);
			}
		});
		btnRetour = new JButton("retour");
		btnRetour.setEnabled(false);
		btnRetour.setVisible(false);
		btnRetour.setBounds(10, 26, 77, 23);
		panelCreation.add(btnRetour);
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// System.out.println("Le bouton \"btnRetour\" a été actionner!");
				simulation.supprimerUnCorps(corpsCree);

				lblCouleur.setVisible(false);// enlever les composants
				lblRayon.setVisible(false);
				lblR.setVisible(false);
				lblKm.setVisible(false);
				lblMasse.setVisible(false);
				lblM.setVisible(false);
				lblKG2.setVisible(false);
				lblMasseTerre.setVisible(false);

				btnChangeCouleurGauche.setVisible(false);
				btnChangeCouleurDroite.setVisible(false);
				appercuPlanete.setVisible(false);
				spnnrRayon.setVisible(false);
				spnnrMasse.setVisible(false);
				btnRetour.setVisible(false);
				btnCorpsDeffinitif.setVisible(false);

				spnnrRayon.setEnabled(false);
				spnnrMasse.setEnabled(false);
				btnChangeCouleurGauche.setEnabled(false);
				btnChangeCouleurDroite.setEnabled(false);
				appercuPlanete.setEnabled(false);
				btnRetour.setEnabled(false);
				btnCorpsDeffinitif.setEnabled(false);

				chckbxExplosable.setEnabled(false);
				chckbxExplosable.setVisible(false);

				lblPlanete.setVisible(true); // mettre les composants
				lblSatellite.setVisible(true);

				btnSelectPlanete.setVisible(true);
				btnSelectSatellite.setVisible(true);

				btnSelectPlanete.setEnabled(true);
				btnSelectSatellite.setEnabled(true);

			}
		});

		lblCouleur.setVisible(false);// enlever les composants
		lblRayon.setVisible(false);
		lblR.setVisible(false);
		lblKm.setVisible(false);
		lblMasse.setVisible(false);
		lblM.setVisible(false);
		lblKG2.setVisible(false);
		lblMasseTerre.setVisible(false);

		btnChangeCouleurGauche.setVisible(false);
		btnChangeCouleurDroite.setVisible(false);
		appercuPlanete.setVisible(false);
		spnnrRayon.setVisible(false);
		spnnrMasse.setVisible(false);
		btnRetour.setVisible(false);
		btnCorpsDeffinitif.setVisible(false);
		spnnrRayon.setEnabled(false);
		spnnrMasse.setEnabled(false);
		btnChangeCouleurGauche.setEnabled(false);
		btnChangeCouleurDroite.setEnabled(false);
		appercuPlanete.setEnabled(false);
		btnRetour.setEnabled(false);
		btnCorpsDeffinitif.setEnabled(false);
		lblSatellite.setVisible(true);
		btnSelectPlanete.setVisible(true);
		btnSelectSatellite.setVisible(true);
		btnSelectPlanete.setEnabled(true);
		btnSelectSatellite.setEnabled(true);

		lblPlanete = new JLabel("Plan\u00E8te");
		lblPlanete.setBounds(232, 106, 74, 14);
		panelCreation.add(lblPlanete);
		lblPlanete.setForeground(Color.WHITE);

		chckbxExplosable = new JCheckBox("Explosable");
		chckbxExplosable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxExplosable.isSelected()) {
					corpsCree.setExplosable(true);
				} else {
					corpsCree.setEstImage(false);
				}
			}
		});
		chckbxExplosable.setForeground(Color.WHITE);
		chckbxExplosable.setBounds(48, 464, 97, 23);
		chckbxExplosable.setBackground(COULEUR_ARRIERE);
		chckbxExplosable.setVisible(false);
		panelCreation.add(chckbxExplosable);

		lblRatioRayon.setVisible(false);
		lblRatioRayon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRatioRayon.setForeground(Color.WHITE);
		lblRatioRayon.setBounds(46, 90, 275, 23);
		panelCreation.add(lblRatioRayon);

		lblRatioRayon2.setVisible(false);
		lblRatioRayon2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRatioRayon2.setForeground(Color.WHITE);
		lblRatioRayon2.setBounds(139, 118, 48, 14);
		panelCreation.add(lblRatioRayon2);

		lblRatioMasse.setVisible(false);
		lblRatioMasse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRatioMasse.setForeground(Color.WHITE);
		lblRatioMasse.setBounds(46, 144, 260, 23);
		panelCreation.add(lblRatioMasse);

		lblRatioMasse2.setVisible(false);
		lblRatioMasse2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRatioMasse2.setForeground(Color.WHITE);
		lblRatioMasse2.setBounds(139, 172, 48, 14);
		panelCreation.add(lblRatioMasse2);

		lblRayonTotal.setVisible(false);
		lblRayonTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRayonTotal.setForeground(Color.WHITE);
		lblRayonTotal.setBounds(46, 194, 199, 23);
		panelCreation.add(lblRayonTotal);

		lblMasseTotal.setVisible(false);
		lblMasseTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMasseTotal.setForeground(Color.WHITE);
		lblMasseTotal.setBounds(46, 247, 293, 23);
		panelCreation.add(lblMasseTotal);

		lblDistance.setVisible(false);
		lblDistance.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDistance.setForeground(Color.WHITE);
		lblDistance.setBounds(46, 305, 293, 23);
		panelCreation.add(lblDistance);

		btnOCreer = new JButton("Cr\u00E9er d\u00E9finitivement le syst\u00E8me");
		btnOCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orbite = new MiseEnOrbite(0.0, 0.0, 0.0);

				suppComposantsOrbite();
			}
		});
		btnOCreer.setEnabled(false);
		btnOCreer.setVisible(false);
		btnOCreer.setBounds(48, 565, 191, 50);
		panelCreation.add(btnOCreer);

		btnOUp = new JButton("^");
		btnOUp.setEnabled(false);
		btnOUp.setVisible(false);
		btnOUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orbite.MoveOrbiteUp(simulation);
			}
		});
		btnOUp.setBounds(94, 416, 40, 40);
		panelCreation.add(btnOUp);

		btnORight = new JButton(">");
		btnORight.setEnabled(false);
		btnORight.setVisible(false);
		btnORight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orbite.MoveOrbiteRight(simulation);
			}
		});
		btnORight.setBounds(140, 442, 40, 40);
		panelCreation.add(btnORight);

		btnOLeft = new JButton("<");
		btnOLeft.setEnabled(false);
		btnOLeft.setVisible(false);
		btnOLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orbite.MoveOrbiteLeft(simulation);
			}

		});
		btnOLeft.setBounds(48, 442, 40, 40);
		panelCreation.add(btnOLeft);

		btnODown = new JButton("v");
		btnODown.setEnabled(false);
		btnODown.setVisible(false);
		btnODown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orbite.MoveOrbiteDown(simulation);
			}
		});
		btnODown.setBounds(94, 467, 40, 40);
		panelCreation.add(btnODown);

		lblPlanete.setVisible(true); // mettre les composants
		pnlInfo = new JPanel();
		tabbedPane.addTab("Informations", null, pnlInfo, null);
		pnlInfo.setLayout(null);
		pnlInfo.setBackground(COULEUR_ARRIERE);

		lblInfoTitre = new JLabel("Information du corps");
		lblInfoTitre.setForeground(Color.WHITE);
		lblInfoTitre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInfoTitre.setBounds(125, 33, 240, 22);
		pnlInfo.add(lblInfoTitre);

		lblInfoMasse = new JLabel("Masse :");
		lblInfoMasse.setForeground(Color.WHITE);
		lblInfoMasse.setBounds(56, 121, 48, 14);
		pnlInfo.add(lblInfoMasse);

		lblInfoMasseNombre = new JLabel("0");
		lblInfoMasseNombre.setForeground(Color.WHITE);
		lblInfoMasseNombre.setBounds(175, 121, 146, 14);
		pnlInfo.add(lblInfoMasseNombre);

		lblInfoMasseUnite = new JLabel("M\u2295");
		lblInfoMasseUnite.setForeground(Color.WHITE);
		lblInfoMasseUnite.setBounds(374, 131, 48, 14);
		pnlInfo.add(lblInfoMasseUnite);

		lblInfoRayon = new JLabel("Rayon :");
		lblInfoRayon.setForeground(Color.WHITE);
		lblInfoRayon.setBounds(56, 198, 48, 14);
		pnlInfo.add(lblInfoRayon);

		lblInfoRayonNombre = new JLabel("0");
		lblInfoRayonNombre.setForeground(Color.WHITE);
		lblInfoRayonNombre.setBounds(175, 198, 158, 14);
		pnlInfo.add(lblInfoRayonNombre);

		lblInfoRayonUnite = new JLabel("Km");
		lblInfoRayonUnite.setForeground(Color.WHITE);
		lblInfoRayonUnite.setBounds(374, 208, 48, 14);
		pnlInfo.add(lblInfoRayonUnite);

		lblInfoCinetique = new JLabel("\u00C9nergie cin\u00E9tique :");
		lblInfoCinetique.setForeground(Color.WHITE);
		lblInfoCinetique.setBounds(56, 275, 109, 14);
		pnlInfo.add(lblInfoCinetique);

		lblInfoCinetiqueNombre = new JLabel("0");
		lblInfoCinetiqueNombre.setForeground(Color.WHITE);
		lblInfoCinetiqueNombre.setBounds(175, 275, 85, 14);
		pnlInfo.add(lblInfoCinetiqueNombre);

		lblInfoCinetiqueUnite = new JLabel("Kj");
		lblInfoCinetiqueUnite.setForeground(Color.WHITE);
		lblInfoCinetiqueUnite.setBounds(374, 285, 48, 14);
		pnlInfo.add(lblInfoCinetiqueUnite);

		lblInfoPotentiel = new JLabel("\u00C9nergie potentielle :");
		lblInfoPotentiel.setForeground(Color.WHITE);
		lblInfoPotentiel.setBounds(56, 352, 117, 14);
		pnlInfo.add(lblInfoPotentiel);

		lblInfoPotentielNombre = new JLabel("0");
		lblInfoPotentielNombre.setForeground(Color.WHITE);
		lblInfoPotentielNombre.setBounds(175, 352, 85, 14);
		pnlInfo.add(lblInfoPotentielNombre);

		lblInfoPotentielUnite = new JLabel("Kj");
		lblInfoPotentielUnite.setForeground(Color.WHITE);
		lblInfoPotentielUnite.setBounds(374, 362, 48, 14);
		pnlInfo.add(lblInfoPotentielUnite);

		lblInfoAccel = new JLabel("Acc\u00E9l\u00E9ration :");
		lblInfoAccel.setForeground(Color.WHITE);
		lblInfoAccel.setBounds(56, 429, 85, 14);
		pnlInfo.add(lblInfoAccel);

		lblInfoAccelNombre = new JLabel("0");
		lblInfoAccelNombre.setForeground(Color.WHITE);
		lblInfoAccelNombre.setBounds(175, 429, 73, 14);
		pnlInfo.add(lblInfoAccelNombre);

		lblInfoAccelUnite = new JLabel("m/s\u00B2");
		lblInfoAccelUnite.setForeground(Color.WHITE);
		lblInfoAccelUnite.setBounds(374, 439, 48, 14);
		pnlInfo.add(lblInfoAccelUnite);

		lblInfoVResultante = new JLabel("Vitesse r\u00E9sultante :");
		lblInfoVResultante.setForeground(Color.WHITE);
		lblInfoVResultante.setBounds(56, 506, 112, 14);
		pnlInfo.add(lblInfoVResultante);

		lblInfoVresultanteNombre = new JLabel("0");
		lblInfoVresultanteNombre.setForeground(Color.WHITE);
		lblInfoVresultanteNombre.setBounds(175, 506, 146, 14);
		pnlInfo.add(lblInfoVresultanteNombre);

		lblInfoVResultanteUnite = new JLabel("m/s");
		lblInfoVResultanteUnite.setForeground(Color.WHITE);
		lblInfoVResultanteUnite.setBounds(374, 516, 48, 14);
		pnlInfo.add(lblInfoVResultanteUnite);

		JCheckBox chckbxVecteurForeceResultante = new JCheckBox("Afficher le vecteur force r\u00E9sultante");
		chckbxVecteurForeceResultante.setForeground(Color.WHITE);
		chckbxVecteurForeceResultante.setBackground(COULEUR_ARRIERE);
		chckbxVecteurForeceResultante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (chckbxVecteurForeceResultante.isSelected()) {
					isDessinVecteurS = true;
				} else {
					isDessinVecteurS = false;
				}
				// System.out.println("La boite a cocher est maintenant : " +
				// chckbxVecteurForeceResultante.isSelected());

			}
		});
		chckbxVecteurForeceResultante.setSelected(false);
		chckbxVecteurForeceResultante.setBounds(56, 616, 276, 23);
		pnlInfo.add(chckbxVecteurForeceResultante);

		lblExposantP = new JLabel("");
		lblExposantP.setForeground(Color.WHITE);
		lblExposantP.setBounds(234, 352, 57, 14);
		pnlInfo.add(lblExposantP);

		lblExposantAccel = new JLabel("");
		lblExposantAccel.setForeground(Color.WHITE);
		lblExposantAccel.setBounds(234, 429, 57, 14);
		pnlInfo.add(lblExposantAccel);

		lblExposantCinetique = new JLabel("");
		lblExposantCinetique.setForeground(Color.WHITE);
		lblExposantCinetique.setBounds(234, 275, 57, 14);
		pnlInfo.add(lblExposantCinetique);

		pnlFichier = new JPanel();
		tabbedPane.addTab("Fichier", null, pnlFichier, null);
		pnlFichier.setLayout(null);
		pnlFichier.setBackground(COULEUR_ARRIERE);

		txtFldFichierSauvegarde = new JTextField();
		txtFldFichierSauvegarde.setToolTipText("Écrivez ici le nom que vous voulez donner à votre fichier");
		txtFldFichierSauvegarde.setBounds(97, 44, 287, 41);
		pnlFichier.add(txtFldFichierSauvegarde);
		txtFldFichierSauvegarde.setColumns(10);

		txtFldFichierCharger = new JTextField();
		txtFldFichierCharger.setToolTipText("Écrivez ici le nom du fichier que vous voulez charger");
		txtFldFichierCharger.setBounds(97, 232, 287, 41);
		pnlFichier.add(txtFldFichierCharger);
		txtFldFichierCharger.setColumns(10);

		JButton btnFicherSauvegarde = new JButton("Sauvegarder le fichier");
		btnFicherSauvegarde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtFldFichierSauvegarde.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null,
							"SVP, veillez choisir un nom pour votre fichier avant de sauvegarder.\nLa sauvegarde n’as pas été effectuer.");
				} else {
					GestionFichier gestionFicher = new GestionFichier();// -
					gestionFicher.saveFile(txtFldFichierSauvegarde.getText());
				}

			}
		});
		btnFicherSauvegarde.setBounds(97, 96, 287, 82);
		pnlFichier.add(btnFicherSauvegarde);

		JButton btnFicherCharger = new JButton("Charger un fichier");
		btnFicherCharger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (txtFldFichierCharger.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null,
							"SVP, veillez écrire le nom du fichier que vous voulez charger.\nLe chargement n’as pas été effectuer.");
				} else {
					GestionFichier gestionFichier = new GestionFichier();
					gestionFichier.loadFile(txtFldFichierCharger.getText(), simulation);
				}

			}
		});
		btnFicherCharger.setBounds(97, 284, 287, 82);
		pnlFichier.add(btnFicherCharger);

		JFileChooser chooser = new JFileChooser("Choisir un fichier");
		chooser.setAcceptAllFileFilterUsed(false);
		// chooser.addChoosableFileFilter(filter);
		chooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = chooser.getSelectedFile().getName();
				System.out.println(fileName);
				if (fileName.endsWith(".txt")) {
					fileName = fileName.subSequence(0, fileName.length() - 4).toString();
				}
				GestionFichier gestionFichier = new GestionFichier();
				gestionFichier.loadFile(fileName, simulation);
			}
		});
		chooser.setBounds(10, 378, 493, 265);
		pnlFichier.add(chooser);

		// ---
		lblSauvegarder = new JLabel("Sauvegarder");
		lblSauvegarder.setForeground(Color.WHITE);
		lblSauvegarder.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSauvegarder.setBounds(97, 11, 184, 22);
		pnlFichier.add(lblSauvegarder);

		lblCharger = new JLabel("Charger");
		lblCharger.setForeground(Color.WHITE);
		lblCharger.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCharger.setBounds(97, 199, 168, 22);
		pnlFichier.add(lblCharger);
		;

		tabbedPane.setSelectedIndex(0);
		creeBoutonExplosable();

		// fin xavier
	}

	//Xavier Boucher
	/**
	 * Méthode qui enleve les composent de création en lien avec les satellites et
	 * qui replace les boutons de selections dans l'onglet création
	 * 
	 * 
	 */
	public void suppComposantsOrbite() {

		lblPlanete.setVisible(true); // mettre les composants
		lblSatellite.setVisible(true);

		btnSelectPlanete.setVisible(true);
		btnSelectSatellite.setVisible(true);

		btnSelectPlanete.setEnabled(true);
		btnSelectSatellite.setEnabled(true);

		spnnrORatioRayon.setVisible(false); // enlever des composants
		spnnrORatioRayon.setEnabled(false);

		spnnrORatioMasse.setVisible(false);
		spnnrORatioMasse.setEnabled(false);

		spnnrOMasseTotal.setVisible(false);
		spnnrOMasseTotal.setEnabled(false);

		spnnrORayonTotal.setVisible(false);
		spnnrORayonTotal.setEnabled(false);

		spnnrODistance.setVisible(false);
		spnnrODistance.setEnabled(false);

		lblRatioRayon.setVisible(false);
		lblRatioRayon2.setVisible(false);
		lblRatioMasse.setVisible(false);
		lblRatioMasse2.setVisible(false);
		lblRayonTotal.setVisible(false);
		lblMasseTotal.setVisible(false);
		lblDistance.setVisible(false);

		btnOLeft.setEnabled(false);
		btnOLeft.setVisible(false);
		btnOUp.setEnabled(false);
		btnOUp.setVisible(false);
		btnORight.setEnabled(false);
		btnORight.setVisible(false);
		btnODown.setEnabled(false);
		btnODown.setVisible(false);

		btnOCreer.setEnabled(false);
		btnOCreer.setVisible(false);

		btnORetour.setEnabled(false);
		btnORetour.setVisible(false);

	}

	// charlyne
	/**
	 * Méthode qui crée les boutons radio pour explosable ou non
	 */
	public void creeBoutonExplosable() {
	}

	// Jeanne
	/**
	 * Méthode permettant de donner au corps dans la simulation la bonne apparance,
	 * que ce soit une simple couleur ou une image, en fonction de la gamme de la
	 * classe appercuCorps
	 * 
	 * @param appercuPlanete L'objet appercuCorps permettant de choisir l'apparence
	 *                       du corps dans la simulation
	 * @param corpsCree      Le corps créé dans la simulation
	 */
	private void gestionCouleurOuImage(AppercuCorps appercuPlanete, Corps corpsCree) {
		corpsCree.setCouleur(appercuPlanete.getCouleurActuelle());

		if (!appercuPlanete.estCouleur()) {
			corpsCree.setNomImage(appercuPlanete.getNomImage());
			corpsCree.setImage(appercuPlanete.getImage());

		} else {
			corpsCree.setEstImage(false);
		}

	}
}// fin de la classe
