package station.lavage.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import station.lavage.model.Lavage;

@Repository
public interface lavageDao extends MongoRepository<Lavage, String> {

}
