package com.example.ioc;

public class Main {
    public static void main(String[] args) {
        // 创建使用MySQL的容器
        System.out.println("=== 使用 MySQL ===");
        SimpleContainer mysqlContainer = new SimpleContainer("mysql");
        UserService mysqlUserService = mysqlContainer.getUserService();

        // 测试MySQL实现
        User user1 = new User("1", "张三", "zhangsan@example.com");
        mysqlUserService.createUser(user1);
        mysqlUserService.getUser("1");
        mysqlUserService.updateUser(user1);
        mysqlUserService.deleteUser("1");

        System.out.println("\n=== 使用 MongoDB ===");
        // 创建使用MongoDB的容器
        SimpleContainer mongoContainer = new SimpleContainer("mongo");
        UserService mongoUserService = mongoContainer.getUserService();

        // 测试MongoDB实现
        User user2 = new User("2", "李四", "lisi@example.com");
        mongoUserService.createUser(user2);
        mongoUserService.getUser("2");
        mongoUserService.updateUser(user2);
        mongoUserService.deleteUser("2");
    }
}