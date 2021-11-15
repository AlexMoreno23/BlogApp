package by.morunov.dao;

import by.morunov.model.Post;
import by.morunov.model.Role;
import by.morunov.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Alex Morunov
 */
public interface PostDao {

    void save(Post post);

    List<Post> getAll();

    List<Post> findPostByAuthor(Long id);

    List<Post> getByTitle(String title);

    public List<Post> findByRole(Role role);
}
