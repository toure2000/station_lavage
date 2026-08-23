package station.lavage.metier.implement;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import jakarta.validation.Valid;
import station.lavage.dao.FichierDao;
import station.lavage.dao.UtilisateurDao;
import station.lavage.metier.FichierMetier;
import station.lavage.metier.UtilisateurMetier;
import station.lavage.model.Utilisateur;
import station.lavage.model.fichier;
import station.lavage.rest.message.Message;

@Service
public class FichierMetierImpl implements FichierMetier {
	@Autowired
	UtilisateurMetier userMetier; 
	@Autowired
	FichierDao fichierdb;

	@Override
	public fichier findById(@Valid String id) {
		// TODO Auto-generated method stub
		return fichierdb.findById(id).get();
	}

	@Override
	public Message delete(fichier fichier) {
		// TODO Auto-generated method stub
		fichierdb.delete(fichier);
		return new Message("supression reussi !!!");
	}

	@Override
	public List<fichier> getAllByIdOrEmailUser(String idOrEmail) {
		// TODO Auto-generated method stub
		Utilisateur user = userMetier.findByIdOrEmail(idOrEmail);

		System.out.println("getAllByIdOrEmailUser_______ " + user.toString());

		List<fichier> result = new ArrayList<>();

		String role = user.getRole();
		System.out.println("getAllByIdOrEmailUser planning role:" + role);
		if (!(role.equals("SIMPLEUTILISATEUR")||role.equals("PATIENT"))) {
			result = this.fichierdb.findAll();
		}
		if (role.equals("SIMPLEUTILISATEUR")||role.equals("PATIENT")) {
			List<fichier> result2 = new ArrayList<>();
			this.fichierdb.findAll().forEach(p -> {
				if (p.getPersonne() != null && p.getPersonne().getId() != null
						&& p.getPersonne().getId().equals(user.getId())) {
					result2.add(p);
				}
			});

			result = result2;

		}
		return result;
	}

	@Override
	public fichier save(fichier p) {
		// TODO Auto-generated method stub
		
		return fichierdb.save(p);
		
	}

	@Override
	public fichier save(String id, String iduser, MultipartFile importFile, String type,
			Authentication authentication) throws IllegalStateException, IOException {
		fichier f= new fichier();
		if(id==null || id.length()==0) {
			UUID uuid = UUID.randomUUID();
			String uuidAsString = uuid.toString();
			id=uuidAsString;
			
		}
		
		Utilisateur user= userMetier.findByIdOrEmail(authentication.getName());
		
		
		if(user!=null&&user.getId()!=null) {
				 
			String absultePath0="C:\\Users\\mamad\\Desktop\\station_lavage\\src\\main\\webapp\\fichier\\";
			String extension=importFile.getOriginalFilename();//.split("\\.")[1];
			String pathcomplet=absultePath0+type+"\\"+iduser+"_."+extension;
			String url="fichier/"+type+"/" +iduser+"_."+extension;
			
			
			importFile.transferTo(new File(pathcomplet));
			
			f.setId(url);
			
			if(user.getRole()!=null&&user.getRole().equals("ADMIN")) {
			 f.setId(id);
			}
			f.setPersonne(user);//personne qui a uploader
			f.setUrl(url);
			f.setType(type);
			f=this.save(f);
			
			if(f.getType().equals("PROFILE")&&user.getRole()!=null&&!user.getRole().equals("ADMIN")) {
				user.setPicture(url);
				userMetier.update(iduser, user);
			}
		}else {
			throw new IOException("ecchec !!");
		}
		return f;
	}
	@Override
	public boolean deleteFichierOnDisc(String file_path){
	    Path path = Paths.get(file_path);
	    try {
	      boolean result = Files.deleteIfExists(path);
	      if (result) {
	        System.out.println("File is deleted!"+file_path);
	      } else {
	        System.out.println("Sorry, could not delete the file."+file_path);
	      }
	      return result;
	    } catch (IOException e) {
	      e.printStackTrace();
	      return false;
	    }
    }
}



