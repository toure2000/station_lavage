package station.lavage.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import station.lavage.model.util.Prix;

@Document
public class Type_lavage {
	@Id
	private String id;
	@NotNull
	@Size(min = 5, max = 30)
	private String type;
	
	private Prix prix;
	@Size(min = 10, max = 200)
	private String picture;
	@NotNull
	@Size(min = 0, max = 100)
	private String description;
	
	private List<Type_voiture> list_type_voiture;
	private String duree;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Prix getPrix() {
		return prix;
	}
	public void setPrix(Prix prix) {
		this.prix = prix;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Type_voiture> getList_type_voiture() {
		return list_type_voiture;
	}
	public void setList_type_voiture(List<Type_voiture> list_type_voiture) {
		this.list_type_voiture = list_type_voiture;
	}
	public String getDuree() {
		return duree;
	}
	public void setDuree(String duree) {
		this.duree = duree;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
	
	
	
	
}
