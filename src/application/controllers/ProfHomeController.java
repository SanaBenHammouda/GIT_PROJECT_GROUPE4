package application.controllers;

import application.data_base.Data_Base_Conn;
import application.models.GestionNotesViewModel;
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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.*;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

public class ProfHomeController implements Initializable {

	   @FXML
	    private ComboBox<String> CB_classe;
	    
	   
	   @FXML
	    private Pane panel_close_mini;
	   private Stage stage;
	   
	    @FXML
	    private Pane Pane_ensg;

	    @FXML
	    private VBox VboxMenu;

	    @FXML
	    private Button btnAccueil;

	    @FXML
	    private Button btnAnnonce;

	    @FXML
	    private Button btnClose;

	    @FXML
	    private Button btnListes;

	    @FXML
	    private Button btnLogOut;

	    @FXML
	    private Button btnMinimize;

	    @FXML
	    private Button btnProfil_prof;

	    @FXML
	    private Button btn_modifier;

	    @FXML
	    private ComboBox<String> cb_annonce_classe;

	    @FXML
	    private TableColumn<GestionNotesViewModel, String> col_apogee;

	    @FXML
	    private TableColumn<GestionNotesViewModel, String> col_cntrl1;

	    @FXML
	    private TableColumn<GestionNotesViewModel, String> col_cntrl2;

	    @FXML
	    private TableColumn<GestionNotesViewModel, String> col_cntrl3;

	    @FXML
	    private TableColumn<GestionNotesViewModel, String> col_moyenne;

	    @FXML
	    private TableColumn<GestionNotesViewModel, String> col_nomComplet;

	    @FXML
	    private DatePicker date_control;

	    @FXML
	    private ImageView img1_Accueil;

	    @FXML
	    private ImageView img2_Accueil;

	    @FXML
	    private ImageView imgUser;

	    @FXML
	    private ImageView imgViewMinimize;

	    @FXML
	    private Text lbl_sexe;

	    @FXML
	    private Label labelinfo_Accueil;

	    @FXML
	    private Label lbl_nom_complet;

	    @FXML
	    private Pane panelAccueil;

	    @FXML
	    private Pane panelAlert;

	    @FXML
	    private Pane panelNotesProf;

	    @FXML
	    private ScrollPane scroll;

	    @FXML
	    private Label som;

	    @FXML
	    private TableView<GestionNotesViewModel> tableView_GestionNotes;

	    @FXML
	    private DatePicker txt_date_naiss_prof;

	    @FXML
	    private TextArea txt_description;

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
	    private TextField txt_userename;

	    @FXML
	    private Label utilisateur;

	    @FXML
	    private VBox vbox_messagerie;  
	    

