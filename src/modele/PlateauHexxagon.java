package modele;

import java.util.ArrayList;

public class PlateauHexxagon extends Plateau {

	private ArrayList<ArrayList<CaseHexxagon>> tablier = new ArrayList<ArrayList<CaseHexxagon>>();
	
	public ArrayList<ArrayList<CaseHexxagon>> getTablier() {
		return tablier;
	}

	public void setTablier(ArrayList<ArrayList<CaseHexxagon>> tablier) {
		this.tablier = tablier;
	}

	public PlateauHexxagon(int taille, Joueur j1, Joueur j2) {
		for(int i=0; i<taille; i++) {
			ArrayList<CaseHexxagon> ligne = new ArrayList<CaseHexxagon>();
			for(int j=0; j<taille; j++) {
				ligne.add(new CaseHexxagon(i, j));
			}
			tablier.add(ligne);
		}
		
		tablier.get(0).get(0).setaJoueur(true);
		tablier.get(0).get(0).setJoueur(j1);
		
		tablier.get(0).get(tablier.size()-1).setaJoueur(true);
		tablier.get(0).get(tablier.size()-1).setJoueur(j2);
		
		tablier.get(tablier.size()-1).get(0).setaJoueur(true);
		tablier.get(tablier.size()-1).get(0).setJoueur(j2);
		
		tablier.get(tablier.size()-1).get(tablier.size()-1).setaJoueur(true);
		tablier.get(tablier.size()-1).get(tablier.size()-1).setJoueur(j1);
	}
	
	public void placerCaseInaccessible(int nbCaseInaccessible)
	{
		if(nbCaseInaccessible!=0 && tablier.size()>1){
			if(nbCaseInaccessible==1){
				if(tablier.size()%2 == 1)
					tablier.get(tablier.size()/2).get(tablier.size()/2).setEstInaccessible(true);
				else
					tablier.get(tablier.size()/2-1).get(tablier.size()/2).setEstInaccessible(true);
			}
			else if(nbCaseInaccessible==2){
				if(tablier.size()%2 == 1){
					tablier.get(tablier.size()/2+1).get(tablier.size()/2-1).setEstInaccessible(true);
					tablier.get(tablier.size()/2-1).get(tablier.size()/2+1).setEstInaccessible(true);
				}
					else{
					tablier.get(tablier.size()/2-1).get(tablier.size()/2-1).setEstInaccessible(true);
					tablier.get(tablier.size()/2).get(tablier.size()/2).setEstInaccessible(true);
				}
			}
		}
	}

	public void afficher() {
		for (ArrayList<CaseHexxagon> arrayList : tablier) {
			for (Case case1 : arrayList) {
				System.out.print(case1+" ");
			}
			System.out.println();
		}
	}
	
	public boolean estDeplacable(CaseHexxagon caseClick) {
		int ligne = caseClick.getLigne();
		int colonne = caseClick.getColonne();
		
		if(colonne > 0)
			if(tablier.get(ligne).get(colonne-1).verifierDestination())
				return true;
		if(ligne < tablier.size()-1 && colonne > 0)	
			if(tablier.get(ligne+1).get(colonne-1).verifierDestination())
				return true;
		if(ligne < tablier.size()-1)
			if(tablier.get(ligne+1).get(colonne).verifierDestination())
				return true;
		if(colonne < tablier.size()-1)
			if(tablier.get(ligne).get(colonne+1).verifierDestination())
				return true;
		if(ligne > 0 && colonne < tablier.size()-1)
			if(tablier.get(ligne-1).get(colonne+1).verifierDestination())
				return true;
		if(ligne > 0 )
			if(tablier.get(ligne-1).get(colonne).verifierDestination())
				return true;

		if(colonne > 1)
			if(tablier.get(ligne).get(colonne-2).verifierDestination())
				return true;
		if(ligne < tablier.size()-1 && colonne > 1)
			if(tablier.get(ligne+1).get(colonne-2).verifierDestination())
				return true;
		if(ligne < tablier.size()-2 && colonne > 1)
			if(tablier.get(ligne+2).get(colonne-2).verifierDestination())
				return true;
		if(ligne < tablier.size()-2 && colonne > 0)
			if(tablier.get(ligne+2).get(colonne-1).verifierDestination())
				return true;
		if(ligne < tablier.size()-2)
			if(tablier.get(ligne+2).get(colonne).verifierDestination())
				return true;
		if(ligne < tablier.size()-1 && colonne < tablier.size()-1)
			if(tablier.get(ligne+1).get(colonne+1).verifierDestination())
				return true;
		if(colonne < tablier.size()-2)
			if(tablier.get(ligne).get(colonne+2).verifierDestination())
				return true;
		if(ligne > 0 && colonne < tablier.size()-2)
			if(tablier.get(ligne-1).get(colonne+2).verifierDestination())
				return true;
		if(ligne > 1 && colonne < tablier.size()-2)
			if(tablier.get(ligne-2).get(colonne+2).verifierDestination())
				return true;
		if(ligne > 1 && colonne < tablier.size()-1)
			if(tablier.get(ligne-2).get(colonne+1).verifierDestination())
				return true;
		if(ligne > 1)
			if(tablier.get(ligne-2).get(colonne).verifierDestination())
				return true;
		if(ligne > 0 && colonne > 0)
			if(tablier.get(ligne-1).get(colonne-1).verifierDestination())
				return true;
		return false;
	}
	
