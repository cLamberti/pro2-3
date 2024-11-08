package com.una.unadb4.services;

import com.una.unadb4.models.Agente;
import com.una.unadb4.models.Empresa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
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
            List<Empresa> empresas = em.createQuery("SELECT e FROM empresas e").getResultList();
            em.close();
            return empresas;
        } catch (Exception e) {
            em.close();
            throw e;
        }
    }

    @Override
    public Empresa getById(int id) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(persistence).createEntityManager();
        try {
            em.getTransaction().begin();
            Empresa empresa = em.find(Empresa.class, id);
            em.close();
            return empresa;
        } catch (Exception e) {
            em.close();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void store(Empresa empresa) throws Exception {
        EntityManager em = Persistence.createEntityManagerFactory(persistence).createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(empresa);
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
            Empresa empresa = em.find(Empresa.class, id);
            if (empresa != null) {
                em.remove(empresa);
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
    public void update(Empresa empresa) throws Exception {
        EntityManagerFactory emf =Persistence.createEntityManagerFactory(persistence);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Empresa emp = em.find(Empresa.class, empresa.getId());
            if (emp != null) {
                emp.setId(empresa.getId());
                emp.setCompanyName(empresa.getCompanyName());
                emp.setAddress(empresa.getAddress());
                emp.setContact(empresa.getContact());
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
