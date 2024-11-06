package com.una.unadb4.services;

import com.una.unadb4.models.Empresa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;

import java.util.List;

public class EmpresaService {

    // Nombre de la unidad de persistencia definida en persistence.xml
    private static final String PERSISTENCE_UNIT_NAME = "my_persistence";

    // Método para obtener todas las empresas
    public List<Empresa> getAll() throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
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

    // Método para obtener una empresa por su ID
    public Empresa getById(int id) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
        try {
            return em.find(Empresa.class, id);
        } finally {
            em.close();
        }
    }

    // Método para almacenar una nueva empresa en la base de datos
    @Transactional
    public void store(Empresa empresa) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
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

    // Método para actualizar una empresa existente
    @Transactional
    public void update(Empresa empresa) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
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

    // Método para eliminar una empresa por su ID
    @Transactional
    public void delete(int id) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
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
