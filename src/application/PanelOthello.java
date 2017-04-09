package application;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import modele.Joueur;
import modele.PlateauOthello;

public class PanelOthello extends Parent {
    
    private GridPane grid = new GridPane();
    private ArrayList<ArrayList<Carre>> liste = new ArrayList<ArrayList<Carre>>();
    
    public PanelOthello()
    {
      
    }
    
    public GridPane addGridPane(Joueur actu,Joueur j1,Joueur j2,PlateauOthello plate)
    {
    	//la gridpane affiche ses éléments en grille
        int i,j;
        //grid.setGridLinesVisible(true);
        for(i=0;i<8;i++)
        {
            ArrayList<Carre> ligne = new ArrayList<Carre>();
            for(j=0;j<8;j++)
            {
                Carre bouton = new Carre(plate.getCase(i,j));//on le compose de carrés
                ligne.add(bouton);
                grid.add(bouton, i, j);
            }
            liste.add(ligne);
        }
        grid.setPadding(new Insets(10,0,0,5));//haut,droit,bas,gauche
        return grid;
    }
    
    public void majPanel(PlateauOthello plate){
        int i,j;
        for(i=0;i<8;i++)
        {
            for(j=0;j<8;j++)
            {
                if(plate.getCase(i,j).isaJoueur()){
                    liste.get(i).get(j).majCarre(plate.getCase(i,j));
                }
            }
        }
    }
    
    public GridPane getGrid(){
        return grid;
    }
}
