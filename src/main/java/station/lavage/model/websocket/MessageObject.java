package station.lavage.model.websocket;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import station.lavage.model.Utilisateur;

@Document
public class MessageObject {
	@Id
	private String id;
	private String iduser;
	private String sessionId;
	private List<String> listId;
	private String message;
	
	private List<String> listIdTo;
	private List<String> listIdVue;
	private List<String> listIdRecue;
	
	private String type;
	private String etat;
	private Utilisateur user;
    
   
	@Override
	public String toString() {
		return "MessageObject [id=" + id + ", iduser=" + iduser + ", sessionId=" + sessionId + ", listId=" + listId
				+ ", message=" + message + ", listIdTo=" + listIdTo + ", type=" + type + ", etat=" + etat + ", user="
				+ user + "]";
	}

	
	public List<String> getListIdRecue() {
		return listIdRecue;
	}


	public void setListIdRecue(List<String> listIdRecue) {
		this.listIdRecue = listIdRecue;
	}


	public List<String> getListIdVue() {
		return listIdVue;
	}


	public void setListIdVue(List<String> listIdVue) {
		this.listIdVue = listIdVue;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public List<String> getListIdTo() {
		return listIdTo;
	}

	public void setListIdTo(List<String> listIdTo) {
		this.listIdTo = listIdTo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getListId() {
		return listId;
	}

	public void setListId(List<String> listId) {
		this.listId = listId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getIduser() {
		return iduser;
	}

	public void setIduser(String iduser) {
		this.iduser = iduser;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}