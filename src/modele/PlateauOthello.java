package modele;

import java.util.ArrayList;

public class PlateauOthello extends Plateau {
	
	private ArrayList<ArrayList<CaseOthello>> tablier = new ArrayList<ArrayList<CaseOthello>>();
	
	public PlateauOthello(Joueur j1, Joueur j2) {//on crée le plateau et on place les 1ers pions
		for(int i=0; i<8; i++) {
			ArrayList<CaseOthello> ligne = new ArrayList<CaseOthello>();
			for(int j=0; j<8; j++) {
				ligne.add(new CaseOthello(i, j));
			}
			tablier.add(ligne);
		}
		tablier.get(3).get(3).setaJoueur(true);
		tablier.get(3).get(3).setJoueur(j2);
		tablier.get(3).get(4).setaJoueur(true);
		tablier.get(3).get(4).setJoueur(j1);
		tablier.get(4).get(3).setaJoueur(true);
		tablier.get(4).get(3).setJoueur(j1);
		tablier.get(4).get(4).setaJoueur(true);
		tablier.get(4).get(4).setJoueur(j2);
	}
	
	public CaseOthello getCase(int i, int j)
	{
		return tablier.get(i).get(j);
	}
	
	public void afficher() {//ancienne fonction d'affichage dans le terminal
		for (ArrayList<CaseOthello> arrayList : tablier) {
			for (Case case1 : arrayList) {
				System.out.print(case1+" ");
			}
			System.out.println();
		}
	}
	
	public boolean verifJouable(Joueur joueur, CaseOthello caseClic){//on verifie si le joueur peut jouer dans la case
            boolean bool=false;
            int i=1;
            int ligne=caseClic.getLigne();
            int colonne=caseClic.getColonne();
            CaseOthello caseTemp;
            if(caseClic.verifVide()){
                    //case dessous
                if(ligne<7)
                {
                    caseTemp=tablier.get(ligne+i).get(colonne);
                    while((ligne+i)<7 && caseTemp.isaJoueur() && caseTemp.getJoueur()!=joueur)
                    {
                        i++;
                        caseTemp=tablier.get(ligne+i).get(colonne);
                    }
                    if(i>1 && caseTemp.getJoueur()==joueur)
                    {
                        bool=true;
                    }
                }
	            	
                if(bool==false)
                {
                    //case haut
                    i=1;
                    if(ligne>0)
                    {
                        caseTemp=tablier.get(ligne-i).get(colonne);
                        while((ligne-i)>0 && caseTemp.isaJoueur() && caseTemp.getJoueur()!=joueur)
                        {
                            i++;
                            caseTemp=tablier.get(ligne-i).get(colonne);
                        }
                        if(i>1 && caseTemp.getJoueur()==joueur)
                        {
                            bool=true;
                        }
                    }
                }

                //case gauche
                if(bool==false)
                {
                    i=1;
                    if(colonne>0)
                    {
                        caseTemp=tablier.get(ligne).get(colonne-i);
                        while((colonne-i)>0 && caseTemp.isaJoueur() && caseTemp.getJoueur()!=joueur)
                        {
                            i++;
                            caseTemp=tablier.get(ligne).get(colonne-i);
                        }
                        if(i>1 && caseTemp.getJoueur()==joueur)
                        {
                            bool=true;
                        }
                    }
                }
                //case droite
                if(bool==false)
                {
                    i=1;
                    if(colonne<7)
                    {
                        caseTemp=tablier.get(ligne).get(colonne+i);
                        while((colonne+i)<7 && caseTemp.isaJoueur() && caseTemp.getJoueur()!=joueur)
                        {
                            i++;
                            caseTemp=tablier.get(ligne).get(colonne+i);
                        }
                        if(i>1 && caseTemp.getJoueur()==joueur)
                        {
                            bool=true;
                        }
                    }
                }
                //case haut gauche
                if(bool==false)
                {
                    i=1;
                    if(ligne>0 && colonne>0)
                    {
                        caseTemp=tablier.get(ligne-i).get(colonne-i);
                        while(ligne-i>0 && colonne-i>0 && caseTemp.isaJoueur() && caseTemp.getJoueur()!=joueur)
                        {
                            i++;
                            caseTemp=tablier.get(ligne-i).get(colonne-i);
                        }
                        if(i>1 && caseTemp.getJoueur()==joueur)
                        {
                            bool=true;
                        }
                    }
                }
                // haut droite
                if(bool==false)
                {
                    i=1;
                    if(ligne>0 && colonne<7)
                    {
                        caseTemp=tablier.get(ligne-i).get(colonne+i);
                        while(ligne-i>0 && colonne+i<7 && caseTemp.isaJoueur() && caseTemp.getJoueur()!=joueur)
                        {
                            i++;
                            caseTemp=tablier.get(ligne-i).get(colonne+i);
                        }
                        if(i>1 && caseTemp.getJoueur()==joueur)
                        {
                            bool=true;
                        }
                    }
                }
                // bas droite
                if(bool==false)
                {
                    i=1;
                    if(ligne<7 && colonne<7)
                    {
                        caseTemp=tablier.get(ligne+i).get(colonne+i);
                        while(ligne+i<7 && colonne+i<7 && caseTemp.isaJoueur() && caseTemp.getJoueur()!=joueur)
                        {
                            i++;
                            caseTemp=tablier.get(ligne+i).get(colonne+i);
                        }
                        if(i>1 && caseTemp.getJoueur()==joueur)
                        {
                            bool=true;
                        }
                    }
                }

                // bas gauche
                if(bool==false)
                {
                    i=1;
                    if(ligne<7 && colonne>0)
                    {
                            caseTemp=tablier.get(ligne+i).get(colonne-i);
                            while(ligne+i<7 && colonne-i>0 && caseTemp.isaJoueur() && caseTemp.getJoueur()!=joueur)
                            {
                                i++;
                                caseTemp=tablier.get(ligne+i).get(colonne-i);
                            }
                            if(i>1 && caseTemp.getJoueur()==joueur)
                            {
                                bool=true;
                            }
                    }
                }
            }
        return bool;
    }
	
