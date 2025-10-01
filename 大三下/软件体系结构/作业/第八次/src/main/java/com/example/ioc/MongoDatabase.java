package com.example.ioc;

public class MongoDatabase implements Database {
    @Override
    public void save(User user) {
        System.out.println("MongoDB: 保存用户 - " + user);
    }

    @Override
    public User findById(String id) {
        System.out.println("MongoDB: 查找用户 ID - " + id);
        return new User(id, "Mongo用户", "mongo@example.com");
    }

    @Override
    public void update(User user) {
        System.out.println("MongoDB: 更新用户 - " + user);
    }

    @Override
    public void delete(String id) {
        System.out.println("MongoDB: 删除用户 ID - " + id);
    }
} 