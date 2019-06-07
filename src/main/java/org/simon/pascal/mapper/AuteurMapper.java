/**
 *
 */
package org.simon.pascal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.simon.pascal.dto.AuteurDto;
import org.simon.pascal.entities.AuteurEntity;

/**
 * The Interface AuteurMapper.
 *
 * @author nsimonin1
 */
@Mapper(componentModel = "spring",implementationName = "AuteurMapperImpl",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS )
public interface AuteurMapper {

	/**
	 * To dto.
	 *
	 * @param entity the entity
	 * @return the auteur dto
	 */
	AuteurDto toDto(AuteurEntity entite);


}
