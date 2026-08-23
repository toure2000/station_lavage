package station.lavage.rest.controleur;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import station.lavage.metier.FichierMetier;
import station.lavage.metier.UtilisateurMetier;
import station.lavage.model.Utilisateur;
import station.lavage.model.fichier;
import station.lavage.rest.message.Message;

@CrossOrigin("*")
@RestController
@RequestMapping("/fichier")
public class FichierRes {
	@Autowired
	FichierMetier fichierMetier;
	@Autowired
	UtilisateurMetier userMetier; 

	@GetMapping("/get/{id}")
	public fichier get(@Valid @PathVariable String id, Authentication authentication) {
		return this.fichierMetier.findById(id);
	}
	@GetMapping("/getUserIdentity/{id}")
	public fichier getUserIdentity(@Valid @PathVariable String id, Authentication authentication) {
		fichier fichier=null;
		for( fichier f:this.fichierMetier.getAllByIdOrEmailUser(id)) {
			if(f.getType().equals("IDENTITY")) {
				fichier=f;
			}
		}
		return fichier;
	}
	
	
	@PostMapping("/removeF")
	public Message removeF(@RequestBody fichier fichier, Authentication authentication) {
		return this.fichierMetier.delete(fichier);
	}

	@GetMapping("/remove/{id}")
	public Message remove(@PathVariable String id, Authentication authentication) {
		fichier fichier = new fichier();
		fichier.setId(id);
		return this.fichierMetier.delete(fichier);
	}

	@GetMapping("/removeAll/{ids}")
	public Message removeAll(@PathVariable String ids, Authentication authentication) {
		String text = "";
		for (String id : ids.split("&")) {
			fichier user = new fichier();
			user.setId(id);
			this.fichierMetier.delete(user);
			text = text + user.toString() + "  ";
		}

		return new Message("supression de plusieurs:  " + text + "  reussi !!");
	}

	@GetMapping("/getAll")
	public List<fichier> getAllMethodName(Authentication authentication) {
		return fichierMetier.getAllByIdOrEmailUser(authentication.getName());
	}

	@PostMapping("/save")
	public fichier save( @RequestParam("id") String id,@RequestParam("iduser") String iduser,@RequestParam("file") MultipartFile importFile,@RequestParam("type") String type, Authentication authentication) throws IllegalStateException, IOException {
		return fichierMetier.save(id,iduser,importFile,type,authentication);
		
	}
	
}
