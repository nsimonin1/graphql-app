/**
 *
 */
package org.simon.pascal.repositories;

import java.util.Optional;

import org.simon.pascal.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Interface BookRepository.
 *
 * @author nsimonin1
 */
public interface BookRepository extends JpaRepository<BookEntity, Integer>{

	/**
	 * Find by code.
	 *
	 * @param code the code
	 * @return the book entity
	 */
	Optional<BookEntity> findByCode(String code);
}
