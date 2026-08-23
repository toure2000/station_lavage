package station.lavage.metier;

import java.util.List;

import station.lavage.model.Utilisateur;
import station.lavage.model.Lavage;
import station.lavage.rest.message.Message;

public interface LavageMetier{

	List<Lavage> findAll();

	Message save(Lavage entity, String name);

	Message delete(Lavage entity, String name);

	List<Lavage> findAllByUser(Utilisateur user);

	List<Lavage> findAllByDate(String date);

}
