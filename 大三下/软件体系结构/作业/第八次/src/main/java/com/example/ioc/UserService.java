package com.example.ioc;

public interface UserService {
    void createUser(User user);

    User getUser(String id);

    void updateUser(User user);

    void deleteUser(String id);
}