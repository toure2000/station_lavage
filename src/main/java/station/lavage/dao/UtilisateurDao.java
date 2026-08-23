package station.lavage.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import station.lavage.model.Utilisateur;

@Repository
public interface UtilisateurDao extends MongoRepository<Utilisateur, String> {
	@Query("{nom:'?0'}")
	Utilisateur findByUsername(String username);
	
	List<Utilisateur> findByIdOrEmail(String id, String email);
	
	List<Utilisateur> findByRole(String role);

}
