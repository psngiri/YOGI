package yogi.report.server.command;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import yogi.base.app.ErrorReporter;
import yogi.base.io.FileToStringListReader;
import yogi.base.io.db.DBException;
import yogi.base.io.db.QueryExecutor;
import yogi.remote.client.app.CommandAdapter;
import yogi.server.util.BaseServerAssistant;

//test 
public class RemoteImportCommand extends CommandAdapter<Integer> {

	private static final long serialVersionUID = -6849748273507429406L;
	private String reportDataFileName;
	private String scriptName;

	private static String INSERT_RECOMMENDATION_SET_INFO = "INSERT INTO CPT_RECOMMENDATION_SET (RECO_SET_ID, CRE_ANL_ID, CRE_DTM,RECO_SET_STAT_CD, RECO_SET_NM, FR_RECO_CT, INT_DOM_FLAG, NO_TAX) VALUES (?, ?, TO_DATE(?,'MM/DD/YYYY'), ?, ?, TO_DATE(?,'MM/DD/YYYY'), ?, ?, ?, ?)";
	private static String INSERT_RECOMMENDATIONS = "INSERT INTO CPT_RECOMMENDATION(RECO_SET_ID,CRE_DT, FR_ACT_CD, ORG_CTY_CD, DEST_CTY_CD, FR_BSS_CD, CURR_CD, NEW_AMT, FN_CD, RTG_NR) VALUES(?,TO_DATE(?,'MM/DD/YYYY'),?,?,?,?,?,?,?,?)";
	
					
	public static String STATUS_CODE = "0";
	public static String BATCH_COMMENT = "CSV Recommendations";
	private String indicator="D";	
	public static String RecommendationNoTax = "0";		
	private static String GENERATE_NEXT_BATCH_ID = "SELECT PRIQ_TRNS_BCH_ID.NEXTVAL FROM DUAL";
	
//	public RemoteImportCommand(String reportDataFileName, String scriptName) {
//		super();
//		this.reportDataFileName = reportDataFileName;
//		this.scriptName = scriptName;
//	}

	public RemoteImportCommand() {
		super(null);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args){
		RemoteImportCommand temp = new RemoteImportCommand();
		System.out.println(temp.execute());
	}
	
	public String getReportDataFileName() {
		return reportDataFileName;
	}

	public String getScriptName() {
		return scriptName;
	}

	public String getIndicator() {
		return indicator;
	}

	@Override
	public Integer execute(){
		//String localFileName = ApplicationProperties.OutputLocation+UserQueryAssistant.ReportQueriesLocation+"/Import/test.csv";
		//ScriptExecuteProcessor scriptExecuteProcessor = new ScriptExecuteProcessor(scriptName,localFileName, reportDataFileName);
		//Executor.get().execute(scriptExecuteProcessor);
		FileToStringListReader reader = new FileToStringListReader("C:\\workspace\\JAF\\src\\com\\aa\\cp\\report\\server\\command\\test.txt",0);
		List<String> recs = reader.read();
		String header = recs.get(0);
		List<String> headerNames = Arrays.asList(header.split(","));
		int actionIndex = headerNames.indexOf("\"Action\"");
		int originIndex = headerNames.indexOf("\"Origin\"");
		int destinationIndex = headerNames.indexOf("\"Destination\"");
		int fareBaisIndex = headerNames.indexOf("\"FareBasis\"");
		int currencyIndex = headerNames.indexOf("\"Currency\"");
		int amountIndex = headerNames.indexOf("\"Amount\"");
		int footNoteIndex = headerNames.indexOf("\"FootNote\"");
		int routingNumberIndex = headerNames.indexOf("\"Routing\"");
		//List<String> records = reader.read();
		List<Object[]> recommendations = new ArrayList<Object[]>(recs.size()-1);
		//Object batchId = getBatchId();
		Object batchId =1;
		for(int i=1 ; i<recs.size();i++){
			
			List<String> recommendation = Arrays.asList(recs.get(i).split(","));
			recommendations.add(new Object[] {batchId,System.currentTimeMillis(), recommendation.get(actionIndex),	recommendation.get(originIndex), recommendation.get(destinationIndex), recommendation.get(fareBaisIndex), recommendation.get(currencyIndex), recommendation.get(amountIndex), recommendation.get(footNoteIndex),recommendation.get(routingNumberIndex)});
		}
		try {
			QueryExecutor.get().executeUpdate(BaseServerAssistant.get().getDbResource(), INSERT_RECOMMENDATION_SET_INFO, new Object[] {
//				RECO_SET_ID, CRE_ANL_ID, CRE_DTM,RECO_SET_STAT_CD, RECO_SET_NM, FR_RECO_CT, INT_DOM_FLAG, NO_TAX
				batchId, "AA627674", System.currentTimeMillis(),STATUS_CODE,"CSV "+batchId.toString(),recommendations.size(),indicator,RecommendationNoTax
			});
			QueryExecutor.get().executeBatchUpdate(BaseServerAssistant.get().getDbResource(), INSERT_RECOMMENDATIONS, recommendations);
		} catch (DBException e) {
			e.printStackTrace();
		}		
		return 1;
	}
	
	@SuppressWarnings("unused")
	private Object getBatchId() {
			List<Integer> rtnValue = null;
			try {
				List<Object[]> batchIds = QueryExecutor.get().executeQuery(BaseServerAssistant.get().getDbResource(), GENERATE_NEXT_BATCH_ID);
				rtnValue = new ArrayList<Integer>(batchIds.size());
				for(Object[] row: batchIds)
				{
					rtnValue.add(((BigDecimal)row[0]).intValue());
				} 
			} catch (Exception ex) {
				ErrorReporter.get().error("Exception While Generating Next Batch Id Using PRIQ_TRNS_BCH_ID.NEXTVAL: " + ex);
			}
			return rtnValue.get(0).intValue();
	}


}
