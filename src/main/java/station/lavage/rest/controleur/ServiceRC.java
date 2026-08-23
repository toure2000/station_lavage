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

import station.lavage.metier.ServiceMetier;
import station.lavage.model.Services;
import station.lavage.rest.message.Message;

@RestController
@CrossOrigin("*")
@RequestMapping("/Services")
public class ServiceRC {
  @Autowired
  private ServiceMetier Servicemetier;
  
	@GetMapping("/getAll")
	public List<Services> getMethodName(Authentication authentication) {
		return Servicemetier.findAll();
	}
	@PostMapping("/save")
	public Message postMethodName(@RequestBody Services entity,Authentication authentication) {
		//TODO: process POST request
		
		return Servicemetier.save(entity,authentication.getName());
	}
	@PostMapping("/delete")
	public Message delete(@RequestBody Services entity,Authentication authentication) {
		//TODO: process POST request
		
		return Servicemetier.delete(entity,authentication.getName());
	}
}
