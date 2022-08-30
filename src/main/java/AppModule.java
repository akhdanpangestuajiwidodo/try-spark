import DataSource.DataSource;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import dao.TranasaksiDao;
import dao.TransaksiDaoImplementation;
import dao.UserDao;
import dao.UserDaoImplementation;
import org.sql2o.Sql2o;

public class AppModule extends AbstractModule {
    @Override
    protected void configure(){
        bind(UserDao.class).to(UserDaoImplementation.class);
        bind(TranasaksiDao.class).to(TransaksiDaoImplementation.class);
    }

    @Provides
    @Singleton
    Sql2o provideSql2o(){
        return new Sql2o(DataSource.getMainDataSource());
    }
}
