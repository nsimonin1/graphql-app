/**
 *
 */
package org.simon.pascal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.simon.pascal.dto.BookInput;
import org.simon.pascal.entities.BookEntity;

/**
 * The Interface AuteurMapper.
 *
 * @author nsimonin1
 */
@Mapper(componentModel = "spring",implementationName = "BookMapperImpl",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS )
public interface BookMapper {

	BookEntity toDto(BookInput input);


}
