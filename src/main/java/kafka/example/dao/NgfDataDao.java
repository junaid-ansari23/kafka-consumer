/**
 * 
 */
package kafka.example.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.example.util.NgfKafkaModel;
import kafka.example.util.NgfKeyModel;

import kafka.example.util.NgfTeamListModel;

/**
 * DAO class for forecast data insertion operation 
 * 
 * @author juansari
 *
 */
public class NgfDataDao {
	private static Logger logger = LoggerFactory.getLogger(NgfDataDao.class);
	
	public static String INSERT_QUERY="insert into DISE_STAGING.NGF_FORECASTED_PERIODS_INT values("
					+"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
					+"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"
					+")";


	public static NgfKafkaModel readKafkaNgmData() {
		System.out.println("hello");
		ObjectMapper mapper = new ObjectMapper();
		//mapper.configure(Feature.class, false);
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		mapper.setDateFormat(df);
		NgfKafkaModel ngfKafkaModel=null;
		String jsonData=null;
		try { 
			InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("edss_sales_ngf_forecast1.json");
			jsonData= IOUtils.toString(inputStream);
			ngfKafkaModel=mapper.readValue(jsonData, NgfKafkaModel.class);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return ngfKafkaModel;
	}

	public static Connection getDatabaseConnection() throws Exception {
		Connection conn=null;
		try {                  
			conn = DriverManager.getConnection(
					"jdbc:sap://eds-hana-stg-01:30013;eds-hana-stg-06:30013;eds-hana-stg-02:30013?databaseName=HIP", "test", "test");                  
		} catch (SQLException e) {
			logger.error("Error while getting JDBC connection", e);
			throw new Exception("Error while getting JDBC connection", e);
		}
		return conn;		
	}
	
	
	/**
	 * Method on perform insertion for flattened kafka messages into staging table.Key1 and Key2 list correspond to product and service data.
	 * By default commit will be disabled and will be done at the end of the insertion of all messages got from current poll.
	 * 
	 * @param ngfModel
	 * @param connection
	 * @throws Exception
	 */
	public static void insertNgfData(NgfTeamListModel ngfModel,Connection connection) throws Exception {
		java.sql.PreparedStatement pstmt=null;
		try {
			if (connection != null) {
				logger.info("Connection to HANA successful!");
				List<NgfKeyModel> key1Model=null;
				List<NgfKeyModel> key2Model=null;
				/*if(ngfModel!=null) {
					key1Model=ngfModel.getKey1();
					key2Model=ngfModel.getKey2();
				}*/				
				pstmt=connection.prepareStatement(INSERT_QUERY);
				
				if(key1Model!=null && !key1Model.isEmpty()) {
					for(NgfKeyModel key1:key1Model) {
						pstmt.setString(1, "");
						pstmt.setString(2, "");
						pstmt.setString(3, "");
						pstmt.setString(4, "");
						pstmt.setString(5, "");
						//pstmt.setDate(6, ngfModel.getFpbDate());
						pstmt.setDate(6, null);
						pstmt.setString(7, ngfModel.getVersion());
						pstmt.setString(8, ngfModel.getCurrencyCode());
						pstmt.setString(9, "usd");
						pstmt.setString(10, ngfModel.getNodeLowestLevelFlag());
						pstmt.setString(11, ngfModel.getLowestLevelFlag());
						pstmt.setString(12, "");

						pstmt.setString(13, key1.getYearName());
						pstmt.setString(14, key1.getQuarterName());
						pstmt.setString(15, key1.getMonthName());
						pstmt.setString(16, key1.getWeekName());
						pstmt.setString(17, key1.getPeriodId());
						pstmt.setString(18, key1.getPeriodComments());
						pstmt.setString(19, key1.getKeyType());
						pstmt.setBigDecimal(20, key1.getFcstNr());
						pstmt.setBigDecimal(21, key1.getFcstNrPrev());
						pstmt.setBigDecimal(22, key1.getFcstAnnualized());
						pstmt.setBigDecimal(23, key1.getFcstAnnualizedPrev());
						pstmt.setBigDecimal(24, key1.getFcstAnnuals());
						pstmt.setBigDecimal(25, key1.getFcstAnnualsPrev());
						pstmt.setBigDecimal(26, key1.getFcstAnnuity());
						pstmt.setBigDecimal(27, key1.getFcstAnnuityPrev());
						pstmt.setBigDecimal(28, key1.getFcstMultiyear());
						pstmt.setBigDecimal(29, key1.getFcstMultiyearPrev());
						pstmt.setBigDecimal(30, key1.getFcstTcv());
						pstmt.setBigDecimal(31, key1.getFcstTcvPrev());
						pstmt.setBigDecimal(32, key1.getFcstTotalCommit());
						pstmt.setBigDecimal(33, key1.getFcstTotalCommitJdg());
						pstmt.setBigDecimal(34, key1.getFcstTotalCommitPrev());
						pstmt.setBigDecimal(35, key1.getFcstNrAdjJdg());
						pstmt.setBigDecimal(36, key1.getFcstAnnualsADjJdg());
						pstmt.setBigDecimal(37, key1.getFcstMultiyearAdjJdg());
						pstmt.setBigDecimal(38, key1.getFcstTotalCommitLow());
						pstmt.setBigDecimal(39, key1.getFcstTotalCommitHigh());
						pstmt.setBigDecimal(40, key1.getFcstNrCmt());
						//pstmt.setBigDecimal(39, key1.getFcstNrCmtDt());
						pstmt.setDate(41, null);						
						pstmt.setBigDecimal(42, key1.getFcstAnnualsCmt());
						//pstmt.setBigDecimal(41, key1.getFcstAnnualsCmtDt());
						pstmt.setDate(43, null);
						pstmt.setBigDecimal(44, key1.getFcstMultiyearCmt());
						//pstmt.setDate(43, key1.getFcstMultiyearCmtDt());
						pstmt.setDate(45, null);
						pstmt.setBigDecimal(46, key1.getUpsideNr());
						pstmt.setBigDecimal(47, key1.getUpsideAnnualized());
						pstmt.setBigDecimal(48, key1.getUpsideAnnuals());
						pstmt.setBigDecimal(49, key1.getUpsideAnnuity());
						pstmt.setBigDecimal(50, key1.getUpsideMultiyear());
						pstmt.setBigDecimal(51, key1.getUpsideTcv());

						pstmt.addBatch();
					}
				}
				//key2 data
				if(key2Model!=null && !key2Model.isEmpty()) {
					for(NgfKeyModel key2:key2Model) {

						pstmt.setString(1, "");
						pstmt.setString(2, "");
						pstmt.setString(3, "");
						pstmt.setString(4, "");
						pstmt.setString(5, "");
						//pstmt.setDate(6, ngfModel.getFpbDate());
						pstmt.setDate(6, null);
						pstmt.setString(7, ngfModel.getVersion());
						pstmt.setString(8, ngfModel.getCurrencyCode());
						pstmt.setString(9, "usd");
						pstmt.setString(10, ngfModel.getNodeLowestLevelFlag());
						pstmt.setString(11, ngfModel.getLowestLevelFlag());
						pstmt.setString(12, "");

						pstmt.setString(13, key2.getYearName());
						pstmt.setString(14, key2.getQuarterName());
						pstmt.setString(15, key2.getMonthName());
						pstmt.setString(16, key2.getWeekName());
						pstmt.setString(17, key2.getPeriodId());
						pstmt.setString(18, key2.getPeriodComments());
						pstmt.setString(19, key2.getKeyType());
						pstmt.setBigDecimal(20, key2.getFcstNr());
						pstmt.setBigDecimal(21, key2.getFcstNrPrev());
						pstmt.setBigDecimal(22, key2.getFcstAnnualized());
						pstmt.setBigDecimal(23, key2.getFcstAnnualizedPrev());
						pstmt.setBigDecimal(24, key2.getFcstAnnuals());
						pstmt.setBigDecimal(25, key2.getFcstAnnualsPrev());
						pstmt.setBigDecimal(26, key2.getFcstAnnuity());
						pstmt.setBigDecimal(27, key2.getFcstAnnuityPrev());
						pstmt.setBigDecimal(28, key2.getFcstMultiyear());
						pstmt.setBigDecimal(29, key2.getFcstMultiyearPrev());
						pstmt.setBigDecimal(30, key2.getFcstTcv());
						pstmt.setBigDecimal(31, key2.getFcstTcvPrev());
						pstmt.setBigDecimal(32, key2.getFcstTotalCommit());
						pstmt.setBigDecimal(33, key2.getFcstTotalCommitJdg());
						pstmt.setBigDecimal(34, key2.getFcstTotalCommitPrev());
						pstmt.setBigDecimal(35, key2.getFcstNrAdjJdg());
						pstmt.setBigDecimal(36, key2.getFcstAnnualsADjJdg());
						pstmt.setBigDecimal(37, key2.getFcstMultiyearAdjJdg());
						pstmt.setBigDecimal(38, key2.getFcstTotalCommitLow());
						pstmt.setBigDecimal(39, key2.getFcstTotalCommitHigh());
						pstmt.setBigDecimal(40, key2.getFcstNrCmt());
						//pstmt.setBigDecimal(39, key2.getFcstNrCmtDt());
						pstmt.setDate(41, null);						
						pstmt.setBigDecimal(42, key2.getFcstAnnualsCmt());
						//pstmt.setBigDecimal(41, key2.getFcstAnnualsCmtDt());
						pstmt.setDate(43, null);
						pstmt.setBigDecimal(44, key2.getFcstMultiyearCmt());
						//pstmt.setDate(43, key2.getFcstMultiyearCmtDt());
						pstmt.setDate(45, null);
						pstmt.setBigDecimal(46, key2.getUpsideNr());
						pstmt.setBigDecimal(47, key2.getUpsideAnnualized());
						pstmt.setBigDecimal(48, key2.getUpsideAnnuals());
						pstmt.setBigDecimal(49, key2.getUpsideAnnuity());
						pstmt.setBigDecimal(50, key2.getUpsideMultiyear());
						pstmt.setBigDecimal(51, key2.getUpsideTcv());

						pstmt.addBatch();
					}
					int[] insertCount=pstmt.executeBatch();
					logger.info("total record inserted: {}",insertCount.length);
					//connection.commit();
					logger.info("rolling back for testing: ");
					//connection.rollback();
				}
			}
		}catch (Exception ex) {			
			throw new Exception("Error while processing kafka message", ex);
		}finally {
			try {
				if(pstmt!=null) {
					pstmt.close();	
				}					
			} catch (SQLException sqx) {
				throw new Exception("Error while closing prepares statement", sqx);
			}								
		}
	}
	
}
