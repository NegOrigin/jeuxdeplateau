package application;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import modele.*;

public class MainOthello{
	
	private PanelVictoire panelVictoire;
	
	private Joueur j1;
	private Joueur j2;
	private Joueur j_actuel;
	private Label score1 = new Label();
	private Label score2 = new Label();
	private Label jAct = new Label();
	private PlateauOthello plateau;
	private int coordX, coordY;
	
	private Scene sceneMenu;
	
	public MainOthello(Joueur j1, Joueur j2, int caseBlo, Scene sceneMenu)
	{		
		this.j1=j1;
		this.j2=j2;
        plateau = new PlateauOthello(j1, j2); 
        if(caseBlo!=0){
            plateau.initTablierBloque(caseBlo);
        }      
        this.sceneMenu = sceneMenu;
	}
	
	public void jouer(Stage primaryStage){        
		if(j2.isIa()){
        	pve(primaryStage); //joueur versus IA
        }
        else{
        	pvp(primaryStage); //joueur versus joueur
        }
	}
	
	public void pvp(Stage primaryStage)
	{	
        j_actuel=j1;

        Group groupOthello = new Group();
        final Scene scene = new Scene(groupOthello, 1000, 700, Color.web("#303030"));
        primaryStage.setScene(scene);
        primaryStage.show();
        PanelOthello panel = new PanelOthello();//on crée un panel pour afficher le plateau
        groupOthello.getChildren().add(panel);    
        
        BorderPane border = new BorderPane();//le borderpane nous permet d'organiser nos elements dans la fenetre
        
        VBox infos2 = addVbox(j1, j2);//les infos sur les joueurs sont en colonne
        border.setLeft(infos2);//a gauche
        
        border.setCenter(panel.addGridPane(j_actuel,j1,j2,plateau));//plateau au centre
        groupOthello.getChildren().add(border);
        
        panel.majPanel(plateau);
        setScore(2, 2, j1);//cette fonction initialise le score à 2 et on j1 commence
        
        panel.getGrid().setOnMouseClicked((event)-> {
        	int s1,s2;
	        boolean verif_j1, verif_j2;
	        PlateauOthello plate;//on est dans une methode d'une autre classe
	        //on doit creer un plateau pour mettre a jour
	        //parce que le plateau de la classe n'est passe qu'en copie    
	        coordX=(int)((event.getSceneX()-305)/84);
	        coordY=(int)((event.getSceneY()-10)/84);//on converti les coordonnées du clic en cases
	        
	        if(coordX>=0 && coordX<8 && coordY>=0 && coordY<8) {
	            plate=clique(coordX,coordY);//clique se charge de vérifier le coup et de faire les changements
	            verif_j2=plate.verif_jouabilite_joueur(j2);//on verirife si les joueurs peuvent jouer
	            verif_j1=plate.verif_jouabilite_joueur(j1);
	            if(verif_j1==false && verif_j2==false){
	                //affiche vainqueur
                    panelVictoire = new PanelVictoire();
    				panelVictoire.afficherVictoire(primaryStage, sceneMenu, 0, j1, j2, plate.calculScore(j1), plate.calculScore(j2));
	            }
	            else if(j_actuel==j1 && verif_j1==false){
	                j_actuel=j2;
	            }
	            else if(j_actuel==j2 && verif_j2==false){
	                j_actuel=j1;
	            }
	            
	            s1=plate.calculScore(j1);
	            s2=plate.calculScore(j2);
	            setScore(s1, s2,j_actuel); //on met a jour le score dans la vbox infos
	            panel.majPanel(plate);//on met à jour l'affichage
	        }
	    });
	}
	
