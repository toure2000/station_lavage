package station.lavage.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import station.lavage.model.Services;


public interface ServiceDao extends MongoRepository<Services, String> {

}
