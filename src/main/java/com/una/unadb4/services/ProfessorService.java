package com.una.unadb4.services;

import com.una.unadb4.models.Professor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.List;

public class ProfessorService extends Service<Professor> {
    private EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("my_persistence").createEntityManager();
    }
    @Override
    public List<Professor> getAll() throws Exception {
        EntityManager em= getEntityManager();
        try {
            em.getTransaction().begin();
            List<Professor> list =em.createQuery("select p from profesores p").getResultList();
            em.close();
            return list;
        }catch (Exception e) {
            em.close();
            throw e;
        }
    }

    @Override
    public Professor getById(int id) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Professor profesor = em.find(Professor.class, id);
            em.getTransaction().commit();
            return profesor;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void store(Professor pojo) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(pojo);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }

    }

    @Override
    public void delete(Integer id) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Professor profesor = em.find(Professor.class, id);
            if (profesor != null) {
                em.remove(profesor);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Professor pojo) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(pojo);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
