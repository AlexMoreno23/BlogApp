package by.morunov.service;

import by.morunov.dao.PostDaoImpl;
import by.morunov.model.Post;
import by.morunov.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alex Morunov
 */
@Service
public class PostService {

    private final PostDaoImpl postDaoImpl;

    public PostService(PostDaoImpl postDaoImpl) {
        this.postDaoImpl = postDaoImpl;
    }

    public void savePost(Post post) {
        postDaoImpl.save(post);
    }

    public Iterable<Post> getAll() {
        return postDaoImpl.getAll();
    }

    public List<Post> getByAuthor(Long id) {
        if (id == null) {
            return null;
        }
        return postDaoImpl.findPostByAuthor(id);
    }

    public List<Post> getByTitle(String title) {
        if (title == null) {
            return null;
        }
        return postDaoImpl.getByTitle(title);
    }


}
