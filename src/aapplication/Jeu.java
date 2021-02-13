package aapplication;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import composants.Boutons;
import composants.EspaceJeu;
import composants.Simulation;
import ecouteurs.BlobListener;
import ecouteurs.ZoneAnimationListener;
import fichier.GestionFichier;
import objetdessinable.Corps;
import physique.Vecteur;
import utilitaire.Fenetre;
import utilitaire.SMath;

/**
 * Classe permettant l'affichage de la fenêtre de jeu
 * 
 * @author Antonin Tritz
 * @author Charlyne Lefrancois
 */

public class Jeu extends JFrame {

	private static final long serialVersionUID = 1L;
	private int niveau;
	private final Color COULEUR_ARRIERE = new Color(0, 0, 25);
	private JPanel contentPane;
	private DecimalFormat de = new DecimalFormat("#0.00001");
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
	private JPanel panelInfo;
	private JButton btnDemarrer;
	private JButton btnRecommencer;
	private JButton btnPauseProchaineImage;
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
	private JLabel lblExposantCinetique;
	private JLabel lblExposantMasse;
	private JLabel lblInfoPotentielUnite;
	private JLabel lblTempsSimulation;
	private JLabel lblTempsRelcoul;
	private JLabel labelTempsS;
	private JLabel labelTempsR;
	private JLabel lblSecondes;
	private JLabel lblSecondes2;
	private JLabel lblExposantPT;
	private EspaceJeu espaceJeu;
	private JCheckBox chckbxAfficherDB;
	private JCheckBox chckbxAfficherVFR;
	private JCheckBox chckbxAfficherDG;
	private boolean donneeGeneralCocher = true;
	private boolean donneeSpecifiqueCocher = true;
	private JLabel lblExposantPotentielle;

	private static int maxPosXEnValAbsolue = 66500 * (180000 / 120);
	private static int maxPosYEnValAbsolue = 33250 * (180000 / 120);
	private static int maxVitessEnValAbsolue = 10 * (180000 / 120);
	private static int densiteParDefault = 995;
	private static int nombreCorpsBase = 90;// 90
	private static int maxValCouleur = 255;
	private static int SEUIL_MINIMAL_COULEUR = 300;

	private double masseCorpsNiv1 = Math.pow(10, 19.5);
	private double masseCorpsNiv2 = Math.pow(10, 20); 
	private double masseCorpsNiv3 = Math.pow(10, 21);
	private JLabel lblExposantCT;
	private JLabel lblExposantET;
	private JLabel lblExposantQM;
	private static long tempsDepart;
	private static long tempsDepartPause;
	private static long tempsFinPause;
	private static long tempsPause;

	private String nomFichier = "";