	public void pve(Stage primaryStage)//le début est comme pvp
	{
        j_actuel=j1;
        
        Group root = new Group();
        Scene scene = new Scene(root, 1000, 700, Color.web("#303030"));
        primaryStage.setScene(scene);
        primaryStage.show();
        PanelOthello panel = new PanelOthello();
        root.getChildren().add(panel);    
        
        BorderPane border = new BorderPane();
        
        VBox infos2 = addVbox(j1, j2);
        border.setLeft(infos2);
        
        border.setCenter(panel.addGridPane(j_actuel,j1,j2,plateau));
        root.getChildren().add(border);
        
        panel.majPanel(plateau);
        setScore(2, 2, j1);
        
        panel.getGrid().setOnMouseClicked((event)-> {
        	int s1,s2;
            boolean verif_j1, verif_j2;
            PlateauOthello plate;//on est dans une methode d'une autre classe
            //on doit creer un plateau pour mettre a jour
            //parce que le plateau de la classe n'est passe qu'en copie    
            coordX=(int)((event.getSceneX()-305)/84);
            coordY=(int)((event.getSceneY()-10)/84);
            if(coordX>=0 && coordX<8 && coordY>=0 && coordY<8) {
                
                plate=clique(coordX,coordY);//ou clique renvoie plateau
                
                verif_j2=plate.verif_jouabilite_joueur(j2);
                verif_j1=plate.verif_jouabilite_joueur(j1);
                if(verif_j1==false && verif_j2==false){
                    //affiche vainqueur
                    panelVictoire = new PanelVictoire();
    				panelVictoire.afficherVictoire(primaryStage, sceneMenu, 0, j1, j2, plate.calculScore(j1), plate.calculScore(j2));
                }
                else if(j_actuel==j2 && verif_j2==false){
                    j_actuel=j1;
                }
                s1=plate.calculScore(j1);
                s2=plate.calculScore(j2);
                setScore(s1, s2,j_actuel);
                panel.majPanel(plate);
                //IA joue juste après le joueur
                while(j_actuel==j2 && verif_j2){
                	CaseOthello caseTemp = plate.IA(j_actuel);//on obtient le meilleur coup
                	plate=clique(caseTemp.getLigne(),caseTemp.getColonne()); //on le joue
                	
                	verif_j2=plate.verif_jouabilite_joueur(j2);
                    verif_j1=plate.verif_jouabilite_joueur(j1);
                  
                    if(verif_j1==false && verif_j2==false){
                        //affiche vainqueur
                        panelVictoire = new PanelVictoire();
        				panelVictoire.afficherVictoire(primaryStage, sceneMenu, 0, j1, j2, plate.calculScore(j1), plate.calculScore(j2));
                    }
                    else if(j_actuel==j1 && verif_j1==false){
                        j_actuel=j2;
                    }
                  
                    s1=plate.calculScore(j1);
                    s2=plate.calculScore(j2);
                    setScore(s1, s2,j_actuel);
                    panel.majPanel(plate);
                }      
            }
        });    
	}	

    public PlateauOthello clique(int coordX, int coordY)//on verifie le coup, on l'effectue et on change le joueur actuel
    {
        CaseOthello caseO = plateau.getCase(coordX, coordY);
        if(plateau.verifJouable(j_actuel, caseO)){
            plateau.prise(j_actuel, caseO);
            if(j_actuel==j1){
                j_actuel=j2;
            }
            else if(j_actuel==j2){
                j_actuel=j1;
            }
        }
        return plateau;
    }
    
    public VBox addVbox(Joueur j1, Joueur j2){//on crée le panneau avec les infos
    	VBox vbox= new VBox();//la vbox affiche ses éléments en colonne

        vbox.setPadding(new Insets(0,20,0,0));
        vbox.setSpacing(20);
        vbox.setMinWidth(300);
        vbox.setPrefWidth(300);//on fixe la taille
        vbox.setMaxWidth(300);
        vbox.setStyle("-fx-padding: 10;" + 
                "-fx-border-style: solid inside;" + 
                "-fx-border-width: 1;" +
                "-fx-border-insets: 8 5 0 10;" + 
                "-fx-border-radius: 10;" + 
                "-fx-border-color: white;" );
    	vbox.setAlignment(Pos.CENTER);
        
    	//on déclare tous les labels qui vont composer la vbox
        Label nom1 = new Label(j1.getPseudo());
        nom1.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        nom1.setTextFill(j1.getCouleur());
        nom1.setStyle("-fx-effect: dropshadow(three-pass-box, #FFFFFF, 3, 0.8, 0, 0);");
        
        VBox.setMargin(score1, new Insets(0, 0, 60,0));

        Label nom2 = new Label();
        if(j2.isIa()){
        	nom2.setText(j2.getPseudo()+" (IA)");
        }
        else{
        	nom2.setText(j2.getPseudo());
        }
        nom2.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        nom2.setTextFill(j2.getCouleur());
        nom2.setStyle("-fx-effect: dropshadow(three-pass-box, #FFFFFF, 3, 0.8, 0, 0);");
       
        VBox.setMargin(score2, new Insets(0, 0, 60,0));
        
        score1.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        score1.setTextFill(Color.WHITE);
        score2.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        score2.setTextFill(Color.WHITE);
        
        Label tourDe = new Label("Joueur actuel :");
        tourDe.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        tourDe.setTextFill(Color.WHITE);
        
        jAct.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        jAct.setTextFill(j1.getCouleur());
        jAct.setStyle("-fx-effect: dropshadow(three-pass-box, #FFFFFF, 3, 0.8, 0, 0);");

        //on ajoute les labels dans l'ordre
        vbox.getChildren().add(nom1);
        vbox.getChildren().add(score1);;
        vbox.getChildren().add(nom2);       
        vbox.getChildren().add(score2);
        vbox.getChildren().add(tourDe);
        vbox.getChildren().add(jAct);
    	
        return vbox;
    }
    
    public void setScore(int s1,int s2, Joueur jActuel){
    	//on transforme les scores int en String
    	score1.setText(Integer.toString(s1));
    	score2.setText(Integer.toString(s2));
    	jAct.setText(jActuel.getPseudo());
    	jAct.setTextFill(jActuel.getCouleur());
        jAct.setStyle("-fx-effect: dropshadow(three-pass-box, #FFFFFF, 3, 0.8, 0, 0);");

    }
}
