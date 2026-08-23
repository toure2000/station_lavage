package station.lavage.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import station.lavage.model.util.Prix;

@Document
public class Lavage {
	
	//attributs
	@Id
	private String id;
	private Date date_enregistrement;
	
	
	
	private String date_lavage;
	private String heure_debut_lavage;
	private String heure_fin_lavage;
	
	private Date date_payement;
	private Prix prix_payer;
	//fin attributs
	
	
	//mouvement
	private Type_voiture typeVoiture;
	private Utilisateur enregistreur; //personne qui enregistre
	private List<Utilisateur> list_ouvrier;
	private Utilisateur client;
	private Type_lavage type_lavage;
	private List<Services> list_Service;
	private List<Materiel> list_Materiel;
    //fin mouvement
   
	//methodes utiles
	public Prix getPrixTotale(String unite_sortie){
		Prix result=new Prix();
		result.setUnite(unite_sortie);
		
		int montant=0;
		for(Services e:list_Service){
		  montant+=e.getPrix().convertirPrix(unite_sortie).getMontant();
		 }
		for(Materiel e:list_Materiel){
			  montant+=e.getPrix().convertirPrix(unite_sortie).getMontant();
	    }
		montant+=type_lavage.getPrix().convertirPrix(unite_sortie).getMontant();
		
		result.setMontant(montant);
		
		return result;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate_enregistrement() {
		return date_enregistrement;
	}

	public void setDate_enregistrement(Date date_enregistrement) {
		this.date_enregistrement = date_enregistrement;
	}

	public String getDate_lavage() {
		return date_lavage;
	}

	public void setDate_lavage(String date_lavage) {
		this.date_lavage = date_lavage;
	}

	public String getHeure_debut_lavage() {
		return heure_debut_lavage;
	}

	public void setHeure_debut_lavage(String heure_debut_lavage) {
		this.heure_debut_lavage = heure_debut_lavage;
	}

	public String getHeure_fin_lavage() {
		return heure_fin_lavage;
	}

	public void setHeure_fin_lavage(String heure_fin_lavage) {
		this.heure_fin_lavage = heure_fin_lavage;
	}

	public Date getDate_payement() {
		return date_payement;
	}

	public void setDate_payement(Date date_payement) {
		this.date_payement = date_payement;
	}

	

	
	public Prix getPrix_payer() {
		return prix_payer;
	}

	public void setPrix_payer(Prix prix_payer) {
		this.prix_payer = prix_payer;
	}

	public Type_voiture getTypeVoiture() {
		return typeVoiture;
	}

	public void setTypeVoiture(Type_voiture typeVoiture) {
		this.typeVoiture = typeVoiture;
	}

	public Utilisateur getEnregistreur() {
		return enregistreur;
	}

	public void setEnregistreur(Utilisateur enregistreur) {
		this.enregistreur = enregistreur;
	}

	public List<Utilisateur> getList_ouvrier() {
		return list_ouvrier;
	}

	public void setList_ouvrier(List<Utilisateur> list_ouvrier) {
		this.list_ouvrier = list_ouvrier;
	}

	public Utilisateur getClient() {
		return client;
	}

	public void setClient(Utilisateur client) {
		this.client = client;
	}

	public Type_lavage getType_lavage() {
		return type_lavage;
	}

	public void setType_lavage(Type_lavage type_lavage) {
		this.type_lavage = type_lavage;
	}

	public List<Services> getList_Service() {
		return list_Service;
	}

	public void setList_Service(List<Services> list_Service) {
		this.list_Service = list_Service;
	}
	
	
	
}
