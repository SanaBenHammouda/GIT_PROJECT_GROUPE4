package application.models;

public class SupportCoursViewModel {
	private String nom_cours;
	private int id_cours;
	public SupportCoursViewModel(int id_cours, String nom_cours) {
		
		this.id_cours = id_cours;
		this.nom_cours = nom_cours;
	}
	public int getId_cours() {
		return id_cours;
	}
	public void setId_cours(int id_cours) {
		this.id_cours = id_cours;
	}
	public String getNom_cours() {
		return nom_cours;
	}
	public void setNom_cours(String nom_cours) {
		this.nom_cours = nom_cours;
	}
	
	
	
	
}
