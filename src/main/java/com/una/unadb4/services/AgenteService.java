package com.una.unadb4.services;

import com.una.unadb4.models.Agente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class AgenteService extends Service<Agente> {

    private static final String persistence = "my_persistence_unit";

    @Override
    public List<Agente> getAll() throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(persistence).createEntityManager();
        try {
            em.getTransaction().begin();
            List<Agente> agents = em.createQuery("SELECT a FROM agentes a").getResultList();
            em.close();
            return agents;
        } catch (Exception e) {
            em.close();
            throw e;
        }
    }

    @Override
    public Agente getById(int id) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(persistence).createEntityManager();
        try {
            em.getTransaction().begin();
            Agente agente = em.find(Agente.class, id);
            em.close();
            return agente;
        } catch (Exception e) {
            em.close();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void store(Agente agent) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(persistence).createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(agent);
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
            Agente agent = em.find(Agente.class, id);
            if (agent != null) {
                em.remove(agent);
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
    public void update(Agente agent) throws Exception {
        EntityManagerFactory emf =Persistence.createEntityManagerFactory(persistence);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Agente ag = em.find(Agente.class, agent.getId());
            if (ag != null) {
                ag.setId(agent.getId());
                ag.setName(agent.getName());
                ag.setLastname(agent.getLastname());
                ag.setAgentType(agent.getAgentType());
                ag.setPhoto(agent.getPhoto());
                ag.setFilename(agent.getFilename());
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
