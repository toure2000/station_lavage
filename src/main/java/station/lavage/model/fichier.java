package station.lavage.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class fichier {
	@Id
	private String id;
	private String type;
	private String Url;

	private Utilisateur personne;
	

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

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public Utilisateur getPersonne() {
		return personne;
	}

	public void setPersonne(Utilisateur personne) {
		this.personne = personne;
	}
	
	
	
	
}
