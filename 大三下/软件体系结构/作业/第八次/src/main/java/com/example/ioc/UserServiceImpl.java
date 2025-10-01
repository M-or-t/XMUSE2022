package com.example.ioc;

public class UserServiceImpl implements UserService {
    private final Database database;

    public UserServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public void createUser(User user) {
        database.save(user);
    }

    @Override
    public User getUser(String id) {
        return database.findById(id);
    }

    @Override
    public void updateUser(User user) {
        database.update(user);
    }

    @Override
    public void deleteUser(String id) {
        database.delete(id);
    }
}