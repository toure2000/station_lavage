package station.lavage.rest.controleur;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import jakarta.validation.Valid;
import station.lavage.email.emaill;
import station.lavage.metier.UtilisateurMetier;
import station.lavage.model.Utilisateur;
import station.lavage.rest.message.Message;

@CrossOrigin("*")
@RestController
@RequestMapping("/utilisateur")
public class UtilisateurRC {
	@Autowired
	UtilisateurMetier userM;

	@GetMapping("/get/{id}")
	public Utilisateur get(@Valid @PathVariable String id,Authentication authentication) {
		return this.userM.findById(id);
	}
	@GetMapping("/getByEmail/{email}")
	public Utilisateur getByEmail(@PathVariable String email,Authentication authentication) {
		return this.userM.findByIdOrEmail(email);
	}
	@PostMapping("/getByListId")
	public List<Utilisateur> getByListId(@RequestBody ArrayList<String> listId,Authentication authentication) {
		return this.userM.getByListId(listId);
	}
	@GetMapping("/getByIdEmail/{idEmail}")
	public Utilisateur getByIdEmail(@PathVariable String idEmail,Authentication authentication) {
		return userM.findByIdOrEmail(idEmail);
	}
	@GetMapping("/getByRole/{role}")
	public List<Utilisateur> getByRole(@PathVariable String role,Authentication authentication) {
		return userM.getByRole(role);
	}
	@GetMapping("/remove/{id}")
	public Message remove(@PathVariable String id,Authentication authentication) {
		Utilisateur user =new Utilisateur();
		user.setId(id);
		return this.userM.delete(user); 
	}
	@PostMapping("/removeuser")
	public Message removeuser(@RequestBody Utilisateur user,Authentication authentication) {
		return this.userM.delete(user); 
	}
	@GetMapping("/removeAll/{ids}")
	public Message removeAll(@PathVariable String ids,Authentication authentication) {
		String text="";
		for(String id : ids.split("&")) {
			Utilisateur user =new Utilisateur();
			user.setId(id);
			text=text+user.toString()+this.userM.delete(user).getText();
		}
		
		return new Message("supression de plusieurs:  "+text+"  reussi !!") ;
	}

	@GetMapping("/getAll")
	public List<Utilisateur> getAllMethodName(Authentication authentication) {
		return this.userM.findAll();
	}
	
	
	@PostMapping("/save")
	public Message postMethodName(@RequestBody Utilisateur user) throws Exception {
		// TODO: process POST request
		return  this.userM.save(user);
	}
	@PutMapping("/update/{id}")
	public Message update( @PathVariable String id,@RequestBody Utilisateur med) throws Exception {
		// TODO: process POST request	
		med.setPassword(this.passwordEncoder().encode(med.getPassword()));
		
		System.out.println(med.toString());
		return this.userM.update(id, med);
	}
	
	@PostMapping("sendEmail")
	public Message sendEmail(@RequestBody Message Message) throws Exception {
		// TODO: process POST request
		emaill.sendEmail(Message.getText(), Message.getText2(), Message.getText3());
		return new Message("Message ENVOYER!!!");
	}
	@PostMapping("/saveimprofile")
	public Message saveimprofile(@RequestParam("file") MultipartFile improfile,@RequestParam("id") String iduser,Authentication authentication) throws Exception {
		// TODO: process POST request
		Utilisateur user=userM.findByIdOrEmail(iduser);
		improfile.transferTo(new File("C:\\Users\\HP\\Desktop\\gestionCM\\src\\main\\webapp\\image\\"+user.getId()+".jpg"));
		user.setPicture("image/"+user.getId()+".jpg");
		userM.save(user);
		return new Message("enregistrement  reussi !!!");
	}
	
//	@PostMapping("/saveUserWidthimprofile")
//	public Message saveUserWidthimprofile(@RequestParam("file") MultipartFile improfile,@RequestBody Utilisateur user,Authentication authentication) throws Exception {
//		// TODO: process POST request
//		improfile.transferTo(new File("C:\\Users\\sacko\\OneDrive\\Bureau\\PFE\\BACKEND\\gestionCM\\src\\main\\webapp\\image\\"+user.getId()+".jpg"));
//		user.setPicture("image/"+user.getId()+".jpg");
//		userM.save(user);
//		return new Message("enregistrement  reussi !!!");
//	}
	
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
