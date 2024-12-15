
package com.neomaxer.neospend.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CustomPhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private final static String PREFIX = "t_";

	@Override
	public Identifier toPhysicalColumnName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
		if (identifier == null) {
			return null;
		}

		final String newName = convertToSnakeCase(identifier);

		return Identifier.toIdentifier(newName);

	}

	@Override
	public Identifier toPhysicalTableName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
		if (identifier == null) {
			return null;
		}
		String newName = "";
		if (identifier.getText().startsWith(PREFIX)) {
			newName = convertToSnakeCase(identifier);
		} else {
			newName = PREFIX + convertToSnakeCase(identifier);
		}

		return Identifier.toIdentifier(newName);
	}

	private String convertToSnakeCase(final Identifier identifier) {

		final String regex = "([a-z])([A-Z])";
		final String replacement = "$1_$2";
		final String newName = identifier.getText().replaceAll(regex, replacement).toLowerCase();
		return newName;
	}

}
