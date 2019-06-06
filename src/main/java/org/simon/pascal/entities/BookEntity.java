/**
 *
 */
package org.simon.pascal.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author nsimonin1
 *
 */
@Table(name = "book")
@Entity
@Data
public class BookEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String code;
	private String name;
	private Integer pageCount;
	@ManyToOne
	private AuteurEntity author;


}
