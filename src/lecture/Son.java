package lecture;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Classe permettant de faire jouer des sons
 * 
 * @author Jeanne Catonguay
 *
 */
public class Son {

	/**
	 * Permet de jouer un son d'aspiration
	 */
	public static void soundSlurp() {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(Son.class.getResource("slurp_sound_effect.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Permet de jouer le son de la fin de la partie
	 */
	public static void soundGameOver() {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(Son.class.getResource("gameOver.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Permet de jouer le son des rebond entre les corps lors des collisions élastiques
	 */
	public static void soundBump() {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(Son.class.getResource("bump.2.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Permet de jouer le son quand le blob perd de la masse
	 */
	public static void soundMouth() {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(Son.class.getResource("mouth.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Permet de jouer le son de la victoire
	 */
	public static void soundVictoire() {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(Son.class.getResource("victory.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void soundFusion() {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(Son.class.getResource("fusionDouce.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void soundExplosion() {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(Son.class.getResource("explosion.wav"));
		
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
