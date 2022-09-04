import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dao.BalanceDao;
import dao.BalanceDaoImpl;
import datasource.DataSource;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import dao.TranasaksiDao;
import dao.TransaksiDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import helper.JsonTransformer;
import org.sql2o.Sql2o;

public class AppModule extends AbstractModule {

    @Override
    protected void configure(){
        bind(UserDao.class).to(UserDaoImpl.class);
        bind(TranasaksiDao.class).to(TransaksiDaoImpl.class);
        bind(BalanceDao.class).to(BalanceDaoImpl.class);
    }

    @Provides
    @Singleton
    Sql2o provideSql2o(){
        return new Sql2o(DataSource.getMainDataSource());
    }

    @Provides
    @Singleton
    ObjectMapper provideObjectMapper(){
        return new ObjectMapper();
    }

    @Provides
    @Singleton
    JsonTransformer provideJsonTransformer(){
        return new JsonTransformer();
    }
}
