package application;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import modele.CaseHexxagon;

public class Hexagone 
{
	private CaseHexxagon caseHexagone;
	private Circle pion;
	private Polygon hexagone;
    
    /**
     * coordonés dans la fenetre, en pixels
     */
    private int x;
    private int y;
    
	
	Hexagone(CaseHexxagon caseHexagone,int x,int y)
	{
		this.caseHexagone=caseHexagone;
		this.x=x;
		this.y=y;
	}
	
	public Polygon hexagone(int cote)
	{
		double top;
		double mid, bot, gg, g, d, dd;
		
		top = (double) (y+Math.sqrt(java.lang.Math.pow(cote, 2)-java.lang.Math.pow(cote/2, 2)));
		mid = (double)y;
		bot = (double) (y-Math.sqrt(java.lang.Math.pow(cote, 2)-java.lang.Math.pow(cote/2, 2)));
		
		gg = (double)x;
		g = (double)x+cote/2;
		d = (double)x+3*cote/2;
		dd = (double)x+2*cote;
		
		Double[] tab=new Double[]{
				g, top,
			    d, top,
			    dd, mid,
			    d,bot,
			    g,bot,
			    gg,mid
			    };
		
		hexagone=new Polygon();
		hexagone.getPoints().addAll(tab);
		return hexagone;
	}
	
	public void affiche(BorderPane pane,int cote)
	{
		Polygon hexagone=hexagone(cote);
		hexagone.setFill(Color.WHITE);
		hexagone.setStroke(Color.BLACK);
		hexagone.setStrokeWidth(2);
		
		pane.getChildren().add(hexagone);
		affichePion(pane,cote);
	}
	
	public void affichePion(BorderPane pane,int cote)
	{
		//affiche un truc diferent selon ce qu'il y a sur la case
		if(caseHexagone.isaJoueur())
		{
			pion=new Circle((double)x+cote,(double) y,(double) cote/2);
			pion.setFill(caseHexagone.getJoueur().getCouleur());
			pion.setStroke(Color.BLACK);
			pion.setEffect(new DropShadow(10, Color.BLACK));
			pane.getChildren().add(pion);
		}
		
		else if(caseHexagone.isEstInaccessible())
		{
			Polygon obstacle=hexagone(cote);
			obstacle.setFill(Color.web("#303030"));
			
			pane.getChildren().add(obstacle);
		}
	}

	public void afficheSelection(BorderPane pane,int cote)
	{
		Circle selection=new Circle((double)x+cote,(double) y,(double) cote/7);
		selection.setFill(Color.BLACK);
		selection.setStroke(Color.BLACK);		
		pane.getChildren().add(selection);
	}
	
	public CaseHexxagon getCaseHexagone() {
		return caseHexagone;
	}

	public void setCaseHexagone(CaseHexxagon caseHexagone) {
		this.caseHexagone = caseHexagone;
	}
}
