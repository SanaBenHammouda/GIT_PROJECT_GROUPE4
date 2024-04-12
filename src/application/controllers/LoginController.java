package application.controllers;

import application.data_base.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

	private double x, y;

	@FXML
	private ImageView btnClose;
	@FXML
	private Button btnSignup;
	@FXML
	private Button btnSignin;
	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private Label message;

	@FXML
	public void CLose_Login_CLick(MouseEvent e) {
		Stage stage = (Stage) btnClose.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void signIn_CLick() throws Exception {
		Data_Base_Conn.etudiant_connecte = null;
		Data_Base_Conn.nom_connecte = null;
		Data_Base_Conn.prof_connecte = 0;
		
		Connection connection = Data_Base_Conn.getConnection();
		String username = txtUsername.getText();
		String password = txtPassword.getText();

//      Test Pour Etudiant
		String sqlE = " select apogee, concat(nom,' ',prenom) as nomEtudiant, username, mdp from etudiant where username = ? and mdp = ?";
		try {

			PreparedStatement psE = connection.prepareStatement(sqlE);
			psE.setString(1, username);
			psE.setString(2, password);

			ResultSet rs = psE.executeQuery();
			if (!rs.next()) {
				message.setVisible(true);
			} else {
				Data_Base_Conn.etudiant_connecte = rs.getString("apogee");
				Data_Base_Conn.nom_connecte = rs.getString("nomEtudiant");
				URL url = new File("src/application/views/EtudiantHome.fxml").toURI().toURL();
				Parent root = FXMLLoader.load(url);
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.initStyle(StageStyle.UNDECORATED);
				Stage stage2 = (Stage) btnSignup.getScene().getWindow();
				stage2.close();
				root.setOnMousePressed(event -> {
					x = event.getSceneX();
					y = event.getSceneY();
				});
				root.setOnMouseDragged(event -> {
					stage.setX(event.getScreenX() - x);
					stage.setY(event.getScreenY() - y);
				});
				stage.show();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// test pour admin
		String sql = "select admin_id,concat(nom,' ',prenom) as NomAdmin, username, mdp from administrateur where username = ? and mdp = ?";
		try {
			PreparedStatement psA = connection.prepareStatement(sql);
			psA.setString(1, username);
			psA.setString(2, password);

			ResultSet resultSetP = psA.executeQuery();
			if (!resultSetP.next()) {
				message.setVisible(true);
				
			} else {
				Data_Base_Conn.prof_connecte = 0;
				Data_Base_Conn.etudiant_connecte = null;
				Data_Base_Conn.admin_connecte = Integer.valueOf(resultSetP.getString("admin_id"));
				Data_Base_Conn.nom_connecte = resultSetP.getString("NomAdmin");
				URL url = new File("src/application/views/AdminHome.fxml").toURI().toURL();
				Parent root = FXMLLoader.load(url);
				Scene scene = new Scene(root);

				Stage stage = new Stage();

				stage.setScene(scene);
				stage.initStyle(StageStyle.UNDECORATED);

				Stage stage2 = (Stage) btnSignup.getScene().getWindow();
				stage2.close();
				root.setOnMousePressed(event -> {
					x = event.getSceneX();
					y = event.getSceneY();
				});
				root.setOnMouseDragged(event -> {
					stage.setX(event.getScreenX() - x);
					stage.setY(event.getScreenY() - y);
				});
				stage.show();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

//        Test Pour Prof
		String queryProf = "  select SOM,concat(nom,' ',prenom) as NomProf, username, mdp from proffesseur where username = ? and mdp = ?";
		try {
			PreparedStatement psPr = connection.prepareStatement(queryProf);
			psPr.setString(1, username);
			psPr.setString(2, password);

			ResultSet resultSetPr = psPr.executeQuery();

			if (!resultSetPr.next()) {
				message.setVisible(true);

			} else {
				Data_Base_Conn.etudiant_connecte = null;
				Data_Base_Conn.admin_connecte = 0;
				Data_Base_Conn.prof_connecte = resultSetPr.getInt("SOM");
				Data_Base_Conn.nom_connecte = resultSetPr.getString("NomProf");
				URL url = new File("src/application/views/ProfHome.fxml").toURI().toURL();
				Parent root = FXMLLoader.load(url);
				Scene scene = new Scene(root);

				Stage stage = new Stage();
				stage.setScene(scene);
				stage.initStyle(StageStyle.UNDECORATED);

				Stage stage2 = (Stage) btnSignup.getScene().getWindow();
				stage2.close();
				stage.show();
				root.setOnMousePressed(event -> {
					x = event.getSceneX();
					y = event.getSceneY();
				});
				root.setOnMouseDragged(event -> {
					stage.setX(event.getScreenX() - x);
					stage.setY(event.getScreenY() - y);
				});
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void Inscrire_Click() throws Exception {
		URL url = new File("src/application/views/inscrire.fxml").toURI().toURL();
		Parent root = FXMLLoader.load(url);
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.initStyle(StageStyle.UNDECORATED);
		root.setOnMousePressed(event -> {
			x = event.getSceneX();
			y = event.getSceneY();
		});
		root.setOnMouseDragged(event -> {
			stage.setX(event.getScreenX() - x);
			stage.setY(event.getScreenY() - y);
		});
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