	public void prise(Joueur joueur, CaseOthello caseClic){
		caseClic.setaJoueur(true);
		caseClic.setJoueur(joueur);
		
		int i=1;
		int ligne=caseClic.getLigne();
		int colonne=caseClic.getColonne();
		CaseOthello caseTemp;
                
                tablier.get(ligne).get(colonne).setaJoueur(true);
                tablier.get(ligne).get(colonne).setJoueur(joueur);
		//case dessous
		if(ligne<7)
		{
			caseTemp=tablier.get(ligne+i).get(colonne);
	        while((ligne+i)<7 && caseTemp.isaJoueur() && caseTemp.getJoueur()!=joueur)
	        {
	            i++;
	            caseTemp=tablier.get(ligne+i).get(colonne);
	        }
	        if(i>1 && caseTemp.getJoueur()==joueur)
	        {
	        	for(i=i-1;i>0;i--)
	        	{
	        		tablier.get(ligne+i).get(colonne).setJoueur(joueur);
	        	}
	        }
		}

    
        //case haut
        i=1;
        if(ligne>0)
        {
	        caseTemp=tablier.get(ligne-i).get(colonne);
	        while((ligne-i)>0 && caseTemp.isaJoueur() && caseTemp.getJoueur()!=joueur)
	        {
	            i++;
	            caseTemp=tablier.get(ligne-i).get(colonne);
	        }
	        if(i>1 && caseTemp.getJoueur()==joueur)
	        {
	        	for(i=i-1;i>0;i--)
	        	{
	        		tablier.get(ligne-i).get(colonne).setJoueur(joueur);
	        	}
	        }
        }

        //case gauche
        i=1;
        if(colonne>0)
        {
	        caseTemp=tablier.get(ligne).get(colonne-i);
	        while((colonne-i)>0 && caseTemp.isaJoueur() && caseTemp.getJoueur()!=joueur)
	        {
	            i++;
	            caseTemp=tablier.get(ligne).get(colonne-i);
	        }
	        if(i>1 && caseTemp.getJoueur()==joueur)
	        {
	        	for(i=i-1;i>0;i--)
	        	{
	        		tablier.get(ligne).get(colonne-i).setJoueur(joueur);
	        	}
	        }
        }
        
        //case droite
        i=1;
        if(colonne<7)
        {
	        caseTemp=tablier.get(ligne).get(colonne+i);
	        while((colonne+i)<7 && caseTemp.isaJoueur() && caseTemp.getJoueur()!=joueur)
	        {
	            i++;
	            caseTemp=tablier.get(ligne).get(colonne+i);
	        }
	        if(i>1 && caseTemp.getJoueur()==joueur)
	        {
	        	for(i=i-1;i>0;i--)
	        	{
	        		tablier.get(ligne).get(colonne+i).setJoueur(joueur);
	        	}
	        }
        }
        //case haut gauche

        i=1;
        if(ligne>0 && colonne>0)
        {
	        caseTemp=tablier.get(ligne-i).get(colonne-i);
	        while(ligne-i>0 && colonne-i>0 && caseTemp.isaJoueur() && caseTemp.getJoueur()!=joueur)
	        {
	            i++;
	            caseTemp=tablier.get(ligne-i).get(colonne-i);
	        }
	        if(i>1 && caseTemp.getJoueur()==joueur)
	        {
	        	for(i=i-1;i>0;i--)
	        	{
	        		tablier.get(ligne-i).get(colonne-i).setJoueur(joueur);
	        	}
	        }
        }
        // haut droite

        i=1;
        if(ligne>0 && colonne<7)
        {
	        caseTemp=tablier.get(ligne-i).get(colonne+i);
	        while(ligne-i>0 && colonne+i<7 && caseTemp.isaJoueur() && caseTemp.getJoueur()!=joueur)
	        {
	            i++;
	            caseTemp=tablier.get(ligne-i).get(colonne+i);
	        }
	        if(i>1 && caseTemp.getJoueur()==joueur)
	        {
	        	for(i=i-1;i>0;i--)
	        	{
	        		tablier.get(ligne-i).get(colonne+i).setJoueur(joueur);
	        	}
	        }
        }
    
        // bas droite

        i=1;
        if(ligne<7 && colonne<7)
        {
	        caseTemp=tablier.get(ligne+i).get(colonne+i);
	        while(ligne+i<7 && colonne+i<7 && caseTemp.isaJoueur() && caseTemp.getJoueur()!=joueur)
	        {
	            i++;
	            caseTemp=tablier.get(ligne+i).get(colonne+i);
	        }
	        if(i>1 && caseTemp.getJoueur()==joueur)
	        {
	        	for(i=i-1;i>0;i--)
	        	{
	        		tablier.get(ligne+i).get(colonne+i).setJoueur(joueur);
	        	}
	        }
        }

        // bas gauche

        i=1;
        if(ligne<7 && colonne>0)
        {
	        caseTemp=tablier.get(ligne+i).get(colonne-i);
	        while(ligne+i<7 && colonne-i>0 && caseTemp.isaJoueur() && caseTemp.getJoueur()!=joueur)
	        {
	            i++;
	            caseTemp=tablier.get(ligne+i).get(colonne-i);
	        }
	        if(i>1 && caseTemp.getJoueur()==joueur)
	        {
	        	for(i=i-1;i>0;i--)
	        	{
	        		tablier.get(ligne+i).get(colonne-i).setJoueur(joueur);

	        	}
	        }
        }      
    }
	
