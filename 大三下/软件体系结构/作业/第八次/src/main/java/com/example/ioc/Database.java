package com.example.ioc;

public interface Database {
    void save(User user);

    User findById(String id);

    void update(User user);

    void delete(String id);
}