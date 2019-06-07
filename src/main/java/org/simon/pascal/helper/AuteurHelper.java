/**
 *
 */
package org.simon.pascal.helper;

import org.simon.pascal.repositories.AuteurRepository;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

/**
 * @author nsimonin1
 *
 */
@Component
public class AuteurHelper {
	private final AuteurRepository auteurRepository;

	/**
	 * @param auteurRepository
	 */
	public AuteurHelper(AuteurRepository auteurRepository) {
		this.auteurRepository = auteurRepository;
	}

	public String getLastCode() {
		String code=auteurRepository.findLastCode();
		if(code == null) {
			code = "00000";
		}
		final Integer ordre = Integer.valueOf(code) + 1;

		return Strings.padStart(ordre.toString(), code.length(), '0');
	}
}
