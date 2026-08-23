package station.lavage.metier.implement;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import station.lavage.dao.UtilisateurDao;
import station.lavage.email.emaill;
import station.lavage.metier.UtilisateurMetier;
import station.lavage.model.Utilisateur;
import station.lavage.rest.message.Message;
@Service
public class UtilisateurMetierImpl implements UtilisateurMetier{
	@Autowired
	UtilisateurDao userDB;
	
	@Override
	public Message save(Utilisateur newuser) {
		// TODO Auto-generated method stub
		if(isRegistrable(newuser)) {
		 newuser.setPassword(this.passwordEncoder().encode(newuser.getPassword()));
		 userDB.save(newuser);
		 return new Message("enregistrement  reussi !!!".toUpperCase());
		}else {
			return new Message("compte deja existant avec le meme identifiant ou email!!!".toUpperCase());
		}
	}
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	@Override
	public Utilisateur findById(String id) {
		// TODO Auto-generated method stub
		return (userDB.findById(id).get());
	}

	

	@Override
	public Message delete(Utilisateur user) {
		// TODO Auto-generated method stub
		userDB.delete(user);
		return new Message("supression "+user.toString()+"  reussi !!");
	}

	@Override
	public List<Utilisateur> findAll() {
		// TODO Auto-generated method stub
		return (userDB.findAll());
	}



	@Override
	public Utilisateur findByIdOrEmail(String idOrEmail) {
		// TODO Auto-generated method stub
		List<Utilisateur> result=userDB.findByIdOrEmail(idOrEmail,idOrEmail);
		Utilisateur user=new Utilisateur();
		if(result.size()>0) {
			user= result.get(0);
		}
		return (user);
	}

   



	@Override
	public Message sendEmail(Message Message) {
		// TODO Auto-generated method stub
		
		try {
			emaill.sendEmail( Message.getText(), Message.getText2(),Message.getText3());
			return new Message("Message envoyer avec succes");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message("Adresse non trouvé !!");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message("Un probleme est survenu sur le server!!!"+Message.getText());
			
		}
	}
    
	@Override
	public Message update(String id, Utilisateur newmed) {
		// TODO Auto-generated method stub
        if(userDB.findById(id).isPresent()&&( id.equals(newmed.getId())|| isRegistrable(newmed))) {
		Utilisateur med = userDB.findById(id).get();
		newmed.setPassword(med.getPassword());
		userDB.delete(med);
		userDB.save(newmed);
		return new Message("modificatition  reussi !!!".toUpperCase());
        }else {
        	return new Message("compte deja existant avec le meme identifiant ou email!!!".toUpperCase());
        }

	}
	
	@Override
	public boolean isRegistrable(Utilisateur newmed){
		boolean resulte = true;
		Utilisateur med0 = this.findByIdOrEmail(newmed.getEmail());
		if ( med0!= null&&med0.getId()!=null&&med0.getId().equals(newmed.getId())) {
			 resulte=false;
		}
		return resulte;
	}
	/*public Utilisateur normaliseImage(Utilisateur user) {
	   if(user!=null&&user.getPicture()!=null) {
		   String pict=user.getPicture();
		   if(pict.indexOf("image/")==0) {
			   user.setPicture(pict.replaceFirst("image/", "https://toure.pagekite.me//image/"));
		   }
	   }
	    return user;
    }
    public List<Utilisateur> normaliseAllImage(List<Utilisateur> luser) {
	   List<Utilisateur> luser2=new ArrayList<>();
	   luser.forEach(user->{
		   luser2.add(this.normaliseImage(user));
	   });
	    return luser2;
   }*/
	@Override
	public List<Utilisateur> getByListId(ArrayList<String> listId) {
		// TODO Auto-generated method stub
		return userDB.findAllById(listId);
	}
	@Override
	public  List<Utilisateur> getByRole(String role) {
		// TODO Auto-generated method stub
		
		return userDB.findByRole(role);
	}
	

}
