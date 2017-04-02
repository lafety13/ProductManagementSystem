package ua.goit.java.service.interfaces;

import ua.goit.java.models.hibernate.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
