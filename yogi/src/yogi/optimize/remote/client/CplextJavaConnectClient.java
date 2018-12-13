package yogi.optimize.remote.client;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

import yogi.property.PropertyManager;

public class CplextJavaConnectClient {
	
	public void createIPSolution(String matrixFileName)  throws CplexJavaConnectException {
		String cplextCommand = null;
		try{
			Object cplextCommandObject = PropertyManager.getPropertyValue(CplexJavaConnectServer.CPLEX_COMMAND_PARAMETER_NAME);
			if ( cplextCommandObject != null ) {
				cplextCommand = cplextCommandObject.toString();
			}
		}
		catch(Throwable t) {
		}
		
		createIPSolution(null, cplextCommand, matrixFileName);
	}
	
	public void createIPSolution(String homeDirectory, String cplexCommand, String matrixFileName) throws CplexJavaConnectException {
		File matrixFile = new File(matrixFileName);
		if ( !matrixFile.exists()) throw new CplexJavaConnectException("Matrix file not found");
		StringBuffer matrixFileContent = null;
		try {
			matrixFileContent = Util.getFileIntoStringBuffer(matrixFileName);
		} catch (IOException e) {
			throw new CplexJavaConnectException("Reading Matrix file failed", e);
		}
		StringBuffer ipsolutionFileContent = getIPSolution(matrixFileContent);
		File ipsolutionFile = new File(matrixFile.getParent() + "/" + CplexJavaConnectServer.IPSOLUTION_FILE);
		try {
			Util.createFileFromStringBuffer(ipsolutionFile, ipsolutionFileContent);
		} catch (IOException e) {
			throw new CplexJavaConnectException("Writing IPSolutio to file failed " + ipsolutionFile.getAbsolutePath() , e);
		}
	}
	
	public StringBuffer getIPSolution(String homeDirectory, String cplexCommand, StringBuffer matrix) throws CplexJavaConnectException {
		CplexJavaConnectServer server = ServerConnectionUtil.getCplexJavaConnectServer();
		try {
			
			
			return server.getIPSolution(homeDirectory, cplexCommand, matrix);
		} catch (RemoteException e) {
			throw new CplexJavaConnectException(e);
		}
	}

	public StringBuffer getIPSolution(StringBuffer matrix) throws CplexJavaConnectException {
		return getIPSolution(null, null, matrix);
	}
	
	public static void main(String[] args) throws Exception {
		CplextJavaConnectClient client = new CplextJavaConnectClient();
		client.createIPSolution("C:/workspace/fam/DlFam/data/AdvanceStationIsolationViolation/output/matrix.mps");
		
	}
}
