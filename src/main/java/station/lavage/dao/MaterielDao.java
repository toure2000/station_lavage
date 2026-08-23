package station.lavage.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import station.lavage.model.Materiel;

@Repository
public interface MaterielDao extends MongoRepository<Materiel, String>{

}