	    @FXML
	    void refh(ActionEvent event) throws SQLException {
	    	ObservableList<GestionNotesViewModel> notes_edited=tableView_GestionNotes.getItems();
	    	
            Connection connection = Data_Base_Conn.getConnection();
            for(int i=0;i<notes_edited.size();i++) {
	            String sql="update note n set  n.Cntrol1=? ,n.Cntrol2=?,n.examfinale=? "
						+ " WHERE n.etudiant_id= ? "
						+ "AND n.matiere_id = (select matiere_id from proffesseur where SOM=? )  ";
	            PreparedStatement ps = connection.prepareStatement(sql);
	            ps.setDouble(1,   Double.parseDouble(notes_edited.get(i).getControl_1()) );
	            ps.setDouble(2,   Double.parseDouble(notes_edited.get(i).getControl_2()) );
	            ps.setDouble(3,   Double.parseDouble(notes_edited.get(i).getControl_3()) );
	            ps.setInt(4,   Integer.parseInt(notes_edited.get(i).getApogee()));
	            System.out.println(Integer.parseInt(notes_edited.get(i).getApogee()));
	            ps.setInt(5, Data_Base_Conn.prof_connecte);
	            ps.executeUpdate();
	            System.out.println( ps.executeUpdate());
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modifiée avec succées");
            alert.setHeaderText("Les notes  sont modifiées avec succées");
            alert.setContentText("Informations actualisées.");
            alert.showAndWait();
	    }

    @FXML
    private void profil_show() {
        GererEffect(btnProfil_prof);


        //Si l'enseignnat qui est connectÃ©
        if (Data_Base_Conn.prof_connecte != 0) {
            try {
                Pane_ensg.toFront();
                Connection con = Data_Base_Conn.getConnection();
                String sql = "SELECT  * FROM proffesseur where SOM = '" + Data_Base_Conn.prof_connecte + "'";
                ResultSet rs = con.createStatement().executeQuery(sql);
                if (rs.next()) {
                    txt_nom.setText(rs.getString("nom"));
                    txt_prenom.setText(rs.getString("prenom"));
                    som.setText(rs.getString("SOM"));
                    lbl_nom_complet.setText(rs.getString("nom") + " " + rs.getString("prenom"));
                    txt_date_naiss_prof.setValue(LocalDate.parse(rs.getString("date_naissance")));                  
                    txt_email.setText(rs.getString("email"));
                    txt_tele.setText(rs.getString("telephone"));
                    lbl_sexe.setText(rs.getString("sexe"));
                    txt_userename.setText(rs.getString("username"));
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
    private ImageView iconBtnProf;


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
    public void GestionNote_Click(ActionEvent event) {
        GererEffect(btnListes);
        panelNotesProf.toFront();
        btnClose.toFront();
        btnMinimize.toFront();
    }
    private void GererEffect(Button buttonException) {
        btnAccueil.setEffect(null);
        btnProfil_prof.setEffect(null);
        btnListes.setEffect(null);
        btnAnnonce.setEffect(null);
        

        buttonException.setEffect(new DropShadow());
    }

    @FXML
    private void btnAccueil_click() {
        Accueil_Load();
        GererEffect(btnAccueil);
        panelAccueil.toFront();
        panel_close_mini.toFront();
    }   
    
    private void PanelGestionNotes_Load() {
        try {
            Connection connection = Data_Base_Conn.getConnection();
            Statement sqlCommand = connection.createStatement();
            ResultSet dataReader = sqlCommand.executeQuery
                    (
                            String.format
                                    (
                                            "select * from classe WHERE id_classe =(SELECT classe_id from proffesseur where SOM = "+Data_Base_Conn.prof_connecte+")"
                                    )
                    );
            ObservableList<String> classess = FXCollections.observableArrayList();
            while (dataReader.next()) {
                String cla = dataReader.getString("nom_classe");
                classess.add("classe " + cla);
            }
            CB_classe.setItems(classess);

            tableView_GestionNotes.setEditable(true);

            col_cntrl1.setCellFactory(TextFieldTableCell.forTableColumn());
            col_cntrl1.setOnEditCommit(e ->
            {
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setControl_1(e.getNewValue());
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setMoyenne(
                        this.Calculer_moyenne
                                (
                                        Double.parseDouble(e.getNewValue().toString()),
                                        Double.parseDouble(e.getTableView().getItems().get(e.getTablePosition().getRow()).getControl_2().toString()),
                                        Double.parseDouble(e.getTableView().getItems().get(e.getTablePosition().getRow()).getControl_3().toString())
                                ));
                tableView_GestionNotes.refresh();
            });

            col_cntrl2.setCellFactory(TextFieldTableCell.forTableColumn());
            col_cntrl2.setOnEditCommit(e ->
            {
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setControl_2(e.getNewValue());
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setMoyenne(
                        this.Calculer_moyenne
                                (
                                        Double.parseDouble(e.getNewValue().toString()),
                                        Double.parseDouble(e.getTableView().getItems().get(e.getTablePosition().getRow()).getControl_1().toString()),
                                        Double.parseDouble(e.getTableView().getItems().get(e.getTablePosition().getRow()).getControl_3().toString())
                                ));
                tableView_GestionNotes.refresh();
            });

            col_cntrl3.setCellFactory(TextFieldTableCell.forTableColumn());
            col_cntrl3.setOnEditCommit(e ->
            {
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setControl_3(e.getNewValue());
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setMoyenne(
                        this.Calculer_moyenne
                                (
                                        Double.parseDouble(e.getNewValue().toString()),
                                        Double.parseDouble(e.getTableView().getItems().get(e.getTablePosition().getRow()).getControl_1().toString()),
                                        Double.parseDouble(e.getTableView().getItems().get(e.getTablePosition().getRow()).getControl_2().toString())
                                ));
                tableView_GestionNotes.refresh();
            });
            tableView_GestionNotes.refresh();

        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
    }

    private String Calculer_moyenne(Double note1, Double note2, Double note3) {
        return (new DecimalFormat("###.##")).format((note1 + note2 + note3) / 3);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
        alertPanel_Load();
      
        PanelGestionNotes_Load();

        
         if (Data_Base_Conn.prof_connecte != 0) {
            
             Connection con = Data_Base_Conn.getConnection();
             
             try {
				ResultSet rs = con.createStatement().executeQuery(String.format("select nom,prenom from proffesseur  where SOM = '" + Data_Base_Conn.prof_connecte + "'"));
				if(rs.next()) {
					utilisateur.setText(rs.getString("nom")+" "+rs.getString("prenom"));
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
            imgUser.setImage(new Image(getClass().getResourceAsStream("..\\..\\resources\\images\\prof.png")));
        }
        btnAccueil_click();

    }
    }

    @FXML
    private void annonce_soumis(ActionEvent e) {

    	
    	
        Connection connection = Data_Base_Conn.getConnection();
        try {
        	 Connection connection1 = Data_Base_Conn.getConnection();
         	PreparedStatement sqlCommand = connection1.prepareStatement("select * from  classe where nom_classe= ?");
         	sqlCommand.setString(1,cb_annonce_classe.getSelectionModel().getSelectedItem().toString());
         	ResultSet dataReader = sqlCommand.executeQuery();
         	int classe_id=0;
         	if(dataReader.next()) {
         		classe_id=dataReader.getInt("id_classe");
         	}
        	String qry= "INSERT INTO annoce_controle (classe_id,date_controle,description)values(?,?,?)";
            PreparedStatement pst = connection.prepareStatement(qry);
            pst.setInt(1, classe_id);   
            pst.setDate(2,java.sql.Date.valueOf(date_control.getValue()));
            pst.setString(3, "Par Mr/Mme " + Data_Base_Conn.nom_connecte + "\n" + txt_description.getText().replace("'", "''"));
            pst.executeUpdate();
            cb_annonce_classe.getSelectionModel().clearSelection();
            date_control.setValue(null);          
            txt_description.setText("");

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
    }

    private void Accueil_Load() {
        if ( Data_Base_Conn.prof_connecte != 0) {
          
        }
        vbox_messagerie.getChildren().clear();
        vbox_messagerie.setSpacing(30);
        Connection connection = Data_Base_Conn.getConnection();
       
            try {
            	System.out.println(Data_Base_Conn.prof_connecte);
            	String sql = "select annance.* , classe.nom_classe"
            			+ " from annoce_controle annance "
            			+ "inner join classe classe on annance.classe_id = classe.id_classe WHERE classe_id = (SELECT classe_id FROM proffesseur WHERE SOM="+Data_Base_Conn.prof_connecte+") ORDER BY id_annonce DESC ";
            			
               Statement s = connection.createStatement();
               
                ResultSet dataReader = s.executeQuery(sql);

                while (dataReader.next()) {
                    labelinfo_Accueil.setVisible(false);
                    img1_Accueil.setVisible(false);
                    img2_Accueil.setVisible(false);
                    
                    vbox_messagerie.setVisible(true);
                    scroll.setVisible(true);
                    TextArea actualite = new TextArea();
                    actualite.setText(
                            String.format
                                    (
                                            "Pour les Etudiants du classe : %s ,Vous avez un controle le %s ,\n" +
                                                    "Plus d'information : %s",
                                            dataReader.getString("nom_classe"),
                                            dataReader.getString("date_controle"),
                                            
                                            dataReader.getString("description")
                                    ));
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

    @FXML
    private void refresh_click(ActionEvent e) {
        this.Accueil_Load();
    }




    @FXML
    public void Modifier_Enseignant(ActionEvent event) throws SQLException {
    		
    	 Connection cnx = Data_Base_Conn.getConnection();
        

            PreparedStatement preparedStatement = cnx.prepareStatement("UPDATE proffesseur SET  date_naissance = ?," +
                    "  email = ?, telephone = ? ,  nom = ?,  prenom= ?, mdp= ? , username=? WHERE SOM = ?");
            preparedStatement.setDate(1, java.sql.Date.valueOf(txt_date_naiss_prof.getValue()));
           
            preparedStatement.setString(2, txt_email.getText());
            preparedStatement.setString(3, txt_tele.getText());
            preparedStatement.setString(4, txt_nom.getText());
            preparedStatement.setString(5, txt_prenom.getText());
            preparedStatement.setString(7, txt_userename.getText());
            preparedStatement.setString(6, txt_mdp.getText());
           
            preparedStatement.setInt(8, Data_Base_Conn.prof_connecte);

            preparedStatement.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modifiée avec succées");
            alert.setHeaderText("Vos données sont bien à jour .");
            alert.setContentText("Informations actualisées.");
            alert.showAndWait();

            // actualiser
            String sql = "SELECT  * FROM proffesseur where SOM = '" + Data_Base_Conn.prof_connecte + "'";
            ResultSet rs = cnx.createStatement().executeQuery(sql);
            if (rs.next()) {
                som.setText(rs.getString("SOM"));
                txt_tele.setText(rs.getString("telephone"));
                lbl_nom_complet.setText(rs.getString("nom") + " " + rs.getString("prenom"));
                txt_date_naiss_prof.setValue(LocalDate.parse(rs.getString("date_naissance")));
                
                txt_userename.setText(rs.getString("username"));
                txt_mdp.setText(rs.getString("mdp"));
            }


        }

    





    @FXML
    public void btnAnnonce_click() {
        GererEffect(btnAnnonce);
        panelAlert.toFront();
        btnClose.toFront();
        btnMinimize.toFront();
    }

    private void alertPanel_Load() {
        try {
        	int id=0;
            Connection connection = Data_Base_Conn.getConnection();
            Statement stmtIDclasse = connection.createStatement();
            ResultSet readerIDCLASSE = stmtIDclasse.executeQuery("select classe_id from proffesseur where SOM="+Data_Base_Conn.prof_connecte);
            if(readerIDCLASSE.next()) {
            	id=readerIDCLASSE.getInt(1);
            }
            Statement stmGrp = connection.createStatement();
            ResultSet reader = stmGrp.executeQuery("select * from classe where id_classe="+id);
            ObservableList<String> groupes = FXCollections.observableArrayList();
            while (reader.next()) {
                String groupe = reader.getString("nom_classe");
                groupes.add(groupe);
            }

            cb_annonce_classe.setItems(groupes);
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
    }

    
    ObservableList<GestionNotesViewModel> etudiantNotes(int id_grp) throws SQLException {
       
        Connection connection = Data_Base_Conn.getConnection();
        ObservableList<GestionNotesViewModel> etudiant = FXCollections.observableArrayList();
        
        try {
            Statement sqlCommand1 = connection.createStatement();
            ResultSet dataReader1 = sqlCommand1.executeQuery
                    (
                            String.format
                                    (
                                    		"select etd.apogee, CONCAT(etd.prenom , ' ' , etd.nom) as nom_complet from etudiant etd inner join classe grp on etd.id_classe = grp.id_classe where grp.id_classe =%d;",
                                            id_grp
                                    )
                    );

            while (dataReader1.next()) {

                int apogee = dataReader1.getInt("apogee");
                String nom_complet = dataReader1.getString("nom_complet");
                String sql="select n.Cntrol1,n.Cntrol2,n.examfinale from note n WHERE n.etudiant_id= ? AND n.matiere_id = (select matiere_id from proffesseur prof where prof.SOM = ?) LIMIT 1";
                
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, apogee);
                ps.setInt(2, Data_Base_Conn.prof_connecte);
                ResultSet rset = ps.executeQuery();
                String[] notes={"0","0","0"};
            		if(rset.next()) {
            			notes[0] = rset.getString("Cntrol1");
            			notes[1] = rset.getString("Cntrol2");
            			notes[2] = rset.getString("examfinale");
            		}
                
                String moyenne = this.Calculer_moyenne(Double.valueOf(notes[0]), Double.valueOf(notes[1]), Double.valueOf(notes[2]));
               
                 etudiant.addAll(new GestionNotesViewModel(Integer.toString(apogee),
                		 nom_complet,
                		 notes[0],
                		 notes[1],
                		 notes[2],
                		 moyenne));
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
		
		return etudiant;
       
    }
    
    
    
    @FXML
    public void CB_grp_gestionNotes_selected(ActionEvent actionEvent) throws SQLException {
    	PreparedStatement ps =Data_Base_Conn.getConnection().prepareStatement("SELECT id_classe from classe where nom_classe=?");
    	String str[]=CB_classe.getSelectionModel().getSelectedItem().split(" ");
    
    	ps.setString(1, str[1]);
    	ResultSet rset=ps.executeQuery();
    	 int id_cls = 0;
    	if(rset.next())
    	{
    		id_cls=rset.getInt("id_classe");
    		System.out.println(id_cls);
    	}
    		tableView_GestionNotes.getItems().clear();
       System.out.println(id_cls+" "+str[1]);
        col_apogee.setCellValueFactory(new PropertyValueFactory<>("apogee"));
        col_nomComplet.setCellValueFactory(new PropertyValueFactory<>("nom_complet"));
        col_cntrl1.setCellValueFactory(new PropertyValueFactory<>("control_1"));
        col_cntrl2.setCellValueFactory(new PropertyValueFactory<>("control_2"));
        col_cntrl3.setCellValueFactory(new PropertyValueFactory<>("control_3"));
        col_moyenne.setCellValueFactory(new PropertyValueFactory<>("moyenne"));
        tableView_GestionNotes.setItems(etudiantNotes(id_cls));
        tableView_GestionNotes.setEditable(true);

       


    }
}
