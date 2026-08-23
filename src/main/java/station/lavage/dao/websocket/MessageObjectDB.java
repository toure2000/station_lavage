package station.lavage.dao.websocket;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import station.lavage.model.Utilisateur;
import station.lavage.model.websocket.MessageObject;

@Repository
public interface MessageObjectDB extends MongoRepository<MessageObject, String>{
	/*@Query("{ $and: [{listIdTo:{$contains:'?0' }, { etat: '?1' }] }")
	List<MessageObject> findByIdToAndEtat(String idTo,String etat);
	@Query("{ user: '?0'}")
	List<MessageObject> findByUser(Utilisateur user);
	*/
	List<MessageObject> findByListIdToContainsOrIduser(List<String> listIdTo, String iduser);
	List<MessageObject> findByUser(Utilisateur user);
	List<MessageObject> findByListIdToContainsAndListIdRecueNotContains(List<String> listIdTo,List<String> listIdRecue);
	
}


