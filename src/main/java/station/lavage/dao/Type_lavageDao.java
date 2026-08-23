package station.lavage.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import station.lavage.model.Type_lavage;

@Repository
public interface Type_lavageDao extends MongoRepository<Type_lavage, String> {

}
