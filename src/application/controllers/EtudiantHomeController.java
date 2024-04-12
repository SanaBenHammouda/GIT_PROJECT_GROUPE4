package application.controllers;

import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;



import application.data_base.Data_Base_Conn;
import application.models.GestionEtudiantsViewModel;
import application.models.SupportCoursViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EtudiantHomeController implements Initializable {
	
    @FXML
    //private TableColumn<SupportCoursViewModel, Blob> col_telechargement;
	private Stage stage;
	@FXML
	private ComboBox<String> matieres;

	@FXML
	private Pane panel_close_min;
	@FXML
	private Label Cntrol1;

	@FXML
	private Label Cntrol2;

	@FXML
	private Label Myenne;

	@FXML
	private Pane Pane_etd;

	@FXML
	private VBox VboxMenu;

	@FXML
	private Button btnAccueil;

	@FXML
	private Button btnClose;

	@FXML
	private Button btnLogOut;

	@FXML
	private Button btnMinimize;

	@FXML
	private Button btnNotes;

	@FXML
	private Button btnProfil_etd;

	@FXML
	private Label complet_etd;

	@FXML
	private Label examfinale;

	@FXML
	private ImageView img1_Accueil;

	@FXML
	private ImageView img2_Accueil;

	@FXML
	private ImageView img3_Accueil;

	@FXML
	private ImageView imgUser;

	@FXML
	private Label profNom;

	@FXML
	private ImageView imgViewMinimize;

	@FXML
	private Label labelinfo_Accueil;

	@FXML
	private Label lbl_matiere_mes_notes;

	@FXML
	private Button modifier_etd;

	@FXML
	private Pane panelAccueil;

	@FXML
	private Pane panelNotes;

	@FXML
	private ScrollPane scroll;

	@FXML
	private Label txt_classe;

	@FXML
	private TextField txt_email_etd;

	@FXML
	private TextField txt_mdp;

	@FXML
	private TextField txt_nom;

	@FXML
	private TextField txt_prenom;

	@FXML
	private TextField txt_tele_etd;

	@FXML
	private Text txtapogee;

	@FXML
	private TextField txtusername;

	@FXML
	private Label utilisateur;

	@FXML
	private VBox vbox_messagerie;
	
    @FXML
    private TableView<SupportCoursViewModel> tableView_CourView;

    @FXML
    private MenuItem telecharger;
    
    @FXML
    private Pane panelCour;
    
    @FXML
    private ComboBox<String> matieres_cour;
    
    @FXML
    private TableColumn<SupportCoursViewModel, String> col_Cour;

    @FXML
    private TableColumn<SupportCoursViewModel, Integer> col_NUM;
    

    @FXML
    private Button btnCours;
    

    
    @FXML
    void btnCours_click(ActionEvent event) {
    	
    	panelCour.toFront();
    	GererEffect(btnCours);
    	try {
			Connection connection = Data_Base_Conn.getConnection();
			PreparedStatement stmMatiere = connection.prepareStatement(
					"select * from matiere where classe_id=(select id_classe from etudiant where apogee=?)");
			stmMatiere.setInt(1, Integer.parseInt((Data_Base_Conn.etudiant_connecte)));
			ResultSet rss = stmMatiere.executeQuery();

			ObservableList<String> mat = FXCollections.observableArrayList();

			while (rss.next()) {
				String matiere2 = rss.getString(2);
				mat.add(matiere2);
			}
			matieres_cour.setItems(mat);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
    }
    
    ObservableList<SupportCoursViewModel> cour(int id_mat) throws SQLException {
		Connection connection = Data_Base_Conn.getConnection();
		ObservableList<SupportCoursViewModel> cours = FXCollections.observableArrayList();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from cours cr where cr.matiere_id="+id_mat);
		int i=0;
		while (resultSet.next()) {
			
			cours.addAll(new SupportCoursViewModel(++i,resultSet.getString("nom_cour")/*,resultSet.getBlob("cour")*/));
		}
		return cours;
	}
    
    @FXML
	public void selectedCour(ActionEvent event) throws SQLException {
    	tableView_CourView.getItems().clear();
    	int id_mat=0;
		String Matiere_name = matieres_cour.getSelectionModel().getSelectedItem();
		Connection conn=Data_Base_Conn.getConnection();
		PreparedStatement ps = conn.prepareStatement("select matiere_id from matiere where nom_matiere =?");
		ps.setString(1, Matiere_name);
		ResultSet rset =ps.executeQuery();
		if(rset.next()) {
			id_mat=rset.getInt(1);
		}
		col_NUM.setCellValueFactory(new PropertyValueFactory<>("id_cours"));
		col_Cour.setCellValueFactory(new PropertyValueFactory<>("nom_cours"));
		//col_telechargement.setCellValueFactory(new PropertyValueFactory<>("cour"));

		tableView_CourView.setItems(cour(id_mat));
		tableView_CourView.setEditable(true);
    }
    
    
    @FXML
    void telecharger_click(ActionEvent event) {
    	 
    }
    

	@FXML
	private void profil_show() {
		GererEffect(btnProfil_etd);

		if (Data_Base_Conn.etudiant_connecte != null) {
			Pane_etd.toFront();

			try {
				Connection con = Data_Base_Conn.getConnection();

				ResultSet rs = con.createStatement().executeQuery(String
						.format("select * from etudiant  where apogee = '" + Data_Base_Conn.etudiant_connecte + "'"));

				if (rs.next()) {
					txtapogee.setText(rs.getString("apogee"));
					txt_nom.setText(rs.getString("nom"));
					txt_prenom.setText(rs.getString("prenom"));
					txt_email_etd.setText(rs.getString("email"));
					txt_tele_etd.setText(rs.getString("telephone"));
					txt_mdp.setText(rs.getString("mdp"));
					txtusername.setText(rs.getString("username"));
					complet_etd.setText(rs.getString("nom") + " " + rs.getString("prenom"));

				}
				String sq = "SELECT c.nom_classe as libClasse FROM classe c where c.id_classe= "
						+ rs.getInt("id_classe");
				ResultSet rd = con.createStatement().executeQuery(sq);
				if (rd.next()) {
					txt_classe.setText(rd.getString("libClasse"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Double moyenne;

	@FXML
	public void logOut_Click() throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../Views/Login.fxml"));
		Stage stage = new Stage();
		stage.setScene(new Scene(root));

		stage.initStyle(StageStyle.UNDECORATED);
		Stage stage2 = (Stage) btnLogOut.getScene().getWindow();
		stage2.close();
		stage.show();

	}

	@FXML
	private void btnClose_Click(ActionEvent e) {
		stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
		stage.close();
	}

	@FXML
	private void btnMinimize_Click(ActionEvent e) {
		stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
		stage.setIconified(true);
	}

	@FXML
	public void btnNotes_click() {

		try {
			Connection connection = Data_Base_Conn.getConnection();
			PreparedStatement stmMatiere = connection.prepareStatement(
					"select * from matiere where classe_id=(select id_classe from etudiant where apogee=?)");
			stmMatiere.setInt(1, Integer.parseInt((Data_Base_Conn.etudiant_connecte)));
			ResultSet rss = stmMatiere.executeQuery();

			ObservableList<String> mat = FXCollections.observableArrayList();

			while (rss.next()) {
				String matiere2 = rss.getString(2);
				mat.add(matiere2);
			}
			matieres.setItems(mat);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		GererEffect(btnNotes);
		panelNotes.toFront();
		btnClose.toFront();
		btnMinimize.toFront();
		lbl_matiere_mes_notes.setText("");
		Cntrol1.setText("");
		Cntrol2.setText("");
		examfinale.setText("");
		Myenne.setText("");

	}

	@FXML
	public void selectedItem(ActionEvent event) throws SQLException {
		Connection connection = Data_Base_Conn.getConnection();
		PreparedStatement sqlCommand = connection.prepareStatement("select * from matiere where nom_matiere= ?");
		sqlCommand.setString(1, matieres.getSelectionModel().getSelectedItem());

		ResultSet dataReader = sqlCommand.executeQuery();

		int id_mat = 0;
		if (dataReader.next())
			id_mat = dataReader.getInt("matiere_id");

		try {

			FXCollections.observableArrayList();
			Statement statementNotes = connection.createStatement();
			ResultSet resultSet = statementNotes
					.executeQuery("select Cntrol1,Cntrol2,examfinale  from note where etudiant_id = '"
							+ Data_Base_Conn.etudiant_connecte + "' and matiere_id = '" + id_mat + "'  ");

			lbl_matiere_mes_notes.setText(matieres.getSelectionModel().getSelectedItem());

			int test = 0;
			if (resultSet.next()) {
				if (Double.parseDouble(resultSet.getString("Cntrol2")) != 0) {
					test += 1;
					Cntrol2.setText(resultSet.getString("Cntrol2"));
				} else {
					Cntrol2.setText("En cours ...");
				}
				if (Double.parseDouble(resultSet.getString("Cntrol1")) != 0) {
					Cntrol1.setText(resultSet.getString("Cntrol1"));
					test += 1;
				} else {
					Cntrol1.setText("En cours ...");
				}
				if (Double.parseDouble(resultSet.getString("examfinale")) != 0) {
					examfinale.setText(resultSet.getString("examfinale"));
					test += 1;
				} else {
					examfinale.setText("En cours ...");
				}

			}
			if (test == 3) {
				moyenne = ((Double.valueOf(Cntrol1.getText()) + Double.valueOf(Cntrol2.getText())
						+ Double.valueOf(examfinale.getText())) / 3);
				Myenne.setText(String.valueOf(moyenne));
			} else {

				Myenne.setText("En Cours ...");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void GererEffect(Button buttonException) {
		btnAccueil.setEffect(null);
		btnProfil_etd.setEffect(null);
		btnNotes.setEffect(null);
		btnCours.setEffect(null);
		buttonException.setEffect(new DropShadow());
	}

	@FXML
	private void btnAccueil_click() {

		Accueil_Load();
		GererEffect(btnAccueil);
		panelAccueil.toFront();
		panel_close_min.toFront();
	}

	public void initialize(URL location, ResourceBundle resources) {
		btnClose.toFront();
		btnMinimize.toFront();

		if (Data_Base_Conn.etudiant_connecte != null) {
			Connection con = Data_Base_Conn.getConnection();

			try {
				ResultSet rs = con.createStatement().executeQuery(String.format(
						"select nom,prenom from etudiant  where apogee = '" + Data_Base_Conn.etudiant_connecte + "'"));
				if (rs.next()) {
					utilisateur.setText(rs.getString("nom") + " " + rs.getString("prenom"));
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		btnAccueil_click();

	}

	private void Accueil_Load() {
		btnMinimize.toFront();
		btnClose.toFront();
		if (Data_Base_Conn.etudiant_connecte != null) {

		}
		vbox_messagerie.getChildren().clear();
		vbox_messagerie.setSpacing(30);
		Connection connection = Data_Base_Conn.getConnection();
		if (Data_Base_Conn.etudiant_connecte != null) {
			try {
				Statement q = connection.createStatement();
				ResultSet rs = q.executeQuery(String.format("select annonce.* , c.nom_classe\n"
						+ "from annoce_controle annonce \n" + "inner join classe c on annonce.classe_id = c.id_classe\n"
						+ "where c.id_classe = (select cl.id_classe\n"
						+ " from classe cl inner join etudiant etd on etd.id_classe = cl.id_classe\n"
						+ "                    where etd.apogee = '%s' \n" + "                     ) ORDER BY id_annonce DESC",
						Data_Base_Conn.etudiant_connecte));

				while (rs.next()) {
					
					labelinfo_Accueil.setVisible(false);
					img1_Accueil.setVisible(false);
					img2_Accueil.setVisible(false);
					img3_Accueil.setVisible(true);
					vbox_messagerie.setVisible(true);
					scroll.setVisible(true);
					TextArea actualite = new TextArea();
					actualite.setText(String.format(
							"Pour les étudiants du classe : %s ,Vous avez un controle le %s  \n"
									+ "Plus d'information : %s",
							rs.getString("nom_classe"), rs.getString("date_controle"), rs.getString("description")));
					actualite.setPrefHeight(150);
					actualite.setMaxHeight(150);
					actualite.setMinHeight(150);
					actualite.setEffect(new DropShadow());
					actualite.setEditable(false);
					actualite.setFont(Font.font("Arial", FontWeight.BOLD, 14));
					DropShadow shadow = new DropShadow();
					shadow.setColor(Color.valueOf("#24ACF2"));
					actualite.setEffect(shadow);

					actualite.setWrapText(true);
					actualite.getStylesheets().add("resources/Styles/AccueilStyle.css");
					actualite.getStyleClass().add("text-area");

					vbox_messagerie.getChildren().add(actualite);
					vbox_messagerie.setPrefHeight(vbox_messagerie.getPrefHeight() + 150);
						
					}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@FXML
	void Modifier_Etudiant(ActionEvent event) throws Exception {
		if (Data_Base_Conn.etudiant_connecte != null) {
			Connection cnx = Data_Base_Conn.getConnection();
			cnx.createStatement();

			PreparedStatement preparedStatement = cnx.prepareStatement("UPDATE etudiant SET  apogee = ?, nom = ? ,"
					+ " email = ?, telephone = ?, prenom = ?, username= ?, mdp= ?   WHERE apogee = ?");
			preparedStatement.setDouble(1, Integer.parseInt(txtapogee.getText()));
			preparedStatement.setString(2, txt_nom.getText());
			preparedStatement.setString(3, txt_email_etd.getText());
			preparedStatement.setString(4, txt_tele_etd.getText());
			preparedStatement.setString(5, txt_prenom.getText());
			preparedStatement.setString(6, txtusername.getText());
			preparedStatement.setString(7, txt_mdp.getText());
			preparedStatement.setString(8, Data_Base_Conn.etudiant_connecte);
			preparedStatement.executeUpdate();

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Modifié avec succées");
			alert.setHeaderText("Vos données sont bien à  jour .");
			alert.setContentText("Informations actualisées.");
			alert.showAndWait();

			// actualiser
			String sql = "SELECT  * FROM etudiant where apogee = '" + Data_Base_Conn.etudiant_connecte + "'";
			ResultSet rs = cnx.createStatement().executeQuery(sql);
			if (rs.next()) {
				txtapogee.setText(rs.getString("apogee"));
				txt_nom.setText(rs.getString("nom"));
				txt_prenom.setText(rs.getString("prenom"));

				txt_email_etd.setText(rs.getString("email"));
				txt_tele_etd.setText(rs.getString("telephone"));

				txtusername.setText(rs.getString("username"));
				txt_mdp.setText(rs.getString("mdp"));
			}
		}
	}

}