	public int simulationPrise(Joueur joueur, CaseOthello caseClic) //pour l'IA
	{		
		int score=0;
		int i=1;
		int ligne=caseClic.getLigne();
		int colonne=caseClic.getColonne();
		CaseOthello caseTemp;
		//case dessous
		if(ligne<7)
		{
			caseTemp=tablier.get(ligne+i).get(colonne);
	        while((ligne+i)<7 && caseTemp.isaJoueur() && caseTemp.getJoueur()!=joueur)
	        {
	            i++;
	            caseTemp=tablier.get(ligne+i).get(colonne);
	        }
	        if(i>1 && caseTemp.getJoueur()==joueur)
	        {
	        	for(i=i-1;i>0;i--)
	        	{
	        		score++;
	        	}
	        }
		}
		//case haut
        i=1;
        if(ligne>0)
        {
	        caseTemp=tablier.get(ligne-i).get(colonne);
	        while((ligne-i)>0 && caseTemp.isaJoueur() && caseTemp.getJoueur()!=joueur)
	        {
	            i++;
	            caseTemp=tablier.get(ligne-i).get(colonne);
	        }
	        if(i>1 && caseTemp.getJoueur()==joueur)
	        {
	        	for(i=i-1;i>0;i--)
	        	{
	        		score++;
	        	}
	        }
        }

        //case gauche
        i=1;
        if(colonne>0)
        {
	        caseTemp=tablier.get(ligne).get(colonne-i);
	        while((colonne-i)>0 && caseTemp.isaJoueur() && caseTemp.getJoueur()!=joueur)
	        {
	            i++;
	            caseTemp=tablier.get(ligne).get(colonne-i);
	        }
	        if(i>1 && caseTemp.getJoueur()==joueur)
	        {
	        	for(i=i-1;i>0;i--)
	        	{
	        		score++;
	        	}
	        }
        }
        
        //case droite
        i=1;
        if(colonne<7)
        {
	        caseTemp=tablier.get(ligne).get(colonne+i);
	        while((colonne+i)<7 && caseTemp.isaJoueur() && caseTemp.getJoueur()!=joueur)
	        {
	            i++;
	            caseTemp=tablier.get(ligne).get(colonne+i);
	        }
	        if(i>1 && caseTemp.getJoueur()==joueur)
	        {
	        	for(i=i-1;i>0;i--)
	        	{
	        		score++;
	        	}
	        }
        }
        //case haut gauche

        i=1;
        if(ligne>0 && colonne>0)
        {
	        caseTemp=tablier.get(ligne-i).get(colonne-i);
	        while(ligne-i>0 && colonne-i>0 && caseTemp.isaJoueur() && caseTemp.getJoueur()!=joueur)
	        {
	            i++;
	            caseTemp=tablier.get(ligne-i).get(colonne-i);
	        }
	        if(i>1 && caseTemp.getJoueur()==joueur)
	        {
	        	for(i=i-1;i>0;i--)
	        	{
	        		score++;
	        	}
	        }
        }
        // haut droite

        i=1;
        if(ligne>0 && colonne<7)
        {
	        caseTemp=tablier.get(ligne-i).get(colonne+i);
	        while(ligne-i>0 && colonne+i<7 && caseTemp.isaJoueur() && caseTemp.getJoueur()!=joueur)
	        {
	            i++;
	            caseTemp=tablier.get(ligne-i).get(colonne+i);
	        }
	        if(i>1 && caseTemp.getJoueur()==joueur)
	        {
	        	for(i=i-1;i>0;i--)
	        	{
	        		score++;
	        	}
	        }
        }
    
        // bas droite

        i=1;
        if(ligne<7 && colonne<7)
        {
	        caseTemp=tablier.get(ligne+i).get(colonne+i);
	        while(ligne+i<7 && colonne+i<7 && caseTemp.isaJoueur() && caseTemp.getJoueur()!=joueur)
	        {
	            i++;
	            caseTemp=tablier.get(ligne+i).get(colonne+i);
	        }
	        if(i>1 && caseTemp.getJoueur()==joueur)
	        {
	        	for(i=i-1;i>0;i--)
	        	{
	        		score++;
	        	}
	        }
        }

        // bas gauche

        i=1;
        if(ligne<7 && colonne>0)
        {
	        caseTemp=tablier.get(ligne+i).get(colonne-i);
	        while(ligne+i<7 && colonne-i>0 && caseTemp.isaJoueur() && caseTemp.getJoueur()!=joueur)
	        {
	            i++;
	            caseTemp=tablier.get(ligne+i).get(colonne-i);
	        }
	        if(i>1 && caseTemp.getJoueur()==joueur)
	        {
	        	for(i=i-1;i>0;i--)
	        	{
	        		score++;
	        	}
	        }
        }
		return score;
	}
	
