package station.lavage.metier;

import java.util.List;

import station.lavage.model.Services;
import station.lavage.rest.message.Message;

public interface ServiceMetier {

	List<Services> findAll();

	Message save(Services entity, String name);

	Message delete(Services entity, String name);

}
