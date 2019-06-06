/**
 *
 */
package org.simon.pascal.resource;

import java.util.List;

import org.simon.pascal.entities.AuteurEntity;
import org.simon.pascal.service.AuteurService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class AuteurResource.
 *
 * @author nsimonin1
 */
@RestController
@RequestMapping("/api")
public class AuteurResource {

	/** The auteur service. */
	private final AuteurService auteurService;

	/**
	 * Instantiates a new auteur resource.
	 *
	 * @param auteurService the auteur service
	 */
	public AuteurResource(AuteurService auteurService) {
		this.auteurService = auteurService;
	}

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@GetMapping("/auteurs")
	public List<AuteurEntity> findAll(){
		return auteurService.findAll();
	}


}
