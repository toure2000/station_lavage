

/* *********************************************/




package station.lavage.rest.controleur.websocket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import station.lavage.metier.websocket.MessageObjectMetier;
import station.lavage.model.websocket.MessageObject;
import station.lavage.rest.message.Message;

@CrossOrigin("*")
@RestController
@RequestMapping("/MessageObject")
public class MessageObjectRes {
	@Autowired
	MessageObjectMetier paraM;

	@GetMapping("/get/{id}")
	public MessageObject get( @PathVariable String id, Authentication authentication) {
		return this.paraM.findById(id);
	}
	@GetMapping("/getByIdUser/{id}")
	public List<MessageObject> getByIdUser( @PathVariable String id, Authentication authentication) {
		return this.paraM.findByIdUser(id);
	}
	@GetMapping("/remove/{id}")
	public Message remove(@PathVariable String id, Authentication authentication) {
		MessageObject para =new MessageObject();
		para.setId(id);
		return this.paraM.delete(para); 
	}
	@PostMapping("/removepara")
	public Message removepara(@RequestBody MessageObject para,Authentication authentication) {
		return this.paraM.delete(para); 
	}
	@GetMapping("/removeAll/{ids}")
	public Message removeAll(@PathVariable String ids,Authentication authentication) {
		String text="";
		for(String id : ids.split("&")) {
			MessageObject para =new MessageObject();
			para.setId(id);
			text=text+para.toString()+this.paraM.delete(para).getText();
		}
		
		return new Message("supression de plusieurs:  "+text+"  reussi !!") ;
	}

	@GetMapping("/getAll")
	public List<MessageObject> getAllMethodName(Authentication authentication) {
		return this.paraM.findAll();
	}
	
	
	@PostMapping("/save")
	public Message postMethodName(@RequestBody MessageObject para) throws Exception {
		// TODO: process POST request
		this.paraM.save(para);
		System.out.println(para.toString());
		return new Message("enregistrement  reussi !!!");
	}
	

	
	

}

