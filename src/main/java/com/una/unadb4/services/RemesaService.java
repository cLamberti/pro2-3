package com.una.unadb4.services;

import com.una.unadb4.models.Remesa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;

import java.util.List;

public class RemesaService extends Service<Remesa> {

    private static final String persistence = "my_persistence_unit";

    @Override
    public List<Remesa> getAll() throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(persistence).createEntityManager();
        try {
            em.getTransaction().begin();
            List<Remesa> remesas = em.createQuery("SELECT r FROM remesas r", Remesa.class).getResultList();
            em.getTransaction().commit();
            return remesas;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Remesa getById(int id) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(persistence).createEntityManager();
        try {
            return em.find(Remesa.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public void store(Remesa remesa) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(persistence).createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(remesa);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Remesa remesa) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(persistence).createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(remesa);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(String id) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(persistence).createEntityManager();
        try {
            em.getTransaction().begin();
            Remesa remesa = em.find(Remesa.class, id);
            if (remesa != null) {
                em.remove(remesa);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
