package modele;

public abstract class Case {

	protected boolean aJoueur;
	protected boolean estInaccessible;
	protected Joueur joueur;
	protected int ligne;
	protected int colonne;
	
	public Case(boolean aJoueur, boolean estInaccessible, Joueur joueur, int ligne, int colonne) {
		this.aJoueur = aJoueur;
		this.estInaccessible = estInaccessible;
		this.joueur = joueur;
		this.ligne = ligne;
		this.colonne = colonne;
	}

	public boolean isaJoueur() {
		return aJoueur;
	}

	public void setaJoueur(boolean aJoueur) {
		this.aJoueur = aJoueur;
	}

	public boolean isEstInaccessible() {
		return estInaccessible;
	}

	public void setEstInaccessible(boolean estInaccessible) {
		this.estInaccessible = estInaccessible;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	
	public int getColonne() {
		return colonne;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}

	public int getLigne() {
		return ligne;
	}

	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	public String toString() {
		if(!isaJoueur())
			return "0";
		return joueur.getPseudo();
	}
}