	// Antonin Tritz
	/**
	 * Lance la classe Jeu
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
					Jeu frame = new Jeu(1, "");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}// fin de la méthode

	// Antonin Tritz
	/**
	 * Permet la construction d'un objet Jeu
	 */
	public Jeu(int niveau, String fileName) {
		this.niveau = niveau;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(0, 0, 1920, 1000);
		setTitle("Jeu " + " - niveau : " + niveau);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(COULEUR_ARRIERE);

		afficherBouton();

		chckbxAfficherDG = new JCheckBox("Afficher donn\u00E9es g\u00E9n\u00E9rales");
		chckbxAfficherDG.setSelected(true);
		chckbxAfficherDG.setForeground(Color.WHITE);
		chckbxAfficherDG.setBackground(COULEUR_ARRIERE);
		afficherDonneeGeneral();
		chckbxAfficherDG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxAfficherDG.isSelected()) {
					donneeGeneralCocher = true;
					afficherDonneeGeneral();
					if (donneeSpecifiqueCocher == false) {
						espaceJeu.setBounds(20, 353, 1864, 597);
					}
					repaint();
				} else {
					donneeGeneralCocher = false;
					pnlSortiesGenerales.setBounds(0, 0, 0, 0);
					if (donneeSpecifiqueCocher == false) {
						pnlOutils.setBounds(0, 0, 0, 0);
						espaceJeu.setBounds(20, 42, 1874, 908);
						repaint();
					} else {
						if (espaceJeu != null) {
							afficherBouton();
							espaceJeu.setBounds(20, 46, 1188, 904);
							repaint();
						}
					}
				}
			}
		});

		chckbxAfficherDG.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxAfficherDG.setBounds(10, 10, 199, 25);
		contentPane.add(chckbxAfficherDG);

		chckbxAfficherVFR = new JCheckBox("Afficher le vecteur force r\u00E9sultante");
		chckbxAfficherVFR.setForeground(Color.WHITE);
		chckbxAfficherVFR.setBackground(COULEUR_ARRIERE);
		chckbxAfficherVFR.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxAfficherVFR.setBounds(223, 10, 242, 25);
		contentPane.add(chckbxAfficherVFR);

		chckbxAfficherDB = new JCheckBox("Afficher les donn\u00E9es du Blob");
		chckbxAfficherDB.setForeground(Color.WHITE);
		chckbxAfficherDB.setBackground(COULEUR_ARRIERE);
		chckbxAfficherDB.setSelected(true);
		afficherDonneeBlob();
		chckbxAfficherDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxAfficherDB.isSelected()) {
					donneeSpecifiqueCocher = true;
					afficherDonneeBlob();
					if (donneeGeneralCocher == false) {
						espaceJeu.setBounds(20, 46, 1188, 904);
					}
					repaint();
				} else {
					donneeSpecifiqueCocher = false;
					panelInfo.setBounds(0, 0, 0, 0);
					if (donneeGeneralCocher == false) {
						pnlOutils.setBounds(0, 0, 0, 0);
						espaceJeu.setBounds(20, 42, 1874, 908);
						repaint();
					} else {
						if (espaceJeu != null) {
							espaceJeu.setBounds(20, 353, 1864, 597);
							repaint();
						}
					}

				}
			}
		});
		chckbxAfficherDB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxAfficherDB.setBounds(485, 13, 231, 22);
		contentPane.add(chckbxAfficherDB);

		// ok on essait de quoi !

		Simulation simulation = new Simulation();

		if (niveau == 1) {
			creerNiveau(simulation, masseCorpsNiv1);
		} else if (niveau == 2) {
			tempsPause = 0;
			tempsDepartPause = 0;
			tempsFinPause = 0;
			tempsDepart = 0;
			creerNiveau(simulation, masseCorpsNiv2);
		} else if (niveau == 3) {
			tempsPause = 0;
			tempsDepartPause = 0;
			tempsFinPause = 0;
			tempsDepart = 0;
			creerNiveau(simulation, masseCorpsNiv3);
		} else if (niveau == 4) {
			tempsPause = 0;
			tempsDepartPause = 0;
			tempsFinPause = 0;
			tempsDepart = 0;
			this.nomFichier = fileName;
			GestionFichier gestionFichier = new GestionFichier();
			gestionFichier.loadFile(nomFichier, simulation);

		}

		espaceJeu = new EspaceJeu(simulation, Jeu.this);
		espaceJeu.addZoneAnimationListener(new ZoneAnimationListener() {
			public void cinétiqueTotaleUpdate(double energieCinetique) {
			}

			public void energieTotaleUpdate(double energieT) {
			}

			public void masseTotaleUpdate(double masse) {
			}

			public void nombreCorpsUpdate(int nbCorps) {
			}

			public void potentielleTotaleUpdate(double energiePotentielle) {
			}

			public void quantiteMouvTotaleUpdate(double quantiteMouv) {
			}

			public void tempsReelUpdate(long tempsR) {
			}

			public void tempsSimulationUpdate(double tempsS) {
			}

			public void zoneAnimationUpdate(int nbCorps, double energieT, double eCinetique, double ePotentielle,
					double quantiteMouv, double masse, double tempsR, double tempsS) {
				if (eCinetique == 0) {
					lblEnergieCT.setText("0");
				} else {
					lblEnergieCT.setText("" + de.format(SMath.getDebutNombre(eCinetique)));
					lblExposantCT.setText("E" + SMath.getExponentForNumber(eCinetique));
				}
				if (ePotentielle == 0) {
					lblEnergiePT.setText("0");
				} else {
					lblEnergiePT.setText("" + de.format(SMath.getDebutNombre(ePotentielle)));
					lblExposantPT.setText("E" + SMath.getExponentForNumber(ePotentielle));
				}
				if (energieT == 0) {
					lblEnergieT.setText("0");
				} else {
					lblEnergieT.setText("" + de.format(SMath.getDebutNombre(energieT)));
					lblExposantET.setText("E" + SMath.getExponentForNumber(energieT));
				}
				if (quantiteMouv == 0) {
					lblQuantiteMT.setText("0");
				} else {
					lblQuantiteMT.setText(de.format(SMath.getDebutNombre(quantiteMouv)) + "");
					lblExposantQM.setText("E" + SMath.getExponentForNumber(quantiteMouv));
				}
				lblMasseT.setText("" + de.format(SMath.getDebutNombre(masse)));
				lblNombreC.setText("" + nbCorps);
				labelTempsR.setText("" + (((tempsR - tempsDepart) / 1000) - tempsPause));
				labelTempsS.setText("" + tempsS);
			}
		});
		espaceJeu.addBlobListener(new BlobListener() {
			public void blobChange(double masse, double vitesse, double rayon, double eCinetique, double ePotentielle,
					double acceleration) {
				lblInfoMasseNombre.setText(de.format(SMath.getDebutNombre(masse)) + "");
				lblExposantMasse.setText("E" + SMath.getExponentForNumber(masse));
				lblInfoRayonNombre.setText(de.format(rayon) + "");
				lblInfoCinetiqueNombre.setText(de.format(SMath.getDebutNombre(eCinetique)) + "");
				lblExposantCinetique.setText("E" + SMath.getExponentForNumber(eCinetique));
				if (ePotentielle == 0) {
					lblInfoPotentielNombre.setText("0");
				} else {
					lblInfoPotentielNombre.setText(de.format(SMath.getDebutNombre(ePotentielle)) + "");
					lblExposantPotentielle.setText("E" + SMath.getExponentForNumber(ePotentielle));
				}
			}
		});

		// C'est ici que ca foire
		espaceJeu.setBounds(20, 349, 1188, 601);

		contentPane.add(espaceJeu);

		Fenetre.ajoutMenu(Jeu.this);
	}// fin de la méthode

	public void afficherDonneeBlob() {
		if (espaceJeu != null) {
			espaceJeu.setBounds(20, 353, 1188, 597);
		}

		panelInfo = new JPanel();
		panelInfo.setBorder(
				new TitledBorder(null, "Info du Blob", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panelInfo.setBounds(1218, 365, 676, 585);
		contentPane.add(panelInfo);
		panelInfo.setLayout(null);
		panelInfo.setBackground(COULEUR_ARRIERE);

		lblInfoTitre = new JLabel("Information du Blob");
		lblInfoTitre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInfoTitre.setForeground(Color.WHITE);
		lblInfoTitre.setBounds(125, 33, 240, 22);
		panelInfo.add(lblInfoTitre);

		lblInfoMasse = new JLabel("Masse :");
		lblInfoMasse.setBounds(56, 105, 48, 14);
		lblInfoMasse.setForeground(Color.WHITE);
		panelInfo.add(lblInfoMasse);

		lblInfoMasseNombre = new JLabel("0");
		lblInfoMasseNombre.setBounds(175, 105, 99, 14);
		lblInfoMasseNombre.setForeground(Color.WHITE);
		panelInfo.add(lblInfoMasseNombre);

		lblInfoMasseUnite = new JLabel("M\u2295");
		lblInfoMasseUnite.setBounds(343, 105, 48, 14);
		lblInfoMasseUnite.setForeground(Color.WHITE);
		panelInfo.add(lblInfoMasseUnite);

		lblInfoRayon = new JLabel("Rayon :");
		lblInfoRayon.setBounds(56, 224, 48, 14);
		lblInfoRayon.setForeground(Color.WHITE);
		panelInfo.add(lblInfoRayon);

		lblInfoRayonNombre = new JLabel("0");
		lblInfoRayonNombre.setBounds(175, 224, 158, 14);
		lblInfoRayonNombre.setForeground(Color.WHITE);
		panelInfo.add(lblInfoRayonNombre);

		lblInfoRayonUnite = new JLabel("Km");
		lblInfoRayonUnite.setBounds(343, 224, 48, 14);
		lblInfoRayonUnite.setForeground(Color.WHITE);
		panelInfo.add(lblInfoRayonUnite);

		lblInfoCinetique = new JLabel("\u00C9nergie cin\u00E9tique :");
		lblInfoCinetique.setBounds(56, 343, 109, 14);
		lblInfoCinetique.setForeground(Color.WHITE);
		panelInfo.add(lblInfoCinetique);

		lblInfoCinetiqueNombre = new JLabel("0");
		lblInfoCinetiqueNombre.setBounds(175, 343, 99, 14);
		lblInfoCinetiqueNombre.setForeground(Color.WHITE);
		panelInfo.add(lblInfoCinetiqueNombre);

		lblInfoCinetiqueUnite = new JLabel("Kj");
		lblInfoCinetiqueUnite.setBounds(343, 343, 48, 14);
		lblInfoCinetiqueUnite.setForeground(Color.WHITE);
		panelInfo.add(lblInfoCinetiqueUnite);

		lblInfoPotentiel = new JLabel("\u00C9nergie potentielle:");
		lblInfoPotentiel.setBounds(56, 462, 112, 14);
		lblInfoPotentiel.setForeground(Color.WHITE);
		panelInfo.add(lblInfoPotentiel);

		lblInfoPotentielNombre = new JLabel("0");
		lblInfoPotentielNombre.setBounds(175, 462, 60, 14);
		lblInfoPotentielNombre.setForeground(Color.WHITE);
		panelInfo.add(lblInfoPotentielNombre);

		lblInfoPotentielUnite = new JLabel("Kj");
		lblInfoPotentielUnite.setBounds(343, 462, 48, 14);
		lblInfoPotentielUnite.setForeground(Color.WHITE);
		panelInfo.add(lblInfoPotentielUnite);

		lblExposantMasse = new JLabel("");
		lblExposantMasse.setBounds(240, 105, 60, 14);
		lblExposantMasse.setForeground(Color.WHITE);
		panelInfo.add(lblExposantMasse);

		lblExposantCinetique = new JLabel("");
		lblExposantCinetique.setBounds(240, 343, 60, 16);
		lblExposantCinetique.setForeground(Color.WHITE);
		panelInfo.add(lblExposantCinetique);

		lblExposantPotentielle = new JLabel("");
		lblExposantPotentielle.setBounds(240, 462, 60, 16);
		lblExposantPotentielle.setForeground(Color.WHITE);
		panelInfo.add(lblExposantPotentielle);

	}

	public void afficherDonneeGeneral() {
		if (espaceJeu != null) {
			espaceJeu.setBounds(20, 353, 1188, 597);
		}

		pnlSortiesGenerales = new JPanel();
		pnlSortiesGenerales.setBorder(
				new TitledBorder(null, "Sorties générales", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnlSortiesGenerales.setBounds(20, 42, 1198, 307);
		contentPane.add(pnlSortiesGenerales);
		pnlSortiesGenerales.setLayout(null);
		pnlSortiesGenerales.setBackground(COULEUR_ARRIERE);

		lblEnergieTotale = new JLabel("Energie totale : ");
		lblEnergieTotale.setBounds(10, 25, 108, 23);
		lblEnergieTotale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnergieTotale.setForeground(Color.WHITE);
		pnlSortiesGenerales.add(lblEnergieTotale);

		lblEnergieT = new JLabel("0");
		lblEnergieT.setBounds(215, 25, 63, 23);
		lblEnergieT.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEnergieT.setForeground(Color.WHITE);
		pnlSortiesGenerales.add(lblEnergieT);

		lblJ = new JLabel("KJ");
		lblJ.setBounds(347, 25, 48, 23);
		lblJ.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblJ.setForeground(Color.WHITE);
		pnlSortiesGenerales.add(lblJ);

		lblEnergieCintiqueTotale = new JLabel("Energie cin\u00E9tique totale : ");
		lblEnergieCintiqueTotale.setBounds(10, 62, 169, 23);
		lblEnergieCintiqueTotale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnergieCintiqueTotale.setForeground(Color.WHITE);
		pnlSortiesGenerales.add(lblEnergieCintiqueTotale);

		lblEnergieCT = new JLabel("0");
		lblEnergieCT.setBounds(215, 62, 63, 23);
		lblEnergieCT.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEnergieCT.setForeground(Color.WHITE);
		pnlSortiesGenerales.add(lblEnergieCT);

		lblKJ = new JLabel("KJ");
		lblKJ.setBounds(347, 62, 63, 23);
		lblKJ.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKJ.setForeground(Color.WHITE);
		pnlSortiesGenerales.add(lblKJ);

		lblEnergiePotentielle = new JLabel("Energie potentielle totale : ");
		lblEnergiePotentielle.setBounds(10, 99, 169, 23);
		lblEnergiePotentielle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnergiePotentielle.setForeground(Color.WHITE);
		pnlSortiesGenerales.add(lblEnergiePotentielle);

		lblEnergiePT = new JLabel("0");
		lblEnergiePT.setBounds(215, 99, 63, 23);
		lblEnergiePT.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEnergiePT.setForeground(Color.WHITE);
		pnlSortiesGenerales.add(lblEnergiePT);

		lblKJ2 = new JLabel("KJ");
		lblKJ2.setBounds(347, 99, 62, 23);
		lblKJ2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKJ2.setForeground(Color.WHITE);
		pnlSortiesGenerales.add(lblKJ2);

		lblQuantiteMouv = new JLabel("Quantit\u00E9 de mouvement totale : ");
		lblQuantiteMouv.setBounds(10, 133, 202, 23);
		lblQuantiteMouv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuantiteMouv.setForeground(Color.WHITE);
		pnlSortiesGenerales.add(lblQuantiteMouv);

		lblQuantiteMT = new JLabel("0");
		lblQuantiteMT.setBounds(215, 133, 63, 23);
		lblQuantiteMT.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuantiteMT.setForeground(Color.WHITE);
		pnlSortiesGenerales.add(lblQuantiteMT);

		lblKG = new JLabel("m*kg/s");
		lblKG.setBounds(347, 133, 48, 23);
		lblKG.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKG.setForeground(Color.WHITE);
		pnlSortiesGenerales.add(lblKG);

		lblMasseTotale = new JLabel("Masse totale : ");
		lblMasseTotale.setBounds(10, 168, 169, 23);
		lblMasseTotale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMasseTotale.setForeground(Color.WHITE);
		pnlSortiesGenerales.add(lblMasseTotale);

		lblMasseT = new JLabel("0");
		lblMasseT.setBounds(215, 168, 63, 23);
		lblMasseT.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMasseT.setForeground(Color.WHITE);
		pnlSortiesGenerales.add(lblMasseT);

		lblKg = new JLabel("M\u2295");
		lblKg.setBounds(347, 168, 48, 14);
		lblKg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKg.setForeground(Color.WHITE);
		pnlSortiesGenerales.add(lblKg);

		lblNombreCorps = new JLabel("Nombre de corps totale : ");
		lblNombreCorps.setBounds(10, 201, 159, 23);
		lblNombreCorps.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombreCorps.setForeground(Color.WHITE);
		pnlSortiesGenerales.add(lblNombreCorps);

		lblNombreC = new JLabel("0");
		lblNombreC.setBounds(215, 201, 122, 23);
		lblNombreC.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombreC.setForeground(Color.WHITE);
		pnlSortiesGenerales.add(lblNombreC);

		lblTempsSimulation = new JLabel("Temps de simulation \u00E9coul\u00E9 : ");
		lblTempsSimulation.setBounds(10, 235, 195, 23);
		lblTempsSimulation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTempsSimulation.setForeground(Color.WHITE);
		pnlSortiesGenerales.add(lblTempsSimulation);

		lblCorps = new JLabel("Corps");
		lblCorps.setBounds(347, 201, 48, 14);
		lblCorps.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCorps.setForeground(Color.WHITE);
		pnlSortiesGenerales.add(lblCorps);

		lblTempsRelcoul = new JLabel("Temps r\u00E9el \u00E9coul\u00E9 : ");
		lblTempsRelcoul.setBounds(10, 269, 169, 23);
		lblTempsRelcoul.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTempsRelcoul.setForeground(Color.WHITE);
		pnlSortiesGenerales.add(lblTempsRelcoul);

		labelTempsS = new JLabel("0");
		labelTempsS.setBounds(215, 235, 122, 23);
		labelTempsS.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelTempsS.setForeground(Color.WHITE);
		pnlSortiesGenerales.add(labelTempsS);

		labelTempsR = new JLabel("0");
		labelTempsR.setBounds(215, 271, 122, 21);
		labelTempsR.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelTempsR.setForeground(Color.WHITE);
		pnlSortiesGenerales.add(labelTempsR);

		lblSecondes = new JLabel("secondes");
		lblSecondes.setBounds(347, 235, 169, 23);
		lblSecondes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSecondes.setForeground(Color.WHITE);
		pnlSortiesGenerales.add(lblSecondes);

		lblSecondes2 = new JLabel("secondes");
		lblSecondes2.setBounds(347, 270, 169, 23);
		lblSecondes2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSecondes2.setForeground(Color.WHITE);
		pnlSortiesGenerales.add(lblSecondes2);

		JLabel lblExposantMT = new JLabel(" ");
		lblExposantMT.setForeground(Color.WHITE);
		lblExposantMT.setBounds(275, 167, 48, 24);

		pnlSortiesGenerales.add(lblExposantMT);

		lblExposantCT = new JLabel("");
		lblExposantCT.setForeground(Color.WHITE);
		lblExposantCT.setBounds(275, 59, 48, 26);
		pnlSortiesGenerales.add(lblExposantCT);

		lblExposantET = new JLabel("");
		lblExposantET.setForeground(Color.WHITE);
		lblExposantET.setBounds(275, 25, 48, 23);
		pnlSortiesGenerales.add(lblExposantET);

		lblExposantQM = new JLabel("");
		lblExposantQM.setForeground(Color.WHITE);
		lblExposantQM.setBounds(275, 133, 48, 23);
		pnlSortiesGenerales.add(lblExposantQM);

		lblExposantPT = new JLabel("");
		lblExposantPT.setForeground(Color.WHITE);
		lblExposantPT.setBounds(275, 99, 48, 23);
		pnlSortiesGenerales.add(lblExposantPT);
	}

	public void afficherBouton() {
		pnlOutils = new JPanel();
		pnlOutils
		.setBorder(new TitledBorder(null, "Outils", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnlOutils.setBounds(1218, 42, 676, 307);
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
				Boutons.btnDemarrer(espaceJeu);
				btnDemarrer.setEnabled(false);
				btnRecommencer.setEnabled(false);
			}
		});
		btnDemarrer.setBounds(96, 31, 97, 97);
		pnlOutils.add(btnDemarrer);
		Fenetre.associerBoutonAvecImage(btnDemarrer, "demarrer.png", Jeu.this);
		btnDemarrer.setToolTipText("Lancer la simulation.");

		btnRecommencer = new JButton("Recommencer");
		btnRecommencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tempsDepartPause = System.currentTimeMillis();
				btnDemarrer.setEnabled(true);
				Boutons.btnRecommencer(espaceJeu, Jeu.this);
			}
		});
		btnRecommencer.setBounds(289, 164, 97, 97);
		pnlOutils.add(btnRecommencer);
		Fenetre.associerBoutonAvecImage(btnRecommencer, "recommencer.png", Jeu.this);
		btnRecommencer.setToolTipText("Restaurer les paramètres par défaut.");

		btnPauseProchaineImage = new JButton("Proch. Img.");
		btnPauseProchaineImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Boutons.btnProchaineImage(espaceJeu);
				btnDemarrer.setEnabled(true);
				btnRecommencer.setEnabled(true);
			}
		});
		btnPauseProchaineImage.setBounds(289, 31, 97, 97);
		pnlOutils.add(btnPauseProchaineImage);
		Fenetre.associerBoutonAvecImage(btnPauseProchaineImage, "pauseProchaineImage.png", Jeu.this);
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
		btnEfface.setEnabled(false);
		Fenetre.associerBoutonAvecImage(btnEfface, "efface.png", Jeu.this);
		btnEfface.setToolTipText("Après avoir cliquer sur ce bouton, la prochaine planète sélectionnée sera effacée.");
	}

	// Antonin Tritz
	/**
	 * Permet de d'ajouter les corps et leurs propriétés à un niveau quelquonque
	 * 
	 * @param simulation Lieu d'ajout des corps
	 * @param masse      Masse initiale de chaque corps ajouté
	 */
	private static void creerNiveau(Simulation simulation, double masse) {
		// AppercuCorps appercuCorps = new AppercuCorps();
		// String img = null;
		Color couleurCorpsAjoute;

		for (int i = 0; i < nombreCorpsBase; i++) {
			Vecteur positionCorpsAjoute = new Vecteur(SMath.aleatoire(-maxPosXEnValAbsolue, maxPosXEnValAbsolue),
					SMath.aleatoire(-maxPosYEnValAbsolue, maxPosYEnValAbsolue));
			double masseCorpsAjoute = masse * 100;

			int rouge = 0;
			int vert = 0;
			int bleu = 0;
			while (rouge + vert + bleu < SEUIL_MINIMAL_COULEUR) {
				rouge = SMath.aleatoire(0, maxValCouleur);
				vert = SMath.aleatoire(0, maxValCouleur);
				bleu = SMath.aleatoire(0, maxValCouleur);
			}

			Corps corpsAjoute;

			//			if (i % 10 == 0) {
			//				img = appercuCorps.imageAleatoire();
			//				couleurCorpsAjoute = new Color(rouge, vert, bleu);
			//				corpsAjoute = new Corps(positionCorpsAjoute, masseCorpsAjoute, couleurCorpsAjoute, densiteParDefault, img);
			//			}else {
			couleurCorpsAjoute = new Color(rouge, vert, bleu);
			corpsAjoute = new Corps(positionCorpsAjoute, masseCorpsAjoute, couleurCorpsAjoute, densiteParDefault);
			//			}

			corpsAjoute.setVitesse(new Vecteur(SMath.aleatoire(-maxVitessEnValAbsolue, maxVitessEnValAbsolue),
					SMath.aleatoire(-maxVitessEnValAbsolue, maxVitessEnValAbsolue)));
			simulation.ajouterUnCorps(corpsAjoute);
		}
	}// fin de la méthode

	// Antonin Tritz
	public int getNiveau() {
		return niveau;
	}// fin méthode

	public String getfileName() {
		return this.nomFichier;
	}

}// fin de la classe
