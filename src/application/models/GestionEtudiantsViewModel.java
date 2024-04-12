package application.models;



public class GestionEtudiantsViewModel {
    public int apogee;
    public String NOM;
    public String preNOM;
    public String email;
    public String telephone;
  

     

	public GestionEtudiantsViewModel(int apogee2, String NOM2, String preNOM2, String email2, String telephone2) {
		 this.apogee = apogee2;
	        this.NOM = NOM2;
	        this.preNOM = preNOM2;
	        this.email = email2;
	        this.telephone = telephone2;
	       
	}

	public int getApogee() {
		return apogee;
	}

	public void setApogee(int apogee) {
		this.apogee = apogee;
	}

	public String getNOM() {
		return NOM;
	}

	public void setNOM(String NOM) {
		this.NOM = NOM;
	}

	public String getPreNOM() {
		return preNOM;
	}

	public void setPreNOM(String preNOM) {
		this.preNOM = preNOM;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	


  

    

}
