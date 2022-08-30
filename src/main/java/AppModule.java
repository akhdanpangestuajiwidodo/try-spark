import DataSource.DataSource;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import dao.TranasaksiDao;
import dao.TransaksiDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import org.sql2o.Sql2o;

public class AppModule extends AbstractModule {
    @Override
    protected void configure(){
        bind(UserDao.class).to(UserDaoImpl.class);
        bind(TranasaksiDao.class).to(TransaksiDaoImpl.class);
    }

    @Provides
    @Singleton
    Sql2o provideSql2o(){
        return new Sql2o(DataSource.getMainDataSource());
    }
}
