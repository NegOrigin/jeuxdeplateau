package modele;

public class CaseOthello extends Case {
	
	public CaseOthello(int i, int j) {
		super(false, false, null, i, j);
	}
	
	public boolean verifVide(){
		if(!isEstInaccessible() && !isaJoueur()){
			return true;
		}
		else{
			return false;
		}
	}
}
