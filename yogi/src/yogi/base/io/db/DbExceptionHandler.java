package yogi.base.io.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DbExceptionHandler {
	
	private static Logger logger = Logger.getLogger("yogi.base.io.db.DbExceptionHandler");

	public static DBException createDbException(String query, SQLException sqlException) {
		DBException dbException = new DBException(sqlException);
		StringBuffer errorMsg = new StringBuffer("FAILED QUERY : " + query  + " : ");
		if (dbException.isInvalidColumnType()) {
			errorMsg.append("Invalid Column Type ");
		}
		else if ( dbException.isUniqueConstrainViolation() ) {
			errorMsg.append("Unique Key Constrain Violation");
		}
		logger.severe(errorMsg.toString());
		return dbException;
	}

	public static DBException createDbException(String query, PreparedStatement statement, SQLException sqlException, int parameterIndex, Object parameter) {
		DBException dbException = new DBException(sqlException);
		StringBuffer errorMsg = new StringBuffer("FAILED QUERY : " + query  + " : ");
		if ( dbException.isInvalidColumnIndex() ) {
			errorMsg.append("Invalid Column Index " + parameterIndex + " with value " + parameter);
		} 
		else if (dbException.isInvalidColumnType()) {
			errorMsg.append("error in setting parameter at index " + parameterIndex + " value = " + parameter + " type = " 	+ parameter.getClass().getName());
			try {
				errorMsg.append(" database data type is " + statement.getParameterMetaData().getParameterTypeName(parameterIndex));
			} catch (SQLException e) {
				errorMsg.append(" verify database datatype");
			}
		}
		else if ( dbException.isUniqueConstrainViolation() ) {
				errorMsg.append("Unique Key Constrain Violation ");
		}
		logger.severe(errorMsg.toString());
		return dbException;
	}
	
	public static DBException createDbException(String query, PreparedStatement statement,  SQLException sqlException, Object[] parameters) {
		DBException dbException = new DBException(sqlException);
		StringBuffer errorMsg = new StringBuffer("FAILED QUERY : " + query  + " : ");
		if ( dbException.isInvalidColumnIndex() ) {
			errorMsg.append("Invalid Column Index , parameters trying to insert : ");
			for (int i = 0; i < parameters.length; i++) {
				errorMsg.append( parameters[i] + " : " + parameters[i].getClass().getName() + " : " );
				try {
					errorMsg.append(statement.getParameterMetaData().getParameterTypeName(i + 1) + ";");
				} catch (SQLException e) {
					errorMsg.append("verify database;");
				}
			}
		}
		else if (dbException.isInvalidColumnType()) {
			for (int i = 0; i < parameters.length; i++) {
				errorMsg.append( parameters[i] + " : " + parameters[i].getClass().getName() + " : " );
				try {
					errorMsg.append(statement.getParameterMetaData().getParameterTypeName(i + 1) + ";");
				} catch (SQLException e) {
					errorMsg.append("verify database;");
				}
			}
		}
		else if ( dbException.isUniqueConstrainViolation() ) {
			errorMsg.append("Unique Key Constrain Violation ");
		}
		logger.severe(errorMsg.toString());
		return dbException;
	}

	public static DBException createDbException(String query, ResultSet rst, SQLException sqlException, int numberOfColumns) {
		DBException dbException = new DBException(sqlException);
		StringBuffer errorMsg = new StringBuffer("FAILED QUERY : " + query  + " : ");
		if (dbException.isInvalidColumnIndex()) {
			errorMsg.append("Invalid column index, expecting total columns " + numberOfColumns);
			try {
				errorMsg.append(" actual number of columns query returned " + rst.getMetaData().getColumnCount());
			} catch (SQLException e) {
				errorMsg.append(" verify query and table structure;");
			}
		}
		logger.severe(errorMsg.toString());
		return dbException;
	}
	
	
}
