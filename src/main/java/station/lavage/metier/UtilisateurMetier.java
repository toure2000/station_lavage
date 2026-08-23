package station.lavage.metier;

import java.util.ArrayList;
import java.util.List;

import com.google.api.client.util.GenericData;

import station.lavage.model.Utilisateur;
import station.lavage.rest.message.Message;

public interface UtilisateurMetier{

	public Message save(Utilisateur newuser);
	public Utilisateur findById(String id);
	public Message delete(Utilisateur user);
	public List<Utilisateur> findAll();
	public Message sendEmail(Message Message);
	public Message update(String id, Utilisateur med);
	boolean isRegistrable(Utilisateur newmed);
	public List<Utilisateur> getByListId(ArrayList<String> listId);
	public Utilisateur findByIdOrEmail(String idOrEmail);
	public List<Utilisateur> getByRole(String role);

	

}
