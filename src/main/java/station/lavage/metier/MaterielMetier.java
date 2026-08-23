package station.lavage.metier;

import java.util.List;

import station.lavage.model.Materiel;
import station.lavage.rest.message.Message;

public interface MaterielMetier {
	List<Materiel> findAll();

	Message save(Materiel entity, String id);

	Message delete(Materiel entity, String name);
}
