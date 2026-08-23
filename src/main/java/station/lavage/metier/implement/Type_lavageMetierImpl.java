package station.lavage.metier.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import station.lavage.dao.UtilisateurDao;
import station.lavage.dao.Type_lavageDao;
import station.lavage.metier.Type_lavageMetier;
import station.lavage.model.Utilisateur;
import station.lavage.model.Type_lavage;
import station.lavage.rest.message.Message;
@Service
public class Type_lavageMetierImpl implements Type_lavageMetier{
	@Autowired
    Type_lavageDao type_lavageDao;
	@Autowired
	UtilisateurDao userDao;
	
	@Override
	public List<Type_lavage> findAll() {
		// TODO Auto-generated method stub
		return type_lavageDao.findAll();
	}

	@Override
	public Message save(Type_lavage entity,String id) {
		// TODO Auto-generated method stub
		String text=null;
		Utilisateur user= userDao.findByIdOrEmail(id,id).get(0);
		if(user!=null) {
			type_lavageDao.save(entity);
			System.out.println(entity.toString());
			text="succes d'enregistrement!!!";
		}
		return new Message(text);
	}

	@Override
	public Message delete(Type_lavage entity, String name) {
		// TODO Auto-generated method stub
		String text=null;
		Utilisateur user= userDao.findByIdOrEmail(name,name).get(0);
		if(user!=null) {
			type_lavageDao.delete(entity);
			System.out.println(entity.toString());
			text="succes de supression!!!";
		}
		return new Message(text);
	}
}
