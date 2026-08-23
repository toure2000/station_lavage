package station.lavage.rest.controleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import station.lavage.metier.Type_lavageMetier;
import station.lavage.model.Type_lavage;
import station.lavage.rest.message.Message;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin("*")
@RequestMapping("/Type_lavage")
public class Type_lavageRC{
    @Autowired
    Type_lavageMetier type_lavageMetier;
    @GetMapping("/getAll")
    public List<Type_lavage> getMethodName(Authentication authentication) {
        return type_lavageMetier.findAll();
    }
    
    @PostMapping("/save")
    public Message postMethodName(@Valid @RequestBody Type_lavage entity,Authentication authentication) {
        //TODO: process POST request
        return type_lavageMetier.save(entity,authentication.getName());
    }
    @PostMapping("/delete")
    public Message delete(@RequestBody Type_lavage entity,Authentication authentication) {
        //TODO: process POST request
        return type_lavageMetier.delete(entity,authentication.getName());
    }
    
}
