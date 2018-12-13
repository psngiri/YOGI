package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.base.io.resource.db.DbResource;
import yogi.base.io.resource.db.SimpleDbResource;

public class AirfliteDataTest extends TestCase {

	public void test()
	{
		
	}
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		SimpleDbResource.JDBCUrl = "jdbc:oracle:thin:PRI_APP/PRIdevNgpApp_99@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=rmdeva.tul.aa.com)(PORT=1521))(CONNECT_DATA=(SERVER=dedicated)(SERVICE_NAME=NGPDEV_SVC.TUL.AA.COM)))";
		//SimpleDbResource.JDBCUrl = "jdbc:oracle:thin:xgt/new98usr@samurai.corp.amrcorp.com:1521:icaps1";
	//	SimpleDbResource.JDBCUrl = "jdbc:oracle:thin:xgt/xgt@wizard.corp.amrcorp.com:1521:icapsdev";
	//	SimpleDbResource.JDBCUrl = "jdbc:oracle:thin:jdbc/jdbc@rmodbs04-vip.pdc.aa.com:1521:pubss1";
		TestErrorReporter.start();
	}

	protected void tearDown() throws Exception {
		TestErrorReporter.end();
		Factory.clearAllFactories();
		super.tearDown();
	}
	
	public void test4()
	{
//		String query = "select ENT_SET_CD, ARP_CD  from ICPR_ENTARP where SUBSTR(ENT_SET_CD, 1, 1) = '1' order by ent_set_cd";
//		String query = "select *  from ICPR_GNDTM where  rownum < 20";
//		String query = "select *  from ICPR_GNDTMEXC where  rownum < 20";
//		String query = "select dst_grp_cd, dst_grp_eff_dt, dst_grp_dis_dt, dst_grp_eff_tm, dst_adj_min  from ICPR_dstgrp";
//		String query = "select SOFL_TYP_CD, SOFL_TYP_VR_NR, SOFL_TYP_DSC  from icpr_sofltyp ";
//		String query = "select *  from ICPR_ARPCNST where arp_cd = 'LHR' and RST_TYP_ID='CFW'";
//		String query = "select *  from ICPR_mgtrul where USR_GRP_CD = 'CORPSTD'";
//		String query = "select *  from icpr_gndtm where USR_GRP_CD = 'CORPSTD'";
//		SystemResource queryResource = new FileResource("C:\\eclipse ws JCPA\\Core\\src\\com\\aa\\cp\\core\\emogt\\rule\\io\\db\\query.txt");
//		SystemResource queryResource = new FileResource("C:\\eclipse ws JCPA\\Core\\src\\com\\aa\\cp\\core\\mgt\\rule\\io\\db\\query.txt");
//		QueryReader queryReader = new QueryReader(queryResource);
//		queryReader.addVariable("ScenarioName", "CORPSTD");
//		String query = queryReader.read();
		
//		String query = "select ALN_CD, icpr_acfcfg.ACF_EQP_CD, '10', ' ', ' ', ' ', ' ', ' ',  ACF_CFG_EFF_DT, ACF_CFG_DIS_DT, FST_CLS_SET_CT, BUS_CLS_SET_CT, CH_CLS_SET_CT, AA_EQP_IND, AE_EQP_IND from ICPR_acfcfg, icpr_acf where icpr_acfcfg.ACF_EQP_CD = icpr_acf.ACF_EQP_CD";
//		String query = "select *  from icpr_mgtrul where rownum < 5";
//		String query = "select arp_cd, dst_grp_cd, GMT_OFS  from ICPR_arp";
		String query = "select *  from ICPR_arp";
//		String query = "select *  from pubss.pb_scenario";
//		String query = "select C.aln_cd, A.acf_eqp_cd, A.fle_cd, A.acf_dsc, B.eqp_eqv_typ_cd, A.AA_EQP_IND, A.AE_EQP_IND from ICPR_acf A, icpr_acfctx B, icpr_acfcfg C where A.acf_eqp_cd = B.acf_eqp_cd and A.acf_eqp_cd = C.acf_eqp_cd and B.inf_rpt_src_cd = 'IATA'";
//		String query = "select * from ICPR_fle";
//		String query = "select ARP_CD, ARP_NM, CTRY_ID, ' ', HEM_LAT_CD, LAT_DEG, LAT_MIN, LAT_SEC, HEM_LONG_CD, LONG_DEG, LONG_MIN, LONG_SEC  from ICPR_arp";
		executeQuery(query);
	}

	private void executeQuery(String query) {
		DbResource dbResource = new SimpleDbResource();
		Connection connection = dbResource.getConnection();
		try {
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(query);
			ResultSet rst = pstmt.executeQuery();
			ResultSetMetaData metaData = rst.getMetaData();
			for(int i = 0; i < metaData.getColumnCount(); i ++)
			{
				int columnIndex = i+1;
				System.out.print( metaData.getColumnName(columnIndex) + ";");
			}
			System.out.println();
			while (rst.next())
			{
				for(int i = 0; i < metaData.getColumnCount(); i ++)
				{
					int columnIndex = i+1;
					System.out.print( rst.getString(columnIndex) + ";");
				}
				System.out.println();
			}
			rst.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

/*	public void test2()
	{
		DbResource dbResource = new SimpleDbResource();
		Connection connection = dbResource.getConnection();
		try {
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement("select distinct usr_grp_cd, acf_eqp_cd  from icaps.icpr_usrsubfle order by usr_grp_cd");
//			pstmt = connection.prepareStatement("select A.ENT_SET_CD, A.ARP_CD from ICPR_ENTARP A, ICPR_ENTSET B where SUBSTR(A.ENT_SET_CD, 1, 1) = '1' and A.ENT_SET_CD = B.ENT_SET_CD order by ent_set_cd");
			ResultSet rst = pstmt.executeQuery();
			String previous = null;
			while (rst.next())
			{
				String entity = rst.getString(1).trim();
				if(!entity.equals(previous)) 
				{
					System.out.println(entity);
					previous = entity;
				}
				System.out.println("           " + rst.getString(2));
			}
			rst.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
*//*	public void test1()
	{
		List<String> tableNames = new ArrayList<String>();
		tableNames.add("icpr_arpcnst");
		tableNames.add("icpr_segcnst");
		tableNames.add("icpr_usracfcfg");
		tableNames.add("icpr_staopb");
		tableNames.add("icpr_gtrstb");
		tableNames.add("icpr_mntrst");
		tableNames.add("icpr_gndtm");
		tableNames.add("icpr_gndtmexc");
		tableNames.add("icpr_entset");
		tableNames.add("icpr_entarp");
		tableNames.add("icpr_mgtrul");
		tableNames.add("icpr_flerst");
		tableNames.add("icpr_rteftc");
		tableNames.add("icpr_rteftcvw");
		tableNames.add("icpr_fleevl");
		tableNames.add("icpr_eqpinvstb");
		tableNames.add("icpr_arp");
		tableNames.add("icpr_usrsubfle");
		tableNames.add("icpr_acf");
		tableNames.add("icpr_bdytyp");
		tableNames.add("icpr_fle");
		tableNames.add("icpr_acfcfg");
		tableNames.add("icpr_blktm");
		tableNames.add("icpr_blktmsn");
		tableNames.add("icpr_scenario");
		describeTables(tableNames);

	}*/

/*	public void test3()
	{
		List<String> tableNames = new ArrayList<String>();
		tableNames.add("icpr_aln");
		tableNames.add("icpr_dstgrp");
		describeTables(tableNames);
	}
*/	
//	private void describeTables(List<String> tableNames) {
//		DbResource dbResource = new SimpleDbResource();
//		Connection connection = dbResource.getConnection();
//		for(String tableName: tableNames)
//		{
//			describe(tableName, connection);
//		}
//		try {
//			connection.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

//	private void describe(String tableName, Connection connection) {
//		try {
//			PreparedStatement pstmt;
//			pstmt = connection.prepareStatement(String.format("select * from %1$s where rownum < 1", tableName));
//			ResultSet rst = pstmt.executeQuery();
//			if (rst.next())
//			{
//				System.out.println(rst.getString(1));
//			}
//			ResultSetMetaData metaData = rst.getMetaData();
//			System.out.println(String.format("%1$s        %2$s ", tableName.toUpperCase(), metaData.getColumnCount()));
//			for(int i = 0; i < metaData.getColumnCount(); i ++)
//			{
//				int columnIndex = i+1;
//				System.out.println(String.format("                 %1$s              %3$s", metaData.getColumnName(columnIndex), metaData.getColumnClassName(columnIndex), metaData.getColumnTypeName(columnIndex)));
//			}
//			rst.close();
//			pstmt.close();
//		} catch (SQLException e) {
//			System.out.println(tableName + " ----- " + e.getMessage());
//		}
//	}
}
