/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import modele.CaseOthello;

/**
 *
 * @author Leroy
 */
public class Carre extends Parent{
    private CaseOthello caseO;
    
    public Carre(CaseOthello azerty){
        Rectangle fondCarre = new Rectangle(0,0,84,84);
        Circle pion = new Circle(42,42,30,Color.WHITE);
        pion.setFill(Color.WHITE);
        pion.setStroke(Color.WHITE);
        
        //chaque carré est lié à une case du plateau
        caseO=azerty;
        //on définit son apparence en fonction de ce qu'elle contient
        if(caseO.isEstInaccessible())
        {
            fondCarre.setFill(Color.web("#303030"));
            fondCarre.setStroke(Color.web("#303030"));  
            pion.setFill(Color.web("#303030"));
            pion.setStroke(Color.web("#303030"));
        }
        else
        {
            fondCarre.setFill(Color.WHITE);
            fondCarre.setStroke(Color.BLACK);
        }
        getChildren().add(fondCarre);
        getChildren().add(pion);
        
        //on définit son comportement par rapport a la souris
        if(caseO.isEstInaccessible()==false && caseO.isaJoueur()==false)
        {
            this.setOnMouseEntered(new EventHandler<MouseEvent>(){
                public void handle(MouseEvent me){
                    fondCarre.setFill(Color.GREY);
                    fondCarre.setStroke(Color.BLACK);
                    pion.setFill(Color.GREY);
                    pion.setStroke(Color.GREY);
                }
            });

            this.setOnMouseExited(new EventHandler<MouseEvent>(){
                public void handle(MouseEvent me){
                    fondCarre.setFill(Color.WHITE);
                    fondCarre.setStroke(Color.BLACK);
                    pion.setFill(Color.WHITE);
                    pion.setStroke(Color.WHITE);
                }
            });   
            
            this.setOnMousePressed(new EventHandler<MouseEvent>(){
                public void handle(MouseEvent me){                  
                    fondCarre.setFill(Color.GREY);
                    fondCarre.setHeight(81);
                    fondCarre.setWidth(82);
                    fondCarre.setStroke(Color.WHITE);
                    fondCarre.setStrokeWidth(2);
                    pion.setFill(Color.GREY);
                    pion.setStroke(Color.GREY);

                }
            });
            
            this.setOnMouseReleased(new EventHandler<MouseEvent>(){
                public void handle(MouseEvent me){
                    fondCarre.setFill(Color.WHITE);
                    fondCarre.setStroke(Color.BLACK);
                    fondCarre.setHeight(84);
                    fondCarre.setWidth(84); 
                    fondCarre.setStrokeWidth(1);
                    pion.setFill(Color.WHITE);
                    pion.setStroke(Color.WHITE);
                }
            });
        }     
    }
    
    public void majCarre(CaseOthello caseO)
    {
    	//on change l'affichage et le comportement en fonction du (nouveau) contenu de sa case
        Rectangle fondCarre = new Rectangle(0,0,84,84);
        fondCarre.setFill(Color.WHITE);
        fondCarre.setStroke(Color.BLACK);
        if(caseO.isaJoueur()==true)
        {
            Circle pion = new Circle();
            pion.setCenterX(42);
            pion.setCenterY(42);
            pion.setRadius(30);
            
            pion.setFill(caseO.getJoueur().getCouleur());
            pion.setStroke(Color.BLACK);
            pion.setEffect(new DropShadow(10, Color.BLACK));
      
            this.setOnMouseEntered(new EventHandler<MouseEvent>(){
                public void handle(MouseEvent me){
                    fondCarre.setFill(Color.WHITE);
                    fondCarre.setStroke(Color.BLACK);

                }
            });
            //on enlève les anciens fonds et pions et on ajoute les nouveaux
            this.getChildren().remove(1);
            this.getChildren().remove(0);
            this.getChildren().add(fondCarre);          
            this.getChildren().add(pion);   
        }
    }
    
    public CaseOthello getCase(){
        return caseO;
    }

}   
    

