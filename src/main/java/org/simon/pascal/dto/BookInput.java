/**
 *
 */
package org.simon.pascal.dto;

import lombok.Data;

/**
 * @author nsimonin1
 *
 */

@Data
public class BookInput {
	private String name;
	private Integer pageCount;
	private String authorCode;


}
