package application.controllers;

import application.data_base.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class InscrireController implements Initializable {

	@FXML
    private TextField apogee;

    @FXML
    private Button btnSignin;

    @FXML
    private Button btnSignup;

    @FXML
    private ComboBox<String> classe;

    @FXML
    private TextField email;

    @FXML
    private Label erreur;

    @FXML
    private PasswordField mdp;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField tele;

    @FXML
    private TextField username;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        
        Connection connection = Data_Base_Conn.getConnection();
        ObservableList<String> d = FXCollections.observableArrayList();
        ObservableList<Integer> dv = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM classe");
            while (rs.next()) {
                rs.getRow();
                String classes = rs.getString("nom_classe");
                Integer valuGroups = rs.getInt("id_classe");
                d.add(classes);
                dv.add(valuGroups);

            }
        } catch (SQLException e) {
            e.getErrorCode();
        }                
        classe.setItems(d);
     

    }
    
    
    

    @FXML
    public void sinscrire_Click() throws Exception {
        
        Connection connection = Data_Base_Conn.getConnection();

        int clss_id=0;
        try {
        	PreparedStatement preparedstm = connection.prepareStatement("SELECT id_classe FROM classe WHERE nom_classe = ?");
        	preparedstm.setString(1, classe.getSelectionModel().getSelectedItem() );
        	ResultSet Result = preparedstm.executeQuery();
        	if(Result.next()) {
        		clss_id=Result.getInt("id_classe");
        	}
            
           
            PreparedStatement preparedStatement = connection.prepareStatement("insert into etudiant(apogee,nom, prenom, email,telephone,id_classe,username,mdp )values(?,?,?,?,?,?,?,?)");
           
            preparedStatement.setString(1, apogee.getText().toString());
            preparedStatement.setString(2, nom.getText());
            preparedStatement.setString(3, prenom.getText());
            preparedStatement.setString(4, email.getText());
            preparedStatement.setString(5, tele.getText());
            preparedStatement.setInt(6,clss_id);
            preparedStatement.setString(7, username.getText());
            preparedStatement.setString(8, mdp.getText().toString());
            preparedStatement.executeUpdate();
            
            
            String sql2="select * from matiere where classe_id=?";
            
            PreparedStatement ps1 = connection.prepareStatement(sql2);
            ps1.setInt(1, clss_id);
            ResultSet Rset = ps1.executeQuery();
            while(Rset.next()) {
	            PreparedStatement ps2 = connection.prepareStatement("insert into note(etudiant_id,Cntrol1,Cntrol2,examfinale,matiere_id) VALUES(?,0.0,0.0,0.0,?)");
	            ps2.setInt(1,Integer.parseInt(apogee.getText()));
	            ps2.setInt(2, Rset.getInt("matiere_id"));
	            ps2.executeUpdate();
            }
            Parent root = FXMLLoader.load(getClass().getResource("../views/Login.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
            Stage stage2 = (Stage) btnSignin.getScene().getWindow();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inscription");
            alert.setContentText("Votre inscription a était bien éffectuée");
            alert.showAndWait();
            stage2.close();
            stage.toBack();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void retour() {
        try {
            URL url = new File("src/application/views/Login.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
            Stage stage2 = (Stage) btnSignup.getScene().getWindow();
            stage2.close();
            stage.toBack();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
