/**
 *
 */
package org.simon.pascal.service;

import java.util.List;

import org.simon.pascal.entities.AuteurEntity;
import org.simon.pascal.repositories.AuteurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import graphql.schema.DataFetcher;

/**
 * The Class AuteurService.
 *
 * @author nsimonin1
 */
@Service
@Transactional(readOnly = true)
public class AuteurService {

	/** The auteur repository. */
	public final AuteurRepository auteurRepository;

	/**
	 * Instantiates a new auteur service.
	 *
	 * @param auteurRepository the auteur repository
	 */
	public AuteurService(AuteurRepository auteurRepository) {
		this.auteurRepository = auteurRepository;
	}

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<AuteurEntity> findAll() {
		return auteurRepository.findAll();
	}

	public DataFetcher<AuteurEntity> getAuthorByIdDataFetcher() {
		return dataFetchingEnvironment->{
			final String bookCode = dataFetchingEnvironment.getArgument("code");
			return auteurRepository.findByCode(bookCode).orElse(null);
		};
	}



}