	public void deplacer(CaseHexxagon origine, CaseHexxagon destination, int mouvement) {
		destination.setaJoueur(true);
		destination.setJoueur(origine.getJoueur());
		
		tablier.get(destination.getLigne()).get(destination.getColonne()).setaJoueur(true);
		tablier.get(destination.getLigne()).get(destination.getColonne()).setJoueur(origine.getJoueur());
		
		if(mouvement == 2) {
			tablier.get(origine.getLigne()).get(origine.getColonne()).setaJoueur(false);
			tablier.get(origine.getLigne()).get(origine.getColonne()).setJoueur(null);

		}
		
		prendre(destination);
	}
	
	public void prendre(CaseHexxagon destination) {
		int ligne = destination.getLigne();
		int colonne = destination.getColonne();
		Joueur joueur = destination.getJoueur();
		
		if(colonne > 0)
			if(tablier.get(ligne).get(colonne-1).isaJoueur())
				tablier.get(ligne).get(colonne-1).setJoueur(joueur);
		if(ligne < tablier.size()-1 && colonne > 0)
			if(tablier.get(ligne+1).get(colonne-1).isaJoueur())
				tablier.get(ligne+1).get(colonne-1).setJoueur(joueur);
		if(ligne < tablier.size()-1)
			if(tablier.get(ligne+1).get(colonne).isaJoueur())
				tablier.get(ligne+1).get(colonne).setJoueur(joueur);
		if(colonne < tablier.size()-1)
			if(tablier.get(ligne).get(colonne+1).isaJoueur())
				tablier.get(ligne).get(colonne+1).setJoueur(joueur);
		if(ligne > 0 && colonne < tablier.size()-1)
			if(tablier.get(ligne-1).get(colonne+1).isaJoueur())
				tablier.get(ligne-1).get(colonne+1).setJoueur(joueur);
		if(ligne > 0)
			if(tablier.get(ligne-1).get(colonne).isaJoueur())
				tablier.get(ligne-1).get(colonne).setJoueur(joueur);
	}
	
	public boolean finPartie(Joueur joueur) {
		for (ArrayList<CaseHexxagon> arrayList : tablier) {
			for (CaseHexxagon caseHexxagon : arrayList) {
				if(caseHexxagon.getJoueur() == joueur && estDeplacable(caseHexxagon))
					return false;
			}
		}
		return true;
	}
	
	public void finPartieRapide(Joueur joueur) {
		for (ArrayList<CaseHexxagon> arrayList : tablier) {
			for (CaseHexxagon caseHexxagon : arrayList) {
				if(!caseHexxagon.isaJoueur() && !caseHexxagon.isEstInaccessible())
				{
					caseHexxagon.setaJoueur(true);
					caseHexxagon.setJoueur(joueur);
				}
			}
		}
	}
	
	public int calculScore(Joueur joueur) {
		int score=0;
		
		for (ArrayList<CaseHexxagon> arrayList : tablier) {
			for (CaseHexxagon caseHexxagon : arrayList) {
				if(caseHexxagon.getJoueur() == joueur)
					score++;
			}
		}
		return score;
	}
	
