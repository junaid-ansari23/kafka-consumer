package kafka.example.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * This class manages Hana Database Connection /Connection pool. Underlying
 * connection pool implementation -Hikari.
 * 
 * 
 *
 */
public class DBCPManager {

	private static final Logger logger = LoggerFactory.getLogger(DBCPManager.class);

	private static final String DB_CONNECTION_FILE = "database-connection.properties";
	private static final String DB_CONNECTION_FILE_DEV = "database-connection-dev.properties";
	private static final String DB_CONNECTION_FILE_STAGE = "database-connection-stage.properties";
	private static final String DB_CONNECTION_FILE_PROD = "database-connection-prod.properties";

	private static HikariDataSource dataSource;

	private DBCPManager() {    	
	}

	/**
	 * Initializes DBCP Manager.
	 * 
	 * @throws Exception
	 */
	public static void initialize() throws Exception {

		String environment = Util.getLifeCycle();
		String dbFile=null;
		if("dev".equalsIgnoreCase(environment)) {
			dbFile=DB_CONNECTION_FILE_DEV;
		}else if("stage".equalsIgnoreCase(environment)){
			dbFile=DB_CONNECTION_FILE_STAGE;	
		}else if("prod".equalsIgnoreCase(environment)){
			dbFile=DB_CONNECTION_FILE_PROD;	
		}else {
			//local
			dbFile=DB_CONNECTION_FILE;
		}
		// Get connection properties
		Properties dbConnProperties = Util.getProperties(dbFile);
		HikariConfig hikariConfig = new HikariConfig(dbConnProperties);
		dataSource = new HikariDataSource(hikariConfig);        
	}

	public HikariDataSource dataSource(){
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
		hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/spring-test"); 
		hikariConfig.setUsername("root");
		hikariConfig.setPassword("admin");

		hikariConfig.setMaximumPoolSize(5);
		hikariConfig.setMinimumIdle(2);
		hikariConfig.setIdleTimeout(30000);
		hikariConfig.setAutoCommit(false);
		//hikariConfig.setConnectionTestQuery("SELECT 1");
		hikariConfig.setPoolName("salesiqKafkaPool");

		hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");

		HikariDataSource dataSource = new HikariDataSource(hikariConfig);

		return dataSource;
	}    
	/**
	 * Gets a connection from the pool if the DBCP instance was instantiated
	 * with poolable option. Underlying data source used for poolable option -
	 * BasicDataSource. If identified as non-poolable manager, the method
	 * creates a new connection based on the properties identified for
	 * connection at the time of instantiation. Underlying data source used for
	 * non-poolable option - OracleDataSource.
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
