package station.lavage.rest.controleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import station.lavage.metier.MaterielMetier;
import station.lavage.model.Materiel;
import station.lavage.rest.message.Message;
@RestController
@CrossOrigin("*")
@RequestMapping("/Materiel")
public class MaterielRC {
	@Autowired
	  private MaterielMetier Materielmetier;
	  
		@GetMapping("/getAll")
		public List<Materiel> getMethodName(Authentication authentication) {
			return Materielmetier.findAll();
		}
		@PostMapping("/save")
		public Message postMethodName(@RequestBody Materiel entity,Authentication authentication) {
			//TODO: process POST request
			
			return Materielmetier.save(entity,authentication.getName());
		}
		@PostMapping("/delete")
		public Message delete(@RequestBody Materiel entity,Authentication authentication) {
			//TODO: process POST request
			
			return Materielmetier.delete(entity,authentication.getName());
		}
}
