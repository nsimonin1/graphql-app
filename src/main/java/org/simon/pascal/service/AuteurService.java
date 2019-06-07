/**
 *
 */
package org.simon.pascal.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.simon.pascal.dto.AuteurDto;
import org.simon.pascal.entities.AuteurEntity;
import org.simon.pascal.helper.AuteurHelper;
import org.simon.pascal.mapper.AuteurMapper;
import org.simon.pascal.repositories.AuteurRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

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
	private final AuteurHelper auteurHelper;
	private final AuteurMapper auteurMapper;
	private final ObjectMapper mapper = new ObjectMapper();




	/**
	 * @param auteurRepository
	 * @param auteurHelper
	 * @param auteurMapper
	 */
	public AuteurService(AuteurRepository auteurRepository, AuteurHelper auteurHelper, AuteurMapper auteurMapper) {
		this.auteurRepository = auteurRepository;
		this.auteurHelper = auteurHelper;
		this.auteurMapper = auteurMapper;
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

	@Transactional
	public DataFetcher<AuteurDto> createAuteur() {
		return dataFetchingEnvironment->{
			final Map<String, Object> args=dataFetchingEnvironment.getArguments();
			final AuteurEntity entity=mapper.convertValue(args, AuteurEntity.class);
			final String newCode=auteurHelper.getLastCode();
			entity.setCode(newCode);
			return auteurMapper.toDto(auteurRepository.save(entity));
		};
	}

	public DataFetcher<List<AuteurDto>> getAll() {
		return dataFetchingEnvironment->{
			final Integer page=dataFetchingEnvironment.getArgument("page");
			final Integer size=dataFetchingEnvironment.getArgument("size");
			final Pageable pageable = PageRequest.of(page, size);
			return auteurRepository.findAll(pageable).stream().map(auteurMapper::toDto)
					.collect(Collectors.toList());
		};
	}



}
