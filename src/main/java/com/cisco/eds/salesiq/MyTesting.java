/**
 * 
 */
package com.cisco.eds.salesiq;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;

import com.cisco.eds.salesiq.dao.NgfDataDao;
import com.cisco.eds.salesiq.util.NgfKeyModel;

/**
 * @author juansari
 *
 */
public class MyTesting {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn=null;
		DataSource ds=null;
		java.sql.PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			//conn=getDatabaseConnection();
			//NgfDataDao.readKafkaNgmData();
			
							
			/*pstmt=conn.prepareStatement("select * from DISE_STAGING.NGF_FORECASTED_PERIODS_INT");
			pstmt.executeQuery();*/
			ds=getDataSource("jdbc:sap://eds-hana-stg-01:30013?databaseName=HIP", "DISE", "g02DISE02",3);
			 //ds=getDataSource("jdbc:sap://eds-hana-stg-01:30013/HIP", "DISE", "g02DISE02",10);
			 conn=ds.getConnection();
			 pstmt=conn.prepareStatement("select * from dummy");
			 rs=pstmt.executeQuery();
			System.out.println("conn: "+ds);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ds!=null) {
				ds.close();	
			}
			
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	public static Connection getDatabaseConnection() throws Exception {
		Connection conn=null;
		try {                  
			/*conn = DriverManager.getConnection(
					"jdbc:sap://eds-hana-stg-01:30013/HIP", "DISE", "g02DISE02");*/
			conn = DriverManager.getConnection(
					"jdbc:sap://eds-hana-stg-01:30013?databaseName=HIP", "DISE", "g02DISE02");
		} catch (SQLException e) {
			//logger.error("Error while getting JDBC connection", e);
			throw new Exception("Error while getting JDBC connection", e);
		}
		return conn;		
	}
	
	public static DataSource getDataSource(String url, String user, String password, int poolSize) {
		DataSource dataSource = new DataSource();
		try {
			System.out.println(url+"  "+url);		
			//DataSource dataSource = new DataSource();
			/*switch (type) {
			case HANA:
				dataSource.setDriverClassName("com.sap.db.jdbc.Driver");
			    //dataSource.setValidationQuery("SELECT * FROM DUMMY");
			    dataSource.setInitSQL("SELECT dbtimezone FROM DUAL");
			break;
			case TERADATA:
				dataSource.setDriverClassName("com.teradata.jdbc.TeraDriver");
				dataSource.setValidationQuery("SELECT 1");
			break;
			default:
				throw new RuntimeException("Unknown data source: "+url);
			}*/
			
			dataSource.setDriverClassName("com.sap.db.jdbc.Driver");
		    dataSource.setValidationQuery("SELECT * FROM DUMMY");
		    //dataSource.setInitSQL("SELECT dbtimezone FROM DUAL");
		    
		    dataSource.setUrl(url);
		    dataSource.setUsername(user);
		    dataSource.setPassword(password);
		    dataSource.setTestOnReturn(true); 
		    dataSource.setMaxActive(poolSize); // loyola: 50, reverted till we find connection leak; archds: moved poolSize to properties file
		    dataSource.setTimeBetweenEvictionRunsMillis(5*60*1000); // 3 minutes
		    dataSource.setValidationInterval(2*60*1000); // 2 minutes
		    dataSource.setValidationQueryTimeout(10*1000); //10 sec
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	    
	    return dataSource;
	    
	}

}