	public boolean verif_jouabilite_joueur(Joueur joueur)
	{
		boolean verif=false; 
		int i=0,j=0;
		
		while(verif==false && j<8)
		{

			verif=this.verifJouable(joueur,tablier.get(i).get(j));
			i++;
			if(i==8)
			{
                            if(j!=8){j++;}
                            i=0;
			}
		}		
		return verif;
	}
	
	public int calculScore(Joueur joueur)
	{
		int i,j;
		int score=0;
		
		for(i=0;i<8;i++)
		{
			for(j=0;j<8;j++)
			{
				if(tablier.get(i).get(j).isaJoueur()==true)
				{
					if(tablier.get(i).get(j).getJoueur()==joueur)
					{
						score++;
					}
				}
			}
		}
		return score;
	}
	
	public void initTablierBloque(int nb)
	{
		int i,j;
		int cpt;
		for(cpt=0;cpt<nb;cpt++)
		{
			do
			{
				i=(int)(Math.random() * 8);
				j=(int)(Math.random() * 8);
			}while(tablier.get(i).get(j).isaJoueur() || tablier.get(i).get(j).isEstInaccessible());
			tablier.get(i).get(j).setEstInaccessible(true);
		}
	}
	
	public CaseOthello IA(Joueur joueur)
	{
		CaseOthello caseMax=null,caseTemp;
		int i,j;
		int nbPrise=0,nbPriseMax=0;
		
		for(i=0;i<8;i++)
		{
			for(j=0;j<8;j++)
			{
				caseTemp=tablier.get(i).get(j);
				if(!caseTemp.isaJoueur() && !caseTemp.isEstInaccessible()){
					nbPrise=simulationPrise(joueur,caseTemp);
					if(nbPrise>nbPriseMax)
					{
						nbPriseMax=nbPrise;
						caseMax=caseTemp;
					}
				}
			}
		}
		return caseMax;
		
	}
}
