package modele;

import javafx.scene.paint.Color;

public class MenuPrincipal {

	private Joueur j1, j2;
	private int jeu, taille, inaccessible;
	
	public MenuPrincipal() {
		j1 = new Joueur("Joueur 1", false);
		j1.setCouleur(Color.WHITE);
		j2 = new Joueur("Joueur 2", false);
		j2.setCouleur(Color.BLACK);
		jeu = 0;
		taille = 5;
		inaccessible = 0;
	}

	public Joueur getJ1() {
		return j1;
	}

	public void setJ1(Joueur j1) {
		this.j1 = j1;
	}

	public Joueur getJ2() {
		return j2;
	}

	public void setJ2(Joueur j2) {
		this.j2 = j2;
	}

	public int getJeu() {
		return jeu;
	}

	public void setJeu(int jeu) {
		this.jeu = jeu;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public int getInaccessible() {
		return inaccessible;
	}

	public void setInaccessible(int inaccessible) {
		this.inaccessible = inaccessible;
	}
}
