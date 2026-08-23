package station.lavage.securite;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import station.lavage.dao.UtilisateurDao;
import station.lavage.metier.UtilisateurMetier;
import station.lavage.model.Utilisateur;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UtilisateurMetier userMetier;
   
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur user = userMetier.findByIdOrEmail(username);
		System.out.println(user.toString());
		User springUser = new User(user.getId(), user.getPassword(),getGrantedAuthorities(user.getRole()));
		return springUser;
	}

	private List<GrantedAuthority> getGrantedAuthorities(String role) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		return authorities;
	}
}
