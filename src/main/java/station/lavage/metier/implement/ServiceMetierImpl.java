package station.lavage.metier.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import station.lavage.dao.ServiceDao;
import station.lavage.dao.UtilisateurDao;
import station.lavage.metier.ServiceMetier;
import station.lavage.model.Services;
import station.lavage.model.Utilisateur;
import station.lavage.rest.message.Message;

@Service
public class ServiceMetierImpl implements ServiceMetier {
	@Autowired
	private ServiceDao Servicedao;
	@Autowired
	UtilisateurDao userDao;

	@Override
	public List<Services> findAll() {
		// TODO Auto-generated method stub
		return Servicedao.findAll();
	}

	@Override
	public Message save(Services entity, String name) {
		// TODO Auto-generated method stub
		String text = null;
		Utilisateur user = userDao.findByIdOrEmail(name,name).get(0);
		if (user != null) {
			Servicedao.save(entity);
			text = "succes de d'enregistrement!!!";
		}
		return new Message(text);
	}

	@Override
	public Message delete(Services entity, String name) {
		// TODO Auto-generated method stub
		String text = null;
		Utilisateur user = userDao.findByIdOrEmail(name,name).get(0);
		if (user != null) {
			Servicedao.delete(entity);
			text = "succes de supression!!!";
		}
		return new Message(text);
	}
}
