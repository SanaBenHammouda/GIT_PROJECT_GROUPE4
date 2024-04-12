package application.models;

public class Classes_View_Model {

	
	private int id_classe;
	private String classe_nom;
	private int nbr_etud_classe;
	
	
	public Classes_View_Model(int id_classe, String classe_nom, int nbr_etud_classe) {
		
		this.id_classe = id_classe;
		this.classe_nom = classe_nom;
		this.nbr_etud_classe = nbr_etud_classe;
	}


	
	public String Id_classetoString() {
		return "" + id_classe +"";
	}
	public String NbrEtudClassetoString() {
		return "" + nbr_etud_classe +"";
	}


	public int getId_classe() {
		return id_classe;
	}


	public void setId_classe(int id_classe) {
		this.id_classe = id_classe;
	}


	public String getClasse_nom() {
		return classe_nom;
	}


	public void setClasse_nom(String classe_nom) {
		this.classe_nom = classe_nom;
	}


	public int getNbr_etud_classe() {
		return nbr_etud_classe;
	}


	public void setNbr_etud_classe(int nbr_etud_classe) {
		this.nbr_etud_classe = nbr_etud_classe;
	}
	
	
	
}
