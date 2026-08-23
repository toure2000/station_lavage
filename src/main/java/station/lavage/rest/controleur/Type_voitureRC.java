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

import jakarta.validation.Valid;
import station.lavage.metier.Type_lavageMetier;
import station.lavage.metier.Type_voitureMetier;
import station.lavage.model.Type_voiture;
import station.lavage.rest.message.Message;

@RestController
@CrossOrigin("*")
@RequestMapping("/Type_voiture")
public class Type_voitureRC{
   @Autowired
   Type_voitureMetier type_voituremetier ;
   
   @GetMapping("/getAll")
  public List<Type_voiture> getMethodName(Authentication authentication) {
      return type_voituremetier.findAll();
  }
  
  @PostMapping("/save")
  public Message postMethodName( @RequestBody Type_voiture entity,Authentication authentication) {
      //TODO: process POST request
      
	  return type_voituremetier.save(entity,authentication.getName());
  }
  @PostMapping("/delete")
  public Message delete(@RequestBody Type_voiture entity,Authentication authentication) {
      //TODO: process POST request
      
	  return type_voituremetier.delete(entity,authentication.getName());
  }
  
  
}
