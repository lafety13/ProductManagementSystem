package ua.goit.java.models;

import ua.goit.java.models.hibernate.User;

public class UserBuilder {
    private User user = new User();

    public UserBuilder id(Long id) {
        user.setId(id);
        return this;
    }

    public UserBuilder username(String username) {
        user.setUsername(username);
        return this;
    }

    public UserBuilder password(String password) {
        user.setPassword(password);
        return this;
    }

    public UserBuilder confirmPassword(String confirmPassword) {
        user.setConfirmPassword(confirmPassword);
        return this;
    }
    public User buidl() {
        return user;
    }

}
