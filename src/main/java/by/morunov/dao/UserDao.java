package by.morunov.dao;

import by.morunov.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * @author Alex Morunov
 */
public interface UserDao extends JpaRepository<User, Long> {
    User findByLogin(String login);


}
