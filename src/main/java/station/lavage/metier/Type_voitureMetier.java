package station.lavage.metier;

import java.util.List;

import station.lavage.model.Type_voiture;
import station.lavage.rest.message.Message;

public interface Type_voitureMetier{

	Message save(Type_voiture entity, String name);

	List<Type_voiture> findAll();

	Message delete(Type_voiture entity, String name);

}
