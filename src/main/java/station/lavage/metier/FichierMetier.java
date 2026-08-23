package station.lavage.metier;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;


import jakarta.validation.Valid;
import station.lavage.model.fichier;
import station.lavage.rest.message.Message;

public interface FichierMetier {

	fichier findById(@Valid String id);

	Message delete(fichier fichier);

	List<fichier> getAllByIdOrEmailUser(String name);

	fichier save(fichier p);

	fichier save(String id, String iduser, MultipartFile importFile, String type, Authentication authentication)
			throws IllegalStateException, IOException;

	boolean deleteFichierOnDisc(String file_path);

}
