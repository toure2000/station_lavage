package station.lavage.rest.controleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import station.lavage.metier.LavageMetier;
import station.lavage.model.Utilisateur;
import station.lavage.model.Lavage;
import station.lavage.rest.message.Message;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin("*")
@RequestMapping("/Lavage")
public class LavageRC {
	@Autowired
	LavageMetier lavageM;

	@GetMapping("/getAll")
	public List<Lavage> getMethodName(Authentication authentication) {
		return lavageM.findAll();
	}

	@PostMapping("/save")
	public Message postMethodName(@Valid @RequestBody Lavage entity, Authentication authentication) {
		// TODO: process POST request

		return lavageM.save(entity, authentication.getName());
	}

	@PostMapping("/delete")
	public Message delete(@RequestBody Lavage entity, Authentication authentication) {
		// TODO: process POST request

		return lavageM.delete(entity, authentication.getName());
	}

	@GetMapping("/getallByUser")
	public List<Lavage> getallByUser(@RequestBody Utilisateur user) {
		return this.lavageM.findAllByUser(user);
	}

	@GetMapping("/getallByDate")
	public List<Lavage> getallByDate(@RequestBody String date) {
		return this.lavageM.findAllByDate(date);
	}

}
