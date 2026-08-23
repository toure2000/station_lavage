




/**********************************************/





package station.lavage.metier.implement.websocket;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import station.lavage.dao.websocket.MessageObjectDB;
import station.lavage.metier.websocket.MessageObjectMetier;
import station.lavage.model.Utilisateur;
import station.lavage.model.websocket.MessageObject;
import station.lavage.rest.message.Message;


@Service
public class MessageObjectMetierImpl implements MessageObjectMetier{  
	@Autowired
	MessageObjectDB paraDB;
	

	@Override
	public MessageObject save(MessageObject newparam){
		if(newparam.getId()==null || newparam.getId().isBlank()) {
			UUID uuid = UUID.randomUUID();
			String uuidAsString = uuid.toString();
			newparam.setId(uuidAsString);
			System.out.println(newparam);
			MessageObject retour = paraDB.save(newparam);
			newparam.setId(null);
			return retour;

		}else {
			System.out.println(newparam);
			MessageObject retour = paraDB.save(newparam);
			return retour;

		}
		
	}

	@Override
	public Message delete(MessageObject newparam) {
		// TODO Auto-generated method stub
		paraDB.deleteById(newparam.getId());
		return new Message("suprimer!!");
	}

	@Override
	public List<MessageObject> findAll() {
		// TODO Auto-generated method stub
		List<MessageObject> listp=new ArrayList<>();
		
		return paraDB.findAll();
	}

	@Override
	public MessageObject findById(String id) {
		// TODO Auto-generated method stub
		return paraDB.findById(id).get();
	}

	@Override
	public List<MessageObject> findByIdToAndNotIdRecu(String id) {
		// TODO Auto-generated method stub
		List<String> listIdto = new ArrayList<>();
		listIdto.add(id);
		List<String> listIdRecu = new ArrayList<>();
		listIdRecu.add(id);
		return paraDB.findByListIdToContainsAndListIdRecueNotContains(listIdto,listIdRecu);
	}

	@Override
	public List<MessageObject> findByUser(Utilisateur user) {
		// TODO Auto-generated method stub
		return paraDB.findByUser(user);
	}

	@Override
	public List<MessageObject> findByIdUser(String id) {
		// TODO Auto-generated method stub
		List<String> l=new ArrayList<>();
		l.add(id);
		return paraDB.findByListIdToContainsOrIduser(l,id);
	}
	



	
}











