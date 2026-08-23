package station.lavage.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Document
public class Utilisateur {
	@Id
	@NotNull
	@Size(min = 8, max = 20)
	private String id;
	@NotNull
	@Size(min = 3, max = 30)
	private String nom;
	@NotNull
	@Size(min = 3, max = 30)
	private String prenom;
	@NotNull
	@Size(min = 10, max = 30)
	private String email;
	@Size(min = 9, max = 20)
	private String tel;
	@NotNull
	@Size(min = 8, max = 40)
	private String password;
	@Size(min = 4, max = 10)
	private String role;
	@Size(min = 10, max = 30)
	private String picture;
	
	
	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
		this.picture = picture;
	}


	public String getPassword() {
		return password;
	}
     
	
	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public void setPassword(String password) {
		this.password = password;
	}

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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}


	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", tel=" + tel
				+ ", password=" + password + ", role=" + role + ", picture=" + picture + "]";
	}

}
