package station.lavage.metier.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import station.lavage.dao.MaterielDao;
import station.lavage.dao.UtilisateurDao;
import station.lavage.metier.MaterielMetier;
import station.lavage.model.Materiel;
import station.lavage.model.Materiel;
import station.lavage.model.Utilisateur;
import station.lavage.rest.message.Message;


@Service
public class MaterielMetierImpl implements MaterielMetier{
 
	@Autowired
	private MaterielDao Materieldao;
	@Autowired
	UtilisateurDao userDao;

	@Override
	public List<Materiel> findAll() {
		// TODO Auto-generated method stub
		return Materieldao.findAll();
	}

	@Override
	public Message save(Materiel entity, String name) {
		// TODO Auto-generated method stub
		String text = null;
		Utilisateur user = userDao.findByIdOrEmail(name,name).get(0);
		if (user != null) {
			Materieldao.save(entity);
			text = "succes de d'enregistrement!!!";
		}
		return new Message(text);
	}

	@Override
	public Message delete(Materiel entity, String name) {
		// TODO Auto-generated method stub
		String text = null;
		Utilisateur user = userDao.findByIdOrEmail(name,name).get(0);
		if (user != null) {
			Materieldao.delete(entity);
			text = "succes de supression!!!";
		}
		return new Message(text);
	}
}
