package com.tencent.cubeli.java.inject;

/**
 * Created by liyong on 07/08/2018.
 */
class DBEngine {
}

class CacheEngine {

}

class UserDB {

    private DBEngine db;
    private CacheEngine cache;

    public UserDB(DBEngine db, CacheEngine cache) {
        this.db = db;
        this.cache = cache;
    }

}

class ItemDB {

    private DBEngine db;
    private CacheEngine cache;

    public ItemDB(DBEngine db, CacheEngine cache) {
        this.db = db;
        this.cache = cache;
    }

}

class UserService {

    private UserDB db;

    public UserService(UserDB db) {
        this.db = db;
    }

}

class ItemService {

    private ItemDB db;

    public ItemService(ItemDB db) {
        this.db = db;
    }

}

class App {

    private UserService user;
    private ItemService item;

    public App(UserService user, ItemService item) {
        this.user = user;
        this.item = item;
    }
}

public class NoInject {

    public static void main(String[] args) {
        DBEngine db = new DBEngine();
        CacheEngine cache = new CacheEngine();
        UserDB userDB = new UserDB(db, cache);
        ItemDB itemDB = new ItemDB(db, cache);
        UserService userServ = new UserService(userDB);
        ItemService itemServ = new ItemService(itemDB);
        App app = new App(userServ, itemServ);
    }

}
