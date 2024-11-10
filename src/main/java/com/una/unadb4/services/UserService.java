package com.una.unadb4.services;

import com.una.unadb4.models.Remesa;
import com.una.unadb4.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.List;

public class UserService extends Service<User>{

    private static final String persistence = "my_persistence_unit";

    @Override
    public List<User> getAll() throws Exception {
        return List.of();
    }

    public User checkCredentials(String username, String password) {
        EntityManager em = Persistence.createEntityManagerFactory(persistence).createEntityManager();
        User user = null;
        try {
            em.getTransaction().begin();
            user = em.createQuery("SELECT r FROM users r WHERE r.userName = :username AND r.password = :password", User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
        return user;
    }


    @Override
    public User getById(int id) throws Exception {
        return null;
    }

    @Override
    public void store(User pojo) throws Exception {

    }

    @Override
    public void delete(String id) throws Exception {

    }

    @Override
    public void update(User pojo) throws Exception {

    }
}
