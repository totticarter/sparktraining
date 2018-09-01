package com.tencent.cubeli.java.inject;


import com.google.inject.*;

/**
 * Created by liyong on 07/08/2018.
 */
class DBEngine2 {
}

class CacheEngine2 {
}

@Singleton
class UserDB2 {

    @Inject
    private DBEngine2 db;
    @Inject
    private CacheEngine2 cache;

}

@Singleton
class ItemDB2 {
    @Inject
    private DBEngine2 db;
    @Inject
    private CacheEngine2 cache;

}

@Singleton
class UserService2 {

    @Inject
    private UserDB2 db;

}

@Singleton
class ItemService2 {

    @Inject
    private ItemDB2 db;

}

@Singleton
class App2 {

    @Inject
    private UserService2 user;
    @Inject
    private ItemService2 item;

}

class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        DBEngine2 db = new DBEngine2();
        CacheEngine2 cache = new CacheEngine2();
        bind(DBEngine2.class).toInstance(db);
        bind(CacheEngine2.class).toInstance(cache);
    }

}

public class UseInject {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppModule());
        App2 app = injector.getInstance(App2.class);
    }

}