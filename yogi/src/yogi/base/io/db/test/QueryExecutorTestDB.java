package yogi.base.io.db.test;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.io.db.DBException;
import yogi.base.io.db.QueryExecutor;
import yogi.base.io.db.QueryReader;
import yogi.base.io.resource.ClassPathResource;
import yogi.base.io.resource.db.DbResource;
import yogi.base.io.resource.db.SimpleDbResource;

public class QueryExecutorTestDB extends TestCase {

	private SimpleDbResource dbResource;
	private String tableName; // just here temporarily to track down our hanging  table problem
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		// make a unique test table using ip... just here temporarily to track down our hanging  table problem in JAF Nightly
		//  -- though we could change it to a random number just to isolate everyone running this test from each other. since we don't
		//     have schema isolation for everyone that runs this test.

		tableName = "TESTTABLE_" + InetAddress.getLocalHost().getHostAddress();  
		tableName = tableName.replace(".", "_");

		SimpleDbResource.JDBCUrl = "jdbc:oracle:thin:weblogic/weblogic@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=rmdeva.tul.aa.com)(PORT=1521))(CONNECT_DATA=(SERVER=dedicated)(SERVICE_NAME=ngpd.tul.aa.com)))";
		dbResource = new SimpleDbResource();
		createTable(dbResource);
	}
	
	public void testExecuteQuery() throws Exception //(DbResource resource, String query, int numberOfColumns)
	{
		String query = new QueryReader(new ClassPathResource("selectQuery.txt", this.getClass())).read();
		query = query.replace("TESTTABLE", tableName);
		List<Object[]> data = QueryExecutor.get().executeQuery(this.dbResource, query);
		assertEquals(3, data.size());
		
		query = new QueryReader(new ClassPathResource("selectQuery.txt", this.getClass())).read();
		query = query.replace("TESTTABLE", tableName);
		try {
			data = QueryExecutor.get().executeQuery(this.dbResource, query, 10);
			assertTrue("expecting DB exception thrown", false);
		} catch (DBException e) {
		}
	}
	
	public void testExecuteQuery1() throws Exception // DbResource resource, String query, Object[] parameters, int numberOfColumns) throws DBException {
	{
		String query = new QueryReader(new ClassPathResource("selectQueryParameter.txt", this.getClass())).read();
		query = query.replace("TESTTABLE", tableName);

		List<Object[]> data = QueryExecutor.get().executeQuery(this.dbResource, query, new Object[] { "row2" });
		assertEquals(1, data.size());
	}
		
	public void testExecuteQuery2() throws Exception //(DbResource resource, String query, int numberOfColumns, int fetchSize) throws DBException {
	{
		String query = new QueryReader(new ClassPathResource("selectQuery.txt", this.getClass())).read();
		query = query.replace("TESTTABLE", tableName);

		List<Object[]> data = QueryExecutor.get().executeQuery(this.dbResource, query, 10);
		assertEquals(3, data.size());
	}
	
	public void testExecuteQuery3() throws Exception // (DbResource resource, String query, Object[] parameters, int numberOfColumns, int fetchSize) throws DBException {
	{
		String query = new QueryReader(new ClassPathResource("selectQueryParameter.txt", this.getClass())).read();
		query = query.replace("TESTTABLE", tableName);

		List<Object[]> data = QueryExecutor.get().executeQuery(this.dbResource, query, new Object[] { "row2" }, 10);
		assertEquals(1, data.size());
	}
	
	public void testExecuteUpdate() throws Exception //DbResource resource, String query) throws DBException {
	{
		String query = new QueryReader(new ClassPathResource("insertQuery.txt", this.getClass())).read();
		query = query.replace("TESTTABLE", tableName);

		int count = QueryExecutor.get().executeUpdate(this.dbResource, query);
		assertEquals(1, count );
		
	}
	
	public void testExecuteUpdate1() throws Exception  //(DbResource resource, String query, Object[] parameters) throws DBException {
	{
		String query = new QueryReader(new ClassPathResource("insertQueryParameter.txt", this.getClass())).read();
		query = query.replace("TESTTABLE", tableName);

		int count = QueryExecutor.get().executeUpdate(this.dbResource, query, new Object[] { 4, "row4",  new java.sql.Timestamp(System.currentTimeMillis()) });
		assertEquals(1, count );
	}
	
	public void testExecuteBatchUpdate() throws Exception //(DbResource resource, String query, List<Object[]> batchPrameters) throws DBException {
	{
		List<Object[]> data = new ArrayList<Object[]>();
		data.add(new Object[] { 4,  "row4",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 5,  "row5",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 6,  "row6",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 7,  "row7",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 8,  "row8",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 9,  "row9",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 10, "row10",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 11, "row11",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 12, "row12",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 13, "row13",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 14, "row14",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 15, "row15",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 16, "row16",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 17, "row17",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 18, "row18",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 19, "row19",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 20, "row20",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 21, "row21",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 22, "row22",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 23, "row23",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 24, "row24",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 25, "row25",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 26, "row26",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 27, "row27",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 28, "row28",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 29, "row29",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 30, "row30",  new java.sql.Timestamp(System.currentTimeMillis()) });
		
		String query = new QueryReader(new ClassPathResource("insertQueryParameter.txt", this.getClass())).read();
		query = query.replace("TESTTABLE", tableName);

		int count[] = QueryExecutor.get().executeBatchUpdate(this.dbResource, query, data);
		assertEquals(27, count.length );
		
		query = new QueryReader(new ClassPathResource("selectQuery.txt", this.getClass())).read();
		query = query.replace("TESTTABLE", tableName);
		data = QueryExecutor.get().executeQuery(this.dbResource, query);
		assertEquals(30, data.size());
		
	}
	
	public void testExecuteBatchUpdate2() throws Exception //(DbResource resource, String query, List<Object[]> batchPrameters, int commitCount) throws DBException {
	{
		List<Object[]> data = new ArrayList<Object[]>();
		data.add(new Object[] { 4,  "row4",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 5,  "row5",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 6,  "row6",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 7,  "row7",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 8,  "row8",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 9,  "row9",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 10, "row10",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 11, "row11",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 12, "row12",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 13, "row13",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 14, "row14",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 15, "row15",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 16, "row16",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 17, "row17",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 18, "row18",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 19, "row19",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 20, "row20",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 21, "row21",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 22, "row22",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 23, "row23",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 24, "row24",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 25, "row25",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 26, "row26",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 27, "row27",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 28, "row28",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 29, "row29",  new java.sql.Timestamp(System.currentTimeMillis()) });
		data.add(new Object[] { 30, "row30",  new java.sql.Timestamp(System.currentTimeMillis()) });
		String query = new QueryReader(new ClassPathResource("insertQueryParameter.txt", this.getClass())).read();
		query = query.replace("TESTTABLE", tableName);

		int count[] = QueryExecutor.get().executeBatchUpdate(this.dbResource, query, data, 25);
		assertEquals(27, count.length );
		
		query = new QueryReader(new ClassPathResource("selectQuery.txt", this.getClass())).read();
		query = query.replace("TESTTABLE", tableName);

		data = QueryExecutor.get().executeQuery(this.dbResource, query);
		assertEquals(30, data.size());
		
	}


	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		dropTable(this.dbResource);
		
	}
	
	private void createTable(DbResource resource) throws Exception {
		Connection connection = resource.getConnection();
		String query =  "CREATE TABLE TESTTABLE ( " +
						"   COL_DEC decimal(7) NOT NULL, " + 
						"   COL_VARCHAR varchar2(32) NOT NULL, " +
						"   COL_TIMESTAMP  timestamp NOT NULL " +
						"	) ";
		query = query.replace("TESTTABLE", tableName);

		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.executeUpdate();
		pstmt.close();
		connection.close();
		
		connection = resource.getConnection();
		query =  "INSERT INTO TESTTABLE VALUES( 1,  'row1', {ts '2008-10-15 10:58:06.000'}) ";
		query = query.replace("TESTTABLE", tableName);
		pstmt = connection.prepareStatement(query);
		pstmt.executeUpdate();
		pstmt.close();
		connection.close();
		
		connection = resource.getConnection();
		query =  "INSERT INTO TESTTABLE VALUES( 2,  'row2', {ts '2008-10-15 10:58:06.000'}) ";
		query = query.replace("TESTTABLE", tableName);
		pstmt = connection.prepareStatement(query);
		pstmt.executeUpdate();
		pstmt.close();
		connection.close();
		
		connection = resource.getConnection();
		query =  "INSERT INTO TESTTABLE VALUES ( 3,  'row3', {ts '2008-10-15 10:58:06.000'} ) ";
		query = query.replace("TESTTABLE", tableName);
		pstmt = connection.prepareStatement(query);
		pstmt.executeUpdate();
		pstmt.close();
		connection.close();
	}
	
	private void dropTable(DbResource resource) throws Exception {
		Connection connection = resource.getConnection();
		String query =  "DROP TABLE TESTTABLE ";
		query = query.replace("TESTTABLE", tableName);
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.executeUpdate();
		pstmt.close();
		connection.close();
	}
}