	public void jouerIa(int nbTour, Joueur joueur) throws InterruptedException {
		int[][] tab = { 
			{0,-1}, {1,-1}, {1,0}, {0,1}, {-1,1}, {-1,0},		
			{0,-2}, {1,-2}, {2,-2}, {2,-1}, {2,0}, {1,1}, {0,2}, {-1,2}, {-2,2}, {-2,1}, {-2,0}, {-1,-1}
		};

		int iDepart = (int) (Math.random()*tablier.size());
		int jDepart = (int) (Math.random()*tablier.size());
		int kDepart = (int) (Math.random()*6);
		
		int i = iDepart;
		int j = jDepart;
		int k = kDepart;
		
		int nbPrise = 0;
		int priseMax = -1;
		
		int mouv;
		int mouvFin;
		
		CaseHexxagon pion = new CaseHexxagon(0,0);
		CaseHexxagon destination = new CaseHexxagon(0,0);
		CaseHexxagon prisePos = new CaseHexxagon(0,0);
		CaseHexxagon pionFin = new CaseHexxagon(0,0);
		CaseHexxagon destFin = new CaseHexxagon(0,0);
		
		pion.setJoueur(joueur);
		pion.setaJoueur(true);
		
		do {
			i = (i+1)%tablier.size();
			
			do {
				j = (j+1)%tablier.size();
				
				if(tablier.get(i).get(j).getJoueur()==joueur)
				{
					pion=tablier.get(i).get(j);

					do {
						k = (k+1)%6;

						if( (i+tab[k][0]) >=0 && (j+tab[k][1]) >=0&& (i+tab[k][0]) < tablier.size() && (j+tab[k][1]) < tablier.size()  )
							destination=tablier.get(i+tab[k][0]).get(j+tab[k][1]);

						mouv = pion.verifierDeplacement(destination);
						if(!destination.isaJoueur() && mouv != 0) 
						{
							for(int l=0; l<6; l++) {
								int prisePosX = destination.getLigne()+tab[l][0];
								int priseposY = destination.getColonne()+tab[l][1];
								
								if (prisePosX >= 0 && priseposY >= 0 && prisePosX < tablier.size() && priseposY < tablier.size())
									prisePos=tablier.get(prisePosX).get(priseposY);
								
								if(prisePos.isaJoueur() && prisePos.getJoueur() != joueur && prisePos.getLigne() >= 0 && prisePos.getColonne() >= 0 && prisePos.getLigne() < tablier.size() && prisePos.getColonne() < tablier.size()) {
									nbPrise++;
								}
							}

							if(nbPrise > priseMax) {
								priseMax = nbPrise;
								pionFin.setValues(pion);
								destFin.setValues(destination);
							}
							
							nbPrise = 0;
						}
					} while (k != kDepart);
				}
			} while (j != jDepart);
		} while (i != iDepart);
		
		kDepart = (int) (Math.random()*12)+6;
		k = kDepart;
		if(nbTour > 4 || ((tablier.size() == 5 && ((tablier.get(1).get(1).isaJoueur() && tablier.get(1).get(1).getJoueur() != joueur) || (tablier.get(3).get(3).isaJoueur() && tablier.get(3).get(3).getJoueur() != joueur))))) {
			do {
				i = (i+1)%tablier.size();
				
				do {
					j = (j+1)%tablier.size();
					
					if(tablier.get(i).get(j).getJoueur()==joueur) {
						pion=tablier.get(i).get(j);

						do {
							k = (k+1)%12+6;
							
							if( (i+tab[k][0]) >=0 && (j+tab[k][1]) >=0&& (i+tab[k][0]) < tablier.size() && (j+tab[k][1]) < tablier.size()  )
								destination=tablier.get(i+tab[k][0]).get(j+tab[k][1]);
							
							mouv = pion.verifierDeplacement(destination);
														
							if(!destination.isaJoueur() && mouv != 0){
								for(int l=0; l<6; l++) {
									int prisePosX = destination.getLigne()+tab[l][0];
									int priseposY = destination.getColonne()+tab[l][1];
									
									if (prisePosX >= 0 && priseposY >= 0 && prisePosX < tablier.size() && priseposY < tablier.size())
										prisePos=tablier.get(prisePosX).get(priseposY);
									
									if(prisePos.isaJoueur() && prisePos.getJoueur() != joueur && prisePos.getLigne() >= 0 && prisePos.getColonne() >= 0 && prisePos.getLigne() < tablier.size() && prisePos.getColonne() < tablier.size()) {
										nbPrise++;
									}
								}

								if(nbPrise > priseMax) {
									priseMax = nbPrise;
									pionFin.setValues(pion);
									destFin.setValues(destination);
								}
								
								nbPrise = 0;
							}
						} while (k != kDepart);
					}
				} while (j != jDepart);
			} while (i != iDepart);
		}
		
		if(nbTour == 2) {
			if(destFin.getLigne() == 3 && destFin.getColonne() == 1) {
				if(Math.random()*2 == 0)
					destFin.setLigne(4);
				else
					destFin.setColonne(0);
			}
			else if(destFin.getLigne() == 1 && destFin.getColonne() == 3) {
				if(Math.random()*2 == 0)
					destFin.setLigne(0);
				else
					destFin.setColonne(4);
			}
		}

		mouvFin = pionFin.verifierDeplacement(destFin);
		deplacer(pionFin, destFin, mouvFin);
	}

	public CaseHexxagon clicToCase(int clicX,int clicY)
	{
		int i, j = 0;
		int cote = 800/(14+(tablier.size()-5)*3);
		
		int posLigneX = 100;
		int posLigneY = 350;
		int posColX, posColY;
		
		int distance;
		//La caseHexa reste null si le clic n'est pas valide
		CaseHexxagon casehexa=null;

		
		for (i=0; i<tablier.size(); i++)
		{
			posColX = posLigneX+cote;
			posColY = posLigneY;
			
			for (j=0; j<tablier.size(); j++)
			{
				/*distance(posCol, clic)*/
				distance=(int) Math.sqrt
						(
							 Math.pow((clicX-posColX),2)
							+Math.pow((clicY-posColY),2)
						);
				
				if(distance<Math.sqrt(Math.pow(cote, 2)-Math.pow(cote/2, 2)))
				{
					casehexa=tablier.get(i).get(j);
				}
				
				posColX += 3*cote/2; posColY += Math.sqrt(Math.pow(cote, 2)-Math.pow(cote/2, 2));
			}
			
			posLigneX += 3*cote/2; posLigneY -= Math.sqrt(Math.pow(cote, 2)-Math.pow(cote/2, 2));
		}
		return casehexa;

	}
	
	
}
