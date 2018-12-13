package yogi.base.io.db;

import java.sql.SQLException;

import yogi.base.io.db.oracle.OracleErrorCodes;

public class DBException extends Exception {
	
	private SQLException sqlException;
	
	private static final long serialVersionUID = 6483017245564225850L;
	
	public DBException(SQLException sqlException){
		super(sqlException);
		this.sqlException = sqlException;
	}

	public DBException(String message, SQLException sqlException){
		super(message, sqlException);
		this.sqlException = sqlException;
	}
	
	public boolean isInvalidColumnIndex() {
		if ( this.sqlException.getErrorCode() == OracleErrorCodes.INVALID_COLUMN_INDEX ) return true;
		return false;
	}
	
	public boolean isInvalidColumnType() {
		if ( this.sqlException.getErrorCode() == OracleErrorCodes.INVALID_COLUMN_TYPE ) return true;
		return false;
	}
	
	public boolean isInvalidColumnName() {
		if ( this.sqlException.getErrorCode() == OracleErrorCodes.INVALID_COLUMN_NAME ) return true;
		return false;
	}
	
	public boolean isUniqueConstrainViolation() {
		if ( this.sqlException.getErrorCode() == OracleErrorCodes.UNIQUE_CONSTRAIN_VIOLATION ) return true;
		return false;
	}
	
	public boolean isIntegrityConstraintViolation() {//Foreign Key Violation
		if ( this.sqlException.getErrorCode() == OracleErrorCodes.INTEGRITY_CONSTRAIN_VIOLATION) return true;
		return false;
	}
}
