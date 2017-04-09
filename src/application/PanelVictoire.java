package application;

import java.sql.*;
import java.util.Calendar;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.Joueur;

public class PanelVictoire {

	private GridPane gridVictoire;
	private Scene scene;
	
	private Label victoire, gagnant, dernieresParties;
	private Button menuPrincipal;
	
	public PanelVictoire() {
		
	}
	
	public void afficherVictoire(Stage primaryStage, Scene sceneMenu, int jeu, Joueur j1, Joueur j2, int score1, int score2) {
		gridVictoire = new GridPane();
		gridVictoire.setHgap(20); 
		gridVictoire.setVgap(20);
		gridVictoire.setAlignment(Pos.CENTER);
		gridVictoire.setId("pane");
		
		scene = new Scene(gridVictoire, 1000, 700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		//AFFICHE LE GAGNANT
		if(score1 > score2) {
			HBox boxVictoire = new HBox(20);
			victoire = new Label("Victoire de");
			victoire.setId("victoire");
			boxVictoire.getChildren().add(victoire);
			gagnant = new Label(j1.getPseudo());
			gagnant.setId("gagnant");
			gagnant.setTextFill(j1.getCouleur());
			boxVictoire.getChildren().add(gagnant);
			gridVictoire.add(boxVictoire, 0, 0);
		}
		else if(score1 < score2) {
			HBox boxVictoire = new HBox(20);
			victoire = new Label("Victoire de");
			victoire.setId("victoire");
			boxVictoire.getChildren().add(victoire);
			gagnant = new Label(j2.getPseudo());
			gagnant.setId("gagnant");
			gagnant.setTextFill(j2.getCouleur());
			boxVictoire.getChildren().add(gagnant);
			gridVictoire.add(boxVictoire, 0, 0);
		}
		else {
			victoire = new Label("Egalité");
			victoire.setId("victoire");
			gridVictoire.add(victoire, 0, 0);
		}
		
		//AJOUT RESULTAT PARTIE BD
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://127.0.0.1:3306/jeuxdeplateau";
			String user = "root";
			String password = "";
			
			Connection connex = DriverManager.getConnection(url, user, password);
			
			Calendar calendar = Calendar.getInstance();
			Date date = new Date(calendar.getTime().getTime());
			
			String query = "INSERT INTO historique (jeu, joueur1, joueur2, score1, score2, couleur1, couleur2, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
			
			PreparedStatement preparedStmt = connex.prepareStatement(query);
			preparedStmt.setInt(1, jeu);
			preparedStmt.setString(2, j1.getPseudo());
			preparedStmt.setString(3, j2.getPseudo());
			preparedStmt.setInt(4, score1);
			preparedStmt.setInt(5, score2);
			preparedStmt.setString(6, j1.getCouleur().toString());
			preparedStmt.setString(7, j2.getCouleur().toString());
			preparedStmt.setDate(8, date);
			
			preparedStmt.execute();
			
			connex.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		afficherHistoirique(jeu);
		
		HBox boxMenu = new HBox(20);
		menuPrincipal = new Button("MENU PRINCIPAL");
		menuPrincipal.setId("menuPrincipal");
		menuPrincipal.setPrefWidth(300);
		menuPrincipal.setOnMouseClicked((event) -> {
			primaryStage.setScene(sceneMenu);
		});
		boxMenu.getChildren().add(menuPrincipal);
		boxMenu.setAlignment(Pos.CENTER);
		gridVictoire.add(boxMenu, 0, 4);

		primaryStage.setScene(scene);
	}
	
	public void afficherHistoirique(int jeu) {		
		//AFFICHE LES 5 DERNIERES PARTIES JOUEES
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://127.0.0.1:3306/jeuxdeplateau";
			String user = "root";
			String password = "";
			
			Connection connex = DriverManager.getConnection(url, user, password);
			
			Statement stmt = connex.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM historique WHERE jeu = "+jeu+" ORDER BY id DESC LIMIT 5;");

			dernieresParties = new Label("Dernières parties :");
			dernieresParties.setId("dernieresParties");
			gridVictoire.add(dernieresParties, 0, 1);

			GridPane gridHistorique = new GridPane();
			gridHistorique.setHgap(20); 
			gridHistorique.setVgap(20);
			gridHistorique.setAlignment(Pos.CENTER);
			int i = 0;
			while (result.next()) {
				Label j1 = new Label(result.getString("joueur1"));
				j1.setTextFill(Color.web(result.getString("couleur1")));
				j1.setId("histoJ1");
				j1.setPrefWidth(150);
				gridHistorique.add(j1, 0, i);
				Label score1 = new Label(result.getString("score1"));
				score1.setId("histoScore1");
				score1.setPrefWidth(60);
				score1.setAlignment(Pos.CENTER_RIGHT);
				gridHistorique.add(score1, 1, i);
				Label tiret = new Label("-");
				tiret.setId("tiret");
				gridHistorique.add(tiret, 2, i);
				Label score2 = new Label(result.getString("score2"));
				score2.setId("histoScore2");
				score2.setPrefWidth(60);
				gridHistorique.add(score2, 3, i);
				Label j2 = new Label(result.getString("joueur2"));
				j2.setTextFill(Color.web(result.getString("couleur2")));
				j2.setId("histoJ2");
				j2.setPrefWidth(150);
				j2.setAlignment(Pos.CENTER_RIGHT);
				gridHistorique.add(j2, 4, i);
				i++;
			}
			gridVictoire.add(gridHistorique, 0, 2);
			
			connex.close();
		} catch (Exception e) {
			dernieresParties = new Label("Echec de la connection à la base de données");
			dernieresParties.setId("dernieresParties");
			gridVictoire.add(dernieresParties, 0, 1);
			e.printStackTrace();
		}
	}
}
