package com.una.unadb4.services;

import com.una.unadb4.models.Remesa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;

import java.util.List;

public class RemesaService {

    // Nombre de la unidad de persistencia definida en persistence.xml
    private static final String PERSISTENCE_UNIT_NAME = "my_persistence";

    // Método para obtener todas las remesas
    public List<Remesa> getAll() throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
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

    // Método para obtener una remesa por su ID
    public Remesa getById(int id) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
        try {
            return em.find(Remesa.class, id);
        } finally {
            em.close();
        }
    }

    // Método para almacenar una nueva remesa en la base de datos
    @Transactional
    public void store(Remesa remesa) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
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

    // Método para actualizar una remesa existente
    @Transactional
    public void update(Remesa remesa) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
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

    // Método para eliminar una remesa por su ID
    @Transactional
    public void delete(int id) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
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
