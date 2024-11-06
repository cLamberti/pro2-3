package com.una.unadb4.services;

import com.una.unadb4.models.Camion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;

import java.util.List;

public class CamionService {

    // Nombre de la unidad de persistencia definida en persistence.xml
    private static final String PERSISTENCE_UNIT_NAME = "my_persistence";

    // Método para obtener todos los camiones
    public List<Camion> getAll() throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
        try {
            em.getTransaction().begin();
            List<Camion> camiones = em.createQuery("SELECT c FROM camiones c", Camion.class).getResultList();
            em.getTransaction().commit();
            return camiones;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Método para obtener un camión por su ID
    public Camion getById(int id) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
        try {
            return em.find(Camion.class, id);
        } finally {
            em.close();
        }
    }

    // Método para almacenar un nuevo camión en la base de datos
    @Transactional
    public void store(Camion camion) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(camion);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Método para actualizar un camión existente
    @Transactional
    public void update(Camion camion) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(camion);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Método para eliminar un camión por su ID
    @Transactional
    public void delete(int id) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
        try {
            em.getTransaction().begin();
            Camion camion = em.find(Camion.class, id);
            if (camion != null) {
                em.remove(camion);
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
