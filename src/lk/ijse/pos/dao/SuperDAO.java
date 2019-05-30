package lk.ijse.pos.dao;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public interface SuperDAO {

    public void setEntityManager(EntityManager em);



}
