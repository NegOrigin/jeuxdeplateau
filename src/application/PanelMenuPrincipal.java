package application;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.MenuPrincipal;

public class PanelMenuPrincipal {
    
	private GridPane gridMenu = new GridPane();
	private MenuPrincipal menu = new MenuPrincipal();
    
	private MainOthello mainOthello;
	private MainHexxagon mainHexxagon;
    
	private Scene scene;

	//OBJETS DU MENU
	private Label titre, j1, j2, vs, jeu, mode;
	private final Label taille = new Label(), inaccessible = new Label();
	private VBox joueur1, joueur2;
	private final HBox boxTaille = new HBox(10), boxInaccessible = new HBox(10);
	private TextField pseudoJ1, pseudoJ2;
	private ColorPicker colorJ1, colorJ2;
	private final Button othello = new Button(), hexxagon = new Button(), jcj = new Button(), jce = new Button();
	private Button jouer;
	
	public PanelMenuPrincipal() {
		
	}
	
	public void afficherMenu(Stage primaryStage) {
		gridMenu = new GridPane();
		gridMenu.setHgap(20); 
		gridMenu.setVgap(20);
		gridMenu.setAlignment(Pos.CENTER);
		gridMenu.setId("pane");
		
		scene = new Scene(gridMenu, 1000, 700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		//LIGNE 0 : TITRE
		titre = new Label("JEUX DE PLATEAU");
		titre.setId("titre");
		titre.setContentDisplay(ContentDisplay.CENTER);
		gridMenu.add(titre, 1, 0, 3, 1);
		
		//LIGNE 1 : JOUEURS
			//COLONNE 0 : "J1"
		j1 = new Label("J1");
		j1.setId("j1");
		gridMenu.add(j1, 0, 1);
			//COLONNE 1 : PARAMETRES J1
		joueur1 = new VBox(20);
		pseudoJ1 = new TextField();
		pseudoJ1.setPadding(new Insets(3));
		pseudoJ1.setId("pseudoJ1");
		pseudoJ1.setPrefWidth(150);
		pseudoJ1.textProperty().addListener((event) -> {
			menu.getJ1().setPseudo(pseudoJ1.getText());
			menu.getJ1().verifierPseudo(menu.getJ2());
		});
		colorJ1 = new ColorPicker(Color.web("#FFFFFF"));
		colorJ1.setPadding(new Insets(4));
		colorJ1.setPrefWidth(150);
		colorJ1.setId("colorJ1");
		colorJ1.setOnAction((event) -> {
			menu.getJ1().setCouleur(colorJ1.getValue());
			colorJ1.setStyle("-fx-color: #"+String.format("%02X%02X%02X", (int)(colorJ1.getValue().getRed()*255), (int)(colorJ1.getValue().getGreen()*255), (int)(colorJ1.getValue().getBlue()*255)));
		});
		joueur1.getChildren().addAll(pseudoJ1, colorJ1);
		joueur1.setAlignment(Pos.CENTER);
		gridMenu.add(joueur1, 1, 1);
			//COLONNE 2 : "VS"
		vs = new Label("VS");
		vs.setId("vs");
		vs.setMinWidth(174);
		vs.setAlignment(Pos.CENTER);
		gridMenu.add(vs, 2, 1);
			//COLONNE 3 : PARAMETRES J2
		joueur2 = new VBox(20);
		pseudoJ2 = new TextField();
		pseudoJ2.setPadding(new Insets(3));
		pseudoJ2.setId("pseudoJ2");
		pseudoJ2.setPrefWidth(150);
		pseudoJ2.textProperty().addListener((event) -> {
			menu.getJ2().setPseudo(pseudoJ2.getText());
			menu.getJ2().verifierPseudo(menu.getJ1());
		});
		colorJ2 = new ColorPicker(Color.web("#000000"));
		colorJ2.setPadding(new Insets(4));
		colorJ2.setPrefWidth(150);
		colorJ2.setId("colorJ2");
		colorJ2.setOnAction((event) -> {
			menu.getJ2().setCouleur(colorJ2.getValue());
			colorJ2.setStyle("-fx-color: #"+String.format("%02X%02X%02X", (int)(colorJ2.getValue().getRed()*255), (int)(colorJ2.getValue().getGreen()*255), (int)(colorJ2.getValue().getBlue()*255)));
		});
		joueur2.getChildren().addAll(pseudoJ2, colorJ2);
		joueur2.setAlignment(Pos.CENTER_LEFT);
		gridMenu.add(joueur2, 3, 1);
			//COLONNE 4 : "J2"
		j2 = new Label("J2");
		j2.setId("j2");
		gridMenu.add(j2, 4, 1);

		//LIGNE 2 : CHOIX JEU
			//COLONNE 1 : OTHELLO
		othello.setText("OTHELLO");
		othello.setId("othello");
		othello.setPrefWidth(150);
		othello.setOnMouseClicked((event) -> {
				menu.setJeu(0);
				taille.setVisible(false);
				boxTaille.setVisible(false);
				othello.setStyle("-fx-effect: dropshadow(three-pass-box, #16BEEB, 20, 0.5, 0, 0)");
				hexxagon.setStyle("-fx-effect: none");
		});
		gridMenu.add(othello, 1, 2);
			//COLONNE 2 : "JEU"
		jeu = new Label("JEU");
		jeu.setId("jeu");
		jeu.setMinWidth(174);
		jeu.setAlignment(Pos.CENTER);
		gridMenu.add(jeu, 2, 2);
			//COLONNE 3 : HEXXAGON
		hexxagon.setText("HEXXAGON");
		hexxagon.setId("hexxagon");
		hexxagon.setPrefWidth(150);
		hexxagon.setOnMouseClicked((event) -> {
				menu.setJeu(1);
				taille.setVisible(true);
				boxTaille.setVisible(true);
				othello.setStyle("-fx-effect: none");
				hexxagon.setStyle("-fx-effect: dropshadow(three-pass-box, #16BEEB, 20, 0.5, 0, 0)");
		});
		gridMenu.add(hexxagon, 3, 2);
		
		//LIGNE 3 : CHOIX MODE
			//COLONNE 1 : JOUEUR VS JOUEUR
		jcj.setText("JOUEUR VS JOUEUR");
		jcj.setId("jcj");
		jcj.setPrefWidth(150);
		jcj.setOnMouseClicked((event) -> {
				menu.getJ2().setIa(false);
				jcj.setStyle("-fx-effect: dropshadow(three-pass-box, #16BEEB, 20, 0.5, 0, 0)");
				jce.setStyle("-fx-effect: none");
		});
		gridMenu.add(jcj, 1, 3);
			//COLONNE 2 : "MODE"
		mode = new Label("MODE");
		mode.setId("mode");
		mode.setMinWidth(174);
		mode.setAlignment(Pos.CENTER);
		gridMenu.add(mode, 2, 3);
			//COLONNE 3 : JOUEUR VS IA
		jce.setText("JOUEUR VS IA");
		jce.setId("jce");
		jce.setPrefWidth(150);
		jce.setOnMouseClicked((event) -> {
			menu.getJ2().setIa(true);
			jcj.setStyle("-fx-effect: none");
			jce.setStyle("-fx-effect: dropshadow(three-pass-box, #16BEEB, 20, 0.5, 0, 0)");
		});
		gridMenu.add(jce, 3, 3);
		
		//LIGNE 4 : CHOIX INACCESSIBLES
			//COLONNE 1,2 : "CASES INACCESSIBLES"
		inaccessible.setText("CASES INACCESSIBLES");
		inaccessible.setId("inaccessible");
		inaccessible.setMinWidth(150);
		inaccessible.setAlignment(Pos.CENTER_LEFT);
		gridMenu.add(inaccessible, 1, 4, 2, 1);
			//COLONNE 2,3 : INACCESSIBLES
		boxInaccessible.setId("boxInaccessible");
		boxInaccessible.setAlignment(Pos.CENTER_RIGHT);
		for(int i=0; i<3; i++) {
			Button bouton = new Button(Integer.toString(i));
			bouton.setPrefWidth(40);
			bouton.setRotate(0);
			bouton.setOnMouseClicked((event) -> {
				menu.setInaccessible(Integer.parseInt(bouton.getText()));
				for (int j=0; j<3; j++) {
					boxInaccessible.getChildren().get(j).setStyle("-fx-effect: none");
				}
				bouton.setStyle("-fx-effect: dropshadow(three-pass-box, #16BEEB, 20, 0.5, 0, 0)");
			});
			boxInaccessible.getChildren().add(bouton);
		}
		gridMenu.add(boxInaccessible, 2, 4, 2, 1);
		
		//LIGNE 5 : CHOIX TAILLE
			//COLONNE 1,2 : "TAILLE DU PLATEAU"
		taille.setText("TAILLE DU PLATEAU");
		taille.setId("taille");
		taille.setMinWidth(150);
		taille.setAlignment(Pos.CENTER_LEFT);
		gridMenu.add(taille, 1, 5, 2, 1);
			//COLONNE 2,3 : TAILLE
		boxTaille.setId("boxTaille");
		boxTaille.setAlignment(Pos.CENTER_RIGHT);
		for(int i=5; i<11; i++) {
			Button bouton = new Button(Integer.toString(i));
			bouton.setPrefWidth(40);
			bouton.setOnMouseClicked((event) -> {
				menu.setTaille(Integer.parseInt(bouton.getText()));
				for (int j=0; j<6; j++) {
					boxTaille.getChildren().get(j).setStyle("-fx-effect: none");
				}
				bouton.setStyle("-fx-effect: dropshadow(three-pass-box, #16BEEB, 20, 0.5, 0, 0)");
			});
			boxTaille.getChildren().add(bouton);
		}
		gridMenu.add(boxTaille, 2, 5, 2, 1);
		
		//LIGNE 6 : JOUER
		jouer = new Button("JOUER");
		jouer.setId("jouer");
		jouer.setPrefWidth(174);
		jouer.setOnMouseClicked((event) -> {
			if(menu.getJeu() == 0) {
				//LANCEMENT OTHELLO
				mainOthello = new MainOthello(menu.getJ1(), menu.getJ2(), menu.getInaccessible(), scene);
				mainOthello.jouer(primaryStage);
			}
			else if(menu.getJeu() == 1) {
				//LANCEMENT HEXXAGON
				mainHexxagon = new MainHexxagon(menu.getTaille(), menu.getJ1(), menu.getJ2(), menu.getInaccessible(), scene);
				mainHexxagon.jouer(primaryStage);
			}
		});
		gridMenu.add(jouer, 2, 7);

		colorJ1.setStyle("-fx-color: #FFFFFF");
		colorJ2.setStyle("-fx-color: #000000");
		othello.setStyle("-fx-effect: dropshadow(three-pass-box, #16BEEB, 20, 0.5, 0, 0)");
		taille.setVisible(false);
		boxTaille.setVisible(false);
		
		primaryStage.setScene(scene);
	}
}
