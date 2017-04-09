package modele;

public class CaseHexxagon extends Case {
	
	public CaseHexxagon(int ligne, int colonne) {
		super(false, false, null, ligne, colonne);
	}
	
	public void setValues(CaseHexxagon caseHexa){
		this.setJoueur(caseHexa.getJoueur());
		this.setLigne(caseHexa.getLigne());
		this.setColonne(caseHexa.getColonne());
		this.setaJoueur(caseHexa.isaJoueur());
		this.setEstInaccessible(caseHexa.isEstInaccessible());
	}
	
	public boolean verifierOrigine(Joueur joueur) {
		return (aJoueur && this.joueur == joueur);
	}
	
	public boolean verifierDestination() {
		return (!aJoueur && !estInaccessible);
	}
	
	public boolean estDistance1(CaseHexxagon caseClick) {
		return (
			(this.ligne-caseClick.getLigne() == 0 && this.colonne-caseClick.getColonne() == -1)
			|| (this.ligne-caseClick.getLigne() == 1 && this.colonne-caseClick.getColonne() == -1)
			|| (this.ligne-caseClick.getLigne() == 1 && this.colonne-caseClick.getColonne() == 0)
			|| (this.ligne-caseClick.getLigne() == 0 && this.colonne-caseClick.getColonne() == 1)
			|| (this.ligne-caseClick.getLigne() == -1 && this.colonne-caseClick.getColonne() == 1)
			|| (this.ligne-caseClick.getLigne() == -1 && this.colonne-caseClick.getColonne() == 0)
		);
	}
	
	public boolean estDistance2(CaseHexxagon caseClick) {
		return (
			(this.ligne-caseClick.getLigne() == 0 && this.colonne-caseClick.getColonne() == -2)
			|| (this.ligne-caseClick.getLigne() == 1 && this.colonne-caseClick.getColonne() == -2)
			|| (this.ligne-caseClick.getLigne() == 2 && this.colonne-caseClick.getColonne() == -2)
			|| (this.ligne-caseClick.getLigne() == 2 && this.colonne-caseClick.getColonne() == -1)
			|| (this.ligne-caseClick.getLigne() == 2 && this.colonne-caseClick.getColonne() == 0)
			|| (this.ligne-caseClick.getLigne() == 1 && this.colonne-caseClick.getColonne() == 1)
			|| (this.ligne-caseClick.getLigne() == 0 && this.colonne-caseClick.getColonne() == 2)
			|| (this.ligne-caseClick.getLigne() == -1 && this.colonne-caseClick.getColonne() == 2)
			|| (this.ligne-caseClick.getLigne() == -2 && this.colonne-caseClick.getColonne() == 2)
			|| (this.ligne-caseClick.getLigne() == -2 && this.colonne-caseClick.getColonne() == 1)
			|| (this.ligne-caseClick.getLigne() == -2 && this.colonne-caseClick.getColonne() == 0)
			|| (this.ligne-caseClick.getLigne() == -1 && this.colonne-caseClick.getColonne() == -1)
		);
	}
	
	public int verifierDeplacement(CaseHexxagon caseClick) {
		if(caseClick.verifierDestination()) {
			if(this.estDistance1(caseClick))
				return 1;
			if(this.estDistance2(caseClick))
				return 2;
		}
		return 0;
	}
}
