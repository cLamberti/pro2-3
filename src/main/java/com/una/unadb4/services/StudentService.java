package com.una.unadb4.services;

import com.una.unadb4.models.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.List;

public class StudentService extends Service<Student> {
    @Override
    public List<Student> getAll() throws Exception {
        EntityManager em= Persistence.
                createEntityManagerFactory("my_persistence_unit")
                .createEntityManager();
        try{
            em.getTransaction().begin();
            List data=em.createQuery("select e from estudiantes e").getResultList();
            em.close();
            return data;
        }catch (Exception e){
            em.close();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Student getById(int id) throws Exception {
        EntityManager em=Persistence.createEntityManagerFactory("my_persistence_unit")
                .createEntityManager();
        try{
            em.getTransaction().begin();
            Student st= em.find(Student.class, id);
            em.close();
            return st;
        }catch (Exception e){
            em.close();
            throw e;
        }
    }

    @Override
    public void store(Student pojo) throws Exception {

    }

    @Override
    public void delete(Integer id) throws Exception {

    }

    @Override
    public void update(Student pojo) throws Exception {
        EntityManager em=Persistence.createEntityManagerFactory("my_persistence_unit").createEntityManager();
        try{
            em.getTransaction().begin();
            Student st= em.find(Student.class, pojo.getId());
            st.setName(pojo.getName());
            st.setLastName(pojo.getLastName());
            st.setEmail(pojo.getEmail());
            st.setPhone(pojo.getPhone());
            em.getTransaction().commit();
            em.close();
        }catch (Exception e){
            em.close();
            throw e;
        }
    }
}
