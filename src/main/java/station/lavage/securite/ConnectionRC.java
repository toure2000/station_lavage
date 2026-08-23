package station.lavage.securite;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import station.lavage.dao.UtilisateurDao;
import station.lavage.metier.UtilisateurMetier;
import station.lavage.model.Utilisateur;
import station.lavage.rest.message.Message;

@RestController
@CrossOrigin("*")
public class ConnectionRC {
	
	@Autowired
	UtilisateurMetier userMetier;

	private JWTService jwtService;

	public ConnectionRC(JWTService jwtService) {
		this.jwtService = jwtService;
	}

	@PostMapping("/inscription")
	public Message postMethodName(@RequestBody Utilisateur user) {
		// TODO: process POST request
		String result = "ok";
		user.setPassword(this.passwordEncoder().encode(user.getPassword()));
		this.userMetier.save(user);
		System.out.println(user.toString());
		return new Message(user.getId());
	}

	@GetMapping("/")
	public Message getGoogle(Principal u) {
		return new Message("Welcome, Google user:  "
				+ u.toString().substring(u.toString().indexOf("name=") + 5, u.toString().indexOf("name=") + 20) + "...");
	}
	
	@PostMapping("/login")
	public Message getToken(Authentication authentication) {
		String token = this.jwtService.generateToken(authentication);
		return new Message(token);
	}
	@PostMapping("/tokenLogin")
	public Utilisateur tokenLogin(Authentication authentication) {
		System.out.println(authentication.getName());
		return userMetier.findByIdOrEmail(authentication.getName());
	}

	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}

