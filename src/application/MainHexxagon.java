package application;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import modele.*;

import javafx.geometry.Insets;


public class MainHexxagon {
	
	private PanelVictoire panelVictoire;
	
	private Joueur j1;
	private Joueur j2;
	private Joueur jActuel;
	private Joueur jAttente;
	private PlateauHexxagon plateau;
	private int nbTour;
	private Label score1=new Label();
	private Label score2=new Label();
	private Label nom1 = new Label();
	private Label nom2 = new Label();
	
	private BorderPane pane;
	private CaseHexxagon caseClique;
	
	private Scene sceneMenu;
	
	public MainHexxagon(int taille, Joueur j1, Joueur j2,int nbCaseInaccessible, Scene sceneMenu) {
		this.j1=j1;
		this.j2=j2;
		plateau = new PlateauHexxagon(taille, j1, j2);
		plateau.placerCaseInaccessible(nbCaseInaccessible);
		this.sceneMenu = sceneMenu;
	}
	
	public void jouer(Stage primaryStage)
	{
		try {
			jActuel = j1;
			jAttente = j2;
			nbTour = 1;
        	caseClique=null;
			
			GridPane root = new GridPane();
			
			PanelHexxagon panelHexa=new PanelHexxagon();
			
			pane=panelHexa.addBorderPane(plateau);
			root.setId("pane");
			BorderPane border = new BorderPane();
			root.getChildren().add(border);
			VBox infos = addVbox(j1, j2);
			border.setLeft(infos);
			root.getChildren().add(pane);
			
			Scene scene = new Scene(root, 1000, 700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			
			primaryStage.show();
				root.setOnMouseClicked((event) -> 
				{
					//SECOND CLIC
					if(caseClique!=null){
						CaseHexxagon caseClique2 = plateau.clicToCase((int)event.getSceneX(), (int)event.getSceneY());
						
						//Si le joueur clique sur un autre de ses pions, la pion selectionné change
						if(caseClique2.getJoueur()==jActuel)
							caseClique = plateau.clicToCase((int)event.getSceneX(), (int)event.getSceneY());
					
						else{
		    			
		    				int mouvement=caseClique.verifierDeplacement(caseClique2);
		    				
		    				if(mouvement!=0)//si le deplacement est valide
		    				{
		    					plateau.deplacer(caseClique,caseClique2,mouvement);
		    					
		    					if(plateau.finPartie(jAttente))
		    					{
		    						plateau.finPartieRapide(jActuel);
		    						
		    	                    panelVictoire = new PanelVictoire();
		    	    				panelVictoire.afficherVictoire(primaryStage, sceneMenu, 1, j1, j2, plateau.calculScore(j1), plateau.calculScore(j2));
		    					}
		    					setScore(plateau.calculScore(j1),plateau.calculScore(j2),jAttente);
		    					
		    					pane=panelHexa.addBorderPane(plateau);
		    					root.getChildren().add(pane);
		    					nbTour++;
		    					
		    					//Tour de l'IA
		    					if(jAttente.isIa() && !plateau.finPartie(jAttente))
		    					{
		    						try {
		    							plateau.jouerIa(nbTour, jAttente);
		    						} catch (Exception e) {
		    							e.printStackTrace();
		    						}
		    						pane=panelHexa.addBorderPane(plateau);
			    					root.getChildren().add(pane);
		    						
			    					if(plateau.finPartie(jActuel))
			    					{
			    						plateau.finPartieRapide(jAttente);
			    						
			    	                    panelVictoire = new PanelVictoire();
			    	    				panelVictoire.afficherVictoire(primaryStage, sceneMenu, 1, j1, j2, plateau.calculScore(j1), plateau.calculScore(j2));
			    					}
			    					setScore(plateau.calculScore(j1),plateau.calculScore(j2),jActuel);
			    					
			    					pane=panelHexa.addBorderPane(plateau);
			    					root.getChildren().add(pane);
		    						nbTour++;
		    					}
		    					else
		    					{
			    					//fin du tour
			    					if(jActuel == j1) {
			    						jActuel = j2;
			    						jAttente = j1;
			    					}
			    					else {
			    						jActuel = j1;
			    						jAttente = j2;
			    					}
		    					}
		    					
		    					caseClique=null;
		    				}
		        		}
					}
					//PREMIER CLIC
					else
					{
		            	caseClique = plateau.clicToCase((int)event.getSceneX(), (int)event.getSceneY());
		            	//Si le clic correspond à une case
		            	
		            	if(caseClique!=null)
		            	{
		            		if(!caseClique.verifierOrigine(jActuel))
		            		{
		            			//si la case cliquée n'est pas valide, on la reinitialise
		            			caseClique=null;
		            		}
		            	}
					}
	        	});
		} catch(Exception e) {
			e.printStackTrace();
		}
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

	public Joueur getjActuel() {
		return jActuel;
	}

	public void setjActuel(Joueur jActuel) {
		this.jActuel = jActuel;
	}

	public Joueur getjAttente() {
		return jAttente;
	}

	public void setjAttente(Joueur jAttente) {
		this.jAttente = jAttente;
	}

	public PlateauHexxagon getPlateau() {
		return plateau;
	}

	public void setPlateau(PlateauHexxagon plateau) {
		this.plateau = plateau;
	}

	public int getNbTour() {
		return nbTour;
	}

	public void setNbTour(int nbTour) {
		this.nbTour = nbTour;
	}
	
	public VBox addVbox(Joueur j1, Joueur j2){
    	VBox vbox= new VBox();

        vbox.setPadding(new Insets(0,60,0,0));
        vbox.setSpacing(20);
        vbox.setMinWidth(900);
        vbox.setPrefWidth(900);
        vbox.setMaxWidth(900);
        vbox.setMinHeight(680);
        vbox.setPrefHeight(680);
        vbox.setMaxHeight(680);
        vbox.setStyle("-fx-padding: 10;");
        
        nom1.setText("> "+j1.getPseudo());
        nom1.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        nom1.setTextFill(j1.getCouleur());
        nom1.setStyle("-fx-effect: dropshadow(three-pass-box, #FFFFFF, 3, 0.8, 0, 0);");
        
        VBox.setMargin(nom1, new Insets(20, 0, 0,60));
        VBox.setMargin(score1, new Insets(0, 30, 330,100));
        VBox.setMargin(nom2, new Insets(0, 0, 0,60));
        VBox.setMargin(score2, new Insets(0, 30, 0,100));
        
        if(j2.isIa()){
        	nom2.setText("   "+j2.getPseudo()+" (IA)");
        }
        else{
        	nom2.setText("   "+j2.getPseudo());
        }
        nom2.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        nom2.setTextFill(j2.getCouleur());
        nom2.setStyle("-fx-effect: dropshadow(three-pass-box, #FFFFFF, 3, 0.8, 0, 0);");
        
        score1.setText(plateau.calculScore(j1)+"");
        score2.setText(plateau.calculScore(j2)+"");
        
        score1.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        score1.setTextFill(Color.WHITE);
        score2.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        score2.setTextFill(Color.WHITE);
        
        Label tourDe = new Label("Joueur actuel :");
        tourDe.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        tourDe.setTextFill(Color.WHITE);
        vbox.getChildren().add(nom1);
        vbox.getChildren().add(score1);
        vbox.getChildren().add(score2);
        vbox.getChildren().add(nom2);
    	
        return vbox;
    }
	
	public void setScore(int s1,int s2, Joueur jActuel){
    	score1.setText(Integer.toString(s1));
    	score2.setText(Integer.toString(s2));
    	if(jActuel==j1)
    	{
    		nom1.setText("> "+j1.getPseudo());
    		if(j2.isIa()){
            	nom2.setText("   "+j2.getPseudo()+" (IA)");
            }
            else{
            	nom2.setText("   "+j2.getPseudo());
            }
    	}
    	else{
    		nom1.setText("   "+j1.getPseudo());
    		if(j2.isIa()){
            	nom2.setText("> "+j2.getPseudo()+" (IA)");
            }
            else{
            	nom2.setText("> "+j2.getPseudo());
            }
    	}
    }
}
