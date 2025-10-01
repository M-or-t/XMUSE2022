package com.example.ioc;

public class MySQLDatabase implements Database {
    @Override
    public void save(User user) {
        System.out.println("MySQL: 保存用户 - " + user);
    }

    @Override
    public User findById(String id) {
        System.out.println("MySQL: 查找用户 ID - " + id);
        return new User(id, "MySQL用户", "mysql@example.com");
    }

    @Override
    public void update(User user) {
        System.out.println("MySQL: 更新用户 - " + user);
    }

    @Override
    public void delete(String id) {
        System.out.println("MySQL: 删除用户 ID - " + id);
    }
}