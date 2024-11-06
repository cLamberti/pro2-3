package com.una.unadb4.services;

import com.una.unadb4.models.Agente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;

import java.util.List;

public class AgenteService {

    // Nombre de la unidad de persistencia definida en persistence.xml
    private static final String PERSISTENCE_UNIT_NAME = "my_persistence";

    // Método para obtener todos los agentes
    public List<Agente> getAll() throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
        try {
            em.getTransaction().begin();
            List<Agente> agents = em.createQuery("SELECT a FROM agentes a", Agente.class).getResultList();
            em.getTransaction().commit();
            return agents;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Método para obtener un agente por su ID
    public Agente getById(int id) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
        try {
            return em.find(Agente.class, id);
        } finally {
            em.close();
        }
    }

    // Método para almacenar un nuevo agente en la base de datos
    @Transactional
    public void store(Agente agent) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(agent);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Método para actualizar un agente existente
    @Transactional
    public void update(Agente agent) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(agent);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Método para eliminar un agente por su ID
    @Transactional
    public void delete(int id) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
        try {
            em.getTransaction().begin();
            Agente agent = em.find(Agente.class, id);
            if (agent != null) {
                em.remove(agent);
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
