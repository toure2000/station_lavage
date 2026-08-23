package station.lavage.metier.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import station.lavage.dao.UtilisateurDao;
import station.lavage.dao.lavageDao;
import station.lavage.metier.LavageMetier;
import station.lavage.model.Utilisateur;
import station.lavage.model.Lavage;
import station.lavage.rest.message.Message;
@Service
public class LavageMetierImpl implements LavageMetier{
	@Autowired
	lavageDao lavageDao;
	@Autowired
	UtilisateurDao userDao;

	@Override
	public List<Lavage> findAll() {
		// TODO Auto-generated method stub
		return lavageDao.findAll();
	}

	@Override
	public Message save(Lavage entity,String name) {
		// TODO Auto-generated method stub
		String text=null;
		Utilisateur user= userDao.findByIdOrEmail(name,name).get(0);
		if(user!=null) {
			entity.setEnregistreur(user);
			lavageDao.save(entity);
			text="succes d'enregistrement!!!";
		}
		return new Message(text);
	}

	@Override
	public Message delete(Lavage entity, String name) {
		// TODO Auto-generated method stub
		String text=null;
		Utilisateur user= userDao.findByIdOrEmail(name,name).get(0);
		if(user!=null) {
			lavageDao.delete(entity);
			text="succes de supression!!!";
		}
		return new Message(text);
	}
	@Override
	public List<Lavage> findAllByUser(Utilisateur user) {
		// TODO Auto-generated method stub
		List<Lavage> listelavage=new ArrayList<Lavage>();
		lavageDao.findAll().forEach(
				lv->{
					if(lv.getEnregistreur().getId().equals(user.getId())) {
						listelavage.add(lv);
					}
				}
				);;
		return listelavage;
	}

	@Override
	public List<Lavage> findAllByDate(String date) {
		// TODO Auto-generated method stub
		return null;
	}

}
