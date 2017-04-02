package ua.goit.java.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.java.models.hibernate.User;

public interface UserDao extends JpaRepository<User, Long>{
    User findByUsername(String username);
}
