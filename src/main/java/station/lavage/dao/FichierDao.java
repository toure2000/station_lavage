package station.lavage.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import station.lavage.model.fichier;

@Repository
public interface FichierDao extends MongoRepository<fichier, String>{

}
