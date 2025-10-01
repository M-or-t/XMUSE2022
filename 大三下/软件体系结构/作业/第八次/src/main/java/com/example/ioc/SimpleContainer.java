package com.example.ioc;

public class SimpleContainer {
    private Database database;
    private UserService userService;

    public SimpleContainer(String databaseType) {
        if ("mysql".equalsIgnoreCase(databaseType)) {
            this.database = new MySQLDatabase();
        } else if ("mongo".equalsIgnoreCase(databaseType)) {
            this.database = new MongoDatabase();
        } else {
            throw new IllegalArgumentException("不支持的数据库类型: " + databaseType);
        }

        this.userService = new UserServiceImpl(database);
    }

    public UserService getUserService() {
        return userService;
    }
}