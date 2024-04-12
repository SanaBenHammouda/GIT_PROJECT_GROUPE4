package application.models;

public class GestionProfViewModel {
   
	int SOM;
    String nomComplet;
    String EMAIL;
    String Matiere;
    String Groupes;
    
    
    public int getSOM() {
		return SOM;
	}

	public void setApogee(int SOM) {
		this.SOM = SOM;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String EMAIL) {
		this.EMAIL = EMAIL;
	}
   

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getMatiere() {
        return Matiere;
    }

    public void setMatiere(String matiere) {
        Matiere = matiere;
    }

    public String getGroupes() {
        return Groupes;
    }

    public void setGroupes(String groupes) {
        Groupes = groupes;
    }


    public GestionProfViewModel(int SOM, String EMAIL, String nomComplet, String matiere, String groupes) {
        this.SOM = SOM;
        this.EMAIL = EMAIL;
        this.nomComplet = nomComplet;
        Matiere = matiere;
        Groupes = groupes;
    }

}
