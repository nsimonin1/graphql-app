/**
 *
 */
package org.simon.pascal.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author nsimonin1
 *
 */
@Table(name = "auteur")
@Entity
@Data
public class AuteurEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String code;
	private String firstName;
	private String lastName;


}
