package DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSource {

    private static final HikariConfig config = new HikariConfig();

    public static HikariDataSource getMainDataSource(){
        config.setJdbcUrl( "jdbc:postgresql://localhost:5432/minioy" );
        config.setUsername( "postgres" );
        config.setPassword( "130301" );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        return new HikariDataSource(config);
    }
}
