package station.lavage.google.auth;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
import station.lavage.rest.message.Message;
import station.lavage.securite.JWTService;

@RestController
@CrossOrigin("*")
@RequestMapping("/googleauthrest")
public class GoogleAuthRest {
	
	//externe service jwt de l'application user;
	private JWTService jwtService;
	@Autowired
	private UtilisateurDao userDAO ;

	public GoogleAuthRest(JWTService jwtService) {
		this.jwtService = jwtService;
	}
	
	
	//lien client d'authentification
	private String urlAngular="http://localhost:4200/";
	private String nomcomponentGoogleClient="ConnectionGoogleSuccesComponent";
	
	//declaration des variables
	private String urlredirect=urlAngular+nomcomponentGoogleClient;//ex:"http://localhost:4200/GoogleAuthConnectionComponent"
	private String idToken="550723132061-vb5fjtdh1h0dk5315fovli2vqu92area.apps.googleusercontent.com";
	private String secretToken="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";
	
	//genere le lien de redirection et l'envoie au client angular
	@GetMapping("/getLienGoogleAuth")
	public Message getLienGoogleAuth() {
		 String url = new GoogleAuthorizationCodeRequestUrl(
				    idToken,
		            urlredirect,
		            Arrays.asList("https://www.googleapis.com/auth/userinfo.email",
		                    "https://www.googleapis.com/auth/userinfo.profile"))
		            .setState("/profile").build();
		return new Message(url);
	}
	
	//Recupere le code token a partir de la request apres authentification du client
	 @GetMapping("/getTokenUser")
	 public Message getTokenGoogle(HttpServletRequest request,HttpServletResponse response) throws IOException {
		 try {
		    	String code=(String) request.getParameter("code");
		        GoogleTokenResponse respons = new GoogleAuthorizationCodeTokenRequest(
		            new NetHttpTransport(), new GsonFactory(),
		            idToken, secretToken,
		            code, urlredirect)
		            .execute();
		        System.out.println("Access token: "+respons.toPrettyString());
		        System.out.println("Access token: "+respons.getIdToken());
		        return new Message(respons.getIdToken());
		      } catch (TokenResponseException e) {
		        if (e.getDetails() != null) {
		          System.err.println("Error: " + e.getDetails().getError());
		          if (e.getDetails().getErrorDescription() != null) {
		            System.err.println(e.getDetails().getErrorDescription());
		          }
		          if (e.getDetails().getErrorUri() != null) {
		            System.err.println(e.getDetails().getErrorUri());
		          }
		        } else {
		          System.err.println(e.getMessage());
		        }
		        return new Message(null);
		      }
	 }
	 
	 @GetMapping("/getUserToken")
	 public UserToken getUserTokenGoogle(HttpServletRequest request,HttpServletResponse response) throws Exception {
		 try {
		    	String code=(String) request.getParameter("code");
		    	String operation_encoure=(String) request.getParameter("operation_encoure");
		    	
		        GoogleTokenResponse respons = new GoogleAuthorizationCodeTokenRequest(
		            new NetHttpTransport(), new GsonFactory(),
		            idToken, secretToken,
		            code, urlredirect)
		            .execute();
		        System.out.println("Access token: "+respons.toPrettyString());
		        UserToken u=UserToken.getUserToken(respons.getIdToken());
		        u.setOperation_encoure(operation_encoure);
		        
		        
		        /**
		         * action supplementaire:
		         * connection:
		         *    generer le token de l'utilisateur;
		         * inscription:
		         *    aucunne acction supplementaire
		         */
		        if(operation_encoure.equals("connection")) {
		        	if(userDAO.findByIdOrEmail(u.getEmail(),u.getEmail()).size()>0) {
		        		String token=jwtService.generateTokenByIdOrEmail(u.getEmail());
				        u.setToken_jwt(token);
		        	}else {
		        		
				        u.setToken_jwt(null);
		        	}
		        	
		        }else if(operation_encoure.equals("inscription")) {
		        	if(userDAO.findByIdOrEmail(u.getEmail(),u.getEmail()).size()<=0) {
		        		System.out.println("Inscription encoure");
		        	}else {
		        		String token=jwtService.generateTokenByIdOrEmail(u.getEmail());
				        u.setToken_jwt(token);
		        	}
		        	
		        }else {
		        	throw new Exception("L'operation connection / inscription doit etre precisé dans la requet");
		        }
		        
		        System.out.println("Access token: "+u.toString());
		        return u;
		      } catch (TokenResponseException e) {
		        if (e.getDetails() != null) {
		          System.err.println("Error: " + e.getDetails().getError());
		          if (e.getDetails().getErrorDescription() != null) {
		            System.err.println(e.getDetails().getErrorDescription());
		          }
		          if (e.getDetails().getErrorUri() != null) {
		            System.err.println(e.getDetails().getErrorUri());
		          }
		        } else {
		          System.err.println(e.getMessage());
		        }
		        return null;
		      }
	 }
	 
	 
	 
}
