package station.lavage.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import station.lavage.model.Type_voiture;

@Repository
public interface Type_voitureDao extends MongoRepository<Type_voiture, String> {

}
