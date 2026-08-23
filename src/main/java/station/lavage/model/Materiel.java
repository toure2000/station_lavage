package station.lavage.model;

import org.springframework.data.annotation.Id;

import station.lavage.model.util.Prix;

public class Materiel {
	@Id
	private String id;
	private String nom;
	private String description;
	private  Prix prix;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Prix getPrix() {
		return prix;
	}
	public void setPrix(Prix prix) {
		this.prix = prix;
	}
	
	
	

}
