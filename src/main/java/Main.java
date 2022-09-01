import static spark.Spark.port;

import controller.BalanceController;
import controller.PingController;
import controller.TransaksiController;
import controller.UserController;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
    public static void main(String[] args) {

        //bikin service untuk nambah saldo duit, dan ketika bikin user baru saldo nya 0 dulu
        //ketika transfer nilai balikanya itu saldo dari pengirim

        //bisa pakai parameter url untuk saldo contoh :saldo/:idUser
        //untuk saldo dibikin service baru dan suatu entitas yang beda
        //perlu ada history balance jg
        //kayaknya dibutuhkan table baru untuk balance/ saldo

        Injector injector = Guice.createInjector(new AppModule());
        UserController userController = injector.getInstance(UserController.class);
        TransaksiController transaksiController = injector.getInstance(TransaksiController.class);
        PingController pingController = injector.getInstance(PingController.class);
        BalanceController balanceController = injector.getInstance(BalanceController.class);
        port(4567);

        pingController.registerApi();
        userController.registerApi();
        transaksiController.registerApi();
        balanceController.registerApi();
    }
}
