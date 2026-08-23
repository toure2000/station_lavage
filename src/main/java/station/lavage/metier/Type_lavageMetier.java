package station.lavage.metier;

import java.util.List;

import station.lavage.model.Type_lavage;
import station.lavage.rest.message.Message;

public interface Type_lavageMetier{


	List<Type_lavage> findAll();


	Message save(Type_lavage entity, String id);


	Message delete(Type_lavage entity, String name);

}
