package by.morunov.service;

import by.morunov.dao.UserDao;
import by.morunov.model.Role;
import by.morunov.model.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author Alex Morunov
 */
@Service
public class UserService implements UserDetailsService {


    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDao userDao, @Lazy PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userDao.findByLogin(login);
    }

    public boolean addUser(User user) {
        User userFromDb = userDao.findByLogin(user.getLogin());

        if (userFromDb != null) {
            return false;
        }

        user.setRoles(Collections.singleton(Role.PERSON));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return true;
    }

    public List<User> userList() {
        return userDao.findAll();
    }



}
