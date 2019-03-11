package org.learning.hibernate.shared;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class SCNamingStatergy extends org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
		return new Identifier("SC_" + name.getText(), name.isQuoted());
	}
}
