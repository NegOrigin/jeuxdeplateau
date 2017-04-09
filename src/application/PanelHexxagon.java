package application;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import modele.PlateauHexxagon;

import java.util.ArrayList;

public class PanelHexxagon extends Parent {

	    private ArrayList<ArrayList<Hexagone>> listeHexagones = new ArrayList<ArrayList<Hexagone>>();
	   
	    public BorderPane addBorderPane(PlateauHexxagon plateau)
	    {
	    	int i,j;
	    	BorderPane border = new BorderPane();
	    	int xLigne, yLigne, xCol, yCol;
	 		
	 		int cote = 800/(14+(plateau.getTablier().size()-5)*3);
	 		
	 		xLigne = 100; yLigne = 350;
	 		
	 		for (i=0; i<plateau.getTablier().size(); i++)
	 		{
	 			ArrayList<Hexagone> ligne = new ArrayList<Hexagone>();
	 			xCol = xLigne;
	 			yCol = yLigne;
	 			
	 			for (j=0; j<plateau.getTablier().size(); j++)
	 			{
	 				Hexagone bouton = new Hexagone(plateau.getTablier().get(i).get(j),xCol,yCol);
	 				bouton.affiche(border,cote);
	 				xCol += 3*cote/2; yCol += Math.sqrt(java.lang.Math.pow(cote, 2)-java.lang.Math.pow(cote/2, 2));
	 			}
	 			listeHexagones.add(ligne);
	 			xLigne += 3*cote/2; yLigne -= Math.sqrt(java.lang.Math.pow(cote, 2)-java.lang.Math.pow(cote/2, 2));
	 		}
	         return border;
	    }
	    
	    public void majPanel(BorderPane pane,PlateauHexxagon plateau){
	        int i,j;
	        int cote = 800/(14+(plateau.getTablier().size()-5)*3);
	        for(i=0;i<listeHexagones.size();i++)
	        {
	            for(j=0;j<listeHexagones.size();j++)
	            {
	            	listeHexagones.get(i).get(j).affichePion(pane, cote);
	            }
	        }
	    }
}
