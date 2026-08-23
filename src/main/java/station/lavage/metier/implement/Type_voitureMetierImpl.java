package station.lavage.metier.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import station.lavage.dao.UtilisateurDao;
import station.lavage.dao.Type_voitureDao;
import station.lavage.metier.Type_voitureMetier;
import station.lavage.model.Utilisateur;
import station.lavage.model.Type_voiture;
import station.lavage.rest.message.Message;
@Service
public class Type_voitureMetierImpl implements Type_voitureMetier{
	@Autowired
    Type_voitureDao type_voitureDao;
	@Autowired
	UtilisateurDao userDao;
	@Override
	public Message save(Type_voiture entity, String name) {
		// TODO Auto-generated method stub
		String text=null;
		Utilisateur user= userDao.findByIdOrEmail(name,name).get(0);
		if(user!=null) {
			type_voitureDao.save(entity);
			text="succes d'enregistrement!!!";
		}
		return new Message(text);
	}
	@Override
	public List<Type_voiture> findAll() {
		// TODO Auto-generated method stub
		return type_voitureDao.findAll();
	}
	@Override
	public Message delete(Type_voiture entity, String name) {
		// TODO Auto-generated method stub
		String text=null;
		Utilisateur user= userDao.findByIdOrEmail(name,name).get(0);
		if(user!=null) {
			type_voitureDao.delete(entity);
			text="succes de suppression!!!";
		}
		return new Message(text);
	}

}
