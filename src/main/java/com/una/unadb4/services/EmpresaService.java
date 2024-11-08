package com.una.unadb4.services;

import com.una.unadb4.models.Empresa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;

import java.util.List;

public class EmpresaService extends Service<Empresa> {

    private static final String persistence = "my_persistence_unit";

    @Override
    public List<Empresa> getAll() throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(persistence).createEntityManager();
        try {
            em.getTransaction().begin();
            List<Empresa> empresas = em.createQuery("SELECT e FROM empresas e", Empresa.class).getResultList();
            em.getTransaction().commit();
            return empresas;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Empresa getById(int id) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(persistence).createEntityManager();
        try {
            return em.find(Empresa.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public void store(Empresa empresa) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(persistence).createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(empresa);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Empresa empresa) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(persistence).createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(empresa);
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
            Empresa empresa = em.find(Empresa.class, id);
            if (empresa != null) {
                em.remove(empresa);
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
