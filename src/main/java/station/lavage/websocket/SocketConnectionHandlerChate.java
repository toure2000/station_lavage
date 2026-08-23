// Program to eastablish the socket connection 

package station.lavage.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import station.lavage.metier.UtilisateurMetier;
import station.lavage.metier.websocket.MessageObjectMetier;
import station.lavage.model.websocket.MessageObject;

@Service
// Socket-Connection Configuration class 
public class SocketConnectionHandlerChate extends TextWebSocketHandler {
	@Autowired
	UtilisateurMetier userm;
	@Autowired
	MessageObjectMetier messageMetier;
	
	Logger logger = Logger.getLogger("MyLog");
	// In this list all the connections will be stored
	// Then it will be used to broadcast the message
	List<id_and_session> listeId_and_session = Collections.synchronizedList(new ArrayList<>());

	// List<MessageObject> listeMessageEchouers = Collections.synchronizedList(new
	// ArrayList<>());

	// This method is executed when client tries to connect
	// to the sockets
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		// Logging the connection ID with Connected Message
		logger.info(session.getId() + " Connected");
		// Adding the session into the list
		id_and_session idas = new id_and_session();
		idas.setIduser(null);
		idas.setSession(session);
		this.listeId_and_session.add(idas);

		MessageObject m = new MessageObject();
		m.setIduser(null);
		m.setSessionId(session.getId());
		m.setListId(getListIdUser());
		m.setType("connection_etablie".toUpperCase());
		m.setMessage("nouvelle connection");
		sendMessageToSession(session, m);

	}

	// When client disconnect from WebSocket then this
	// method is called
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		logger.info(session.getId() + " DisConnected");

		// Removing the connection info from the list
		id_and_session idas = new id_and_session();
		idas.setIduser(null);
		idas.setSession(session);
		this.listeId_and_session.remove(findById_or_session(idas));

		MessageObject m = new MessageObject();
		m.setIduser(null);
		m.setSessionId(session.getId());
		m.setListId(getListIdUser());
		m.setMessage("nouvelle connection");

		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();

		for (int i = 0; i < listeId_and_session.size(); i++) {
			id_and_session elem = listeId_and_session.get(i);
			if (elem.getSession().getId().equals(session.getId())
					|| m.getIduser() != null && m.getIduser().equals(elem.getIduser())) {
				elem.setIduser(m.getIduser());
				elem.setSession(session);

				m.setSessionId(session.getId());
				m.setSessionId(session.getId());
				listeId_and_session.set(i, elem);
				continue;
			}
			// sendMessage is used to send the message to
			// the session
			elem.getSession().sendMessage(new TextMessage(gson.toJson(m)));
		}

	}

	public id_and_session findById_or_session(id_and_session idas) {
		id_and_session result = null;
		for (id_and_session elem : listeId_and_session) {
			if (elem.getIduser() != null && elem.getIduser().equals(idas.getIduser())
					|| (elem.getSession() != null) && (elem.getSession() == idas.getSession())) {
				result = elem;
				return elem;
			}
		}

		return result;
	}

	/*
	 * public List<MessageObject> getListeMessageEchouers() { return
	 * this.listeMessageEchouers; }
	 * 
	 * public void setListeMessageEchouers(List<MessageObject> listemessageEchouers)
	 * { this.listeMessageEchouers=listemessageEchouers; }
	 */

	// normalise l'id dans listeId_and_session
	public id_and_session NormaliseId(id_and_session idas) {
		id_and_session result = null;

		for (id_and_session elem : listeId_and_session) {
			if (elem.getSession() == idas.getSession()) {

				elem.setIduser(idas.getIduser());

				listeId_and_session.set(listeId_and_session.indexOf(elem), elem);
				result = elem;
				return elem;
			}
		}

		return result;
	}

	// normalise la session dans listeId_and_session
	public id_and_session NormaliseSession(id_and_session idas) {
		id_and_session result = null;

		for (id_and_session elem : listeId_and_session) {
			if (idas.getIduser() != null && idas.getIduser().equals(elem.getIduser())) {
				elem.setSession(idas.getSession());
				listeId_and_session.set(listeId_and_session.indexOf(elem), elem);
				result = elem;
				return elem;
			}
		}

		return result;
	}

	// normalise la session et l'id dans listeId_and_session
	public id_and_session NormaliseSessionAnId(id_and_session idas) {
		id_and_session result = null;

		for (id_and_session elem : listeId_and_session) {

			if (idas.getIduser() != null && idas.getIduser().equals(elem.getIduser())) {
				elem.setSession(idas.getSession());
				listeId_and_session.set(listeId_and_session.indexOf(elem), elem);
				result = elem;
			}

			if (elem.getSession() == idas.getSession()) {

				elem.setIduser(idas.getIduser());

				listeId_and_session.set(listeId_and_session.indexOf(elem), elem);
				result = elem;
			}

		}

		return result;
	}

	public List<String> getListIdUser() {
		List<String> l = new ArrayList<>();
		listeId_and_session.forEach(elem -> {
			if (elem.getIduser() != null && !elem.getIduser().equals("")) {
				l.add(elem.getIduser());
			}
		});
		return l;
	}

	public void sendMessageToSession(WebSocketSession session, MessageObject object) throws IOException {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		String slistuser = gson.toJson(object);
		TextMessage message = new TextMessage(slistuser);
		session.sendMessage(message);
	}

	// It will handle exchanging of message in the network
	// It will have a session info who is sending the
	// message Also the Message object passes as parameter
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

		super.handleMessage(session, message);
		// Iterate through the list and pass the message to
		// all the sessions Ignore the session in the list
		// which wants to send the message.

		// declaration de gson (pour pouvoir contertir obj en json ou json en object
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		// fin declaration de gson (pour pouvoir contertir obj en json ou json en object

		// recuperation du message (text)
		String messagetext = message.getPayload().toString();
		// fin recuperation du message (text)

		// conversion du message (text) en messageObject
		MessageObject messageobject = gson.fromJson(messagetext, MessageObject.class);
		// conversion du message (text) en messageObject

		if (messageobject.getType().equals("finalisation".toUpperCase())) {
			// normalisation des parametres de user emeteur dans la list puis recuperation
			// de valeur
			id_and_session idas = new id_and_session();
			idas.setIduser(messageobject.getIduser());
			idas.setSession(session);
			idas = NormaliseSessionAnId(idas);
			// fin normalisation des parametres de user emeteur dans la list puis
			// recuperation de valeur

			messageobject.setListId(getListIdUser()); // mise a joure de la list iduser dans message object
			messageobject.setUser(userm.findById(messageobject.getIduser()));

			logger.info("handleMessage:"+messageobject.toString());

			// envoi aux destinateurs
			session.sendMessage(new TextMessage(gson.toJson(messageobject)));// lui meme

			// envoi aux utilisateur courant, les messages qui lui etait destiner mais qui
			// onts echouées
			List<MessageObject> listeMessageEchouers = messageMetier.findByIdToAndNotIdRecu(messageobject.getIduser());
			//logger.info(gson.toJson(listeMessageEchouers));
			listeMessageEchouers.forEach(elem -> {
				List<String> listIdTo = elem.getListIdTo();
				List<String> listIdRecue = elem.getListIdRecue();
				logger.info(listIdRecue + " a traiter ,message non envoyer");
				if ((listIdTo.indexOf(messageobject.getIduser()) >= 0)
						&& (listIdRecue.indexOf(messageobject.getIduser()) < 0)) {
					try {
						elem.setEtat("originale".toUpperCase());
						session.sendMessage(new TextMessage(gson.toJson(elem)));
						listIdRecue.add((messageobject.getIduser()));
						logger.info(listIdRecue
								+ " a traiter ,message non envoyer apres listidelem.remove((messageobject.getIduser()));");

						elem.setListIdRecue(listIdRecue);
						messageMetier.save(elem);

						if (!elem.getType().equals("ENVOI_REUSSI".toUpperCase())) {
							logger.info("if (!elem.getType().equals(\"ENVOI_REUSSI\".toUpperCase())) {");
							MessageObject messageO = new MessageObject();
							messageO.setIduser(messageobject.getIduser());
							messageO.setType("ENVOI_REUSSI".toUpperCase());
							messageO.setMessage(elem.getId());
							List<String> l = new ArrayList<>();
							l.add(elem.getIduser());
							messageO.setListIdTo(l);
							this.envoiDeMessageAvecGaranti(messageO, session);
						} else {
							messageMetier.delete(elem);
						}
						if (messageobject.getListIdRecue().size() == messageobject.getListIdTo().size()
								&& messageobject.getType().equals("MESSAGE_VUE".toUpperCase())) {
							messageMetier.delete(elem);
						}

					} catch (IOException e) {
						// TODO Auto-generated catch block
						logger.info("erorrrrrrrrr");
						e.printStackTrace();
					}
				}

//				if (listidelem.size() > 0) { // message echoues mis a jour appres envoi
//					logger.info(listidelem + "messages echouers mis a jour appres envoi. message: "
//							+ messageobject.toString());
//					elem.setEtat("non_envoyer".toUpperCase());
//					// listeMessageEchouers2.add(elem);
//					messageMetier.save(elem);
//				}
//				if (listidelem.size() == 0) { // message suprimer appres envoi
//					logger.info(
//							listidelem + "//message suprimer appres envoi .message: " + messageobject.toString());
//					messageMetier.delete(elem);
//				}
			});
			// this.setListeMessageEchouers(listeMessageEchouers2);
			// fin envoi aux utilisateur courant, les messages qui lui etait destiner mais
			// qui onts echouées

		}

		if (messageobject.getType().equals("MESSAGE_VUE".toUpperCase())) {
			MessageObject messageconcerner = messageMetier.findById(messageobject.getMessage());
			List<String> listvue0 = messageconcerner.getListIdVue();
			if (listvue0.indexOf(messageobject.getIduser()) < 0) {
				listvue0.add(messageobject.getIduser());
				messageconcerner.setListIdVue(listvue0);
				messageMetier.save(messageconcerner);// mise a jour
			}
			messageobject.setListIdVue(listvue0);
			envoiDeMessageAvecGaranti(messageobject, session);

		}

		if (messageobject.getType().equals("CHATE_ENCOURE".toUpperCase())
				|| messageobject.getType().equals("FIN_ENCOURE_CHATE".toUpperCase())) {
			id_and_session idas = new id_and_session();
			idas.setIduser(messageobject.getListIdTo().get(0));
			idas = this.findById_or_session(idas);
			if (idas != null && idas.getSession() != null) {
				this.sendMessageToSession(idas.getSession(), messageobject);
			}

		}

		if (messageobject.getType().equals("message".toUpperCase())||messageobject.getType().equals("MESSAGE_RENDEVOUS".toUpperCase())) {
			messageobject.setListId(getListIdUser()); // mise a joure de la list iduser dans message object
			messageobject.setUser(userm.findById(messageobject.getIduser()));
			UUID uuid = UUID.randomUUID();
			String uuidAsString = uuid.toString();
//			logger.info("uuid: "+uuid);
//			if (messageobject.getType() == "message".toUpperCase()) {
//    		  messageobject.setId(uuidAsString);
//			}
			messageobject.setId(uuidAsString);
			envoiDeMessageAvecGaranti(messageobject, session);

		}

	}

	public void envoiDeMessageAvecGaranti(MessageObject messageobject, WebSocketSession session) throws IOException {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();

		logger.info(  "envoiDeMessageAvecGaranti" + messageobject.toString()  );

		// List<String> listIdEchouers = new ArrayList<>();// liste des ids qui n'ont
		// pas reussi
		List<String> listIdReussi = new ArrayList<>();// liste des ids qui ont reussi

		// preparation de la listRecue
		for (String idto : messageobject.getListIdTo()) {
			id_and_session idasto = new id_and_session();
			idasto.setIduser(idto);
			idasto = findById_or_session(idasto);
			if (idasto != null && idasto.getSession() != null) {
				listIdReussi.add(idto);
			}
//			if (idasto == null || idasto.getSession() == null) { // collect de id echouer
//				listIdEchouers.add(idto);
//			}
		}
		// fin preparation de la listRecue
		messageobject.setListIdRecue(listIdReussi);// ajout de la liste recue

		// envoi aux destinateurs
		session.sendMessage(new TextMessage(gson.toJson(messageobject)));// lui meme
		for (String idto : messageobject.getListIdTo()) {
			id_and_session idasto = new id_and_session();
			idasto.setIduser(idto);
			idasto = findById_or_session(idasto);
			if (idasto != null && idasto.getSession() != null) {
				idasto.getSession().sendMessage(new TextMessage(gson.toJson(messageobject)));
			}
		}

		messageobject.setEtat("originale".toUpperCase()); // enregistrement du message sous etat originale
		logger.info(/*gson.toJson(messageobject) + "\n" +*/ messageobject.toString());

		if (messageobject.getType().equals("MESSAGE".toUpperCase())||messageobject.getType().equals("MESSAGE_RENDEVOUS".toUpperCase())) {
			messageMetier.save(messageobject);
		}
		if ((messageobject.getListIdRecue().size() < messageobject.getListIdTo().size())
				&& messageobject.getType().equals("MESSAGE_VUE".toUpperCase())) {
			messageMetier.save(messageobject);
		}
		if ((messageobject.getListIdRecue().size() < messageobject.getListIdTo().size())
				&& messageobject.getType().equals("ENVOI_REUSSI".toUpperCase())) {
			messageMetier.save(messageobject);
		}

//		if (listIdEchouers.size() > 0) {
//			messageobject.setId(null);
//			messageobject.setEtat("non_envoyer".toUpperCase());
//			messageobject.setListIdTo(listIdEchouers);// redefinition de message en fonction des echecs
//			// listeMessageEchouers.add(messageobject);
//
////			if (messageobject.getType() == "MESSAGE_VUE".toUpperCase()) {
//////				MessageObject newm = new MessageObject();
//////				newm.setId(uuidAsString);
//////				newm.setEtat(messageobject.getEtat());
//////				newm.setListIdTo(messageobject.getListIdTo());
//////				newm.setIduser(messageobject.getIduser());
//////				newm.setListIdVue(messageobject.getListIdVue());
//////				newm.setType(messageobject.getType());
////			//	messageMetier.save(newm);
////				
////				
////			}
//			messageMetier.save(messageobject);
//		}
		// fin envoi aux destinateurs
	}
}

class id_and_session {
	private String iduser;
	private WebSocketSession session;

	public String getIduser() {
		return iduser;
	}

	public void setIduser(String iduser) {
		this.iduser = iduser;
	}

	public WebSocketSession getSession() {
		return session;
	}

	public void setSession(WebSocketSession session) {
		this.session = session;
	}

}
