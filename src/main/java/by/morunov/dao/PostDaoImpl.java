package by.morunov.dao;

import by.morunov.model.Post;
import by.morunov.model.Role;
import by.morunov.util.JPAUtil;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Alex Morunov
 */
@Component
public class PostDaoImpl implements PostDao{

    @Override
    public void save(Post post) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(post);
        entityManager.flush();
        entityManager.getTransaction().commit();

    }

    @Override
    public List<Post> getByTitle(@Param("title") String title) {
        EntityManager em = JPAUtil.getEntityManager();
        String query = "SELECT a FROM Post a WHERE LOWER(a.title) LIKE LOWER ( CONCAT('%', : title, '%')) ";
        Query q = em.createQuery(query);
        q.setParameter("title", title);
        return q.getResultList();

    }

    @Override
    public List<Post> findByRole(Role role) {
        EntityManager em = JPAUtil.getEntityManager();
        String query = "SELECT a FROM Post a JOIN a.user u WHERE u.roles = :roles";
        Query q = em.createQuery(query);
        q.setParameter("roles", role);
        return q.getResultList();
    }

    @Override
    public List<Post> getAll() {
        EntityManager em = JPAUtil.getEntityManager();
        String query = "SELECT a FROM Post a";
        Query q = em.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<Post> findPostByAuthor(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        String query = "SELECT a FROM Post a JOIN a.user u WHERE u.id = :id";
        Query q = em.createQuery(query, Post.class);
        q.setParameter("id", id);
        return q.getResultList();
    }


}
