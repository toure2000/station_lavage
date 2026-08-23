package station.lavage.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import station.lavage.model.util.Prix;

@Document
public class Services {
	@Id
	private String id;
	private String nom;
	private String description;
	private Prix prix;
	private String duree;
	private List<Type_voiture> list_type_voiture;
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
	public String getDuree() {
		return duree;
	}
	public void setDuree(String duree) {
		this.duree = duree;
	}
	public List<Type_voiture> getList_type_voiture() {
		return list_type_voiture;
	}
	public void setList_type_voiture(List<Type_voiture> list_type_voiture) {
		this.list_type_voiture = list_type_voiture;
	}
	
	
	
}
