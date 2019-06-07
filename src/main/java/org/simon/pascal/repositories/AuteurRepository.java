/**
 *
 */
package org.simon.pascal.repositories;

import java.util.Optional;

import org.simon.pascal.entities.AuteurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * The Interface AuteurRepository.
 *
 * @author nsimonin1
 */
public interface AuteurRepository extends JpaRepository<AuteurEntity, Integer>{

	/**
	 * Find by code.
	 *
	 * @param code the code
	 * @return the auteur entity
	 */
	Optional<AuteurEntity> findByCode(String code);

	@Query("SELECT MAX(entity.code) FROM  AuteurEntity entity ")
	String findLastCode();

}
