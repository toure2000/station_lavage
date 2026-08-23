

/***********************/

package station.lavage.metier.websocket;

import java.util.List;


import station.lavage.model.Utilisateur;
import station.lavage.model.websocket.MessageObject;
import station.lavage.rest.message.Message;


public interface MessageObjectMetier {
    public MessageObject save(MessageObject newparam) ;
    public Message delete(MessageObject newparam);
	public List<MessageObject> findAll();
	public MessageObject findById( String id);
	public List<MessageObject> findByUser( Utilisateur user);
	public List<MessageObject> findByIdUser( String id);
	//public List<MessageObject> findByIdToAndEtat(String iduserto, String upperCase);
	public List<MessageObject> findByIdToAndNotIdRecu(String id);
}


