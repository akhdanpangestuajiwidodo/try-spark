package DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {

    private static final HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

//    static {
//        config.setJdbcUrl( "jdbc:postgresql://localhost:5432/minioy" );
//        config.setUsername( "postgres" );
//        config.setPassword( "130301" );
//        config.addDataSourceProperty( "cachePrepStmts" , "true" );
//        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
//        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
//        ds = new HikariDataSource( config );
//    }
//
//    private DataSource.DataSource() {}

    public static HikariDataSource getMainDataSource(){
        config.setJdbcUrl( "jdbc:postgresql://localhost:5432/minioy" );
        config.setUsername( "postgres" );
        config.setPassword( "130301" );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource( config );
        return ds;
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
