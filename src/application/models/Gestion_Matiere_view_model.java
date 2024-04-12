package application.models;

public class Gestion_Matiere_view_model {

	private int matiere_id;
	private String nom_matiere;
	private String classe_concerne;
	public Gestion_Matiere_view_model(int matiere_id, String nom_matiere, String classe_concerne) {
		super();
		this.matiere_id = matiere_id;
		this.nom_matiere = nom_matiere;
		this.classe_concerne = classe_concerne;
	}
	public Gestion_Matiere_view_model() {
		
	}
	public int getMatiere_id() {
		return matiere_id;
	}
	public void setMatiere_id(int matiere_id) {
		this.matiere_id = matiere_id;
	}
	public String getNom_matiere() {
		return nom_matiere;
	}
	public void setNom_matiere(String nom_matiere) {
		this.nom_matiere = nom_matiere;
	}
	public String getClasse_concerne() {
		return classe_concerne;
	}
	public void setClasse_concerne(String classe_concerne) {
		this.classe_concerne = classe_concerne;
	}
	
	
}
