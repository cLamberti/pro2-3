package com.una.unadb4.services;

import com.una.unadb4.models.Agente;
import com.una.unadb4.models.Remesa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
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
            List<Remesa> remesas = em.createQuery("SELECT r FROM remesas r").getResultList();
            em.close();
            return remesas;
        } catch (Exception e) {
            em.close();
            throw e;
        }
    }

    public List<Remesa> getByUser(String userName) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(persistence).createEntityManager();
        try {
            em.getTransaction().begin();
            List<Remesa> remesas = em.createQuery("SELECT r FROM remesas r WHERE user.userName = "+userName).getResultList();
            em.close();
            return remesas;
        } catch (Exception e) {
            em.close();
            throw e;
        }
    }

    @Override
    public Remesa getById(int id) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(persistence).createEntityManager();
        try {
            em.getTransaction().begin();
            Remesa remesa = em.find(Remesa.class, id);
            em.close();
            return remesa;
        } catch (Exception e) {
            em.close();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void store(Remesa remesa) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(persistence).createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(remesa);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            em.getTransaction().rollback();
            em.close();
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void delete(String id) throws Exception {
        EntityManagerFactory emf =Persistence.createEntityManagerFactory(persistence);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Remesa remesa = em.find(Remesa.class, id);
            if (remesa != null) {
                em.remove(remesa);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Remesa remesa) throws Exception {
        EntityManagerFactory emf =Persistence.createEntityManagerFactory(persistence);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Remesa re = em.find(Remesa.class, remesa.getId());
            if (re != null) {
                re.setId(remesa.getId());
                re.setDate(remesa.getDate());
                re.setTime(remesa.getTime());
                re.setStatus(remesa.getStatus());
                re.setAgente(remesa.getAgente());
                re.setCamion(remesa.getCamion());
                re.setEmpresa(remesa.getEmpresa());
                re.setUser(remesa.getUser());
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
