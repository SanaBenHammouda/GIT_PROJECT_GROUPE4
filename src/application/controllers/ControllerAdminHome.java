package application.controllers;

import application.data_base.Data_Base_Conn;
import application.models.Classes_View_Model;
import application.models.GestionEtudiantsViewModel;
import application.models.GestionProfViewModel;
import application.models.Gestion_Matiere_view_model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class ControllerAdminHome implements Initializable {

	@FXML
	private MenuItem supprimer_matiere;

	@FXML
	private TableColumn<Gestion_Matiere_view_model, String> col_classe_concerne;

	@FXML
	private TableColumn<Gestion_Matiere_view_model, Integer> col_id_matiere;

	@FXML
	private TableColumn<Gestion_Matiere_view_model, Integer> col_nom_matiere;

	@FXML
	private TableView<Gestion_Matiere_view_model> table_view_gestion_matiere;

	@FXML
	private TableColumn<Classes_View_Model, Integer> col_id_classe;

	@FXML
	private TableColumn<Classes_View_Model, Integer> col_nbr_etudiants;

	@FXML
	private TableColumn<Classes_View_Model, String> col_nom_classe;

	@FXML
	private TableView<Classes_View_Model> table_view_gestion_classes;

	@FXML
	private MenuItem supprimer_classe;
	@FXML
	private Pane Pane_Classes;

	@FXML
	private TextField txt_nom_classe;

	@FXML
	private Button btnClasses;

	@FXML
	private Pane Pane_Matiere;

	@FXML
	private Pane panel_close_minimize;
	@FXML
	private Button btnMatiere;

	@FXML
	private ComboBox<String> cb_classe_gestion_matiere;

	@FXML
	private TextField txt_nom_matiere;

	@FXML
	private Label lbl_message_matiere;
	@FXML
	private Label lbl_message;

	@FXML
	private Button btn_ajouter;

	@FXML
	private Button Btn_Rechercher;

	@FXML
	private ComboBox<String> cb_classe;

	@FXML
	private ComboBox<String> cb_matiere;

	@FXML
	private ComboBox<String> CB_classe_gestionEtudiant;

	@FXML
	private DatePicker txt_dn;

	@FXML
	private Pane Pane_pers;

	@FXML
	private RadioButton RB_Femme;

	@FXML
	private RadioButton RB_Homme;

	@FXML
	private TableView<GestionProfViewModel> TableViewProfs;

	@FXML
	private VBox VboxMenu;

	@FXML
	private Pane addProfPanel;

	@FXML
	private Button btnAccueil;

	@FXML
	private Button btnClose;

	@FXML
	private Button btnGestionProf;

	@FXML
	private Button btnGestion_Etudiant;

	@FXML
	private Button btnLogOut;

	@FXML
	private Button btnMinimize;

	@FXML
	private Button btnProfil;

	@FXML
	private TableColumn<GestionProfViewModel, String> col_NomComplet;

	@FXML
	private TableColumn<GestionEtudiantsViewModel, Integer> col_apogee;

	@FXML
	private TableColumn<GestionProfViewModel, String> col_email;

	@FXML
	private TableColumn<GestionProfViewModel, String> col_groupe;

	@FXML
	private TableColumn<GestionEtudiantsViewModel, String> col_mail;

	@FXML
	private TableColumn<GestionProfViewModel, String> col_matiere;

	@FXML
	private TableColumn<GestionEtudiantsViewModel, String> col_nom;

	@FXML
	private TableColumn<GestionEtudiantsViewModel, String> col_prenom;

	@FXML
	private TableColumn<GestionProfViewModel, Integer> col_som;

	@FXML
	private TableColumn<GestionEtudiantsViewModel, String> col_tele;

	@FXML
	private Label admin_nom_complet;

	@FXML
	private ImageView iconBtnProf;

	@FXML
	private ImageView img1_Accueil;

	@FXML
	private ImageView img2_Accueil;

	@FXML
	private ImageView imgUser;

	@FXML
	private ImageView imgViewMinimize;

	@FXML
	private Label labelinfo_Accueil;

	@FXML
	private Button modifier_pers;

	@FXML
	private Pane panelAccueil;

	@FXML
	private Pane panelEtudiant;

	@FXML
	private Pane panelListerProf;

	@FXML
	private ScrollPane scroll;

	@FXML
	private ToggleGroup sexe1;

	@FXML
	private MenuItem supprimer;

	@FXML
	private MenuItem supprimerProf;

	@FXML
	private TableView<GestionEtudiantsViewModel> tableView_GestionEtudiant;

	@FXML
	private TextField txt_prenom_prof;

	@FXML
	private TextField txt_som;

	@FXML
	private TextField txt_email_prof;

	@FXML
	private TextField txt_nom_prof;

	@FXML
	private TextField txt_mdp_prof;

	@FXML
	private TextField txtSearch;

	@FXML
	private TextField txt_tele_prof;

	@FXML
	private TextField txt_username_prof;

	@FXML
	private TextField txt_email;

	@FXML
	private TextField txt_mdp;

	@FXML
	private TextField txt_nom;

	@FXML
	private TextField txt_prenom;

	@FXML
	private TextField txt_tele;

	@FXML
	private TextField txt_username;

	@FXML
	private Label utilisateur;

	@FXML
	private VBox vbox_messagerie;

	ObservableList<Gestion_Matiere_view_model> Matieres() throws SQLException {
		Connection connection = Data_Base_Conn.getConnection();
		ObservableList<Gestion_Matiere_view_model> classes = FXCollections.observableArrayList();
		Statement s = connection.createStatement();
		ResultSet rset = s.executeQuery("SELECT * FROM matiere");

		while (rset.next()) {

			String classename = null;

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM classe WHERE id_classe = ?");
			ps.setInt(1, rset.getInt("classe_id"));
			ResultSet rset1 = ps.executeQuery();
			if (rset1.next())
				classename = rset1.getString("nom_classe");

			classes.addAll(new Gestion_Matiere_view_model(rset.getInt("matiere_id"), rset.getString("nom_matiere"),
					classename));

		}

		return classes;
	}

	@FXML
	void supprimer_matiere_Click(ActionEvent event) {
		Gestion_Matiere_view_model mat = (Gestion_Matiere_view_model) table_view_gestion_matiere.getSelectionModel()
				.getSelectedItem();
		if (mat == null) {
			System.out.println("aucun etudiant a supprimer !");
			return;
		}
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Supression d'une matiere !");
		alert.setContentText("Etes vous totalement sur de  supprimer la classe  " + mat.getNom_matiere());
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.setHeight(400);
		Optional<ButtonType> reponse = alert.showAndWait();
		if (reponse.get().equals(ButtonType.OK)) {
			try {
				Connection connection = Data_Base_Conn.getConnection();
				Statement sqlCommand = connection.createStatement();
				sqlCommand.execute(String.format("delete from matiere where matiere_id = %d;", mat.getMatiere_id()));
				table_view_gestion_matiere.getItems()
						.remove(table_view_gestion_matiere.getSelectionModel().getSelectedItem());
				table_view_gestion_matiere.refresh();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	void supprimer_classe_Click(ActionEvent event) {
		Classes_View_Model classe = (Classes_View_Model) table_view_gestion_classes.getSelectionModel()
				.getSelectedItem();
		if (classe == null) {
			System.out.println("aucun etudiant a supprimer !");
			return;
		}
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Supression d'une classe !");
		alert.setContentText("Etes vous totalement sur de  supprimer la classe  " + classe.getClasse_nom()
				+ "??\n Toutes les  étudiants " + " inscrit dans cette classe seront ainsi supprimer !!");
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.setHeight(400);
		Optional<ButtonType> reponse = alert.showAndWait();
		if (reponse.get().equals(ButtonType.OK)) {
			try {
				Connection connection = Data_Base_Conn.getConnection();
				Statement sqlCommand = connection.createStatement();
				sqlCommand.execute(String.format("delete from classe where id_classe = %d;", classe.getId_classe()));
				table_view_gestion_classes.getItems()
						.remove(table_view_gestion_classes.getSelectionModel().getSelectedItem());
				table_view_gestion_classes.refresh();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	ObservableList<Classes_View_Model> Classes() throws SQLException {
		Connection connection = Data_Base_Conn.getConnection();
		ObservableList<Classes_View_Model> classes = FXCollections.observableArrayList();
		Statement s = connection.createStatement();
		ResultSet rset = s.executeQuery("SELECT * FROM classe");

		while (rset.next()) {

			int nbr = 0;

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM etudiant WHERE id_classe = ?");
			ps.setInt(1, rset.getInt("id_classe"));
			ResultSet rset1 = ps.executeQuery();
			while (rset1.next())
				nbr++;

			classes.addAll(new Classes_View_Model(rset.getInt("id_classe"), rset.getString("nom_classe"), nbr));

		}

		return classes;
	}

	@FXML
	void btnMatiere_click(ActionEvent event) throws SQLException {
		GererEffect(btnMatiere);
		Pane_Matiere.toFront();
		panel_close_minimize.toFront();
		cb_classe_gestion_matiere.setPromptText("classe");
		col_id_matiere.setCellValueFactory(new PropertyValueFactory<>("matiere_id"));
		col_nom_matiere.setCellValueFactory(new PropertyValueFactory<>("nom_matiere"));
		col_classe_concerne.setCellValueFactory(new PropertyValueFactory<>("classe_concerne"));
		table_view_gestion_matiere.setItems(Matieres());
		table_view_gestion_matiere.setEditable(true);
		try {
			Connection connection = Data_Base_Conn.getConnection();
			Statement stmMatiere = connection.createStatement();
			ResultSet rss = stmMatiere.executeQuery("select * from classe");
			ObservableList<String> mat = FXCollections.observableArrayList();

			while (rss.next()) {
				String matieres = rss.getString("nom_classe");
				mat.add(matieres);
			}
			cb_classe_gestion_matiere.setItems(mat);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	@FXML
	void btnClasse_click(ActionEvent event) throws SQLException {
		Pane_Classes.toFront();
		GererEffect(btnClasses);

		col_id_classe.setCellValueFactory(new PropertyValueFactory<>("id_classe"));
		col_nom_classe.setCellValueFactory(new PropertyValueFactory<>("classe_nom"));
		col_nbr_etudiants.setCellValueFactory(new PropertyValueFactory<>("nbr_etud_classe"));
		table_view_gestion_classes.setItems(Classes());
		table_view_gestion_classes.setEditable(true);
	}

	@FXML
	void ajouter_classe(ActionEvent event) throws SQLException {
		String query = "INSERT INTO classe (nom_classe)VALUES(?)";
		PreparedStatement ps = Data_Base_Conn.getConnection().prepareStatement(query);
		ps.setString(1, txt_nom_classe.getText());
		ps.executeUpdate();
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("classe ajouté");

		alert.setContentText("la Classe " + txt_nom_classe.getText() + " a était bien ajouté");
		alert.showAndWait();
		txt_nom_classe.setText("");
		btnClasse_click(event);
	}

	@FXML
	private void profil_show() {
		GererEffect(btnProfil);
		panel_close_minimize.toFront();

		if (Data_Base_Conn.admin_connecte != 0) {
			Pane_pers.toFront();
			try {
				Connection con = Data_Base_Conn.getConnection();
				String sql = "SELECT  * FROM administrateur WHERE admin_id = " + Data_Base_Conn.admin_connecte;
				ResultSet rs = con.createStatement().executeQuery(sql);
				if (rs.next()) {
					admin_nom_complet.setText(Data_Base_Conn.nom_connecte);

					txt_email.setText(rs.getString("email"));
					txt_tele.setText(rs.getString("telephone"));
					txt_prenom.setText(rs.getString("prenom"));
					txt_nom.setText(rs.getString("nom"));
					txt_username.setText(rs.getString("username"));
					txt_mdp.setText(rs.getString("mdp"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		btnClose.toFront();
		btnMinimize.toFront();
	}

	@FXML
	private void supprimerProf() {
		GestionProfViewModel professeur = (GestionProfViewModel) TableViewProfs.getSelectionModel().getSelectedItem();
		if (professeur == null) {
			System.out.println("aucun etudiant a supprimer !");

		}
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Supression d'un enseignet !");
		alert.setContentText("Etes vous totalement sur de vouloir supprimer l'enseignant <" + professeur.getSOM() + "-"
				+ professeur.getNomComplet() + " " + professeur.getEMAIL() + "> ??\n");
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.setHeight(400);
		Optional<ButtonType> reponse = alert.showAndWait();
		if (reponse.get().equals(ButtonType.OK)) {
			try {
				Connection connection = Data_Base_Conn.getConnection();
				Statement sqlCommand = connection.createStatement();
				sqlCommand.execute(String.format("delete from proffesseur where SOM = %d;", professeur.getSOM()));
				TableViewProfs.getItems().remove(TableViewProfs.getSelectionModel().getSelectedItem());
				TableViewProfs.refresh();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	public void Mode_Click(ActionEvent e) {
		if (((ToggleButton) e.getSource()).isSelected()) {

			Btn_Rechercher.setVisible(true);
			txtSearch.setVisible(true);
			btn_ajouter.setText("Mettre à  jour");
			cb_matiere.setVisible(true);
			btnClose.toFront();
			btnMinimize.toFront();
		} else {
			Btn_Rechercher.setVisible(false);
			txtSearch.setVisible(false);
			btn_ajouter.setText("Nouveau Professeur");
			txt_nom_prof.clear();
			txt_dn.setValue(null);
			cb_matiere.setPromptText("Matieres");
			cb_classe.setPromptText("Groupes");
			txtSearch.clear();
			txt_som.clear();
			txt_prenom_prof.clear();
			txt_dn.setValue(LocalDate.now());
			txt_email_prof.clear();
			txt_tele_prof.clear();
			RB_Homme.setSelected(true);
			txt_username_prof.clear();
			txt_mdp_prof.clear();
			cb_matiere.setVisible(false);
		}
	}

	@FXML
	void selected_classe_for_matiere(ActionEvent event) throws SQLException {

		PreparedStatement ps = Data_Base_Conn.getConnection()
				.prepareStatement("SELECT * FROM classe WHERE nom_classe =?");
		ps.setString(1, cb_classe.getSelectionModel().getSelectedItem());
		ResultSet rset = ps.executeQuery();
		int class_id = 0;
		if (rset.next()) {

			class_id = rset.getInt("id_classe");
		}
		cb_matiere.setVisible(true);
		try {
			Connection connection = Data_Base_Conn.getConnection();
			PreparedStatement stmMatiere = connection
					.prepareStatement("select nom_matiere from matiere where classe_id= ?");
			stmMatiere.setInt(1, class_id);
			ResultSet rss = stmMatiere.executeQuery();
			ObservableList<String> mat = FXCollections.observableArrayList();
			while (rss.next()) {
				String matieres = rss.getString(1);
				mat.add(matieres);
			}
			cb_matiere.setItems(mat);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	@FXML
	public void groupe_click(MouseEvent e) {
		IdGrp.remove(e.getSource());
	}

	public List<Integer> IdGrp = new ArrayList<>();

	private Stage stage;

	public void BindComboGroupe() {
		try {
			Connection connection = Data_Base_Conn.getConnection();
			Statement stmMatiere = connection.createStatement();
			ResultSet rss = stmMatiere.executeQuery("select * from classe");
			ObservableList<String> mat = FXCollections.observableArrayList();

			while (rss.next()) {
				String matieres = rss.getString("nom_classe");
				mat.add(matieres);
			}
			cb_classe.setItems(mat);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	ObservableList<GestionProfViewModel> Professeurs() throws SQLException {
		Connection connection = Data_Base_Conn.getConnection();
		ObservableList<GestionProfViewModel> professeurs = FXCollections.observableArrayList();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from proffesseur");

		System.out.println("sd");
		while (resultSet.next()) {
			System.out.println("12");
			PreparedStatement st01 = connection.prepareStatement("select * from classe where id_classe=?");
			st01.setInt(1, resultSet.getInt("classe_id"));
			ResultSet rset01 = st01.executeQuery();

			String nom_classe = " ";
			String nom_matiere = " ";
			if (rset01.next()) {
				nom_classe = rset01.getString("nom_classe");
			}
			PreparedStatement st02 = connection
					.prepareStatement("select * from matiere where matiere_id=? and classe_id=?");
			st02.setInt(1, resultSet.getInt("matiere_id"));
			st02.setInt(2, resultSet.getInt("classe_id"));
			ResultSet rset02 = st02.executeQuery();
			if (rset02.next()) {
				nom_matiere = rset02.getString("nom_matiere");
			}
			professeurs.addAll(new GestionProfViewModel(resultSet.getInt("som"), resultSet.getString("email"),
					resultSet.getString("nom") + " " + resultSet.getString("prenom"), nom_matiere, nom_classe

			));

		}

		return professeurs;
	}

	@FXML
	public void backGestionProf_click() {
		addProfPanel.toFront();
		panel_close_minimize.toFront();
	}

	@FXML
	public void listProf_click() throws SQLException {
		panelListerProf.toFront();
		btnClose.toFront();
		btnMinimize.toFront();
		col_som.setCellValueFactory(new PropertyValueFactory<>("SOM"));
		col_email.setCellValueFactory(new PropertyValueFactory<>("EMAIL"));
		col_NomComplet.setCellValueFactory(new PropertyValueFactory<>("nomComplet"));
		col_matiere.setCellValueFactory(new PropertyValueFactory<>("Matiere"));
		col_groupe.setCellValueFactory(new PropertyValueFactory<>("Groupes"));
		col_groupe.setCellFactory(TextFieldTableCell.forTableColumn());
		TableViewProfs.setItems(Professeurs());
		TableViewProfs.setEditable(true);

	}

	@FXML
	public void gestionProf_click() {
		GererEffect(btnGestionProf);
		BindComboGroupe();
		cb_matiere.setVisible(false);
		addProfPanel.toFront();
		btnClose.toFront();
		btnMinimize.toFront();
	}

	@FXML
	public void ajouterProf_click() {
		Connection connection = Data_Base_Conn.getConnection();
		if (btn_ajouter.getText().equals("Nouveau Professeur")) {
			if (txt_som.getText().isEmpty() || txt_prenom_prof.getText().isEmpty() || txt_dn.getValue() == null
					|| txt_nom_prof.getText().isBlank() || cb_classe.getSelectionModel().isEmpty()
					|| cb_matiere.getSelectionModel().isEmpty() || txt_email_prof.getText().isBlank()
					|| txt_username_prof.getText().isBlank() || txt_mdp_prof.getText().isBlank()
					|| txt_tele_prof.getText().isBlank() || (!RB_Femme.isSelected() && !RB_Homme.isSelected())) {
				if (txt_som.getText().isBlank()) {
					txt_som.setStyle("-fx-border-color:#f46d6d");
				} else {
					txt_som.setStyle("-fx-border-color:#5eef41");
				}

				if (txt_prenom_prof.getText().isBlank()) {
					txt_prenom_prof.setStyle("-fx-border-color:#f46d6d");
				} else {
					txt_prenom_prof.setStyle("-fx-border-color:#5eef41");
				}

				if (txt_dn.getValue() == null) {
					txt_dn.setStyle("-fx-border-color:#f46d6d");
				} else {
					txt_dn.setStyle("-fx-border-color:#5eef41");
				}

				if (txt_nom_prof.getText().isBlank()) {
					txt_nom_prof.setStyle("-fx-border-color:#f46d6d");
				} else {
					txt_nom_prof.setStyle("-fx-border-color:#5eef41");
				}

				if (txt_email_prof.getText().isBlank()) {
					txt_email_prof.setStyle("-fx-border-color:#f46d6d");
				} else {
					txt_email_prof.setStyle("-fx-border-color:#5eef41");
				}

				if (txt_username_prof.getText().isBlank()) {
					txt_username_prof.setStyle("-fx-border-color:#f46d6d");
				} else {
					txt_username_prof.setStyle("-fx-border-color:#5eef41");
				}

				if (txt_mdp_prof.getText().isBlank()) {
					txt_mdp_prof.setStyle("-fx-border-color:#f46d6d");
				} else {
					txt_mdp_prof.setStyle("-fx-border-color:#5eef41");
				}

				if (cb_matiere.getSelectionModel().isEmpty()) {
					cb_matiere.setStyle("-fx-border-color:#f46d6d");
				} else {
					cb_matiere.setStyle("-fx-border-color:#5eef41");
				}
				if (cb_classe.getSelectionModel().isEmpty()) {
					cb_classe.setStyle("-fx-border-color:#f46d6d");
				} else {
					cb_classe.setStyle("-fx-border-color:#5eef41");
				}

				if (txt_tele_prof.getText().isBlank()) {
					txt_tele_prof.setStyle("-fx-border-color:#f46d6d");
				} else {
					txt_tele_prof.setStyle("-fx-border-color:#5eef41");
				}

				lbl_message.setVisible(true);

			} else {
				try {

					lbl_message.setVisible(false);
					PreparedStatement preparedStatement = connection
							.prepareStatement("INSERT INTO proffesseur (SOM, email, nom, prenom, date_naissance, "
									+ " telephone, sexe, username, mdp,classe_id,matiere_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)");

					preparedStatement.setInt(1, Integer.parseInt(txt_som.getText()));
					preparedStatement.setString(3, txt_nom_prof.getText());
					preparedStatement.setString(4, txt_prenom_prof.getText());
					preparedStatement.setDate(5, java.sql.Date.valueOf(txt_dn.getValue()));
					preparedStatement.setString(2, txt_email_prof.getText());
					preparedStatement.setString(6, txt_tele_prof.getText());
					if (RB_Femme.isSelected()) {
						preparedStatement.setString(7, RB_Femme.getText());
					} else if (RB_Homme.isSelected())
						preparedStatement.setString(7, RB_Homme.getText());

					preparedStatement.setString(8, txt_username_prof.getText());
					preparedStatement.setString(9, txt_mdp_prof.getText());

					Connection connection1 = Data_Base_Conn.getConnection();
					PreparedStatement sqlCommand = connection1
							.prepareStatement("select * from matiere where nom_matiere= ?");
					sqlCommand.setString(1, cb_matiere.getSelectionModel().getSelectedItem().toString());
					ResultSet dataReader = sqlCommand.executeQuery();
					int id_mat = 0;
					int test = 0;
					if (dataReader.next()) {
						id_mat = dataReader.getInt("matiere_id");

						PreparedStatement sqll = connection1.prepareStatement("select matiere_id from proffesseur");
						ResultSet rsett = sqll.executeQuery();

						while (rsett.next()) {
							if (id_mat == rsett.getInt(1))
								test = 1;
						}

					}
					if (test == 1)
						lbl_message_matiere.setVisible(true);
					else {

						preparedStatement.setInt(11, id_mat);

						PreparedStatement sqlCommand1 = connection1
								.prepareStatement("select * from classe where nom_classe= ?");
						sqlCommand1.setString(1, cb_classe.getSelectionModel().getSelectedItem().toString());
						ResultSet dataReader1 = sqlCommand1.executeQuery();
						int id_classe = 0;
						if (dataReader1.next()) {
							id_classe = dataReader1.getInt("id_classe");

						}
						preparedStatement.setInt(10, id_classe);
						preparedStatement.executeUpdate();
						txt_som.setStyle("-fx-border-color: #24ACF2");
						txt_prenom_prof.setStyle("-fx-border-color: #24ACF2");
						txt_dn.setStyle("-fx-border-color: #24ACF2");
						txt_nom_prof.setStyle("-fx-border-color: #24ACF2");
						cb_classe.setStyle("-fx-border-color: #24ACF2");
						cb_matiere.setStyle("-fx-border-color: #24ACF2");
						txt_email_prof.setStyle("-fx-border-color: #24ACF2");
						txt_username_prof.setStyle("-fx-border-color: #24ACF2");
						txt_mdp_prof.setStyle("-fx-border-color: #24ACF2");
						txt_tele_prof.setStyle("-fx-border-color: #24ACF2");
						lbl_message_matiere.setVisible(false);
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("Saisie avec succées");
						alert.setHeaderText("Un Professeur ajouté");
						alert.setContentText("Professeur a bien était ajouté!! ");
						alert.showAndWait();
						txt_som.clear();
						txt_prenom_prof.clear();
						txt_dn.setValue(null);
						txt_nom_prof.clear();
						cb_matiere.setPromptText("-Matiéres-");
						cb_classe.setPromptText("-Classes-");
						txt_email_prof.clear();
						txt_tele_prof.clear();
						RB_Homme.setSelected(false);
						RB_Femme.setSelected(false);
						txt_username_prof.clear();
						txt_mdp_prof.clear();

					}
				} catch (SQLException e) {
					e.getMessage();
				}

			}

		} else if (btn_ajouter.getText().equals("Modifier")) {
			try {

				PreparedStatement preparedStatement = connection.prepareStatement(
						"UPDATE PROFFESSEUR SET email = ? , nom = ? , prenom = ?, date_naissance = ?, telephone = ? ,"
								+ "  sexe = ?,  username= ?, mdp= ? WHERE SOM = ?");
				preparedStatement.setString(1, txt_email_prof.getText());
				preparedStatement.setString(2, txt_nom_prof.getText());
				preparedStatement.setString(3, txt_prenom_prof.getText());
				preparedStatement.setDate(4, java.sql.Date.valueOf(txt_dn.getValue()));

				preparedStatement.setString(5, txt_tele_prof.getText());
				if (RB_Femme.isSelected()) {
					preparedStatement.setString(6, RB_Femme.getText());
				} else if (RB_Homme.isSelected())
					preparedStatement.setString(6, RB_Homme.getText());

				preparedStatement.setString(7, txt_username_prof.getText());
				preparedStatement.setString(8, txt_mdp_prof.getText());
				preparedStatement.setString(9, txt_som.getText());

				preparedStatement.executeUpdate();

			} catch (SQLException e) {
				e.getStackTrace();
			}

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Mis à jour de données.");
			alert.setContentText("Professeur a bien était Modifier !!");
			alert.showAndWait();
			txt_nom_prof.clear();
			cb_matiere.setPromptText("Matieres");
			cb_classe.setPromptText("Classes");
			txtSearch.clear();
			txt_som.clear();
			txt_prenom_prof.clear();
			txt_dn.setValue(null);
			txt_email_prof.clear();
			txt_tele_prof.clear();
			RB_Homme.setSelected(false);
			RB_Femme.setSelected(false);
			txt_username_prof.clear();
			txt_mdp_prof.clear();
		}
	}

	@FXML
	void ajouter_matiere(ActionEvent event) throws SQLException {
		Connection conn = Data_Base_Conn.getConnection();

		PreparedStatement stmt = conn.prepareStatement("SELECT id_classe from classe where nom_classe=?");
		stmt.setString(1, cb_classe_gestion_matiere.getSelectionModel().getSelectedItem().toString());
		ResultSet rset = stmt.executeQuery();
		int id_cla = 0;
		if (rset.next())
			id_cla = rset.getInt(1);
		String query = "INSERT INTO matiere (classe_id,nom_matiere)VALUES(?,?)";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setInt(1, id_cla);
		pst.setNString(2, txt_nom_matiere.getText());
		pst.executeUpdate();

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Matière ajoutée");
		alert.setContentText("La matière " + txt_nom_matiere.getText() + " a était ajoutée au classe "
				+ cb_classe_gestion_matiere.getSelectionModel().getSelectedItem().toString());
		alert.showAndWait();
		txt_nom_matiere.clear();
		cb_classe_gestion_matiere.setPromptText("classe");
		btnMatiere_click(event);
	}

	@FXML
	public void chercherProf_click() {
		btn_ajouter.setText("Modifier");
		Connection connection = Data_Base_Conn.getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"select * from proffesseur  where SOM ='" + Integer.parseInt(txtSearch.getText()) + "'");
			if (resultSet.next()) {
				txt_som.setText(resultSet.getString("SOM"));
				txt_nom_prof.setText(resultSet.getString("nom"));
				txt_prenom_prof.setText(resultSet.getString("prenom"));
				txt_dn.setValue(LocalDate.parse(resultSet.getString("date_naissance")));
				txt_email_prof.setText(resultSet.getString("email"));
				txt_tele_prof.setText(resultSet.getString("telephone"));
				if (resultSet.getString("sexe").equals("Homme")) {
					RB_Homme.setSelected(true);
					RB_Femme.setSelected(false);
				} else {
					RB_Femme.setSelected(true);
					RB_Homme.setSelected(false);
				}
				txt_username_prof.setText(resultSet.getString("username"));
				txt_mdp_prof.setText(resultSet.getString("mdp"));
				Statement groupeProfstm = connection.createStatement();

				System.out.println("Brahim hh");
				ResultSet classeResultSet = groupeProfstm.executeQuery(
						"SELECT * from classe cl JOIN proffesseur prof on cl.id_classe = prof.classe_id WHERE prof.SOM = '"
								+ txtSearch.getText() + "'");
				classeResultSet.next();
				cb_classe.setPromptText(classeResultSet.getString("nom_classe"));
				;
				Statement matiereStm = connection.createStatement();
				ResultSet matiResultSet = matiereStm.executeQuery(
						"SELECT * FROM MATIERE mat JOIN proffesseur prof on  prof.matiere_id = mat.matiere_id WHERE prof.SOM = '"
								+ txtSearch.getText() + "'");
				matiResultSet.next();
				cb_matiere.setPromptText(matiResultSet.getString("nom_matiere"));
				System.out.println(matiResultSet.getString("nom_matiere"));
				System.out.println("sdg");

			} else {
				txt_nom_prof.clear();
				txt_prenom_prof.clear();
				txt_nom_prof.clear();

				txt_dn.setValue(null);

				cb_classe.setPromptText("-Classes-");

				txt_email_prof.clear();
				txt_tele_prof.clear();
				RB_Homme.setSelected(true);

				txt_username_prof.clear();
				txt_mdp_prof.clear();
				cb_matiere.setPromptText("Matieres");
				cb_classe.setPromptText("Classe");
			}

		} catch (SQLException s) {
			s.getStackTrace();
		}
	}

	@FXML
	public void logOut_Click() throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../views/Login.fxml"));
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
	public void panelGestionEtudiant_click(ActionEvent e) {
		GererEffect(btnGestion_Etudiant);
		panelEtudiant.toFront();
		panel_close_minimize.toFront();

		String sql = "select * from classe";
		Connection con = Data_Base_Conn.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rset = ps.executeQuery();
			ObservableList<String> classess = FXCollections.observableArrayList();
			while (rset.next()) {
				String cla = rset.getString("nom_classe");
				classess.add(cla);
			}
			CB_classe_gestionEtudiant.setItems(classess);
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
	}

	@FXML
	private void gestionGroupe_click(ActionEvent e) {
		try {
			FXMLLoader loader = new FXMLLoader(new File("src/application/views/GestionGroupe.fxml").toURI().toURL());
			Parent root = (Parent) loader.load();

			Scene scene = new Scene(root);
			scene.setFill(Color.valueOf("transparent"));

			Stage stage = new Stage(StageStyle.TRANSPARENT);
			stage.setTitle("Ajout dde groupe");
			stage.setScene(scene);
			stage.show();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void GererEffect(Button buttonException) {
		btnAccueil.setEffect(null);
		btnGestionProf.setEffect(null);
		btnGestion_Etudiant.setEffect(null);

		btnMatiere.setEffect(null);
		btnClasses.setEffect(null);
		btnProfil.setEffect(null);

		buttonException.setEffect(new DropShadow());
	}

	@FXML
	private void btnAccueil_click() {
		Accueil_Load();
		GererEffect(btnAccueil);
		panelAccueil.toFront();
		panel_close_minimize.toFront();
	}

	@FXML
	private void supprimer_click() {
		GestionEtudiantsViewModel etudiant = (GestionEtudiantsViewModel) tableView_GestionEtudiant.getSelectionModel()
				.getSelectedItem();
		if (etudiant == null) {
			System.out.println("aucun etudiant a supprimer !");
			return;
		}
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Supression d'un étudiant !");
		alert.setContentText("Etes vous totalement sur de vouloir supprimer l'étudiant  " + etudiant.getApogee() + "-"
				+ etudiant.getNOM() + " " + etudiant.getPreNOM() + "??\n Toutes ses notes seront ainsi supprimer !!");
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.setHeight(400);
		Optional<ButtonType> reponse = alert.showAndWait();
		if (reponse.get().equals(ButtonType.OK)) {
			try {
				Connection connection = Data_Base_Conn.getConnection();
				Statement sqlCommand = connection.createStatement();
				sqlCommand.execute(String.format("delete from etudiant where apogee = %d;", etudiant.getApogee()));
				tableView_GestionEtudiant.getItems()
						.remove(tableView_GestionEtudiant.getSelectionModel().getSelectedItem());
				tableView_GestionEtudiant.refresh();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void Form_Load() {
		utilisateur.setText(Data_Base_Conn.nom_connecte);
		Connection connection = Data_Base_Conn.getConnection();
		String NomComplet[] = Data_Base_Conn.nom_connecte.split(" ");
		String Nom = NomComplet[0];
		String Prenom = NomComplet[1];
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"SELECT * from administrateur WHERE nom = '" + Nom + "' and prenom = '" + Prenom + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Form_Load();

		btnClose.toFront();
		btnMinimize.toFront();

		btnGestionProf.setVisible(true);

		btnAccueil_click();

	}

	private void Accueil_Load() {
		btnClose.toFront();
		btnMinimize.toFront();
		vbox_messagerie.getChildren().clear();
		vbox_messagerie.setSpacing(30);
		Connection connection = Data_Base_Conn.getConnection();
		if (Data_Base_Conn.admin_connecte != 0) {
			try {
				String sql = "select annance.* , classe.nom_classe from annoce_controle annance "
						+ "inner join classe classe on annance.classe_id = classe.id_classe ORDER BY id_annonce DESC ";

				Statement s = connection.createStatement();

				ResultSet dataReader = s.executeQuery(sql);

				while (dataReader.next()) {
					labelinfo_Accueil.setVisible(false);
					img1_Accueil.setVisible(false);
					img2_Accueil.setVisible(false);

					vbox_messagerie.setVisible(true);
					scroll.setVisible(true);
					TextArea actualite = new TextArea();
					actualite.setText(String.format(
							"Pour les Etudiants du classe : %s ,Vous avez un controle le %s ,\n"
									+ "Plus d'information : %s",
							dataReader.getString("nom_classe"), dataReader.getString("date_controle"),

							dataReader.getString("description")));
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
	private void refresh_click(ActionEvent e) {
		this.Accueil_Load();
	}

	ObservableList<GestionEtudiantsViewModel> etudiants(int id_grp) throws SQLException {
		Connection connection = Data_Base_Conn.getConnection();
		ObservableList<GestionEtudiantsViewModel> etudiant = FXCollections.observableArrayList();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select et.apogee, et.prenom, et.nom,  et.email, et.telephone \n"
				+ "from etudiant et inner join classe grp on et.id_classe = grp.id_classe\n" + "where grp.id_classe = "
				+ (id_grp));
		while (resultSet.next()) {
			etudiant.addAll(new GestionEtudiantsViewModel(resultSet.getInt("apogee"), resultSet.getString("prenom"),
					resultSet.getString("nom"), resultSet.getString("email"), resultSet.getString("telephone")));
		}
		return etudiant;
	}

	public void CB_grp_gestionEtudiant_selected(ActionEvent actionEvent) throws SQLException {
		tableView_GestionEtudiant.getItems().clear();
		PreparedStatement ps = Data_Base_Conn.getConnection().prepareStatement("SELECT * FROM classe WHERE nom_classe = ?");
		ps.setString(1, CB_classe_gestionEtudiant.getSelectionModel().getSelectedItem().toString());
		ResultSet rset = ps.executeQuery();
		int id_clss = 0;
		if (rset.next())
			id_clss = rset.getInt("id_classe");
		col_apogee.setCellValueFactory(new PropertyValueFactory<>("apogee"));
		col_prenom.setCellValueFactory(new PropertyValueFactory<>("preNOM"));
		col_nom.setCellValueFactory(new PropertyValueFactory<>("NOM"));
		col_mail.setCellValueFactory(new PropertyValueFactory<>("email"));
		col_tele.setCellValueFactory(new PropertyValueFactory<>("telephone"));
		col_groupe.setCellFactory(TextFieldTableCell.forTableColumn());
		tableView_GestionEtudiant.setItems(etudiants(id_clss));
		tableView_GestionEtudiant.setEditable(true);
	}

	@FXML
	void Modifier_admin(ActionEvent event) throws Exception {
		if (Data_Base_Conn.admin_connecte != 0) {
			int id = Data_Base_Conn.admin_connecte;
			Connection cnx = Data_Base_Conn.getConnection();

			PreparedStatement preparedStatement = cnx.prepareStatement("UPDATE administrateur SET nom=?,email= ? ,"
					+ " telephone = ?,prenom=?, username= ?, mdp= ? WHERE admin_id = ?");
			preparedStatement.setString(1, txt_nom.getText());

			preparedStatement.setString(2, txt_email.getText());
			preparedStatement.setString(3, txt_tele.getText());
			preparedStatement.setString(4, txt_prenom.getText());
			preparedStatement.setString(5, txt_username.getText());
			preparedStatement.setString(6, txt_mdp.getText());
			preparedStatement.setInt(7, id);

			preparedStatement.executeUpdate();

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Modifié avec succées");
			alert.setHeaderText("Vos données sont bien à jour .");
			alert.setContentText("Informations actualisées.");
			alert.showAndWait();

			String sql = "SELECT  * FROM administrateur  where admin_id= '" + Data_Base_Conn.admin_connecte + "'";
			ResultSet rs = cnx.createStatement().executeQuery(sql);
			if (rs.next()) {

				admin_nom_complet.setText(rs.getString("nom") + " " + rs.getString("prenom"));
				utilisateur.setText(rs.getString("nom") + " " + rs.getString("prenom"));
				txt_email.setText(rs.getString("email"));
				txt_tele.setText(rs.getString("telephone"));
				txt_prenom.setText(rs.getString("prenom"));
				txt_nom.setText(rs.getString("nom"));
				txt_username.setText(rs.getString("username"));
				txt_mdp.setText(rs.getString("mdp"));
			}

		}

	}

}
